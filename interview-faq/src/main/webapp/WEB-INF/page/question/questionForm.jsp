<%--
	Copyright © 2012, Source Tree, All Rights Reserved
--%><%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" media="screen"
	href="<c:url value="/"/><s:theme code='chosenStyleSheet'/>" />
<link rel="stylesheet"
	href="<c:url value="/static/styles/wysiwyg.css"/>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><s:message code="questionForm.title" /></title>
</head>
<body>
	<form id="questionForm" name="questionForm" method="post"
		class="cleanform" action="<c:url value="/question/addNew"/>">

		<h2>
			<strong><s:message code="questionForm.title" /></strong>
		</h2>
		<p>
			<input type="text" id="question" maxlength="200" name="question"
				title="<s:message code="questionForm.question"/>"
				placeholder="<s:message code="questionForm.question"/>" /> <span
				id="error_question"></span>
		</p>


		<div id="wysihtml5-editor-toolbar">
			<header class="img_header">
				<ul class="commands">
					<li data-wysihtml5-command="bold" title="Make text bold (CTRL + B)"
						class="command" href="javascript:;" unselectable="on"
						style="border-left: 0px"></li>
					<li data-wysihtml5-command="italic"
						title="Make text italic (CTRL + I)" class="command"
						href="javascript:;" unselectable="on"></li>
					<li data-wysihtml5-command="insertUnorderedList"
						title="Insert an unordered list" class="command"
						href="javascript:;" unselectable="on"></li>
					<li data-wysihtml5-command="insertOrderedList"
						title="Insert an ordered list" class="command" href="javascript:;"
						unselectable="on"></li>
					<li data-wysihtml5-command="createLink" title="Insert a link"
						class="command" href="javascript:;" unselectable="on"></li>
					<li data-wysihtml5-command="insertImage" title="Insert an image"
						class="command" href="javascript:;" unselectable="on"></li>
					<li data-wysihtml5-command="formatBlock"
						data-wysihtml5-command-value="h1" title="Insert headline 1"
						class="command wysihtml5-command-active" href="javascript:;"
						unselectable="on"></li>
					<li data-wysihtml5-command="formatBlock"
						data-wysihtml5-command-value="h2" title="Insert headline 2"
						class="command" href="javascript:;" unselectable="on"></li>
					<li data-wysihtml5-command-group="foreColor" class="fore-color"
						title="Color the selected text">
						<ul>
							<li data-wysihtml5-command="foreColor"
								data-wysihtml5-command-value="black" href="javascript:;"
								unselectable="on"></li>
							<li data-wysihtml5-command="foreColor"
								data-wysihtml5-command-value="silver" href="javascript:;"
								unselectable="on"></li>
							<li data-wysihtml5-command="foreColor"
								data-wysihtml5-command-value="gray" href="javascript:;"
								unselectable="on"></li>
							<li data-wysihtml5-command="foreColor"
								data-wysihtml5-command-value="maroon" href="javascript:;"
								unselectable="on"></li>
							<li data-wysihtml5-command="foreColor"
								data-wysihtml5-command-value="red" href="javascript:;"
								unselectable="on"></li>
							<li data-wysihtml5-command="foreColor"
								data-wysihtml5-command-value="purple" href="javascript:;"
								unselectable="on"></li>
							<li data-wysihtml5-command="foreColor"
								data-wysihtml5-command-value="green" href="javascript:;"
								unselectable="on"></li>
							<li data-wysihtml5-command="foreColor"
								data-wysihtml5-command-value="olive" href="javascript:;"
								unselectable="on"></li>
							<li data-wysihtml5-command="foreColor"
								data-wysihtml5-command-value="navy" href="javascript:;"
								unselectable="on"></li>
							<li data-wysihtml5-command="foreColor"
								data-wysihtml5-command-value="blue" href="javascript:;"
								unselectable="on"></li>
						</ul>
					</li>
				</ul>
			</header>
			<div data-wysihtml5-dialog="createLink" style="display: none;">
				<label> Link: <input data-wysihtml5-dialog-field="href"
					value="http://">
				</label> <a data-wysihtml5-dialog-action="save">OK</a>&nbsp;<a
					data-wysihtml5-dialog-action="cancel">Cancel</a>
			</div>

			<div data-wysihtml5-dialog="insertImage" style="display: none;">
				<label> Image: <input data-wysihtml5-dialog-field="src"
					value="http://">
				</label> <a data-wysihtml5-dialog-action="save">OK</a>&nbsp;<a
					data-wysihtml5-dialog-action="cancel">Cancel</a>
			</div>
		</div>
		<p>
			<textarea name="answer" id=answer rows="5" cols="47"
				title="<s:message code="questionForm.answer"/>"
				placeholder="<s:message code="questionForm.answer"/>"></textarea>
			<span id="error_answer"></span>
		</p>

		<p>
			<select name="categoryDTOs" id="categoryDTOs"
				class="chzn-select-deselect" multiple="multiple"
				data-placeholder="Add some tags">
				<c:forEach items="${categories}" var="categories">
					<option value="${categories.id}">${categories.categoryName}</option>
				</c:forEach>
			</select> <span id="error_category"></span>
		</p>

		<%-- <p><label id="label_category" for="category"></label>
		<select name="cat" id="cat" class="chzn-select" data-placeholder="Choose an option">
			<option value=""></option>
			<option value="abcd">ABCD</option>
			<option value="abcd1">ABCD</option>
			<option value="abcd2">ABCD</option>
		</select>
		<span id="error_category"></span></p> --%>

		<p>
			<label for="submit"></label>
			<button type="submit" id="btnSubmit" name="btnSubmit">
				<s:message code="question.add.btn" />
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
			$("#btnSubmit")
					.after(
							'<span class="loading"><s:message code="form.processing"/></span>');
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
				location.href = "<c:url value="/"/>";
			} else {
				var errors = data.errors;

				for ( var key in errors) {
					$('#error_' + key).append(
							'<span class="warning">' + errors[key] + '</span>');
				}
				$('.warning').show();
			}
		}

		$(document)
				.ready(
						function() {
							var options = {
								//beforeSubmit:validateRegForm,
								success : questionFormResponse,
								beforeSend : showLoader,
								complete : hideLoader,
								dataType : 'json',
								error : gotError
							};

							$("#questionForm").ajaxForm(options);

							cachedScript(
									"<c:url value="/static/scripts/jquery/chosen.jquery.min.js"/>")
									.done(function() {
	<%--$(".chzn-select").chosen();--%>
		$(".chzn-select-deselect").chosen({
											allow_single_deselect : true
										});
									});
						});
	</script>
	<script src="<c:url value="/static/scripts/jquery/advanced.js"/>"></script>
	<script
		src="<c:url value="/static/scripts/jquery/wysihtml5-0.3.0.min.js"/>"></script>
	<script>
		var editor = new wysihtml5.Editor("answer", {
			toolbar : "wysihtml5-editor-toolbar",
			stylesheets : [ "<c:url value="/static/styles/wysiwyg.css"/>" ],
			parserRules : wysihtml5ParserRules
		});

		editor.on("load", function() {
			var composer = editor.composer, h1 = editor.composer.element
					.querySelector("h1");
			if (h1) {
				composer.selection.selectNode(h1);
			}
		});
	</script>
</body>
</html>