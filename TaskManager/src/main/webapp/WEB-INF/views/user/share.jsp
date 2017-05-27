<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<sec:authentication property="principal.id" var="userId"/>
<c:if test="${userId == task.customer.user.id}">
<div class="row"><h3 class="text-center">Share task To this email</h3></div>
<div class="row">
	<div class="col-md-offset-3 col-md-7 col-sm-12 col-xs-12">
		<form:form class="form-horizontal" action="/user/${users.id}/tasks/share/${task.id}" method="POST">
		<div class="form-group">
    		<div class="col-sm-12">
				<input type="text" name="email" class="form-control"/>
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-7">
				<button type="submit" class="btn btn-success btn-block">Share</button>
			</div>
		</div>
		</form:form>
	</div>
</div>
</c:if>
<c:if test="${userId != task.customer.user.id}">
<div class="row"><h3 class="text-center">You can't share this task !</h3></div>
</c:if>