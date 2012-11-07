<%--

	Copyright © 2012, Source Tree, All Rights Reserved

--%><%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglibs.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><s:message code="categoryForm.title"/></title>

</head>
<body>
	<form id="categoryForm" name="categoryForm" method="post" class="cleanform" action="<c:url value="/category/new"/>">
		<h2><s:message code="categoryForm.title"/></h2>
		<input name="clientType" id="clientType" type="hidden" value="internal">
		<p><label id="label_categoryName" for="categoryName"><s:message code="categoryForm.name"/><span class="required">*</span></label>
		<input type="text" id="categoryName" maxlength="80" name="categoryName"/>
		<span id="error_categoryName"></span></p>
		
		<p><label id="label_categoryDescription" for="categoryDescription"><s:message code="categoryForm.description"/></label>
		<input type="text" id="categoryDescription" maxlength="150" name="categoryDescription"/>
		<span id="error_categoryDescription"></span></p>
		
		<p><label for="submit"></label>
		<button type="submit" id="btnSubmit" name="btnSubmit"><s:message code="categoryForm.btn.add"/></button>
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

function categoryFormResponse(data) {
	var status = data.status;
	if (status == "SUCCESS") {
		alert("<s:message code="categoryForm.catAdd.success"/>");
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
		success : categoryFormResponse,
		beforeSend : showLoader,
		complete : hideLoader,
		dataType : 'json',
		error : gotError
	};

	$("#categoryForm").ajaxForm(options);
});
</script>
</body>
</html>