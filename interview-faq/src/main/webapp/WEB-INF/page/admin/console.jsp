<%--
	Copyright © 2012, Source Tree, All Rights Reserved
--%><%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Admin Console</title>
</head>
<body>
	<div class="col_50 col_border">
		<div class="mgmt_section">
			<a href="#"><strong><s:message code="console.categoryManagement.link"/></strong> </a>
			<br> 
			<em><s:message code="console.categoryManagement.description"/></em>
			
			<br /><br/>
			<a class="link_font12" href="<c:url value="/category/new"/>"><strong>Add New Category</strong></a><br/>
			<a class="link_font12" href="<c:url value="/category/list"/>"><strong>View All Categories</strong></a><br/>
			<a class="link_font12" href="<c:url value="/question/manageQuestionsByCategory"/>"><strong>Manage Questions (By Category)</strong></a>
		</div>
	</div>
	
	<div class="col_50 col_border">
		<div class="mgmt_section">
			<a href="#"><strong><s:message code="console.faqManagement.link"/></strong></a>
			<br> 
			<em><s:message code="console.faqManagement.description"/></em>
			<br /><br/>
			<a class="link_font12" href="<c:url value="/question/addNew"/>"><strong>Add New Question</strong></a><br/>
			<a class="link_font12" href="<c:url value="/question/export"/>"><strong>Download Questions</strong></a><br/>
			<a class="link_font12" href="<c:url value="/question/uploadExcel"/>"><strong>Upload Questions</strong></a><br/>
		</div>
	</div>
	<div class="clearfix"></div>
</body>
</html>