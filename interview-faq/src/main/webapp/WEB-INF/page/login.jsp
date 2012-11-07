<%--

	Copyright © 2012, Source Tree, All Rights Reserved

--%><%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglibs.jsp"%>
<!DOCTYPE HTML>
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
							<label id="label_email" for="email"><s:message
										code="loginForm.email" /><span class="required">*</span></label> <input
								type="text" id="email" maxlength="220" name="email"
								value="<c:if test="${not empty login}"><c:out value="${login.email}"/></c:if>" />
								<c:if test="${not empty login && not empty login.errors}">
									<span class="warning"><c:out
											value="${login.errors['email']}" /></span>
								</c:if></p>

							<p><label id="label_password" for="password"><s:message
										code="loginForm.password" /><span class="required">*</span></label> <input
								type="password" id="password" maxlength="80" name="password" />
								<c:if test="${not empty login && not empty login.errors}">
									<span class="warning"><c:out
											value="${login.errors['password']}" /></span>
								</c:if></p>
							
							<p><label id="label_password" for="select_chioce"><s:message
										code="loginForm.password" /><span class="required">*</span></label> 
								<select id="select_choice" name="select_chioce">
									<option value="123">Value1</option>
									<option value="2">Value 2</option>
								</select>
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