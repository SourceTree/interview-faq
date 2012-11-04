<%--

	Copyright © 2012, Source Tree, All Rights Reserved

--%><%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglibs.jsp"%>
<!DOCTYPE HTML>
<!--[if IE 6]><html lang="en" class="no-js ie6"><![endif]-->
<!--[if (gt IE 6)|!(IE)]><!-->
<html lang="en" class="no-js">
<!--<![endif]-->
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width,initial-scale=1">
<c:set property="title">
	<decorator:title />
</c:set>
<title><s:message code="window.title"
		arguments="${title}" /></title>
<%-- HTTP 1.1 --%>
<meta http-equiv="Cache-Control"
	content="no-cache, no-store, must-revalidate" />
<%-- HTTP 1.0 --%>
<meta http-equiv="Pragma" content="no-cache" />
<%-- Proxies --%>
<meta http-equiv="Expires" content="0" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="content-Style-Type" content="text/css" />
<!--[if lt IE 9]><script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script><![endif]-->
<link rel="stylesheet" type="text/css" media="screen"
	href="<c:url value="/"/><s:theme code='styleSheet'/>" />
<link rel="stylesheet" type="text/css" media="screen"
	href="<c:url value="/"/><s:theme code='menuStyleSheet'/>" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<%-- <script src="<c:url value="/static/scripts/jquery/1.7.1/jquery.min.js"/>"
	type="text/javascript"></script>--%>
<decorator:head />
<meta name="author" content="Source Tree" />
<meta content="index, follow" name="robots" />
<meta name="description" content="Interview Questions" />
<meta name="keywords" content="" />
<script type="text/javascript">
	cachedScript = function(url, options) {
		options = $.extend(options || {}, {
			dataType : 'script',
			cache : true,
			async : true,
			url : url
		});
		return $.ajax(options);
	};
</script>
</head>
<c:set var="defaultfocus" scope="page">
	<decorator:getProperty property="meta.nodefaultfocus" />
</c:set>
<body
	<decorator:getProperty property="body.onload" writeEntireProperty="true"/>
	<decorator:getProperty property="body.onunload" writeEntireProperty="true"/>>
	<decorator:body />
	<script type="text/javascript">
		$(document).ready(function() {
			$('input').attr("autocomplete", "off");

			<c:if test="${not defaultfocus}">
			$("input:visible:enabled:first").focus();
			</c:if>
		});

		function goHome() {
			window.location.href = "<c:url value="/"/>";
		}

		function gotError(jqXHR, textStatus, errorThrown) {
			if (jqXHR.status == 500) {
				alert("Unexpected error occured. Please try again later.");
			} else if (jqXHR.status == 999) {
				alert("Illegal character(s) found within the request. To protect the server against from any illegal activity, the request has been rejected.");
			}
		}
	</script>
</body>
</html>