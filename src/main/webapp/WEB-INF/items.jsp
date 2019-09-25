<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="/css/style.css" />
</head>
<body>
	<header>
		<h1>
			Welcome,
			<c:out value="${userInfo.name}" />
		</h1>
		<a href="/logout">Logout?</a>
	</header>
	<main>
	<div class="items">
		<h1>Avatars</h1>
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
									<input type="submit" value="Set As Current" />
							</td></form>
						<tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<div class="items">
		<h1>Tokens</h1>
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
									<input type="submit" value="Set As Current" />
							</td></form>
						<tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	</main>
</body>
</html>