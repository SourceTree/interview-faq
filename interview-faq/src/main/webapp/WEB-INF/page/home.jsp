<%--
	Copyright © 2012, Source Tree, All Rights Reserved
--%><%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ include
	file="/WEB-INF/page/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<title><s:message code="title.home" /></title>
<body>
	<form class="cleanform" id="search" name="search"
		action="<c:url value="ab"/>" method="post">
		<div class="search">
			<input type="text" class="search_big" title="Search"
				placeholder="Search" />
			<button type="submit" name="searchBtn" id="searchBtn">Search</button>
		</div>
		<br />		
	</form>
	<c:forEach items="${parentCategories.categoryDTOs}" var="categoryDTO">
			<div class="col_50 col_border">
				<div>
					<a href="<c:url value="/category/questions/"/>${categoryDTO.categoryName}"><strong>${categoryDTO.categoryName}</strong></a> <br>
					<em>${categoryDTO.categoryDescription}</em>
				</div>
			</div>
		</c:forEach>
	
</body>
</html>