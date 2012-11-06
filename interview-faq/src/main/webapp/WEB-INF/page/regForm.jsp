<%--

	Copyright © 2012, Source Tree, All Rights Reserved

--%><%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglibs.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><s:message code="registerForm.title"/></title>

</head>
<body>
	<form id="regForm" name="regForm" method="post" class="cleanform" action="<c:url value="/register/new"/>">
		<h2><s:message code="registerForm.title"/></h2>
		<input name="clientType" id="clientType" type="hidden" value="internal">
		<p><label id="label_name" for="name"><s:message code="registerForm.fullname"/><span class="required">*</span></label>
		<input type="text" id="name" maxlength="80" name="name" title="<s:message code="registerForm.fullname"/>"/>
		<span id="error_name"></span></p>
		
		<p><label id="label_email" for="email"><s:message code="registerForm.email"/><span class="required">*</span></label>
		<input type="text" id="email" maxlength="220" name="emails" title="<s:message code="registerForm.email"/>"/>
		<span id="error_email"></span></p>
		
		<p><label id="label_password" for="password"><s:message code="registerForm.password"/><span class="required">*</span></label>
		<input type="password" id="password" maxlength="80" name="password"/>
		<span id="error_password"></span></p>
		
		<p><label id="label_confirmPassword" for="confirmPassword"><s:message code="registerForm.confirm.password"/><span class="required">*</span></label>
		<input type="password" id="confirmPassword" name="confirmPassword" maxlength="80"/>
		<span id="error_confirmPassword"></span></p>
		
		<p><label for="submit"></label>
		<button type="submit" id="btnSubmit" name="btnSubmit"><s:message code="registerForm.btn.register"/></button>
		<button type="reset" id="btnReset" name="btnReset" onclick="$('.warning').remove();"><s:message code="btn.reset"/></button>
		</p>
	</form>
<script src="<c:url value="/static/scripts/jquery/jquery.form.js"/>"
	type="text/javascript"></script>

<script type="text/javascript">
function showLoader() {
	$('.warning').remove();
	$("#btnSubmit").after('<span class="loading"><s:message code="form.processing"/></span>');
	$("#btnSubmit").hide();
	$("#btnReset").hide();
	$(document.activeElement).blur();
}
function hideLoader() {
	$(".loading").hide();
	$("#btnSubmit").show();
	$("#btnReset").show();
}

function regFormResponse(data) {
	var status = data.status;
	if (status == "SUCCESS") {
		alert("<s:message code="registerForm.userAdd.success"/>");
		location.href="<c:url value="/"/>";
	} else {
		var errors = data.errors;
		
		for(var key in errors){
			$('#error_' + key).append('<span class="warning">'+ errors[key] + '</span>');
		}
		$('.warning').show();
	}
}

$(document).ready(function() {
	var options = {
		//beforeSubmit:validateRegForm,
		success : regFormResponse,
		beforeSend : showLoader,
		complete : hideLoader,
		dataType : 'json',
		error : gotError
	};

	$("#regForm").ajaxForm(options);
});
</script>
</body>
</html>