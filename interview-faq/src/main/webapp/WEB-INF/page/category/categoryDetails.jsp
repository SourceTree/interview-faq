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
		action="<c:url value="/category/${categoryDTO.categoryName}/1"/>" method="GET">
		<div class="search">
			<input type="text" class="search_big" title="Search" id="searchValue" name="searchValue"
				placeholder="Search ${categoryDTO.categoryDisplayName}" value="${searchValue}" /><span id="error_searchValue"></span>	
			<button type="submit" name="searchBtn" id="searchBtn">Search</button>
		</div>
		<br />		
	</form>
	<div>
			<h2>${categoryDTO.categoryDisplayName}</h2>
	</div>
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
									href="<c:url value="/category/"/>${childCategoryDTO.categoryName}"><strong>${childCategoryDTO.categoryDisplayName}</strong></a>
							</div>
						</c:otherwise>

					</c:choose>

				</c:forEach>
			</div>
		</c:if>
		<div id="listing" class="<c:if test="${!empty childCategories}">col_75</c:if> col_border">
	<c:choose>
		<c:when test="${!empty questionsList.questionDTOs}">
			<c:forEach items="${questionsList.questionDTOs}" var="questionDTO">
				<div  class="infinite_grid_item initial_load_item">
					<strong class="question_hightlight">${questionDTO.question}</strong>
					<br>${questionDTO.answer}
				</div>
			</c:forEach>
			 <c:url var="itemUrl" value="/category/${categoryDTO.categoryName}/"/>
			<paging:paginate listProp="${questionsList.listProp}" urlPrefix="${itemUrl}" searchValue="${searchValue}"/>
		
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
	<script type="text/javascript">
		$(document).ready(function() {
			$('#search').submit(function(){
				$(document.activeElement).blur();
				
				if(!$('#searchValue').val() || $('#searchValue').val().length() < 0){
					return false;
				}
				else{
					return true;
				}
			});
		});
	</script>
</body>
</html>