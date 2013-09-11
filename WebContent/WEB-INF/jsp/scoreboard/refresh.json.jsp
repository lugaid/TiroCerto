<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
{"description" : "${scoreboard.description}", 
"qtyRows" : ${scoreboard.qtyRows},
"timeToRefresh" : ${scoreboard.timeToRefresh},
"descriptionTitle" : "${championshipStage.championship.description} - ${championshipStage.championship.modality.description} - ${championshipStage.description} <fmt:formatDate value="${championshipStage.date.time}" pattern="dd/MM/yyyy"/>",
"modalityMaxSeries" : ${championshipStage.championship.modality.maxSeries},
"championshipStageRankings" : [
<c:set var="firstStageRanking" value="true" scope="page" />
<c:forEach var="championshipStageRanking" items="${championshipStage.championshipStageRanking}">
	<c:if test="${firstStageRanking eq 'false'}">
		,
	</c:if>
	{"position" : "${championshipStageRanking.position}", 
	"associateName" : "${championshipStageRanking.championshipEnrolled.associate.name}", 
	"penalty" : ${championshipStageRanking.penalty},
	"total" : ${championshipStageRanking.total}, 
	"championshipResult" : [
	<c:forEach var="i" begin="1" end="${championshipStage.championship.modality.maxSeries}" step="1">
		<c:set var="championshipStagesSeriePoint" value="0" scope="page" />
		
		<c:forEach var="championshipResult"
			items="${championshipStage.championshipResult}" varStatus="iteration">
		
			<c:if test="${championshipStageRanking.championshipEnrolled eq championshipResult.championshipEnrolled && i eq championshipResult.serie}">
				<c:set var="championshipStagesSeriePoint" value="${championshipResult.points}" scope="page" />
			</c:if>
		</c:forEach>
		
		{"serie" : ${i}, "total" : ${championshipStagesSeriePoint}}
		<c:if test="${i lt championshipStage.championship.modality.maxSeries}">
			,
		</c:if>
	</c:forEach>
	]}
	<c:set var="firstStageRanking" value="false" scope="page" />
</c:forEach>
]}