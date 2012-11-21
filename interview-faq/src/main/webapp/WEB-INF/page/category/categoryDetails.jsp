<%--
	Copyright © 2012, Source Tree, All Rights Reserved
--%><%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ include
	file="/WEB-INF/page/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<title>${categoryDTO.categoryName}</title>
<body>
	<form class="cleanform" id="search" name="search"
		action="<c:url value="/category/searchQuestions"/>" method="GET">
		<div class="search">
			<input type="text" class="search_big" title="Search" id="searchValue" name="searchValue"
				placeholder="Search" value="${searchValue}" /><span id="error_searchValue"></span>	
			<input type="hidden" id="catId" name="catId" value="${categoryDTO.id}">	
			<button type="submit" name="searchBtn" id="searchBtn">Search</button>
		</div>
		<br />		
	</form>
		<c:if test="${!empty childCategories}">
			<div class="col_25 col_border">

				<c:forEach items="${childCategories}" var="childCategoryDTO">
					<c:choose>
						<c:when
							test="${childCategoryDTO.categoryName eq categoryDTO.categoryName}">
							<div>
								<strong class="question_hightlight">${childCategoryDTO.categoryDisplayName}</strong>
							</div>
						</c:when>
						<c:otherwise>
							<div>
								<a
									href="<c:url value="/category/"/>${parentCategoryName}/${childCategoryDTO.categoryName}"><strong>${childCategoryDTO.categoryDisplayName}</strong></a>
							</div>
						</c:otherwise>

					</c:choose>

				</c:forEach>
			</div>
		</c:if>
		<div>
			<h2>${categoryDTO.categoryDisplayName}</h2>
		</div>
		<c:if test="${!empty questionsList.questionDTOs}">
		<div align="right" style="margin-right: 30px">
			<a href="<c:url value="/question/exportToExcel/"/>${categoryDTO.categoryName}"><strong>Export ${categoryDTO.categoryDisplayName} Questions To Excel</strong></a>
		</div>
		</c:if>
		<div id="listing" class="<c:if test="${!empty childCategories}">col_75</c:if> col_border">
	<c:choose>
		<c:when test="${!empty questionsList.questionDTOs}">
			<c:forEach items="${questionsList.questionDTOs}" var="questionDTO">
				<div>
					<strong class="question_hightlight">${questionDTO.question}</strong>
					<br> <strong><em>${questionDTO.answer}</em></strong>
				</div>
			</c:forEach>
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
	<div class="pager">LoadMore</div>
	
	<script type="text/javascript" src="<c:url value="/static/scripts/jquery/jquery.infinite-paging.js"/>"></script>
	<script type="text/javascript">
	var curPage = 1;
	var searchVal = "";
	function showLoader() {
		$(document.activeElement).blur();
	}
	function hideLoader() {
	}
	 
	function resetDefaults()
	{
		$('#listing').empty();
		 curPage = 0;
	}
	
	$(document).ready(function() {
		$(function() {
			$('#listing').infinitePaging({
				'url' : '<c:url value="/category/searchQuestions"/>', 
				page: curPage,
				'contentData':{'categoryName': '${categoryDTO.categoryName}', 'searchVal' : searchVal},
				'beforeLoad' : showLoader,
				'renderData': function (data){
					return renderData(data);
				},
				'afterLoad' : function(elementsLoaded) { // after loading content, you can use this function to animate your new elements
					hideLoader();
					$(elementsLoaded).fadeInWithDelay();
				}
			});

			if((${questionsList.listProp.endIndex} + 1) >= ${questionsList.listProp.totalRecords}){
				$('#listing').stopInfinitePaging();
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
					htmlStr.push('<div><strong class="question_hightlight">');
					htmlStr.push(questionDTO.question);
					htmlStr.push('</strong><br> <strong><em>');
					htmlStr.push(questionDTO.answer);
					htmlStr.push('</em></strong></div>');
				});
				
				return htmlStr.join('');
			};
		});
	});
	</script>
</body>
</html>