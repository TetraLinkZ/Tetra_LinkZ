<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<style>
	* {
		font-family: verdana;
	}
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
	
	tr:nth-child(even) {
		background-color: #f2f2f2;
	}
</style>
<meta charset="UTF-8">
<title>Avatars</title>
</head>
<body>
	<div>
		<ul>
			<li><a href="/admin">Go back to Admin Panel</a></li>
			<li><a href="/admin/newAvatar">Add a new Avatar</a></li>
		</ul>
	</div>
	<h1>Available Avatars:</h1>
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
			<c:forEach items="${avatars}" var="avatar">
				<tr style="text-align:center">
					<td>
						<c:out value="${avatar.id}" />
					</td>
					<td>
						<img src="${avatar.url}" alt="${avatar.name}" style="width:200px;"/>
					</td>
					<td>
						<c:out value="${avatar.name}" />
					</td>
					<td>
						<c:out value="${avatar.rarity}" />
					</td>
					<td>
						<c:out value="${avatar.cost}" />
					</td>
					<td>
						<c:out value="${avatar.description}" />
					</td>
					<td>
						<a href="/admin/avatars/${avatar.id}/edit">Edit Avatar</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>