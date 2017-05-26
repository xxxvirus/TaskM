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
</style>
<div class="row">
	<div class="col-md-8">
		<div class="row">
			<div class="col-md-9">
				<h2>${task.title}</h2>
				<div>Add at: ${task.date}</div>
				<div>by <a href="/user/${task.customer.user.id}">${task.customer.user.name} ${task.customer.user.surname}</a></div>
				<div>Team: 
					<c:forEach items="${taskPerf.performers}" var="performer">
						<a href="/user/${performer.user.id}">${performer.user.name} ${performer.user.surname}</a>
					</c:forEach>
				</div>
			</div>
			<sec:authorize access="isAuthenticated()">
	 		<sec:authentication property="principal.id" var="userId"/>
	 			<div class="col-md-1">
	 			<c:if test="${isImPerformer!=true}">
	 			<a class="btn btn-primary btn-xs" href="/task/${task.id}/join">join</a> 
	 			</c:if>
	 			<c:if test="${isImPerformer==true}">
	 			<a class="btn btn-danger btn-xs" href="/task/${task.id}/exit">exit</a>
	 			</c:if>
	 			</div>
			<c:if test="${userId == task.customer.user.id}">
		 		<div class="col-md-1"><a class="btn btn-danger btn-xs" href="/task/${task.id}/deleteTask">delete</a></div>
		 		<div class="col-md-1"><a class="btn btn-warning btn-xs" href="/task/${task.id}/editTask">edit</a></div>
	 		</c:if>
	 		</sec:authorize>
		</div>
		<hr>
		<div class="row">
			<div class="col-md-12">
				<p>${task.text}</p>
			</div>	 
		</div>
	</div>
	<sec:authorize access="isAuthenticated()">
	<div class="col-md-4">
		<div class="col-md-12 blockNavigation">
			<div class="row blockNavigationRow">
				<a href="/member/<sec:authentication property="principal.id" />">My profile</a>
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
