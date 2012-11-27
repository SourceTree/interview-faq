<%--
	Copyright © 2012, Source Tree, All Rights Reserved
--%><%@ include file="/WEB-INF/page/common/taglibs.jsp"%>
<page:applyDecorator name="layer2Admin">
	<decorator:usePage id="decoratorPage" />
	<html>
<head>
<decorator:head />
<title><decorator:title /></title>
</head>
<body
	<decorator:getProperty property="body.onload" writeEntireProperty="true"/>
	<decorator:getProperty property="body.onunload" writeEntireProperty="true"/>>
	<c:set var="sidemenu">
		<decorator:getProperty property="meta.sidemenu"></decorator:getProperty>
	</c:set>
	<div id="main" class="container_col admin_content">
		<c:if test="${sidemenu ne ''}">
			<div class="sidemenu"><jsp:include page="/WEB-INF/page/admin/${sidemenu}.jsp"/></div>
		</c:if>
		<div id="info" class="info <c:if test="${empty sidemenu}">nosidemenu</c:if>">
			<article class="hero clearfix">
				<decorator:body />
			</article>
		</div>
	</div>
	<div class="clearfix"></div>
</body>
	</html>
</page:applyDecorator>