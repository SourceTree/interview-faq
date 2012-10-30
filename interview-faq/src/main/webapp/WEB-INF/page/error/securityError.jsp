<%--

	Copyright © 2012, Source Tree, All Rights Reserved

--%><%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglibs.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Illegal Activity</title>
</head>
<body>
	<div class="accordion">
		<h1>We're sorry...</h1>

<br />
		<div align="left">
			We have detected illegal character(s) within the request, which are
			either <font class="requiredField"> * &#36; &lt; &gt; &#124;
				&quot;</font> or <font class="requiredField"> &#94; </font>.<br /> To
			protect the server against from any illegal activity, the request has
			been rejected. <br />
			<br />Thank you.
			<br /><br />
		</div>
	</div>
</body>
</html>