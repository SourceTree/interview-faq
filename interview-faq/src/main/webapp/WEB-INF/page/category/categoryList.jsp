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
	<ul id="category_list" class="infinite_grid">
		<c:forEach items="${categories.categoryDTOs}" var="categoryDTO">
			<li class="infinite_grid_item initial_load_item">${categoryDTO.categoryDisplayName}<br />
				<span>${categoryDTO.categoryDescription}</span>
			</li>
		</c:forEach>
	</ul>
	<div class="pager">LoadMore</div>

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
				$('#category_list').infinitePaging({
					'url' : '<c:url value="/category/list"/>', 
					page: curPage,
					'beforeLoad' : showLoader,
					'renderData': function (data){
						return renderData(data);
					},
					'afterLoad' : function(elementsLoaded) { // after loading content, you can use this function to animate your new elements
						hideLoader();
						$(elementsLoaded).fadeInWithDelay();
					}
				});

				if((${categories.listProp.endIndex} + 1) >= ${categories.listProp.totalRecords}){
					$('#category_list').stopInfinitePaging();
					$('.pager').remove();
				}
				
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
				
				renderData = function(data) {
					var htmlStr = [];
					$.each(data.categoryDTOs, function(i, categoryDTO){
						htmlStr.push('<li class="infinite_grid_item">');
						htmlStr.push(categoryDTO.categoryDisplayName);
						htmlStr.push('<br /><span>');
						htmlStr.push(categoryDTO.categoryDescription);
						htmlStr.push('</span>');
						htmlStr.push('</li>');	
					});
					
					return htmlStr.join('');
				};
			});
		});
	</script>
</body>
</html>