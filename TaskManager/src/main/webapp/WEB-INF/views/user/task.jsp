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
			<div class="col-md-8">
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
	 			<c:if test="${isImPerformer!=true && task.done!=true}">
	 			<a class="btn btn-primary btn-xs" href="/task/${task.id}/join">join</a> 
	 			</c:if>
	 			<c:if test="${isImPerformer==true && task.done!=true}">
	 			<a class="btn btn-danger btn-xs" href="/task/${task.id}/exit">exit</a>
	 			</c:if>
	 			</div>
			<c:if test="${userId == task.customer.user.id}">
				<c:if test="${task.done!=true}">
				<div class="col-md-1"><a class="btn btn-success btn-xs" href="/task/${task.id}/finish">finish</a></div>
		 		<div class="col-md-1"><a class="btn btn-danger btn-xs" href="/task/${task.id}/deleteTask">delete</a></div>
		 		<div class="col-md-1"><a class="btn btn-warning btn-xs" href="/task/${task.id}/editTask">edit</a></div>
		 		</c:if>
	 		</c:if>
	 		</sec:authorize>
	 		<c:if test="${task.done==true}">
	 		<div class="col-md-4"><h3>Task completed !</h3></div>
	 		</c:if>
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
<hr>
<c:if test="${task.done==false}">
<c:if test="${userId == task.customer.user.id || isImPerformer==true}">
<div class="row">
	<div class="col-md-12">
	<div class="col-sm-offset-3 col-md-7 col-sm-12 col-xs-12">
		<form:form class="form-horizontal" action="/task/${task.id}"
			method="POST" modelAttribute="report">
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-7">
					<form:textarea class="form-control" path="text" id="text" rows="5"/>
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-7">
					<button type="submit" class="btn btn-success btn-block">Add report</button>
				</div>
			</div>
		</form:form>
	</div>
	</div>
</div>
</c:if>
</c:if>
<c:if test="${userId == task.customer.user.id || isImPerformer==true}">
<div class="row">
	<div class="col-md-12">
	<c:forEach items="${reports}" var="report">
		<div class="row post">
			<div class="col-md-9"><p>Author: <a href="/user/${report.user.id}">${report.user.name} ${report.user.surname}</a></p></div>
			<div class="col-md-3"><p class="infoRowDate">${report.date}</p></div>
			<div class="col-md-10 postText"><p>${report.text}</p></div>
			<c:if test="${report.user.id==userId || userId == task.customer.user.id}">
			<div class="col-md-2 postButton">
				<a class="btn btn-danger btn-xs" href="/task/${task.id}/delete/${report.id}">delete</a>
			</div>
			</c:if>
		</div>
	</c:forEach>
	</div>
</div>
</c:if>