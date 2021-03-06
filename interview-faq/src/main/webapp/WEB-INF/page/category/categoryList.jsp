<%--
	Copyright © 2012, Source Tree, All Rights Reserved
--%><%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="sidemenu" content="categorySideMenu">
<title>Category</title>
</head>
<body>
	<div id="listing">
			<c:forEach items="${categories.categoryDTOs}" var="categoryDTO">
				<div  class="infinite_grid_item initial_load_item col_50 col_border">
					<div>
						<a href="<c:url value="/category/"/>${categoryDTO.categoryName}"><strong>${categoryDTO.categoryDisplayName}</strong></a>
						<br><em>${categoryDTO.categoryDescription}</em>
						<br><a href="<c:url value="/category/edit/"/>${categoryDTO.id}">Edit</a>
					</div>
				</div>
			</c:forEach>
			
			<div class="pagination"></div>
	</div>
	
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
				$('.pagination').html('<span class="pagination_total">Total Records : ${categories.listProp.totalRecords} </span><a class="pager">LoadMore</a><span class="pagination_page">Page  ${categories.listProp.page} of ${categories.listProp.totalPages}</span>');
				
				$('#listing').infinitePaging({
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
					$('#listing').stopInfinitePaging();
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
						$(this).attr('rel', 'loaded');
					});
				};
				
				renderData = function(data) {
					var htmlStr = [];
					$.each(data.categoryDTOs, function(i, categoryDTO){
						htmlStr.push('<div  class="infinite_grid_item initial_load_item col_50 col_border">');
						htmlStr.push('<div>');
						htmlStr.push('<a href="<c:url value="/category/"/>');
						htmlStr.push(categoryDTO.categoryName);
						htmlStr.push('"><strong>');
						htmlStr.push(categoryDTO.categoryDisplayName);
						htmlStr.push('</strong></a><br>');
						htmlStr.push(categoryDTO.categoryDescription);
						htmlStr.push('<br><a href="<c:url value="/category/edit/"/>');
						htmlStr.push(categoryDTO.id);
						htmlStr.push('">Edit</a>');
						htmlStr.push('</div>');
						htmlStr.push('</div>');
					});
					
					return htmlStr.join('');
				};
			});
		});
	</script>
</body>
</html>