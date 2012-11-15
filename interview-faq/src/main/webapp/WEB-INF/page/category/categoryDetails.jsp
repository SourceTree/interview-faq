<%--
	Copyright © 2012, Source Tree, All Rights Reserved
--%><%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><s:message code="categoryDetail.title"/> - ${category.categoryName}</title>
</head>
<body>
<form id="categoryDetails" name="categoryDetails" method="post" class="cleanform" action="<c:url value="/category/categoryEdit"/>">
<h2><s:message code="categoryDetail.title"/> - ${category.categoryName}</h2>
	<input name="id" id="id" type="hidden" value="${category.id}"/>
	<p>
		${category.categoryName}	
	</p>	
	<p>
		${category.categoryDescription}
	</p>
		
	<p><label for="submit"></label>
		<button type="button" id="btnBack" name="btnBack" onclick=""><s:message code="btn.back"/></button>
	</p>
</form>
</body>
</html>