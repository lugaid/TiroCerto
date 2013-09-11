<%@ include file="/header.jsp"%>

<div class="span3 bs-docs-sidebar">
	<ul class="nav nav-list bs-docs-sidenav affix-top" id="adminMenu">
		<li><a href="<c:url value="/admin/associate" />"><i
				class="icon-chevron-right"></i><fmt:message key="associate" /></a></li>
		<li><a href="<c:url value="/admin/modality" />"><i
				class="icon-chevron-right"></i><fmt:message key="modality" /></a></li>
		<li><a href="<c:url value="/admin/championship" />"><i
				class="icon-chevron-right"></i><fmt:message key="championship" /></a></li>
		<li><a href="<c:url value="/admin/scoreboard" />"><i
				class="icon-chevron-right"></i><fmt:message key="scoreboard" /></a></li>
	</ul>
</div>

<div class="span9">