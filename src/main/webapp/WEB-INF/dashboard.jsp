<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dashboard</title>
</head>
<body>
	<header>
<<<<<<< HEAD
		<h1>Welcome, <c:out value="${userInfo.name}" /></h1>
		<form action = "">
			<input type = "submit" value = "Profile"/>
		</form>
=======
		<h1>
			Welcome,
			<c:out value="${userInfo.name}" />
		</h1>
		<a href="/logout">Logout?</a>
>>>>>>> f8bd8b8b89d177867bf6f1133b1c8d85613890bb
	</header>
	<main>
		<div id = "gacha">
			<form action = "">
				<input type = "submit" value = "Open Box"/>
			</form>
		</div>
		<div id = "body">
			<div id = "avatar" class = "bodypart">
				<form action = "">
					<input type = "submit" value = "Change"/>
				</form>
			</div>			
			<div id = "stat" class = "bodypart">
				<form action = "">
					<input type = "submit" value = "View Rank"/>
				</form>
			</div>
			<div id = "friends" class = "bodypart">
				<div class="search-container">
    				<form action="">
      					<input type="text" placeholder="Search.." name="search">
      					<button type="submit">Search</button>
    				</form>
  				</div>
				<h2>Friends</h2>
			</div>
		</div>
		<div id = "play">
			<form action = "">
				<input type = "submit" value = "Ranked"/>
			</form>
			<form action = "">
				<input type = "submit" value = "Casual"/>
			</form>
		</div>	
	</main>
	<footer>
	</footer>
</body>
</html>