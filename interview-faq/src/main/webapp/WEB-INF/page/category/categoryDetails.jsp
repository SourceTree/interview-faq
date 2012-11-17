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

	<div class="col_25 col_border">

		<c:set var="subCategory" value="${categoryDTO.categoryName}" />
		<c:choose>
			<c:when test="${!empty childCategories}">
				<c:forEach items="${childCategories}" var="childCategoryDTO">
					<c:choose>
						<c:when
							test="${childCategoryDTO.categoryName eq categoryDTO.categoryName}">
							<div>
								<strong class="question_hightlight">${childCategoryDTO.categoryName}</strong>
							</div>
						</c:when>
						<c:otherwise>
							<div>
								<a
									href="<c:url value="/category/"/>${parentCategoryName}/${childCategoryDTO.categoryName}"><strong>${childCategoryDTO.categoryName}</strong></a>
							</div>
						</c:otherwise>

					</c:choose>

				</c:forEach>
			</c:when>

			<c:otherwise>
				<div>
					<strong><s:message
							code="catergorySubCategory.notAvailable" /> -
						${parentCategoryName}</strong>
				</div>
			</c:otherwise>
		</c:choose>

	</div>
	<div class="col_75 col_border">
		<h2>${categoryDTO.categoryName}</h2>
		<c:choose>
			<c:when test="${!empty categoryDTO.questionDtos}">
				<c:forEach items="${categoryDTO.questionDtos}" var="questionDTO"
					varStatus="questionNum">
					<div>
						<strong class="question_hightlight">${questionDTO.question}</strong>
						<br> <strong><em>${questionDTO.answer}</em></strong>
					</div>
				</c:forEach>
			</c:when>

			<c:otherwise>
				<div>
					<strong><s:message code="catergoryQuestion.notAvailable" />
						- ${categoryDTO.categoryName}</strong>
				</div>
			</c:otherwise>
		</c:choose>

	</div>


</body>
</html>