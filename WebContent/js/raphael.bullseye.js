(function() {
	var RAD_CONST = Math.PI / 180;
	
	/**
	 * Bullseye chart constructor
	 * @param {Object}   opts                 Chart options
	 * @param {Integer}  [opts.startDegree]   Optional. Defines where to start drawing the first slice
	 * @param {String[]} opts.labels          Array of slice labels. Defines how to carve up the chart.
	 * @param {Object[]} opts.rings           Array where each cell can be a config object or string. A ring config supports following options:
	 * 											- label		ring label (e.g. year)
	 * 											- fill		ring fill in hex
	 * 											- [span]	Optional. Defaults to 1
	 * @param {String}   [opts.bullseyeFill]  Color of the bullseye in hex
	 * @param {Boolean}  [opts.allowDrag]     Defaults to false. True to allow dragging points.
	 * @param {Function} [opts.onPointClick]  Callback when point is clicked on. First param is the clicked point.
	 * @param {Function} [opts.onSliceClick]  Callback when the slice label is clicked on. First param is the opts.sliceLabels' index
	 * @param {Function} [opts.onMouseOver]   Callback when mouse is over a point. First param is the point itself.
	 * @param {Object[]} {opts.legend}		  Array of objects. Each object describes the variable and possible values. If provided, displays legend below the chart.
	 */
	Raphael.fn.bullseye = function(opts) {
	    function Bullseye() {
	        this.init.apply(this, arguments);
	    }
	
	    var B = Bullseye.prototype;
	
	    B.init = function(canvas, opts) {
	        this.width        = canvas.canvas.clientWidth  || canvas.width,
	        this.height       = canvas.canvas.clientHeight || canvas.height;
	        this.startDegree  = opts.startDegree;
	        this.sliceLabels  = opts.slices  || [];
	        this.ringConfigs  = opts.rings   || [];
	        this.bullseyeFill = opts.bullseyeFill || '#c0c0c0';
	        this.allowDrag    = opts.allowDrag;
	        this.onPointClick = opts.onPointClick || function (){};
	        this.onSliceClick = opts.onSliceClick || this.onSliceClick;
	        this.onMouseOver  = opts.onMouseOver  || function (){};
	        this.legend       = opts.legend;
	
	        this.centerX 			= this.width / 2;
	        this.centerY 			= this.height / 2;
	        this.numRings 			= this.ringConfigs.length;
	        this.maxRadius 			= this.width * .4;
	        this.sliceLabelRadius 	= this.maxRadius + 35;
	        this.bullseyeRadius     = 30;
		
	        this.canvas = canvas;
	        this.points = [];
	        this.ringRadii = [];
	        this.sliceAreas = [];
	        
	        // calculate ringSize
	    	var numSpans = 0;
	        for (var i = 0; i < this.ringConfigs.length; i++) {
				if (typeof this.ringConfigs[i] == 'string' || typeof this.ringConfigs[i] == 'number') {
					this.ringConfigs[i] = {
						'label' : this.ringConfigs[i]
					};
				}
	        	var ring_span = this.ringConfigs[i].span ? this.ringConfigs[i].span : 1;
	        	numSpans += ring_span;
	        }
	        this.ringSize = this.width / numSpans * 0.35;
	        
	        // draw chart components in specific order
	        this.drawRings(canvas);
	        this.drawSlices(canvas);
			this.drawRibbon(canvas);
	        this.drawBullseye(canvas);
	        
	        // draw legend
	        this.drawLegend(canvas);
	
	        // in order to determine the first ring
	        this.ringRadii.push(this.bullseyeRadius);
	        // make it ascending order
	        this.ringRadii.reverse();
	    }
	
	    /**
	     * This function gets invoked when clicking on a slice label.
	     * The default behavior is to zoom into the clicked slice and hide all other points.
	     * 
	     * @param sliceIdx Index of the clicked slice
	     */
	    B.onSliceClick = function(sliceIdx) {
			if (!this.isZoom) {
	    	  	// this setViewBox is needed to make the zoom in IE work 
	    	  	// or else it doesnt zoom out correctly
	    	  	this.canvas.setViewBox(1, 1, this.width, this.height);
	    	  	
				var zoomHeight  		= this.height * 0.6,
					zoomWidth   		= this.width * 0.6,
					centerX     		= this.centerX,
					centerY     		= this.centerY,
					maxRadius   		= this.maxRadius,
					numSlices    		= this.sliceLabels.length, 
					sliceAngle   		= 360 / numSlices,
		            startDegree  		= this.startDegree != null ? this.startDegree : -sliceAngle/2
		            mySliceAngle 		= startDegree + sliceAngle * sliceIdx,
		            isLeftHemisphere 	= mySliceAngle >= 90 && mySliceAngle < 270,
		        	isNorthHemisphere 	= mySliceAngle > -30 && mySliceAngle < 180; 
		        
		       	// mask the slices which arent in focus
		        this.zoomSliceMask = this.drawSliceArea(this.canvas, centerX, centerY, maxRadius + 999, mySliceAngle + sliceAngle, mySliceAngle + 360, {'fill': '#fff', 'stroke-width': 0});
		        	
	            var y_coords = [
	            	centerY,
	            	centerY - Math.sin(mySliceAngle * RAD_CONST) * maxRadius,
	            	centerY - Math.sin((mySliceAngle + sliceAngle) * RAD_CONST) * maxRadius
	            ];
	
	            //
	            // find the min y coord
	            var min_y = 99999;
	            for (var i = 0; i < y_coords.length; i++) {
	            	var y = y_coords[i];
	            	if (y < min_y)
	            		min_y = y;
	            }
	                        
				this.zoomX = isLeftHemisphere ? centerX - maxRadius - 80 : centerX - 50;
				this.zoomY = isNorthHemisphere ? min_y - 100 : min_y - 50;
				this.canvas.setViewBox(this.zoomX, this.zoomY, zoomWidth, zoomHeight);
	    	}
	    	else {
	    		// reset view box
	    		this.canvas.setViewBox(0, 0, this.width, this.height);
	    	}
			
			var points = this.getPoints();
			
			this.isZoom = !this.isZoom;
			
			// check if zoom mode
			if (!this.isZoom && this.zoomSliceMask) {
				// remove slice mask
				this.zoomSliceMask.remove();
				this.zoomSliceMask = null;
				
				this.bullseyeCenter.show();
				this.legend.show();
				this.ribbon.show();
	
			}
			else {
				this.bullseyeCenter.hide();
				this.legend.hide();
				this.ribbon.hide();
			}
	
			for (var i = 0; i < points.length; i++) {
				var point = points[i];
				
				if (this.isZoom) {
					if (point.getSlice() == sliceIdx) {
						point.toFront();
						point.label.toFront();
					}
					else {
						// hide points which are not in focus
						point.label.hide();
						point.hide();
					}
				} else {
					point.label.show();
					point.show();
				}
			}		
	    }
	    
	    B.onMouseOverSlice = function(area) {
	        area.attr({
	            fill: '#0000ff',
	            opacity: 0.1
	        });
	    }
	
	    B.onMouseOutSlice = function(area) {
	        area.attr({
	            fill: '#fff',
	            opacity: 0
	        });
	    }
	
	    B.drawLegend = function(canvas) {
	    	if (!this.legend)
	    		return;
	    		
			var  width = this.legend.length * 110,
				height = this.legend[0].items.length * 24,
					 x = this.centerX - width/2,
					 y = this.centerY + this.maxRadius + 35;
					 
			this.legend = canvas.legend(this.legend, x, y, width, height);
	    }
	    
	    B.drawRings = function(canvas) {
	        var numRings = this.numRings,
	            maxRadius = this.maxRadius,
	            ringConfigs = this.ringConfigs,
	            ringSize = this.ringSize,
	            x = this.centerX,
	            y = this.centerY;
	
	        for (var i = numRings - 1, radius = maxRadius; i >= 0 ; i--) {
	        	var ring_span = this.ringConfigs[i].span ? this.ringConfigs[i].span : 1;
	        	
	            // shadow/separator
	            canvas.circle(x, y, radius + 2)
	            .attr({
	                stroke: '#000000',
	                'stroke-width': 2,
	                'stroke-opacity': 0.2
	            });
	                    
	            // ring
	            canvas.circle(x, y, radius)
	            .attr({
	                fill: ringConfigs[i].fill || '#ffffff',
	                stroke: '#ffffff',
	                'stroke-width': 4
	            });		
	
	            this.ringRadii.push(radius);
	            
	            radius -= ringSize*ring_span;
	        }
	
	    }
	
	
	    B.drawBullseye = function(canvas) {
	        // draw the shadowed circle for a bullseye
	        this.bullseyeCenter = canvas.circle_sh(this.centerX, this.centerY, this.bullseyeRadius, this.bullseyeFill);
	    }
	
	    B.drawRibbon = function(canvas) {
	    	this.ribbon = canvas.ribbon(this.centerX, this.centerY, this.maxRadius, this.ringSize, this.ringConfigs);
	    }
	
	    B.drawSliceArea = function(canvas, cx, cy, r, startAngle, endAngle, params) {
	        var x1 = cx + r * Math.cos(-startAngle * RAD_CONST),
	            x2 = cx + r * Math.cos(-endAngle * RAD_CONST),
	            y1 = cy + r * Math.sin(-startAngle * RAD_CONST),
	            y2 = cy + r * Math.sin(-endAngle * RAD_CONST);
	
	        return canvas.path(["M", cx, cy, "L", x1, y1, "A", r, r, 0, + (endAngle - startAngle > 180), 0, x2, y2, "z"]).attr(params);
	    }
	
	    B.drawLine = function(canvas, cx, cy, r, angle, params) {
			var x = cx + r * Math.cos(-angle * RAD_CONST),
	            y = cy + r * Math.sin(-angle * RAD_CONST);
	        var line = canvas.path(["M", cx, cy, "L", x, y]).attr(params);
	        line.endX = x;
	        line.endY = y;
	        return line;
	    }
	
	    B.drawSliceLabel = function(canvas, sliceIdx, labelDeg, line1, line2) {
	        var self = this;
	        var sliceLabelRadius = this.sliceLabelRadius,
	            centerX = this.centerX,
	            centerY = this.centerY,
	            sliceLabels = this.sliceLabels,
	            onSliceClick = this.onSliceClick;
	
			// calc label coords
			var x = Math.cos(labelDeg * RAD_CONST) * sliceLabelRadius + centerX;
			var y = centerY - Math.sin(labelDeg * RAD_CONST) * sliceLabelRadius;
			
			// calc label rotation
			var lx = line1.endX - line2.endX;
			var ly = line1.endY - line2.endY;
			var rotate = Math.atan2(ly, lx) / RAD_CONST;
			if (labelDeg > 180 && labelDeg < 360) rotate = 180 + rotate;
	
			// require closure for click event
			(function(sliceIdx) {
				canvas.text(x, y, sliceLabels[sliceIdx]).transform("r" + rotate)
				.attr({
					'font-size': 14,
					'stroke-width': 2,
					stroke: '#fff',
					'stroke-opacity': 0.05		// semi transparent stroke, so mouse has more space to hover over
				})
				.click(function() { onSliceClick.call(self, sliceIdx); })
				.hover(function() {
					this.node.style.cursor = "pointer";
					this.attr({
						fill: '#437dd3'
					});
	                self.onMouseOverSlice(self.sliceAreas[sliceIdx]);
				}, 
				function() {
					this.attr({
						fill: '#000000'						
					});
	                self.onMouseOutSlice(self.sliceAreas[sliceIdx]);
				});
			})(sliceIdx);
		}
	    
	    // draw slice separators and slice labels
	    B.drawSlices = function(canvas) {
	        var self = this;
	        var centerX = this.centerX,
	            centerY = this.centerY,
	            maxRadius = this.maxRadius,
	            sliceLabels = this.sliceLabels,
	            numSlices  = sliceLabels.length,
	            sliceAngle = 360 / numSlices,
	            startDegree = this.startDegree != null ? this.startDegree : -sliceAngle/2,
	            line_deg, 
	            line;
	
	        if (this.startDegree > 0) {
	            // make the start degree negative, preserves correct label rotation
	            // eg 330 -> -30
	            this.startDegree = this.startDegree % 360 - 360;
	        }
	
	        for (var i = -1, line_deg = startDegree; i < numSlices; i++, line_deg += sliceAngle) {
	            var prev_line = line;
	            
	            // draw separator shadow
	            this.drawLine(canvas, centerX, centerY, maxRadius + 2, line_deg, {stroke: '#000000', 'stroke-width': 5, 'stroke-opacity': 0.15});
	            // draw slice separator
	            line = this.drawLine(canvas, centerX, centerY, maxRadius + 2, line_deg, {stroke: '#ffffff', 'stroke-width': 4});
	            
	            if (prev_line) {  
	                // draw slice label between 2 line separators
	                this.drawSliceLabel(canvas, i, line_deg - sliceAngle / 2, prev_line, line);
	
	                // slice highlight area
	                var area = this.drawSliceArea(canvas, centerX, centerY, maxRadius + 2, line_deg - sliceAngle, line_deg, {'fill': '#fff', 'opacity': 0});
	
	                this.sliceAreas.push(area);
	            }
	        }
	    }
	
		B.removePoint = function(myPoint) {
	        var points = this.points;
			var removeIndex;
			for (var i = 0; i < points.length; i++) {
				if (myPoint.id == points[i].id) {
					removeIndex = i;
					break;
				}
			}
	
			if (removeIndex != null)
				points.splice(removeIndex, 1);
			
			myPoint.label.remove();
			myPoint.remove();
		}
	
	     
	    B.getPoints = function() {
	        return this.points;
	    }
	
	    /**
	     * Adds new point to the chart
	     * @param {Object}  opts             Options for the new point
	     * @param {Decimal} opts.angle       Positive angle of the point from the origin (in radians)          
	     * @param {Integer} [opts.ring]      The ring index (-1 for origin, 0 for first ring)
	     * @param {Decimal} opts.distance    If ring is provided, % distance from start of ring boundary to the end else % distance from origin to the last ring  
	     * @param {String}  [opts.pointFill] Hex color of point
	     * @param {Integer} [opts.pointSize] Radius in pixels
	     * @param {String}  [opts.label]     Optional label
	     */
	    B.addPoint = function(opts) {
	        var self = this;
	        var canvas = this.canvas,
	            maxRadius = this.maxRadius,
	            centerX = this.centerX,
	            centerY = this.centerY,
	            onPointClick = this.onPointClick,
	            onMouseOver = this.onMouseOver,
	            allowDrag = this.allowDrag,
	            bullseyeRadius = this.bullseyeRadius;
	
	        var angle    = opts.angle || 0,
	            distance = opts.distance || 0,
	            ring     = opts.ring;
	
	        var radius;
	        if (ring == null) {
	            radius = distance * maxRadius
	        } else {
	            var bounds = this.getBounds(ring);
	            var ringSize = bounds[1] - bounds[0];
	            var d = distance * ringSize;
	            radius = d + bounds[0];
	        }
	        
	        var pointX = centerX + radius * Math.cos(angle),
	            pointY = centerY - radius * Math.sin(angle);	
	        
	        var pointFill = opts.pointFill || '#00ff00',
	            pointSize = opts.pointSize || 5;
	        
	        var point = canvas.circle(pointX, pointY, pointSize)
	        .attr({
	            fill: pointFill,
	            stroke: 0,
	            'stroke-width': 0
	        })
	        .click(function() { onPointClick(this) })
	        .hover(function() {
	            // introduce a small delay (mitigates IE's mouse flicker)
	            setTimeout(function() {
	                onMouseOver(point);
	            }, 50);
	            
	            this.attr({
	                stroke: '#FFFF00',
	                'stroke-width': 2,
	                'stroke-opacity': .5
	            });
	            this.node.style.cursor = "pointer";
	        },
	        function() {
	            this.attr({
	                stroke: 0,
	                'stroke-width': 0
	            });
	        });
	        
	        if (allowDrag) {
	            point.drag(
	                function (dx, dy) {	// on move
	                    this.update(dx - (this.dx || 0), dy - (this.dy || 0));
	                    this.dx = dx;
	                    this.dy = dy;
	                }, 
	                function(dx, dy) {	// start drag
	                    this.dx = this.dy = 0;
	                }, 
	                function() {	// end drag
	                    var X = this.attr("cx") - centerX,
	                        Y = centerY - this.attr("cy");
	                    
	                    // polar coordinate system: (distance, degrees)
	                    this.radius = Math.sqrt(Math.pow(X, 2) + Math.pow(Y, 2));
	                    // calculate relative distance from origin
	                    this.distance = this.radius / maxRadius;
	                    
	                    // 
	                    // Calculate relative distance from ring boundary 
	                    var ring = this.getRing();
	                    
	                    var bounds = self.getBounds(ring),
	                        low_bound = bounds[0],
	                        upper_bound = bounds[1];
	                    
	                    if (upper_bound == -1)
	                        this.ringDistance = -1;  // we're out of bounds, ie not in a ring
	                    else
	                        this.ringDistance = (this.radius - low_bound) / (upper_bound - low_bound);
	
	                    this.angle = Math.atan2(Y, X);
	                   
	                    if (this.angle < 0)
	                        this.angle = 2*Math.PI + this.angle; 
	                }
	            );
	        }
	        
	        
	        this.points.push(point);
	        
	        point.update = function (x, y) {
	            // update point coord
	            var X = this.attr("cx") + x,
	                Y = this.attr("cy") + y;
	            this.attr({cx: X, cy: Y});
	            
	            // update label Y-position
	            this.label.attr({y: this.label.attrs.y + y});
	            // update label X-position
	            this.setLabel();
	        }
	        
	        point.getSlice = function() {
	            if (this.radius < bullseyeRadius)
	                return null;
	            return self.getSlice(this.angle);
	        }
	        
	        point.getRing = function() {
	            return self.getRing(this.radius);
	        }
	        
	        point.setLabel = function(str) {
	        	if (!this.label) {
	        		this.label = canvas.text(-9999, this.attrs.cy, str);
	        	}
	        	else if (str !== undefined) {
	                // update label text
	                this.label.attr({
	                    text: str
	                });
	            }
	            
	            // update label X-positon
	            var pw = this.getBBox().width;
	            var px = this.attrs.cx;
	            var lw = this.label.getBBox().width;			
	            var offset = pw + lw / 2;
	            
	            // if point is left of the Y-axis, place label to the left of the point
	            if (px + 10 < centerX) 
	                offset = offset * -1;
	
	            this.label.attr({
	                x: px + offset
	            });
	        }
	        
	        point.angle = angle;
	        point.radius = radius;
	        point.distance = radius / maxRadius;
	        point.ringDistance = distance;
	        
	        point.setLabel(opts.label || 'Point ' + point.id);
	        
	        return point;
	    }
	
	    /**
	     * Returns upper and lower radii (bounds) for the specified ring index
	     * @param ring Ring index
	     * @return Array
	     */
		B.getBounds = function(ring) {
	        var ringRadii = this.ringRadii;
	
			// defaults when inside bullseye
			var low_bound = 0,
	        	upper_bound = ringRadii[0];
	        
	        if (ring == null) {	// out of bounds
	        	low_bound = ringRadii[ringRadii.length - 1];
	        	upper_bound = -1;
	        } else if (ring >= 0) {	// inside a ring
	        	low_bound = ringRadii[ring];
	        	upper_bound = ringRadii[ring + 1];
	        }
	        return [low_bound, upper_bound];
		}
		
		B.getSlice = function(radians) {
	        var sliceLabels = this.sliceLabels,
	            degrees     = radians / RAD_CONST,
	            sliceDeg    = 360 / sliceLabels.length,
	            startDeg    = -1 * sliceDeg / 2,	// eg -30
	            posStartDeg = 360 + startDeg;	// -30 becomes 330
	
			if (degrees < 0) 
				degrees = 180 + (180 + degrees);	// -90 becomes 270 
			
			for (var d = startDeg, i = 0; d < posStartDeg; d += sliceDeg, i++)
				// 1st condition: if degrees > posStartDeg, then we're in the first slice. dont have to test upper bound
				// 2nd condition: test all slices one by one against the degree param
				if (degrees >= posStartDeg || degrees >= d && degrees < d + sliceDeg) 				
					return i; 
		}
		
		B.getRing = function(radius) {
	        var ringRadii = this.ringRadii;
	
			// there are no rings beyond max, just white space
			var max = ringRadii[ringRadii.length - 1];
			for (var i = ringRadii.length - 1; i >= 0; i--) 
				if (max > radius && radius > ringRadii[i]) 
					return i;
					
			if (ringRadii[0] > radius) 
				return -1;
		}
		
	    var b = new Bullseye(this, opts);
	
	    return b;
	
	};
	
	/**
	 * Simplifies hiding grouped components. This function is intended to be extended.
	 */
	HideableElement = function() {
		this.components = [];
		
		this.hide = function() {
			this.toggle(false);
		}
		
		this.show = function() {
			this.toggle(true);
		}
		
		this.toggle = function(show) {		
			for (var i = 0; i < this.components.length; i++) {
				if (show)
					this.components[i].show();
				else
					this.components[i].hide();
			}
		}
	}
	
	/**
	 * Draws a ribbon at the specified (x, y) coords for the specified size (radius).
	 * Typically X,Y are a center of the circle and the ribbon extends south to the radius.
	 * The ribbon holds the ring labels.
	 * 
	 * @param {Integer} x				X coord where to start drawing the ribbon
	 * @param {Integer} y				Y coord where to start drawing the ribbon
	 * @param {Integer} radius			Ribbon size
	 * @param {Integer} ringSize		Determines label placement
	 * @param {Object[]} ringConfigs	See bullseye constructor. Contains info to carve up ribbon i.e. label, ring span...
	 * @return new ribbon object
	 */
	Raphael.fn.ribbon = function(x, y, radius, ringSize, ringConfigs) {
		function drawRibbon(canvas, cx, cy, r, startAngle, endAngle, params) {
	        var x1 = cx + r * Math.cos(-startAngle * RAD_CONST),
	            x2 = cx + r * Math.cos(-endAngle * RAD_CONST),
	            y1 = cy + r * Math.sin(-startAngle * RAD_CONST),
	            y2 = cy + r * Math.sin(-endAngle * RAD_CONST);
	        
	        return canvas.path(["M", x1, cy, "L", x1, y1, "A", r, r, 0, +(endAngle - startAngle > 180), 0, x2, y2,  "L", x2, cy, "z"]).attr(params);
		}
		
		function Ribbon() {
			this.init.apply(this, arguments);
		}
		
		Ribbon.prototype = new HideableElement();
		
		Ribbon.prototype.init = function(canvas, x, y, radius, ringSize, ringConfigs) {
			this.components = [];
			var idx = 0;
			
			this.components[idx++] = drawRibbon(canvas, x, y, radius + 5, 265, 275, {fill: '45-#808080-#000000', 'fill-opacity': 0.4, 'stroke-width': 2, stroke: '#ffffff'});
	
	        //
	        // draw the ring labels on top of the ribbon
	        for (var i = ringConfigs.length - 1, r = radius; i >= 0 ; i--) {
	        	var ring_span = ringConfigs[i].span ? ringConfigs[i].span : 1,
	        		label     = (""+ringConfigs[i].label).replace(/ /g, '\n'),
	        		labelPosY = y + r - (ringSize/2.0 * ring_span);
	        	
	            this.components[idx++] = canvas.text(x, labelPosY, label)
		            .attr({
		                fill: '#ffffff',
		                'font-weight': 'bold',
		                'font-size': '8pt'
		            });
	            
	            r -= ringSize * ring_span;	
	        }
	
		
		}
		
		return new Ribbon(this, x, y, radius, ringSize, ringConfigs);
	}
	
	/**
	 * Draws a circle and a shadow.
	 * This element inherits hide/show methods from the HideableElement object.
	 * 
	 * @param {Integer} x			Center X coord
	 * @param {Integer} y			Center Y coord
	 * @param {Integer} radius		Radius of the circle
	 * @param {String} fill			Hex color of the circle fill
	 * @return new circle_sh object
	 */
	Raphael.fn.circle_sh = function(x, y, radius, fill) {
		function Circle() {
			this.init.apply(this, arguments);
		}
		
		Circle.prototype = new HideableElement();
		
		Circle.prototype.init = function(canvas, x, y, radius, fill) {
			var idx = 0;
			
			// draw bullseye
		    // shadow
		    this.components[idx++] = canvas.circle(x, y, radius + 1)
		    	.attr({
		        	stroke: '#000000',
		        	'stroke-width': 2,
		        	'stroke-opacity': 0.5
		    	});
	
	    
		    // ring
		    this.components[idx++] = canvas.circle(x, y, radius)
		    	.attr({
		        	fill: fill,
		        	'stroke-width': 0
				});
		}
		
		return new Circle(this, x, y, radius, fill);
	}
	
	
	/**
	 * Draws the legend at the specified (x,y) coords. 
	 * This element inherits hide/show methods from the HideableElement object.
	 * 
	 * @param {Object[]} 	options 		See bullseye constructor
	 * @param {Integer} 	x				X coord of the legend
	 * @param {Integer}		y				Y coord of the legend
	 * @param {Integer}		width			legend width
	 * @param {Integer} 	height			legend height
	 * @return new legend object
	 */
	Raphael.fn.legend = function(options, x, y, width, height) {
		function Legend() {
			this.init.apply(this, arguments);
		}
		
		Legend.prototype = new HideableElement();
		
		Legend.prototype.init = function(canvas, options, x, y, width, height) {
			var idx = 0;
			
			// draw legend box
			this.components[idx++] = canvas.rect(x, y, width, height, 5)
				.attr({
					'stroke-width' : 1,
					'stroke-opacity' : 0.2,
					'fill': '#D8D8D8'
				});
			
			// loop through variables in a legend
			for (var variable_num = 0; variable_num < options.length; variable_num++) {
				var col = options[variable_num],
					col_x = x + 10;
					col_y = y + variable_num * 35 + 10
					indicator = col.indicator;
				
				// draw variable label
				this.components[idx++] = canvas.text(col_x, col_y, col.label).attr({'text-anchor': 'start'});
				
				// loop through possible values for the variable
				for (var item_num = 0; item_num < col.items.length; item_num++) {
					var item = col.items[item_num],
						item_y = col_y + 15,
						item_x = col_x + item_num * 70;
					
					var item_text_attr = {
						'text-anchor': 'start'
					};
					
					// decide what to draw in the legend; can be based color, size, etc
					switch(indicator) {
						case 'color':
							var circle_x = item_x + 8;
							this.components[idx++] = canvas.circle(circle_x, item_y, 6).attr({'fill': item.value, 'stroke-width': 0});
							item_x += 18;
							break;
						case 'size':
							var circle_x = item_x + 8;
							this.components[idx++] = canvas.circle(circle_x, item_y, item.value).attr({'fill': '#FFFFFF', 'stroke-opacity': 0.7 });
							item_x += 18;
							break;
					}
					
					// draw text label for the indicator
					this.components[idx++] = canvas.text(item_x, item_y, item.label).attr(item_text_attr);
				}
			}	
		}
		
		return new Legend(this, options, x, y, width, height);
	}
})();