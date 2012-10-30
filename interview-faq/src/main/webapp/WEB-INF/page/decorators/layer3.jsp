<%--

	Copyright © 2012, Source Tree, All Rights Reserved

--%><%@ include file="/WEB-INF/page/common/taglibs.jsp"%>
<page:applyDecorator name="layer2">
	<decorator:usePage id="decoratorPage" />

	<html>
<head>
<decorator:head />
<title><decorator:title /></title>
</head>
<body
	<decorator:getProperty property="body.onload" writeEntireProperty="true"/>
	<decorator:getProperty property="body.onunload" writeEntireProperty="true"/>>
	<div id="contentarea">
		<decorator:body />
	</div>
</body>
	</html>
</page:applyDecorator>