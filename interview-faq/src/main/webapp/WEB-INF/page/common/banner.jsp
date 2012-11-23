<%--

	Copyright © 2012, Source Tree, All Rights Reserved

--%><%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglibs.jsp"%>
<div class="container_col content">
	<div class="logo">FAQ Masters <br /><span>sees all.knows all</span></div>
	
	<div class="clearfix" style="text-align: right;">
		<c:if test="${not empty sessionScope.sess_vars && sessionScope.sess_vars.role eq 'ADMIN'}">
			Welcome <strong>${sessionScope.sess_vars.email}</strong>. <a href="<c:url value="/admin/logout"/>">Logout</a>
			<br />
		</c:if>
	</div>
	<nav class="menu_main">
		<ul class="sf-menu sf-js-enabled">
			<li class="first-button"><a href="<c:url value="/home"/>">Home</a></li>
			
			<c:if test="${not empty sessionScope.sess_vars && sessionScope.sess_vars.role eq 'ADMIN'}">
				<li><a href="<c:url value="/admin/dashboard"/>">Dashboard</a></li>
			</c:if>
			<li><a href="<c:url value="/about"/>">About Us</a></li>
			<li class="last-button"><a href="<c:url value="/contact"/>">Contact Us</a></li>
			<%--Sample code for sub menu 
			<li><a href="#about" class="sf-with-ul">About<span class="sf-sub-indicator"></span></a><ul><li><a href="#contact">SubMenu</a></li><li><a href="#contact">SubMenu2</a></li></ul></li>
			--%>
		</ul>
		<div class="clearfix"></div>
	</nav>
</div>
<div class="clearfix"></div>