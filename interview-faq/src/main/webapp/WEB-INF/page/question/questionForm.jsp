<%--
	Copyright © 2012, Source Tree, All Rights Reserved
--%><%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglibs.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<link rel="stylesheet" type="text/css" media="screen"
	href="<c:url value="/static/styles/jHtmlArea.css"/>" />
<link rel="stylesheet" type="text/css" media="screen"
	href="<c:url value="/"/><s:theme code='chosenStyleSheet'/>" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><s:message code="questionForm.title"/></title>
</head>
<body>
	<form id="questionForm" name="questionForm" method="post" class="cleanform" action="<c:url value="/question/addNew"/>">
		<h2><s:message code="questionForm.title"/></h2>
		<p>
		<input type="text" id="question" maxlength="200" name="question" title="<s:message code="questionForm.question"/>" placeholder="<s:message code="questionForm.question"/>"/>
		<span id="error_question"></span></p>
		
		<p>
			<textarea name="answer" id=answer rows="5" cols="47" title="<s:message code="questionForm.answer"/>" placeholder="<s:message code="questionForm.answer"/>"></textarea>
		<span id="error_answer"></span></p>
		
		<p>
		<select name="categoryDTOs" id="categoryDTOs" class="chzn-select-deselect" multiple="multiple" data-placeholder="Add some tags">
			<c:forEach items="${categories}" var="categories">
				<option value="${categories.id}">${categories.categoryName}</option>
			</c:forEach>
		</select>
		<span id="error_category"></span></p>
		
		<%-- <p><label id="label_category" for="category"></label>
		<select name="cat" id="cat" class="chzn-select" data-placeholder="Choose an option">
			<option value=""></option>
			<option value="abcd">ABCD</option>
			<option value="abcd1">ABCD</option>
			<option value="abcd2">ABCD</option>
		</select>
		<span id="error_category"></span></p> --%>
		
		<p><label for="submit"></label>
		<button type="submit" id="btnSubmit" name="btnSubmit"><s:message code="question.add.btn"/></button>
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

function questionFormResponse(data) {
	var status = data.status;
	if (status == "SUCCESS") {
		alert("<s:message code="question.add.success"/>");
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
		success : questionFormResponse,
		beforeSend : showLoader,
		complete : hideLoader,
		dataType : 'json',
		error : gotError
	};

	$("#questionForm").ajaxForm(options);
	cachedScript("<c:url value="/static/scripts/jquery/jHtmlArea-0.7.5.min.js"/>").done(function(){
		$('#answer').htmlarea({
		    toolbar: [
		              ["bold", "italic", "underline"],
		              ["increasefontsize", "decreasefontsize"],
		              ["orderedlist", "unorderedlist"],
		              ["indent", "outdent"],
		              ["justifyleft", "justifycenter", "justifyright"],
		              ["link", "unlink", "image"],
		              ["p"]]
		});
	});
	cachedScript("<c:url value="/static/scripts/jquery/chosen.jquery.min.js"/>").done(function(){
			$(".chzn-select").chosen();
			<%--$(".chzn-select-deselect").chosen({allow_single_deselect:true});--%>
		});
	});
</script>
</body>
</html>