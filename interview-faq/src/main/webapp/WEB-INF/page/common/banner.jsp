<%--

	Copyright © 2012, Source Tree, All Rights Reserved

--%><%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglibs.jsp"%>
<div class="col_12">&nbsp;</div>
<div class="col_75">
	<div class="logo" style="border: 1px solid;">The great Logo</div>
	
	<div class="clearfix"></div>
	<nav class="menu_main">
		<div id="responsive_menu">Menu</div>
		<ul class="sf-menu responsive-menu sf-js-enabled">
			<li class="first-button"><a href="<c:url value="/"/>">Home</a></li>
			<li><a href="#about">About Us</a></li>
			<li class="last-button"><a href="#contact">Contact Us</a></li>
			
			<%--Sample code for sub menu 
			<li><a href="#about" class="sf-with-ul">About<span class="sf-sub-indicator"></span></a><ul><li><a href="#contact">SubMenu</a></li><li><a href="#contact">SubMenu2</a></li></ul></li>
			--%>
			<%--Sample code for normal middle menu's
			<li><a href="#contact">Contact Us</a></li>
			 --%>
		</ul>
		<div class="clearfix"></div>
	</nav>
</div>
<div class="col_12">&nbsp;</div>
<div class="clearfix"></div>