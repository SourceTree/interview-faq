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
		action="<c:url value="ab"/>" method="post">
		<div class="search">
			<input type="text" class="search_big" title="Search"
				placeholder="Search" />
			<button type="submit" name="searchBtn" id="searchBtn">Search</button>
		</div>
		<br />
	</form>
	<c:choose>
		<c:when test="${!empty childCategories}">
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
			<div class="col_75 col_border">
		</c:when>
		<c:otherwise>
			<div class="col_border">
		</c:otherwise>
	</c:choose>
	<div>
		<h2>${categoryDTO.categoryDisplayName}</h2>
	</div>
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
				<strong class="question_hightlight"><s:message
						code="catergoryQuestion.notAvailable" /> -
					${categoryDTO.categoryDisplayName}</strong>
			</div>
		</c:otherwise>
	</c:choose>

	</div>


</body>
</html>