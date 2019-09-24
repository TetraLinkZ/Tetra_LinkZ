<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<header>
<h1>Tetra LinkZ</h1>
<form:form modelAttribute="login">
<form:label path="name"/>
<form:input path="name"/>
<form:errors path='name'/>

<form:label path="password"/>
<form:input type = "password" path="password"/>
<form:errors path='password'/>
<input type="submit" value="Login" />
</form:form>
</header>
<main>
<div id="registration-box">
<h2>Register</h2>
<form:form modelAttribute="newUser">
<form:label path="name"/>
<form:input path="name"/>
<form:errors path='name'/>


<form:label path="email"/>
<form:input path="email"/>
<form:errors path='email'/>

<form:label path="password"/>
<form:input type = "password" path="password"/>
<form:errors path='password'/>

<form:label path="paswordConfirm"/>
<form:input type="password" path="passwordConfirm"/>
<form:errors path='passwordConfirm'/>
<input type="submit" value="Register" />
</form:form>
</div>

</main>
</body>
</html>