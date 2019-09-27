

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>

<title>Tetra LinkZ</title>
<link rel="stylesheet" href="/css/style.css" />
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
	integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
	crossorigin="anonymous"></script>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
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
	<h1 class="text-center m-4">The Shoppe</h1>
	<hr class="m-auto"style="width:60%;"/>
	<div class="container-fluid">
		<div class="row m-auto">
			<div class="col-sm-12">
				<h1 class="text-center">Avatar</h1>
				<table class="m-auto" style="width: 50%;">
					<thead class="text-center p">
						<tr>
							<th>Name</th>
							<th>Picture</th>
							<th>Rarity</th>
							<th>Cost</th>
							<th>Description</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${allAvatar}" var="a">
							<tr>
								<td><c:out value="${a.name}" /></td>
								<td><img src="${a.url}" alt="" style="width: 200px;" /></td>
								<td><c:out value="${a.rarity}" /></td>
								<td><c:out value="${a.cost}" /></td>
								<td><c:out value="${a.description}" /></td>
								<td><form action="/buyAvatar" method="Post">
										<input type="hidden" name="avatarId" value="${a.id}">
										<input type="submit" class="btn" value="BUY!" />
									</form></td>
							<tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<div class="col-sm-12">
				<h1 class="text-center">Tokens</h1>
				<table class="m-auto" style="width: 50%;">
					<thead >
						<tr>
							<th class="text-center">Name</th>
							<th class="text-center">Picture</th>
							<th class="text-center">Rarity</th>
							<th class="text-center">Cost</th>
							<th class="text-center">Description</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${allToken}" var="t">
							<tr>
								<td><c:out value="${t.name}" /></td>
								<td><img src="${t.url}" alt="" style="width: 50px;" /></td>
								<td><c:out value="${t.rarity}" /></td>
								<td><c:out value="${t.cost}" /></td>
								<td><c:out value="${t.description}" /></td>
								<td><form action="/buyToken" method="Post">
										<input type="hidden" name="tokenId" value="${t.id}"> <input
											type="submit" value="BUY!" class="btn" />
									</form></td>
							<tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</body>
</html>