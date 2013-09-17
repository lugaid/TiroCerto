<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<link rel="icon" 
      type="image/png" 
      href="<c:url value="/images/fav-target.png" />">
<title><fmt:message key="project.name" /></title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="Emerson Rancoletta">
<meta charset="UTF-8">

<!-- Le styles -->
<link href="<c:url value="/css/bootstrap.min.css" />" rel="stylesheet">
<link href="<c:url value="/css/bootstrap-combobox.css" />"
	rel="stylesheet">
<link href="<c:url value="/css/DT_bootstrap.css" />" rel="stylesheet">
<link href="<c:url value="/css/docs.css" />" rel="stylesheet">
<link href="<c:url value="/css/datepicker.css" />" rel="stylesheet">
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

<body data-spy="scroll" data-target=".bs-docs-sidebar"
	data-twttr-rendered="true">

	<!-- navbar -->
	<div class="navbar navbar-inverse navbar-fixed-top">
		<div class="navbar-inner">
			<div class="container">
				<a class="brand" style="float: left;" href="<c:url value="/"/>"><fmt:message
						key="project.short.name" /></a>
				<button type="button" class="btn btn-navbar" data-toggle="collapse"
					data-target=".nav-collapse">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<div class="nav-collapse collapse">
					<ul class="nav">
						<li class=""><a
							href="<c:url value="/"/>">Home</a></li>
					</ul>

					<c:if test="${empty associateLogged.associate}">
						<form class="navbar-search pull-right"
							action="<c:url value="/admin/associate/login" />" method="post"
							id="topLoginForm">
							<input type="text" style="width: 120pt;" class="search-query"
								placeholder="<fmt:message key="associate.email" />"
								name="associate.email"> <input type="password"
								style="width: 60pt;" class="search-query"
								placeholder="<fmt:message key="associate.password" />"
								name="associate.password">
							<button type="submit" class="btn btn-mini btn-primary">
								<fmt:message key="login" />
							</button>
						</form>
					</c:if>
					
					<c:if test="${not empty associateLogged.associate}">
						<ul class="nav">
							<li class=""><a
								href="<c:url value="/admin/associate"/>"><fmt:message key="associate" /></a></li>
						</ul>
						
						<ul class="nav">
							<li class=""><a
								href="<c:url value="/admin/modality"/>"><fmt:message key="modality" /></a></li>
						</ul>
						
						<ul class="nav">
							<li class=""><a
								href="<c:url value="/admin/championship"/>"><fmt:message key="championship" /></a></li>
						</ul>
						
						<ul class="nav">
							<li class=""><a
								href="<c:url value="/admin/scoreboard"/>"><fmt:message key="scoreboard" /></a></li>
						</ul>
						
						<div class="navbar-search pull-right">
							<fmt:message key="associate.logged" />
							<i><strong>${associateLogged.associate.name}</strong></i> <a
								href="<c:url value="/admin/associate/logout"/>"
								class="btn btn-primary btn-mini"><fmt:message key="logout" />
							</a>
						</div>
					</c:if>
				</div>
			</div>
		</div>
	</div>

	<!-- conteúdo -->
	<div class="container">
		<div class="row">