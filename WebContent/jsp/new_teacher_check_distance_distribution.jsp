<%@page import="java.util.*"%>
<%@ page language="java" import="edu.auburn.domain.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
<title>Exam Distance Distribution</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
<script src="js/zingchart.min.js"></script>
<script src="js/raphael-min.js" type="text/javascript"></script>
<script src="js/raphael.bullseye.js" type="text/javascript"></script>
<meta charset="utf-8">
<style>
body {
	background-image: url("gray.jpg");
}
</style>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.1/Chart.min.js"></script>

</head>
<body>
	<nav class="navbar navbar-inverse">
		 
		<div class="container-fluid">
			   
			<div class="navbar-header">
				      <a class="navbar-brand"
					href="${pageContext.request.contextPath }/teacher">ALT</a>      
			</div>
			   <%
			String sname = (String) (request.getAttribute("sname"));
		Exam exam = (Exam) (request.getAttribute("exam"));
		HashMap<String, Integer> pieChart = (HashMap<String, Integer>) (request.getAttribute("pie"));;
		ArrayList<Integer> barChart = (ArrayList<Integer>)(request.getAttribute("bar"));
		List<Integer> position = (List<Integer>)(request.getAttribute("position"));
		
		List<WordStudent> answerList = (List<WordStudent>)request.getAttribute("answerList");
		int sid = (int)request.getAttribute("sid");
		double pos = (double)position.get(1) / position.get(0);
		int pos_int = (int)(pos*100);
		String pos_string = pos_int + "%";
	%>
			<button
				onclick="window.location.href='${pageContext.request.contextPath }/teacher'"
				class="w3-button w3-blue">Return Main Page</button>
				<button
				onclick="javascript:history.back(-1);"
				class="w3-button w3-blue">Return</button>
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
	<%!
	public String getRandColorCode(){  
		  String r,g,b;  
		  Random random = new Random();  
		  r = Integer.toHexString(random.nextInt(256)).toUpperCase();  
		  g = Integer.toHexString(random.nextInt(256)).toUpperCase();  
		  b = Integer.toHexString(random.nextInt(256)).toUpperCase();  
		     
		  r = r.length()==1 ? "0" + r : r ;  
		  g = g.length()==1 ? "0" + g : g ;  
		  b = b.length()==1 ? "0" + b : b ;  
		  return r+g+b;  
		 }
	%>
	<%
	//out.print(pieChart.toString() + "," + barChart.toString() + ", "+position.toString()); 
	ArrayList<String> labelList = new ArrayList<String>();
	ArrayList<String> colorList = new ArrayList<String>();
	ArrayList<Integer> dataList = new ArrayList<Integer>();
	Iterator it = pieChart.entrySet().iterator();
	while(it.hasNext()){
		Map.Entry entry = (Map.Entry)it.next();
		String key = (String)entry.getKey();
		Integer value = (Integer)entry.getValue();
		labelList.add("'"+key+"'");
		dataList.add(value);
		colorList.add("'#"+getRandColorCode()+"'");
	}
	//out.print(labelList.toString() + "," + dataList.toString() + "," + colorList.toString());
	%>
<style>
* {
  margin: 0;
  padding: 0;
}
html,
body {
  height: 100%;
}
.wrap {
  height: 100%;
  display: table;
  width: 100%;
  text-align: center;
}
.header {
  display: none;
  height: 0px;
  line-height: 0px;
}
.main {
  height: 100%;
  display: table;
  width: 100%;
}
.center{
    display:none
  	height: 0px;
   	line-height: 0px;
}
.box {
  display: table-cell;
}
.sidebar {
  padding-left:150px;
  padding-right: 150px;
  width: 100px;
  height: 200px;
  line-height: 200px;
}
.footer {
  display: none;
  height: 0px;
  line-height: 0px;
}
#myProgress {
	margin-left:200px;
	margin-right:200px;
    width: 100%;
    background-color: grey;
}
#myBar {
    width: 1%;
    height: 30px;
    background-color: green;

}

a{color: #d8dedc;outline: none;}
a:hover,a:focus{color:#74777b;text-decoration: none;}
.demo{width: 100%;padding: 2em 0;}

.progress{
    height: 30px;
    line-height: 35px;
    background: #809495;
    box-shadow: none;
    padding: 6px;
    margin-top:20px;
    overflow: visible;
    border-radius:10px;
}
.progress:after{
    content: "";
    display: block;
    border-top: 4px dashed #fff;
    margin-top:8px;
}
.progressbar-title{
    color:black;
    font-size:15px;
    margin:5px 0;
    font-weight: bold;
}
.progress .progress-bar{
    position: relative;
    border-radius: 10px 0 0 10px;
    animation: animate-positive 2s;
}
.progress .progress-bar span{
    position: absolute;
    top: -50px;
    right: -40px;
    color: #fff;
    display: block;
    font-size: 17px;
    font-weight: bold;
    padding: 5px 7px;
    background: #333;
    border-radius: 0 0 5px 5px;
}
.progress .progress-bar span:before{
    content: "";
    position: absolute;
    bottom: -14px;
    left: 18px;
    border: 7px solid transparent;
    border-top: 7px solid #333;
}
.progress .progress-bar span:after{
    content: "\f072";
    font-family: fontawesome;
    font-size: 48px;
    color: #333;
    position: absolute;
    top: 51px;
    right: 6px;
    transform: rotateZ(48deg);
}
@-webkit-keyframes animate-positive {
    0% { width: 0%;}
}
@keyframes animate-positive {
    0% { width:0%; }
}
</style>
<div class="demo">
	<div class="container">
		<div class="row">
			<div class="col-md-offset-3 col-md-6">
				<h3 class="progressbar-title">Your position:(Red point) </h3>
				
				
				<div id=canvas></div>
<script>
window.onload = function() {
    var RAD = Math.PI / 180;
    var bullseye = Raphael('canvas', 550, 550).bullseye({
        'slices' : [],
        'rings'  : ['1', '2', '3', '4', '5',  '6',  '7',  '8',  '9', '10']
    });
    <%for(int i = 0; i< answerList.size(); i++){%>
    var text1 = {
        'label'    : ' ',
        'angle'    : <%out.print(Math.random());%>*100 * RAD,
        'distance' : 0.1 * <%out.print(answerList.get(i).getScore());%>,   // 25% of the radius
        'pointFill': <%if(sid==answerList.get(i).getSid()) out.print("'#ff00ff'"); else out.print("'#00ff00'");%>,
        'pointSize': <%out.print(sid==answerList.get(i).getSid() ? 10:5);%>
    };
    bullseye.addPoint(text1);
	<%}%>
   
}
</script>
				
				
				
				<div hidden="true" class="progress">
					<div class="progress-bar" id="s_p" style="width: <%out.print(pos_string); %>; background:#005394;">
						<span id="student_position"><%out.print(pos_string); %></span>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

	<div class="wrap">
  <div class="header"></div>
  <div class="main">
    <div class="box sidebar"><canvas id="barchart" height="400" width="400"></canvas>Students' distance distribution</div>
    <div class="box center"></div>
    <div class="box sidebar"><canvas id="piechart" height="400" width="400"></canvas>Students' answer distribution</div>
  </div>
  <div class="footer"></div>
</div>

	<script type="text/javascript">
		var bc = document.getElementById("barchart");
		var pc = document.getElementById("piechart");
		Chart.defaults.scale.ticks.beginAtZero = true;
		var d_bc = {
			    labels: ["[0,2)", "[2,4)", "[4,6)", "[6,8)", "[8,10)", "[10,∞)"],
			    datasets: [
			        {
			            label: "Distance Distribution",
			            fillColor : "rgba(220,220,220,0.5)",
						strokeColor : "rgba(220,220,220,1)",
			            data: <%out.print(barChart.toString());%>
			        }
			    ]
			};
		
		
		var d_pc = {
				labels: <%out.print(labelList.toString());%>,
				datasets: [
				   {
					   label: "Students",
					   backgroundColor:<%out.print(colorList.toString());%>,
					   data:<%out.print(dataList.toString());%>
				   }
				]
		};
		var bar = new Chart(bc, {
			type : 'bar',
			data: d_bc
		});
		var pie = new Chart(pc, {
			type : 'pie',
			data : d_pc
		});
	</script>
	
</body>
</html>