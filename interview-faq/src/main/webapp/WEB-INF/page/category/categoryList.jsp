<%--
	Copyright © 2012, Source Tree, All Rights Reserved
--%><%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Category</title>
</head>
<body>
	<ul id="" class="infinite_grid">
		<c:forEach items="${categories.categoryDTOs}" var="categoryDTO">
			<li class="infinite_grid_item"></li>
		</c:forEach>
	</ul>
	<div class="grid_loading"></div>
	<script type="text/javascript"
		src="<c:url value="/static/scripts/jquery/jquery.infinite-paging.js"/>"></script>
	<script type="text/javascript">
		var curPage = 1;
		function showLoader() {
			$(".grid_loading").fadeIn();
		}
		
		function hideLoader() {
			$(".grid_loading").fadeOut();
		}

		$(document).ready(function() {
			$(function() {
				$('#content').infinitePaging({
					'url' : '<c:url value="/category/list"/>', 
					'heightOffset' : 120,
					'beforeLoad' : showLoader,
					'renderData': renderData(data),
					'afterLoad' : function(elementsLoaded) { // after loading content, you can use this function to animate your new elements
						hideLoader();
						$(elementsLoaded).fadeInWithDelay();
					}
				});

				// code for fade in element by element
				$.fn.fadeInWithDelay = function() {
					var delay = 0;
					return this.each(function() {
						$(this).delay(delay).animate({
							opacity : 1
						}, 200);
						delay += 100;
					});
				};
				
				$.fn.renderData = function(data) {
					var htmlStr = ['<li style="opacity:0;-moz-opacity: 0;filter: alpha(opacity=0);>'];
					
					htmlStr.push('</li>');
					
					return htmlStr.join();
				};
			});
		});
	</script>
</body>
</html>