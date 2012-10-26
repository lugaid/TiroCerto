<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
<title><fmt:message key="project.name" /></title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<!-- Le styles -->
<link href="<c:url value="/css/bootstrap.min.css" />" rel="stylesheet">
<link href="<c:url value="/css/bootstrap-combobox.css" />" rel="stylesheet">
<style type="text/css">
-->
body {
	padding-top: 60px;
	padding-bottom: 40px;
}

.sidebar-nav {
	padding: 9px 0;
}
</style>
<link href="<c:url value="/css/bootstrap-responsive.min.css" />"
	rel="stylesheet">

<!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"/>"></script>
    <![endif]-->
</head>

<body>

	<div class="navbar navbar-inverse navbar-fixed-top">
		<div class="navbar-inner">
			<div class="container-fluid">
				<a class="btn btn-navbar" data-toggle="collapse"
					data-target=".nav-collapse"> <span class="icon-bar"></span> <span
					class="icon-bar"></span> <span class="icon-bar"></span>
				</a> <a class="brand" href="<c:url value="/"/>"><fmt:message
						key="project.short.name" /></a>
				<div class="nav-collapse collapse">
					<form class="navbar-search pull-right">
						<input type="text" maxlength="10" class="search-query"
							placeholder="<fmt:message key="associate.cr" />"> <input
							type="password" style="width: 60pt;" class="search-query"
							placeholder="<fmt:message key="associate.password" />">
						<button type="submit" class="btn btn-mini btn-primary">
							<fmt:message key="login" />
						</button>
					</form>
				</div>
				<!--/.nav-collapse -->
			</div>
		</div>
	</div>