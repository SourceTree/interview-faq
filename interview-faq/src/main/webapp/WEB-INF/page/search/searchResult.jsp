<%--
	Copyright © 2012, Source Tree, All Rights Reserved
--%><%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ include
	file="/WEB-INF/page/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<title>Search</title>
<body>
<form class="cleanform" id="search" name="search"
		action="<c:url value="/search/searchResult"/>" method="GET">
		<div class="search">
			<input type="text" class="search_big" title="Search" id="searchValue" name="searchValue"
				placeholder="Search" value="${searchValue}" /><span id="error_searchValue"></span>	
			<input type="hidden" id="categoryName" name="categoryName" <c:if test="${!empty categoryName}">value="${categoryName}"</c:if>>	
			<button type="submit" name="searchBtn" id="searchBtn">Search</button>
		</div>
		<br />		
</form>
<div id="search_list" class="col_border">
	<c:choose>
		<c:when test="${!empty questions && !empty questions.questionDTOs }">
		<c:forEach items="${questions.questionDTOs}" var="questionDTO">
				<div class="infinite_grid_item initial_load_item">
					<strong class="question_hightlight">${questionDTO.question}</strong><br/>
					${questionDTO.answer}<br />
					<span class="tags">
					<c:forEach items="${questionDTO.categoryDTOs}" var="categoryDTO">
						<span class="tag-name">${categoryDTO.categoryName}</span>
					</c:forEach>
					</span>
				</div>
		</c:forEach>
		<div class="pagination"></div>
		</c:when>
		<c:otherwise>
			<div>
				<strong class="question_hightlight">No Results Found!</strong>
			</div>
		</c:otherwise>
		</c:choose>
</div>
	<script type="text/javascript"
		src="<c:url value="/static/scripts/jquery/jquery.infinite-paging.js"/>"></script>
	<script type="text/javascript">
		var curPage = 1;
		function showLoader() {
			$(".grid_loading").fadeIn();
		}
		
		function hideLoader() {
			$(".grid_loading").fadeOut();
		}

		$(document).ready(function() {
			$(function() {
				$('.pagination').html('<span class="pagination_total">Total Records : ${questions.listProp.totalRecords} </span><a class="pager">LoadMore</a><span class="pagination_page">Page  ${questions.listProp.page} of ${questions.listProp.totalPages}</span>');
				
				$('#search_list').infinitePaging({
					'url' : '<c:url value="/search/searchResult"/>', 
					page: curPage,
					contentData:{"searchValue": "${searchValue}" <c:if test="${!empty categoryName}">,"categoryName" : "${categoryName}"</c:if>},
					'beforeLoad' : showLoader,
					'renderData': function (data){
						return renderData(data);
					},
					'afterLoad' : function(elementsLoaded) { // after loading content, you can use this function to animate your new elements
						hideLoader();
						$(elementsLoaded).fadeInWithDelay();
					}
				});
				
				<c:if test="${empty questions || empty questions.listProp || !empty questions && !empty questions.listProp && (questions.listProp.endIndex + 1) >= questions.listProp.totalRecords}">
					$('#search_list').stopInfinitePaging();
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
						htmlStr.push('<div class="infinite_grid_item"><strong class="question_hightlight">');
						htmlStr.push(questionDTO.question);
						htmlStr.push('</strong><br/>');
						htmlStr.push(questionDTO.answer);
						htmlStr.push('<br /><span class="tags">');
						$.each(questionDTO.categoryDTOs, function(i, categoryDTO){
							htmlStr.push('<span class="tag-name">');
							htmlStr.push(categoryDTO.categoryDisplayName);
							htmlStr.push('</span>');
						});
						htmlStr.push('</span></div>');	
					});
					
					return htmlStr.join('');
				};
			});
		});
	</script>

</body>
</html>