<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>The shop</title>
</head>
<body>
	<h1>Avatar</h1>
	<table>
		<thead>
			<th>Name</th>
			<th>Picture</th>
			<th>Rarity</th>
			<th>Cost</th>
			<th>Description</th>
		</thead>
		<tbody>
			<c:forEach items = "${allAvatar}" var = "a">
				<tr>
					<td><c:out value = "${a.name}"/></td>
					<td><img src="${a.url}" alt="" style="width: 200px;" /></td>
					<td><c:out value = "${a.rarity}"/></td>
					<td><c:out value = "${a.cost}"/></td>		
					<td><c:out value = "${a.description}"/></td>	
					<td><form action="" method = "Post">
							<input type="hidden" name="avatarId" value = "${a.id}">
							<input type="submit" value="BUY!"/>
					</form>
					</td>
				<tr>
			</c:forEach>
		</tbody>
	</table>
	<h1>Tokens</h1>
	<table>
		<thead>
			<th>Name</th>
			<th>Picture</th>
			<th>Rarity</th>
			<th>Cost</th>
			<th>Description</th>
		</thead>
		<tbody>
			<c:forEach items = "${allToken}" var = "t">
				<tr>
					<td><c:out value = "${t.name}"/></td>
					<td><img src="${t.url}" alt="" style="width: 50px;" /></td>
					<td><c:out value = "${t.rarity}"/></td>
					<td><c:out value = "${t.cost}"/></td>		
					<td><c:out value = "${t.description}"/></td>	
					<td><form action="" method = "Post">
							<input type="hidden" name="tokenId" value = "${t.id}">
							<input type="submit" value="BUY!" />
					</td></form>
				<tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>