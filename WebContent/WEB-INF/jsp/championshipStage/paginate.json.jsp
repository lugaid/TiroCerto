<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c-rt" uri="http://java.sun.com/jsp/jstl/core" %>

<c-rt:set var="echo" value="<%=new java.util.Date().getTime()%>" />
 
{"sEcho": ${echo},
"iTotalRecords": ${associatePage.totalElements},
"iTotalDisplayRecords": ${associatePage.numberOfElements},
"aaData": [
<c:if test="${not empty associatePage.content}">
	<c:forEach items="${associatePage.content}" var="associate" varStatus="iteration">
		<c:if test="${iteration.index != 0}">
		,
		</c:if>
		
		<c:set var="enrolled" value=""/>
		
		<c:forEach items="${associate.championshipEnrolled}" var="championshipEnrolled" varStatus="iteration">
			<c:if test="${championshipEnrolled.championship eq championship}">
				<c:set var="enrolled" value="checked"/>
			</c:if>
		</c:forEach>
		
		{"id": ${associate.id},"cr": ${associate.cr},"name": "${associate.name}","email": "${associate.email}", "enrolled" : "${enrolled}"}
	</c:forEach>
</c:if>

]}