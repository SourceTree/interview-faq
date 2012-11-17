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
	<div id="ads" class="container_col left">&nbsp;</div>
	<div id="main" class="container_col content">
		<div id="info" class="info">
			<article class="hero clearfix">
				<decorator:body />
			</article>
		</div>
	</div>
	<div id="ads" class="container_col right">&nbsp;</div>
	<div class="clearfix"></div>
</body>
	</html>
</page:applyDecorator>