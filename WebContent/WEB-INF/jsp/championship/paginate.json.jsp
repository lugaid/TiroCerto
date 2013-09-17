<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c-rt" uri="http://java.sun.com/jsp/jstl/core" %>

<c-rt:set var="echo" value="<%=new java.util.Date().getTime()%>" />
 
{"sEcho": ${echo},
"iTotalRecords": ${championshipPage.totalElements},
"iTotalDisplayRecords": ${championshipPage.numberOfElements},
"aaData": [
<c:if test="${not empty championshipPage.content}">
	<c:forEach items="${championshipPage.content}" var="championship" varStatus="iteration">
		<c:if test="${iteration.index != 0}">
		,
		</c:if>
		
		{"id": ${championship.id},"description": "${championship.description}","modality_description": "${championship.modality.description}"}
	</c:forEach>
</c:if>

]}