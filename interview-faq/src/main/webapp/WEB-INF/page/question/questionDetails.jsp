<%--
	Copyright © 2012, Source Tree, All Rights Reserved
--%><%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><s:message code="questionDetails.title" /></title>
</head>
<body>
	<form id="questionDetails" name="questionDetails" method="post"
		class="cleanform" action="<c:url value="/question/details"/>">
		<h2>
			<strong><s:message code="questionDetails.title" /></strong>
		</h2>
		<p>
			<input type="hidden" id="id" name="id" value="${question.id}"/>
			${question.question}
		</p>		
			
		<p>
			${question.answer}
		</p>
		<p>
		<c:forEach items="${question.categoryDTOs}" var="category">
			<a href="<c:url value="../category/${category.categoryName}"/>">${category.categoryName}</a>&nbsp;&nbsp;			
		</c:forEach>
		</p>

		<p>
			<label for="submit"></label>
			<button type="button" id="btnBack" name="btnBack">
				<s:message code="btn.back" />
			</button>
		</p>
		</form>
</body>
</html>