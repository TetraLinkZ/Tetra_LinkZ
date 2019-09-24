<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form:form action="/admin/tokens/${token.id}/update" method="POST" modelAttribute="token">
	<input type="hidden" name="_method" value="put"/>
	<form:input type="hidden" path="id" name="id" value="${token.id}"/>
		<table>
			<tbody>
				<tr>
					<td>
						<form:form path="name">Name: </form:form>
					</td>
					<td>
						<form:input path="name" />
						<form:errors path="name" />					
					</td>
				</tr>
				<tr>
					<td>
						<form:form path="description">Description: </form:form>
					</td>
					<td>
						<form:input path="description" />
						<form:errors path="description" />					
					</td>
				</tr>
				<tr>
					<td>
						<form:form path="cost">Cost: </form:form>
					</td>
					<td>
						<form:input type="integer" path="cost" />
						<form:errors path="cost" />					
					</td>
				</tr>
				<tr>
					<td>
						<form:form path="rarity">Rarity: </form:form>
					</td>
					<td>
						<form:input type="integer" path="rarity" />
						<form:errors path="rarity" />					
					</td>
				</tr>
				<tr>
					<td>
						<form:form path="url">Url: </form:form>
					</td>
					<td>
						<form:input path="url" />
						<form:errors path="url" />					
					</td>
				</tr>
			</tbody>
		</table>
		<input type="submit" value="Submit"/>
	</form:form>
</body>
</html>