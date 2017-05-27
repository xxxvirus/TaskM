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
	<div class="col-md-12"><h3>${users.name}'s created tasks: </h3></div>
	<div class="col-md-8">
	 	<table class="table table-condensed">
    <thead>
      <tr>
        <th>Date</th>
        <th>Title</th>
	    <th>Status</th>
	    <th>Option</th>
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
	 		<c:if test="${task.done==true}">
	 			<td><a class="btn btn-success btn-xs" href="/user/${users.id}/tasks/open/${task.id}">Completed</a></td>
	 		</c:if>
	 		<c:if test="${task.done!=true}">
	 			<td><a class="btn btn-danger btn-xs" href="/user/${users.id}/tasks/finish/${task.id}">Uncompleted</a></td>
	 		</c:if>
	 		<td><a class="btn btn-danger btn-xs" href="/user/${users.id}/deleteTask/${task.id}">delete</a>
	 		<a class="btn btn-warning btn-xs" href="/user/${users.id}/editTask/${task.id}">edit</a></td>
	 		</c:if>
	 		<c:if test="${userId != task.customer.user.id}">
	 		<c:if test="${task.done==true}">
	 			<td><a class="btn btn-danger btn-xs" href="/task/${task.id}">Closed</a></td>
	 		</c:if>
	 		<c:if test="${task.done!=true}">
	 			<td><a class="btn btn-success btn-xs" href="/task/${task.id}">Open</a></td>
	 		</c:if>
	 		<c:if test="${userId != task.customer.user.id}">
	 		<td><a class="btn btn-primary btn-xs" href="/task/${task.id}">view</a></td>
	 		</c:if>
	 		</c:if>
	 		</sec:authorize>
	 	</tr>
	 </c:forEach>
    </tbody>
  </table>
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
