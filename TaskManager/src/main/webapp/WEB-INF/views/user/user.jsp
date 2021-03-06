<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<style>
.blockNavigation{
	background: #fff;
	padding:15px;
	border: 4px inset #FF7F50;
	word-wrap:break-word;
}
.blockNavigationRow{
	padding:5px;
	margin-left:15px;
}
.infoRow {
	color: #828282;
}
</style>
<div class="row">
	<div class="col-md-8">
	 	<div class="row"><h2>${users.name} ${users.surname}</h2></div>
		<div class="row">
			<div class="col-md-3 infoRow">Country:</div> <div class="col-md-9">${users.country}</div>
			<div class="col-md-3 infoRow">City:</div> <div class="col-md-9">${users.city}</div>
			<div class="col-md-3 infoRow">Email:</div> <div class="col-md-9">${users.email}</div>
			<div class="col-md-3 infoRow">Phone:</div> <div class="col-md-9">${users.phoneNumber}</div>
			<div class="col-md-12"><hr></div>
			<div class="col-md-12"><a href="/user/${users.id}/tasks">${users.name}'s created tasks</a></div>
			<div class="col-md-12"><a href="/user/${users.id}/active">${users.name}'s active tasks</a></div>
			<div class="col-md-12"><a href="/user/${users.id}/finished">${users.name}'s completed tasks</a></div>
		</div>
	</div>
	<sec:authorize access="isAuthenticated()">
	<div class="col-md-4">
		<div class="col-md-12 blockNavigation">
			<div class="row blockNavigationRow">
				<a href="/user/<sec:authentication property="principal.id" />">My profile</a>
			</div>
			<div class="row blockNavigationRow">
				<a href="/user/<sec:authentication property="principal.id" />/tasks">My tasks</a>
			</div>
			<div class="row blockNavigationRow">
				<a href="/user/<sec:authentication property="principal.id" />/active">My active tasks</a>
			</div>
			<div class="row blockNavigationRow">
				<a href="/user/<sec:authentication property="principal.id" />/finished">My completed tasks</a>
			</div>
		</div>
	</div>
	</sec:authorize>
	
</div>
