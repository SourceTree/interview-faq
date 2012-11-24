<%--
	Copyright © 2012, Source Tree, All Rights Reserved
--%><%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><s:message code="questionEdit.title" /></title>
</head>
<body>
	<form id="uploadQuestionExcel" name="uploadQuestionExcel" method="post"
		class="cleanform" enctype="multipart/form-data"
		action="<c:url value="/question/uploadExcel"/>">

		   <p>
			<input type="file" id="file" name="file"
				 /> <span
				id="error_uploadExcel"></span>
		</p>
		
		<p>
			<label for="submit"></label>
			<button type="submit" id="btnSubmit" name="btnSubmit">
				<s:message code="question.upload.btn" />
			</button>
			<button type="reset" id="btnReset" name="btnReset"
				onclick="$('.warning').remove();">
				<s:message code="btn.reset" />
			</button>
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
		alert("<s:message code="question.upload.success"/>");
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
		success : regFormResponse,
		beforeSend : showLoader,
		complete : hideLoader,
		dataType : 'json',
		error : gotError
	};

	$("#uploadQuestionExcel").ajaxForm(options);
});
</script>

</body>
</html>