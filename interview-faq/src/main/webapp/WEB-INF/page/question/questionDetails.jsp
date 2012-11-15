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
			<label for="submit"></label>
			<button type="button" id="btnBack" name="btnBack">
				<s:message code="btn.back" />
			</button>
		</p>
</body>
</html>