<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.PrintWriter" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width", intial-scale="1">
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/custom.css">
<title>MINSEO'S PORTFOLIO</title>
</head>
<body>
	<%
		String userID = null;
		if(session.getAttribute("userID") != null){
			userID = (String) session.getAttribute("userID");
		}	
		int userIdentity = 0;
		if(session.getAttribute("userIdentity") != null){
			userIdentity = (int) session.getAttribute("userIdentity");
		}	
		String userPost = null;
		if(session.getAttribute("userPost") != null){
			userPost = (String) session.getAttribute("userPost");
		}
		String userAddress = null;
		if(session.getAttribute("userAddress") != null){
			userAddress = (String) session.getAttribute("userAddress");
		}
	%>

	<nav class="navbar navbar-default">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
				aria-expanded="false">
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="main.jsp">MINSEO'S PORTFOLIO</a>
		</div>
		<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li class="active"><a href="main.jsp">MAIN</a></li>
				<li><a href="bbs.jsp">BOARD</a></li>
				<li><a href="bbs.jsp">CONTENT</a></li>
			</ul>
			
			<%
				if(userID == null){	
			%>
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown">
					<a href="#" class="dropdown-toggle"							
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false">CONNECT<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="login.jsp">LOGIN</a></li>
						<li><a href="join.jsp">SIGN UP</a></li>
					</ul>
				</li>
			</ul>
			<%
				} else {
			%>
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown">
					<a href="#" class="dropdown-toggle"							
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false">MANAGE<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="logoutAction.jsp">LOGOUT</a></li>
					</ul>
				</li>
			</ul>
			<%		
				}
			%>	
		</div>
	</nav>
	<div class="container">
		<div class="jumbotron">
			<div class="container">
				<h1>MINSEO'S PORTFOLIO</h1>
				<p> ?????????</P> 
				<p> 2000.06.15</p>
				<p> ??????????????? ????????????????????????</P>
				<p> ?????????</P>
				<p> ???????????????</P>
				<p> skaalstj0615@naver.com</P>
				<p> TENSORFLOW</P>
			</div>
		</div>
	</div>
	
	</div>
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="js/bootstrap.js"></script>
</body>
</html>