<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c-rt" uri="http://java.sun.com/jsp/jstl/core" %>

<c-rt:set var="echo" value="<%=new java.util.Date().getTime()%>" />
 
{"sEcho": ${echo},
"iTotalRecords": ${championshipResultPage.totalElements},
"iTotalDisplayRecords": ${championshipResultPage.numberOfElements},
"aaData": [
<c:if test="${not empty championshipResultPage.content}">
	<c:forEach items="${championshipResultPage.content}" var="championshipResult" varStatus="iteration">
		<c:if test="${iteration.index != 0}">
		,
		</c:if>
		
		{"id": ${championshipResult.id},"serie": ${championshipResult.serie},"associate_cr": "${championshipResult.championshipEnrolled.associate.cr}","associate_name": "${championshipResult.championshipEnrolled.associate.name}"}
	</c:forEach>
</c:if>

]}