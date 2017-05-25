<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<style>
.navbar{
	background-color:#008B8B;
}
.home{
	color:white;
	font-size: 30pt;
}
</style>
<nav class="navbar navbar-fixed-top">
	<div class="container-fluid">
		<ul class="nav navbar-nav">
			<li><a href="/" class="home">Task Manager</a></li>
		</ul>
		<ul class="nav navbar-nav navbar-right">
				<li><sec:authorize access="!isAuthenticated()">
					<form:form action="/login" method="GET">
						<button type="submit" class="btn btn-danger">Login</button>
					</form:form>
				</sec:authorize></li>
				<li><sec:authorize access="!isAuthenticated()">
					<form:form action="/registration" method="GET">
						<button type="submit" class="btn btn-danger">Sign up</button>
					</form:form>
				</sec:authorize></li>
				<li><sec:authorize access="isAuthenticated()">
					<form:form action="/logout" method="POST">
						<button type="submit" class="btn btn-danger">Logout</button>
					</form:form>
				</sec:authorize></li>
		</ul>
	</div>
</nav>
