<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Tetra LinkZ</title>

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
	integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
	crossorigin="anonymous"></script>
		<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="/css/style.css" />
<style>
	table {
		border-collapse: collapse;
		width: 100%;
	}
	
	th, td {
		text-align: center;
		padding: 0 2.5px;
		border-left: 2px solid #000;
		border-right: 2px solid #000;
	}
</style>
</head>
<body>
<header class="container-fluid">
			<div class="row">
				<div class="col-sm-8">
					<h1 class="header-title">Tetra LinkZ</h1>
				</div>
				<div class="col-sm-2">
					<h2>
						Welcome,
						<c:out value="${userInfo.name}" />
					</h2>
					<a href="/dashboard" class="btn">Profile</a> <a href="/logout"
						class="btn">Logout</a>
					<div></div>
				</div>

			</div>
		</header>
	<main>
	<div class="items">
		<h1 class="text-center">Avatars</h1>
		<div id="avatars">
			<!-- obtained avatars -->
			<table>
				<thead>
					<th>Name</th>
					<th>Picture</th>
					<th>Rarity</th>
					<th>Cost</th>
					<th>Description</th>
				</thead>
				<tbody>
					<c:forEach items = "${ownedAvatar}" var = "oA">
						<tr>
							<td><c:out value = "${oA.avatar.name}"/></td>
							<td><img src="${oA.avatar.url}" alt="" style="width: 200px;" /></td>
							<td><c:out value = "${oA.avatar.rarity}"/></td>
							<td><c:out value = "${oA.avatar.cost}"/></td>		
							<td><c:out value = "${oA.avatar.description}"/></td>	
							<td><form action="/setCurrentAvatar" method = "Post">
									<input type="hidden" name="avatarId" value = "${oA.avatar.id}">
									<input type="submit" value="Set As Current" class="btn"/>
							</form>
							</td>
						<tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<div class="items">
		<h1 class="text-center">Tokens</h1>
		<div id="tokens">
			<!-- obtained tokens -->
			<table>
				<thead>
					<th>Name</th>
					<th>Picture</th>
					<th>Rarity</th>
					<th>Cost</th>
					<th>Description</th>
				</thead>
				<tbody>
					<c:forEach items = "${ownedToken}" var = "oT">
						<tr>
							<td><c:out value = "${oT.token.name}"/></td>
							<td><img src="${oT.token.url}" alt="" style="width: 50px;" /></td>
							<td><c:out value = "${oT.token.rarity}"/></td>
							<td><c:out value = "${oT.token.cost}"/></td>		
							<td><c:out value = "${oT.token.description}"/></td>	
							<td><form action="/setCurrentToken" method = "Post">
									<input type="hidden" name="tokenId" value = "${oT.token.id}">
									<input type="submit" value="Set As Current" class="btn" />
							</form>
							</td>
						<tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	</main>
</body>
</html>