<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Tokens</title>
</head>
<body>
	<div>
		<ul>
			<li><a href="/admin">Go back to Admin Panel</a></li>
			<li><a href="/admin/newToken">Add a new token</a></li>
		</ul>
	</div>
	<h1>Available tokens:</h1>
	<table>
		<thead>
			<tr>
				<th>ID</th>
				<th>Picture</th>
				<th>Name</th>
				<th>Rarity</th>
				<th>Cost</th>
				<th>Description</th>
				<th>Action</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${tokens}" var="token">
				<tr style="text-align:center">
					<td>
						<c:out value="${token.id}" />
					</td>
					<td>
						<img src="${token.url}" alt="${token.name}" style="width:200px;"/>
					</td>
					<td>
						<c:out value="${token.name}" />
					</td>
					<td>
						<c:out value="${token.rarity}" />
					</td>
					<td>
						<c:out value="${token.cost}" />
					</td>
					<td>
						<c:out value="${token.description}" />
					</td>
					<td>
						<a href="/admin/tokens/${token.id}/edit">Edit token</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</body>
</html>