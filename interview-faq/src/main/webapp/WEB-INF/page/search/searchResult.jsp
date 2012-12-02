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
		action="<c:url value="/search/1"/>" method="GET">
		<div class="search">
			<input type="text" class="search_big" title="Search" id="searchValue" name="searchValue"
				placeholder="Search" value="${searchValue}" />	
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
		<paging:paginate listProp="${questions.listProp}" urlPrefix="<c:url value="/search/"/>" searchValue="${searchValue}"/>
		</c:when>
		<c:otherwise>
			<div>
				<strong class="question_hightlight">No Results Found!</strong>
			</div>
		</c:otherwise>
		</c:choose>
</div>
</body>
</html>