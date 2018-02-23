window.onload = function() {
    function rand(min, max) {
        return Math.floor(Math.random() * (max - min + 1)) + min;
    }
    function capitalize(str) {
        return str.charAt(0).toUpperCase() + str.slice(1);
    }
    function showData(point) {
        var data = point.data;
        var elem = document.getElementById('output');
        var html = '<table class=datatable>'
                   + '<tr><th colspan=2>Data</th></tr>'
                   + '<tr><td class=field>Theater</td><td class=value>' + theaters[data[0]] + '</td></tr>'
                   + '<tr><td class=field>Year</td><td class=value>' + data[1] + '</td></tr>'
                   + '<tr><td class=field>Name</td><td class=value>' + data[2] + '</td></tr>'
                   + '<tr><td class=field>Casualties</td><td class=value>' + data[3] + '</td></tr>'
                   + '<tr><td class=field>Victor</td><td class=value>' + capitalize(data[4]) + '</td></tr>'
                   + '</table><br>'
                   + '<table class=datatable>'
                   + '<tr><th colspan=2>Point Coordinates</th></tr>'
                   + '<tr><td class=field>Angle</td><td class=value>' + point.angle + '</td></tr>'
                   + '<tr><td class=field>Radius</td><td class=value>' + point.radius + '</td></tr>'
                   + '<tr><td class=field>Distance (%)</td><td class=value>' + point.distance + '</td></tr>'
                   + '<tr><td class=field>Ring Distance (%)</td><td class=value>' + point.ringDistance + '</td></tr>'
                   + '</table>';


        elem.innerHTML = html;
    }

    var slices = ['Pacific', 'Atlantic', 'Mediterranean'];
    var theaters = {
        a: 'Atlantic',
        p: 'Pacific',
        m: 'Mediterranean'
    };
    var bullseye = Raphael('canvas', 640, 600).bullseye({
        'slices': slices,
        'rings' : [1940, 1941, 1942, 1943, 1944, 1945],
        'startDegree': -30,
        'allowDrag': true,
        'onMouseOver': showData,
        'onPointClick': showData,
        'onSliceClick': function(sliceIdx) {
            alert("You've clicked on " + slices[sliceIdx]);
        }

    });
    var baseYear = 1940;
    //http://history1900s.about.com/od/worldwarii/a/wwiibattles.htm
    var data = [
        ['a', 1940, 'Battle of France', 520000, 'axis'],
        ['a', 1940, 'Battle of Belgium', 220000, 'axis'],
        ['a', 1945, 'Battle of Berlin', 180000, 'allies'],
        ['a', 1944, 'Battle the Bulge', 190000, 'allies'],
        ['m', 1942, 'First Battle of El Alamein', 23000, 'allies'],
        ['m', 1942, 'Second Battle of El Alamein', 44000, 'allies'],
        ['p', 1942, 'Guadalcanal Campaign', 39000, 'allies'],
        ['p', 1945, 'Iwo Jima', 28000, 'allies'],
        ['a', 1943, 'Kursk', 1066000, 'allies'],
        ['a', 1941, 'Siege of Leningrad', 1000000, 'allies'],
        ['p', 1944, 'Battle of Leyte Gulf', 13000, 'allies'],
        ['a', 1944, 'D-Day', 230000, 'allies'],
        ['p', 1941, 'Pearl Harbor',  2500, 'axis']
    ];


    var angle;
    var upper_bound, lower_bound;
    for (var i = 0; i < data.length; i++) {
        switch(data[i][0]) {
            case 'p':
                lower_bound = -25;
                upper_bound = 85;
                break;
            case 'a':
                lower_bound = 95;
                upper_bound = 205;
                break;
            case 'm':
                lower_bound = 215;
                upper_bound = 325;
                break;
        }

        angle = rand(lower_bound, upper_bound);

        // show positive angle when you hover over a point
        if (angle < 0) angle += 360;

        if (data[i][3] > 500000)
            size = 8;
        else if (data[i][3] > 100000)
            size = 6;

        var point = bullseye.addPoint({
            label: data[i][2],
            ring: data[i][1] - baseYear,
            angle: angle * Math.PI / 180,
            pointFill: data[i][4] == 'allies' ? '#00ff00' : '#ff0000',
            pointSize: size,
            distance: .5
        });

        point.data = data[i];
    }
        
}
