<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Insert title here</title>
	<style>
		body{
			font-size: 40px;
			font-family:Arial;
			margin: 40px 0px 0px 40px;
		}
		
		input[type='text']{
			width: 500px;
			height: 100px;
			font-size:25px;
			padding-left: 20px;
		}
		
		input[type='submit']{
			margin:10px 0px 0px 0px;
			width: 200px;
			height: 75px;
			background-color: #0C817B;
			color: #AFF5AD;
			border-radius: 3px;
			border : 0px;
			font-size : 25px;
		}
		
		input[type='submit']:hover{
			-webkit-box-shadow: 0px 1px 2px rgba(10,10,10,0.2);
		}
	</style>
	
	<script src="jquery-2.1.1.js"></script>
	<script>
		$(document).ready(function(){
			$("input[type='submit']").click(function(e){
				if($("input[type='text']").val()==""){
					e.preventDefault();
					$("input[type='text']").css("background-color", "#F86F86");
				}
			});
			$("input[type='text']").keydown(function(){
				if($("input[type='text']").val()!=""){
					$("input[type='text']").css("background-color", "#FFFFFF");
				}
			});
		});
	</script>
</head>
<body>
	Add Note : <br>
	<form action="add" method="POST">
		<input type="text" name="note" placeholder="Note"/><br>
		<input type="submit" value="Add"/>
	</form>
</body>
</html>