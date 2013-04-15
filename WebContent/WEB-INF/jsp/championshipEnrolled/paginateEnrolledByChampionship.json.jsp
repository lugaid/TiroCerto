<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c-rt" uri="http://java.sun.com/jsp/jstl/core" %>

<c-rt:set var="echo" value="<%=new java.util.Date().getTime()%>" />
 
{"sEcho": ${echo},
"iTotalRecords": ${championshipEnrolledPage.totalElements},
"iTotalDisplayRecords": ${championshipEnrolledPage.numberOfElements},
"aaData": [
<c:if test="${not empty championshipEnrolledPage.content}">
	<c:forEach items="${championshipEnrolledPage.content}" var="championshipEnrolled" varStatus="iteration">
		<c:if test="${iteration.index != 0}">
		,
		</c:if>
		
		{"championship_id": ${championshipEnrolled.championship.id},"associate_id": ${championshipEnrolled.associate.id},"associate_cr": ${championshipEnrolled.associate.cr},"associate_name": "${championshipEnrolled.associate.name}","associate_email": "${championshipEnrolled.associate.email}"}
	</c:forEach>
</c:if>

]}