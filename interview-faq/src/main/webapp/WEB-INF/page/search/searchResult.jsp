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
			<input type="hidden" id="catId" name="catId" <c:if test="${categoryId!=null}">value="${categoryId}"</c:if>>	
			<button type="submit" name="searchBtn" id="searchBtn">Search</button>
		</div>
		<br />		
</form>
<ul id="search_list" class="infinite_grid">
		<c:forEach items="${questions.questionDTOs}" var="questionDTO">
			<li class="infinite_grid_item initial_load_item">
				<div>
					<strong class="question_hightlight">${questionDTO.question}</strong><br/>
					<em>${questionDTO.answer}</em>
					<c:if test="${not empty questionsDTO.categoryDTOs}">
						<c:forEach items="${questionsDTO.categoryDTOs}" var="categoryDTO">
							<span class="tag-name">${categoryDTO.categoryName}</span>
						</c:forEach>
						<div class="tags"></div>
					</c:if>
				</div>
			</li>
		</c:forEach>
	</ul>
	<div class="pager">LoadMore</div>

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
				$('#search_list').infinitePaging({
					'url' : '<c:url value="/search/searchResult"/>', 
					page: curPage,
					contentData:{"searchValue": "${searchValue}", "catId" : "${categoryId}"},
					'beforeLoad' : showLoader,
					'renderData': function (data){
						return renderData(data);
					},
					'afterLoad' : function(elementsLoaded) { // after loading content, you can use this function to animate your new elements
						hideLoader();
						$(elementsLoaded).fadeInWithDelay();
					}
				});

				if((${questions.listProp.endIndex} + 1) >= ${questions.listProp.totalRecords}){
					$('#search_list').stopInfinitePaging();
					$('.pager').remove();
				}
				
				// code for fade in element by element
				$.fn.fadeInWithDelay = function() {
					var delay = 0;
					return this.each(function() {
						$(this).delay(delay).animate({
							opacity : 1
						}, 200);
						delay += 100;
					});
				};
				
				renderData = function(data) {
					var htmlStr = [];
					$.each(data.questionDTOs, function(i, questionDTO){
						htmlStr.push('<li class="infinite_grid_item">');
						htmlStr.push('<div><strong class="question_hightlight">');
						htmlStr.push(questionDTO.question);
						htmlStr.push('</strong><br/><em>');
						htmlStr.push(questionDTO.answer);
						htmlStr.push('</em></div>');
						htmlStr.push('</li>');	
					});
					
					return htmlStr.join('');
				};
			});
		});
	</script>

</body>
</html>