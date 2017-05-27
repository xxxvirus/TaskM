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
	<div class="col-md-12"><h3>${users.name}'s active tasks: </h3></div>
	<div class="col-md-8">
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
    	<c:if test="${task.done==false}">
	 	<tr>
	 		<td>${task.date}</td>
	 		<td><a href="/task/${task.id}">${task.title}</a></td>
	 		<sec:authentication property="principal.id" var="userId"/>
			<c:if test="${userId == users.id}">
	 		<td><a class="btn btn-danger btn-xs" href="/user/${users.id}/active/exit/${task.id}">exit</a></td>
	 		</c:if>
	 		<c:if test="${userId != users.id}">
	 		<td><a class="btn btn-primary btn-xs" href="/task/${task.id}">view</a></td>
	 		</c:if>
	 	</tr>
	 	</c:if>
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
