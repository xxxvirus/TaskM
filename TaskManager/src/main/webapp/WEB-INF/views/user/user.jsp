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
		</div>
	</div>
	<sec:authorize access="isAuthenticated()">
	<div class="col-md-4">
		<div class="col-md-12 blockNavigation">
			<div class="row blockNavigationRow">
				<a href="/user/<sec:authentication property="principal.id" />">My profile</a>
			</div>
			<div class="row blockNavigationRow">
				<a href="/member/<sec:authentication property="principal.id" />/friends">My tasks</a>
			</div>
			<div class="row blockNavigationRow">
				<a href="">My order tasks</a>
			</div>
			<div class="row blockNavigationRow">
				<a href="/member/<sec:authentication property="principal.id" />/mygroups">My done tasks</a>
			</div>
		</div>
	</div>
	</sec:authorize>
	
</div>
<div class="row">
	<div class="col-md-12">
	<hr>
	<h2>Task by ${users.name} ${users.surname}:</h2>
	<table class="table table-condensed">
    <thead>
      <tr>
        <th>Date</th>
        <th>Title</th>
        <sec:authorize access="isAuthenticated()"><th>Option</th></sec:authorize>
      </tr>
    </thead>
    <tbody>
    <c:forEach items="${tasks}" var="task">
	 	<tr>
	 		<td>${task.date}</td>
	 		<td><a href="/task/${task.id}">${task.title}</a></td>
	 		<sec:authorize access="isAuthenticated()">
	 		<sec:authentication property="principal.id" var="userId"/>
			<c:if test="${userId == task.customer.user.id}">
	 		<td><a class="btn btn-danger btn-xs" href="/deleteTask/${task.id}">delete</a>
	 		<a class="btn btn-warning btn-xs" href="/editTask/${task.id}">edit</a></td>
	 		</c:if>
	 		</sec:authorize>
	 	</tr>
	 </c:forEach>
    </tbody>
  </table>
	</div>
</div>
