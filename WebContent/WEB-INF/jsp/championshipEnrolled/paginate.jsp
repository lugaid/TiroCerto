<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

{"sEcho": "1360877238967",
"iTotalRecords": 1,
"iTotalDisplayRecords": 1,
"aaData": [
<c:if test="${not empty associatePage.content}">
			<c:forEach items="${associatePage.content}" var="associate">
				{"id": ${associate.id},"cr": ${associate.cr},"name": "${associate.name}","email": "${associate.email}"}
			</c:forEach>
</c:if>

]}