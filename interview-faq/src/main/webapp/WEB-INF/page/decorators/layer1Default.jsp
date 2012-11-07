<%--

	Copyright © 2012, Source Tree, All Rights Reserved

--%><%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglibs.jsp"%>
<!DOCTYPE html>
<!--[if IE 6]><html lang="en" class="no-js ie6"><![endif]-->
<!--[if (gt IE 6)|!(IE)]><!-->
<html lang="en" class="no-js">
<!--<![endif]-->
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width,initial-scale=1">
<c:set var="windowTitle" scope="page">
	<decorator:title />
</c:set>
<title><s:message code="window.title"
		arguments="${windowTitle}" /></title>
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
<script src="<c:url value="/static/scripts/jquery/superfish.js"/>"
	type="text/javascript"></script>
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
		
		$(function(){
			// IPad/IPhone
				var viewportmeta = document.querySelector && document.querySelector('meta[name="viewport"]'),
				ua = navigator.userAgent,

				gestureStart = function () {viewportmeta.content = "width=device-width, minimum-scale=0.25, maximum-scale=1.6";},

				scaleFix = function () {
					if (viewportmeta && /iPhone|iPad/.test(ua) && !/Opera Mini/.test(ua)) {
						viewportmeta.content = "width=device-width, minimum-scale=1.0, maximum-scale=1.0";
						document.addEventListener("gesturestart", gestureStart, false);
					}
				};
				
				scaleFix();
				// Menu Android
				var userag = navigator.userAgent.toLowerCase();
				var isAndroid = userag.indexOf("android") > -1; 
				if(isAndroid) {
					$('.sf-menu').responsiveMenu({autoArrows:true});
				}
			});
		
		(function($) {
			$.fn.responsiveMenu = function(options) {
				var defaults = {autoArrows: false}
				var options = $.extend(defaults, options);
				return this.each(function() {
					var $this = $(this);
					var $window = $(window);
					var setClass = function() {
						if ($window.width() > 480) {$this.addClass('dropdown').removeClass('accordion').find('li:has(ul)').removeClass('accorChild');}
						else {$this.addClass('accordion').find('li:has(ul)').addClass('accorChild').parent().removeClass('dropdown');}
					}
					$window.resize(function() {
						setClass();
						$this.find('ul').css('display', 'none');
					});
					setClass();
					$this
						.addClass('responsive-menu')
						.find('li.current a')
						.live('click', function(e) {
							var $a = $(this);
							var container = $a.next('ul,div');
							if ($this.hasClass('accordion') && container.length > 0) {
								container.slideToggle();
								return false;
							}
						})
						.stop()
						.siblings('ul').parent('li').addClass('hasChild');
					if (options.autoArrows) {
						$('.hasChild > a', $this)
						.find('strong').append('<span class="arrow">&nbsp;</span>');
					}
				});
			}
		})(jQuery);
		
	</script>
</body>
</html>