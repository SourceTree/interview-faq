<%--
	Copyright © 2012, Source Tree, All Rights Reserved
--%><%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><s:message code="loginForm.title" /></title>
</head>
<body>
	<c:choose>
		<c:when test="${not empty sessionScope.sess_vars}">
			<c:redirect url="/" />
		</c:when>
		<c:otherwise>
		<div class="col_25">&nbsp;</div>
		<div class="col_75">
				<form id="loginForm" name="loginForm" method="post"
					class="cleanform" action="<c:url value="/admin/login"/>">
					<h2><s:message code="loginForm.title"/></h2>
						<c:if test="${not empty errorMsg}">
							<ul id="general_error">
								<li><label>&nbsp;</label><span class="warning"><c:out
											value="${errorMsg}" /></span></li>
							</ul>
						</c:if>
						<input name="clientType" id="clientType" type="hidden"
							value="internal">
						<p>
							<input type="text" id="email" maxlength="220" name="email"
								value="<c:if test="${not empty login}"><c:out value="${login.email}"/></c:if>" placeholder="<s:message
										code="loginForm.email" />"/>
								<c:if test="${not empty login && not empty login.errors}">
									<span class="warning"><c:out
											value="${login.errors['email']}" /></span>
								</c:if></p>

							<p><input type="password" id="password" maxlength="80" name="password" placeholder="<s:message
										code="loginForm.password" />"/>
								<c:if test="${not empty login && not empty login.errors}">
									<span class="warning"><c:out
											value="${login.errors['password']}" /></span>
								</c:if></p>
							<p>
								<button type="submit" id="btnSubmit" name="btnSubmit">
									<s:message code="loginForm.btn.login" />
								</button>
								<button type="button" id="btnReset" name="btnReset"
									onclick="resetLoginForm(this.form);">
									<s:message code="loginForm.btn.reset" />
								</button></p>
				</form>
			</div>
		</c:otherwise>
	</c:choose>
	<script type="text/javascript">
	function resetLoginForm(loginForm)
	{
		$('.warning').remove();
		loginForm.reset();
		$('#general_error').remove();
		$('#email').val('');
	}
	</script>
</body>
</html>