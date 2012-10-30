<%--

	Copyright © 2012, Source Tree, All Rights Reserved

--%><%@ include file="/WEB-INF/page/common/taglibs.jsp"%>
<page:applyDecorator name="layer1Default">
	<decorator:usePage id="decoratorPage" />

	<html>
<head>
<decorator:head />
<title><decorator:title /></title>
</head>
<body
	<decorator:getProperty property="body.onload" writeEntireProperty="true"/>
	<decorator:getProperty property="body.onunload" writeEntireProperty="true"/>>
	<div id="wrapper">
	<div>
		<%@include file="/WEB-INF/page/common/banner.jsp"%>
	</div>
	<decorator:body />
</div>
	<%@include file="/WEB-INF/page/common/footer.jsp"%>

</body>
	</html>
</page:applyDecorator>