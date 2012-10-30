<%--

	Copyright © 2012, Source Tree, All Rights Reserved

--%><%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglibs.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate" /> <%-- HTTP 1.1 --%>
<meta http-equiv="Pragma" content="no-cache" /> <%-- HTTP 1.0 --%>
<meta http-equiv="Expires" content="0" /> <%-- Proxies --%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<c:set var="windowTitle" scope="page">
	<decorator:title />
</c:set>
<title><s:message code="window.title" arguments="${windowTitle}" /></title>
<meta name="viewport" content="width=device-width;initial-scale=1.0">
<meta http-equiv="content-Style-Type" content="text/css" />
<link rel="stylesheet" type="text/css" media="screen"
	href="<c:url value="/"/><s:theme code='styleSheet'/>" />
	<link rel="stylesheet" type="text/css" media="screen"
	href="<c:url value="/"/><s:theme code='menuStyleSheet'/>" />
<script src="<c:url value="/static/scripts/jquery/1.7.1/jquery.min.js"/>"
	type="text/javascript"></script>
<!--[if IE]><script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script><![endif]-->
<!--[if lte IE 7]> <script src="<c:url value="/static/scripts/IE8.js"/>" type="text/javascript"></script><![endif]-->

<decorator:head />
<meta name="author" content="Source Tree" />
<meta content="index, follow" name="robots" />
<meta name="description"
	content="Interview Questions" />
<meta name="keywords"
	content="" />
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
	<decorator:getProperty property="meta.nodefaultfocus"/>
</c:set>
<body
	<decorator:getProperty property="body.onload" writeEntireProperty="true"/>
	<decorator:getProperty property="body.onunload" writeEntireProperty="true"/>>

	<decorator:body />

	<script type="text/javascript">
		$(document).ready(function() { 
			$('input[type="text"]').each(function(){
				if(this.value == '') {
					if(!$(this).attr('title') == '') {
						this.value = $(this).attr('title');
					    $(this).addClass('text-label');	
					}	
				}
			 
			    $(this).focus(function(){
			        if(this.value == $(this).attr('title')) {
			            this.value = '';
			            $(this).removeClass('text-label');
			        }
			    });
			 
			    $(this).blur(function(){
			        if(this.value == '') {
			        	if(!$(this).attr('title') == '') {
			            	this.value = $(this).attr('title');
			            	$(this).addClass('text-label');
			        	}
			        }
			    });
			});
			
			$('input').attr("autocomplete", "off");
			
			<c:if test="${not defaultfocus}">
				$("input:visible:enabled:first").focus();
			</c:if>
			
		});

		function goHome(){
			window.location.href = "<c:url value="/trace/advancedTrace.htm"/>";
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