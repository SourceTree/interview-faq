<%--
	Copyright © 2012, Source Tree, All Rights Reserved
--%><%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><s:message code="categoryForm.title"/></title>
</head>
<body>
<form id="categoryEdit" name="categoryEdit" method="post" class="cleanform" action="<c:url value="/category/categoryEdit"/>">
<h2><s:message code="categoryForm.title"/></h2>
	<input name="id" id="id" type="hidden" value="${category.id}"/>
	<input name="categoryName" type="hidden" value="${category.categoryName}"/>
	<p>
		${category.categoryName}
	</p>
	<p>
	<textarea name="categoryDescription" id=categoryDescription rows="5" cols="47"
				title="<s:message code="categoryForm.description"/>"
				placeholder="<s:message code="categoryForm.description"/>">${category.categoryDescription}</textarea>
		<span id="error_categoryDescription"></span></p>
	<p><label for="submit"></label>
		<button type="submit" id="btnSubmit" name="btnSubmit"><s:message code="categoryForm.btn.save"/></button>
		<button type="button" id="btnBack" name="btnBack" onclick=""><s:message code="btn.back"/></button>
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

function categoryEditResponse(data) {
	var status = data.status;
	if (status == "SUCCESS") {
		alert("<s:message code="categoryForm.catEdit.success"/>");
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
		success : categoryEditResponse,
		beforeSend : showLoader,
		complete : hideLoader,
		dataType : 'json',
		error : gotError
	};

	$("#categoryEdit").ajaxForm(options);
});
</script>
</body>
</html>