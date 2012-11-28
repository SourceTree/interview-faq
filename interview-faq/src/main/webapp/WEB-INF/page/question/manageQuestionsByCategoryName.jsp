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
<link rel="stylesheet" type="text/css" media="screen"
	href="<c:url value="/"/><s:theme code='iconStyleSheet'/>" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="sidemenu" content="categorySideMenu">
<title>Manage Questions (By Category)</title>
</head>
<body>
	<form id="questionForm" name="questionForm" method="post"
		class="cleanform" action="<c:url value="/manageQuestions"/>">

		<h2>
			<strong>Manage Questions (By Category)</strong>
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
			<button type="submit" id="btnSubmit" name="btnSubmit">
				Search
			</button>
		</p>
	</form>
	<c:if test="${!empty categoryDTO}">
		<div id="listing" class="col_border">
		<h3 align="center">Manage Questions On - ${categoryDTO.categoryDisplayName}</h3>
		<c:choose>
			<c:when test="${!empty questionsList.questionDTOs}">
				<c:forEach items="${questionsList.questionDTOs}" var="questionDTO">
					<div  class="infinite_grid_item initial_load_item">
						<strong class="question_hightlight">${questionDTO.question}</strong>
						<br/>${questionDTO.answer}<br/>
						<div class="questionsSeperator" >
							<span class="tags">
								<c:forEach items="${questionDTO.categoryDTOs}" var="categoryDTO">
									<span class="tag-name">${categoryDTO.categoryDisplayName}</span>
								</c:forEach>
							</span>
							<span class="pagination_page edit_viewLinkPlacement">
								<a href="<c:url value="/question/edit/"/>${questionDTO.id}"><strong>Edit</strong></a> 
								<%--<a href="<c:url value="/question/view/"/>${questionDTO.id}"><strong>View Details</strong></a>--%>
							</span>
						</div>
					</div>
				</c:forEach>
				
				<div class="pagination"></div>
			</c:when>
			<c:otherwise>
				<div>
					<strong class="question_hightlight"><s:message
						code="catergoryQuestion.notAvailable" /> -
					${categoryDTO.categoryDisplayName}</strong>
				</div>
			</c:otherwise>
		</c:choose>	
		</div>
	</c:if>
	<script type="text/javascript">
		$(document).ready(function() {
			$('#questionForm').submit(function(){
				if(!$('#category').val() || $('#category').val().length <= 0){
					return false;
				} else {
					$('#manageQuestionsForm').attr('action', '<c:url value ="/question/manageQuestions/"/>' + $('#category').val());
					$('#manageQuestionsForm').submit();
					return false;
				}
			});

			$('#category').val('${categoryDTO.categoryName}');
			
			cachedScript("<c:url value="/static/scripts/jquery/chosen.jquery.min.js"/>").done(function() {
				$(".chzn-select").chosen();
			});
		});
	</script>
	<script type="text/javascript" src="<c:url value="/static/scripts/jquery/jquery.infinite-paging.js"/>"></script>
	<script type="text/javascript">
	var curPage = 1;
	var searcVal = "";
	var pagingObj = null;
	function showLoader() {
		$(document.activeElement).blur();
	}
	function hideLoader() {
	}
	 	
	$(document).ready(function() {
		
		
		$(function() {
			
			$('.pagination').html('<span class="pagination_total">Total Records : ${questionsList.listProp.totalRecords} </span><a class="pager">LoadMore</a><span class="pagination_page">Page  ${questionsList.listProp.page} of ${questionsList.listProp.totalPages}</span>');
			
			$('#listing').infinitePaging({
				'url' : '<c:url value="/question/manageQuestions/${categoryDTO.categoryName}"/>', 
				page: curPage,
				'beforeLoad' : showLoader,
				'renderData': function (data){
					return renderData(data);
				},
				'afterLoad' : function(elementsLoaded) { // after loading content, you can use this function to animate your new elements
					hideLoader();
					$(elementsLoaded).fadeInWithDelay();
				}
			});
			
			<c:if test="${empty questionsList || empty questionsList.listProp || !empty questionsList && !empty questionsList.listProp && (questionsList.listProp.endIndex + 1) >= questionsList.listProp.totalRecords}">
				$('#listing').stopInfinitePaging();
				$('.pager').remove();
			</c:if>
			
			// code for fade in element by element
			$.fn.fadeInWithDelay = function() {
				var delay = 0;
				return this.each(function() {
					$(this).delay(delay).animate({
						opacity : 1
					}, 200);
					delay += 100;
					$(this).attr('rel', 'loaded');
				});
			};
			
			renderData = function(data) {
				var htmlStr = [];
				$.each(data.questionDTOs, function(i, questionDTO){
					htmlStr.push('<div  class="infinite_grid_item"><strong class="question_hightlight">');
					htmlStr.push(questionDTO.question);
					htmlStr.push('</strong><br>');
					htmlStr.push(questionDTO.answer+'<br>');					
					htmlStr.push('<div  class="questionsSeperator">');
					htmlStr.push('<span class="tags">');
					$.each(questionDTO.categoryDTOs, function(i, categoryDTO){
						htmlStr.push('<span class="tag-name">'+categoryDTO.categoryDisplayName);
						htmlStr.push('</span>');
					});
					htmlStr.push('</span>');
					htmlStr.push('<span class="pagination_page edit_viewLinkPlacement">');
					htmlStr.push('<a id="editIcon" class="edit" href="<c:url value="/question/edit/"/>'+questionDTO.id+'">Edit</a> ');
					//htmlStr.push('<a href="<c:url value="/question/view/"/>'+questionDTO.id+'"><strong>View Details</strong></a>');
					htmlStr.push('</span>');
					htmlStr.push('</div>');
					htmlStr.push('</div> ');
				
				});
				
				return htmlStr.join('');
			};
		});
	});
	</script>
	<form action="" method="GET" name="manageQuestionsForm" id="manageQuestionsForm">
		
	</form>
</body>
</html>