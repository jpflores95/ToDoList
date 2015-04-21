<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Insert title here</title>
	<style>
		html, body{
			height:100%;
			width:100%;
			margin:0px;
		}
		
		div, p{
			margin:0px;
			color:#1D1E1E;
			font-size:30px;
		}
		
		#add{
			float:left;
			height: 100%;
			width: 10%;
			background-color:#EB4B55;
		}
			
		.nav{
			background-color:#F4B6AC;
			width:100%;
			height:15%;
		}
		
		#up p, #down p{
			position:relative;
			top:45%;
			right:50%:
		}
		
		#content p{
			position:relative;
			top:45%;
			left:20%;
			width:75%;
		}
		
		#add p{
			position:relative;
			top:45%;
			left:45%;
			color:#FFF7F3;
		}
		
		#content{
			background-color:#FFF7F3;
			width:100%;
			height:70%;
		}
		
		#adddiv{
			padding-top: 50px;
			padding: 20px;
			color:white;
			width:300px;
			height:100%;
			background-color:#EB4B55;
			float:left;
			/* position:absolute;
			left:-100%; */
		}
		
		input[type='text']{
			width: 250px;
			height: 50px;
			font-size:15px;
			padding-left: 20px;
			margin:10px 0px 0px 0px;
		}
	
		input[type='submit']{
			margin:10px 0px 0px 0px;
			width: 150px;
			height: 50px;
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
		var id = ${todo.idtodo};
		$(document).ready(function(){
			if(id != -1){
				$("#hiddeninput").val(id);
				//$("#up").click(function(){
					//$("#hiddeninput").val(id);
					//$("#hiddenform").attr("action","previous");
					//$("#hiddenform").submit();
					$("#up").click(function(){
						$.ajax({
							url: "previous",
							method: "GET",
							data : {"id" : id},
							success: function(data){
								$('#content p').text(data.note);
								id = data.idtodo;
							}
								
						});
					});
				
				$("#down").click(function(){
					//$("#hiddenform").attr("action","next");
					//$("#hiddenform").submit();
					$.ajax({
						url : "next",
						method : "GET",
						data : {"id" : id},
						success : function(data){
							$('#content p').text(data.note);
							id = data.idtodo;
						}
						
					});
				});
				$("#content").dblclick(function(){
					$("#hiddenform").attr("action","done").attr("method","POST");
					$("#hiddenform").submit();
				});
				// $('#adddiv').animate({right:300px},500);
			}

			$("#adddiv").hide();
			$("#add").click(function(){
				// window.location = "addnote.jsp";
				// $("#adddiv").toggle("slide");
				$("#adddiv").animate({width:'toggle'},350);
				
				
				// $("#adddiv").animate({margin-right:'-='+300},350);
				// $("#adddiv").show("slide", { direction: "right" }, 5000);
				// $('#adddiv').css("float","left").css("position","static");
				// $('#adddiv').animate({left:300},5000);
			});
			$('input[type=submit]').click(function(e){
				e.preventDefault();
				$.ajax({
					url : "add",
					method : "POST",
					data : {"note" : $("input[type=text]").val()},
					success : function(data){
						// $('#content p').text(data.note);
						// id = data.idtodo;
						$("input[type=text]").val("");
					}
					
				});
			});
			
		});
	</script>
</head>
<body>
	<div id="adddiv">
		Add Note : <br>
		<form action="add" method="POST">
			<input type="text" name="note" placeholder="Note"/><br>
			<input type="submit" value="Add"/>
		</form>
	</div>
	<div id="add">
		<p>+</p>
	</div>
	
	<div class="nav" id="up"></div>
	
	<div id="content">
		<p>${todo.note}</p>
	</div>
	
	<div class="nav" id="down"></div>
	
	<form id="hiddenform">
		<input type="hidden" id="hiddeninput" name="id"/>
	</form>
</body>
</html>