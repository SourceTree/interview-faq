<%--
	Copyright © 2012, Source Tree, All Rights Reserved
--%><%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ include
	file="/WEB-INF/page/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<title>${categoryDTO.categoryName}</title>
<body>
<c:forEach items="${questions}" var="questionDTO">
				<div>
					${questionDTO.question}
					<br> <strong><em>${questionDTO.answer}</em></strong>
				</div>
</c:forEach>

</body>
</html>