<%@ include file="/header.jsp"%>

<c:if test="${not empty errors}">
	<div class="alert alert-error">
		<button type="button" class="close" data-dismiss="alert">×</button>
		<ul>
		    <c:forEach items="${errors}" var="error">
		    <li><strong><fmt:message key="${error.category}" /></strong> - ${error.message}</li>
		    </c:forEach>
	    </ul>
	</div>
</c:if>

<h2 align="center"><fmt:message key="access.denied" /></h2>

<%@ include file="/include_js.jsp"%>

<%@ include file="/footer.jsp"%>