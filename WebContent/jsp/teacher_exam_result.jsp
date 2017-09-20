<%@page import="java.util.List"%>
<%@ page language="java" import="edu.auburn.domain.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
<title>Exam Result</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
 
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<style>
body {
	background-image: url("gray.jpg");
}

.pie-legend li span {
    width: 1em;
    height: 1em;
    display: inline-block;
    margin-right: 5px;
}

.pie-legend {
    list-style: none;
}
</style>
<script src="js/Chart.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
 
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
	<nav class="navbar navbar-inverse">
		 
		<div class="container-fluid">
			   
			<div class="navbar-header">
				      <a class="navbar-brand"
					href="${pageContext.request.contextPath }/teacher">ALT</a>      
			</div>
			   <%
		Exam e = (Exam) (request.getAttribute("exam"));
		List<DisplayStudentExamResult> ds = (List<DisplayStudentExamResult>) (request.getAttribute("result"));
	%>
			<button
				onclick="window.location.href='${pageContext.request.contextPath }/teacher'"
				class="w3-button w3-blue">Return Main Page</button>
			<ul class="nav navbar-nav navbar-right">
				      
				<li><a href="#"><span class="glyphicon glyphicon-user"></span>
						<%
							if (session == null || session.getAttribute("user") == null) {
						%> <input type="submit" value="LogIn"> <input
						type="submit" value="SignUp"> <%
 	}
 	if (session.getAttribute("user") != null) {
 		out.println("hello, " + session.getAttribute("user"));
 %> <%
 	}
 %> </a></li>   
 <li>
					<button
						onclick="window.location.href='${pageContext.request.contextPath }/logout'"
						class="btn btn-danger navbar-btn">
						<span class="glyphicon glyphicon-log-out">Logout 
					</button>
				</li>   
			</ul>
			 
		</div>
	</nav>
	<div class="container">
		<h1>
			<%
				out.print(e.getName());
			%>
		</h1>
		You have taken this exam.
		<hr>
		<table class="table table-hover">
			<thead>
				<tr>
					<th>Student</th>
					<th>Distance</th>
					<th>Details</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${result }" var="item" varStatus="counter">
					<tr>
						<td>${item.stuName }</td>
						<td>${item.score }</td>
						<td>
						<a href="${pageContext.request.contextPath }/teacher?method=checkse&eid=${item.eid}&uid=${item.sid}">Check</a>
						
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	
	
	<script type="text/javascript">
		// 饼状图数据
	    var pieData = [
	        {
	            value: 300, color: '#57c7d4', label: '1月'
	        },
	        {
	            value: 150, color: '#3aa99e', label: '2月'
	        },
	        {
	            value: 100, color: '#f2a654', label: '3月'
	        },
	        {
	            value: 140, color: '#f96868', label: '4月'
	        },
	        {
	            value: 120, color: '#926dde', label: '5月'
	        }
	    ];
	    // 饼状图选项设置
	    var configs  = {
	    	// 是否显示提示
	        showTooltips:false,
	        // 标签字体的颜色
	        scaleFontColor: '#000',
	    	// 块和块之间是否有间距
	    	segmentShowStroke : true,
	 	// 块和块之间间距的颜色
	     segmentStrokeColor : "#fff",
	 	// 块和块之间间距的宽度
	     segmentStrokeWidth : 2,
	 	// 是否有从0度到360度的动画
	     animateRotate : true,
	 	// 是否有从中心到边缘的动画
	     animateScale : false,
	 	// 图例
	 	// 动画完成后调用的函数(每个扇区显示对应的数据)		
	        onAnimationComplete: function () {
	            var ctx = this.chart.ctx;
	            var segs = this.segments;
	            ctx.textAlign = 'start';
	            ctx.textBaseline = 'middle';
	            var total = 0;
	            for (var i = 0; i < segs.length; i++) {
	                total += parseFloat(segs[i].value);
	            }
	            ctx.fillText(total , ctx.width / 2 - 20, ctx.height / 2, 100);
	            for(var i = 0; i < segs.length; i++){
	                var centreAngle = segs[i].startAngle + ((segs[i].endAngle - segs[i].startAngle) / 2),
	                    rangeFromCentre = (segs[i].outerRadius - segs[i].innerRadius) / 1.5 + segs[i].innerRadius;
	                var x = segs[i].x + (Math.cos(centreAngle) * rangeFromCentre);
	                var y = segs[i].y + (Math.sin(centreAngle) * rangeFromCentre);
	                ctx.textAlign = 'center';
	                ctx.textBaseline = 'middle';
	                ctx.fillStyle = '#000';
	                ctx.font = 'normal 12px Helvetica';
	                ctx.fillText(segs[i].value, x, y);
	            }
	        }
	 };


	    // 开始绘制饼状图
	 var ctx = document.getElementById('pie').getContext('2d');
	 var pie = new Chart(ctx).Pie(pieData, configs);
	 var legend = document.getElementById('legend');
	 // 图例
	 legend.innerHTML = pie.generateLegend();
	 	</script>
	<div class="pie-chart">
	 		<h5 class="pie-title">Question distribution</h5>
	 		<canvas id="pie" width=400 height=400></canvas>
	 		<div id="legend"></div>
	 	</div> 
	 	
	 	
</body>
</html>