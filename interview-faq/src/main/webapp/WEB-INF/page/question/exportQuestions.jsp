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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Download Questions</title>
</head>
<body>
	<form id="questionForm" name="questionForm" method="post"
		class="cleanform" action="<c:url value="/exportToExcel/"/>">

		<h2>
			<strong>Download Questions</strong>
		</h2>
		<p>
			<select name="category" id="category"
				class="chzn-select"
				data-placeholder="Select a category">
				<option value=""></option>
				<c:forEach items="${categories}" var="categories">
					<option value="${categories.categoryName}">${categories.categoryDisplayName}</option>
				</c:forEach>
			</select>
		</p>

		<p>
			<label for="submit"></label>
			<button type="submit" id="btnSubmit" name="btnSubmit">
				Download
			</button>
		</p>
	</form>
	<script type="text/javascript">
		$(document).ready(function() {
			$('#questionForm').submit(function(){
				if(!$('#category').val() || $('#category').val().length <= 0){
					return false;
				} else {
					$('#exportForm').attr('action', '<c:url value ="/question/exportToExcel/"/>' + $('#category').val());
					$('#exportForm').submit();
					return false;
				}
			});

			cachedScript("<c:url value="/static/scripts/jquery/chosen.jquery.min.js"/>").done(function() {
				$(".chzn-select").chosen();
			});
		});
	</script>
	<form action="" method="GET" name="exportForm" id="exportForm">
		
	</form>
</body>
</html>