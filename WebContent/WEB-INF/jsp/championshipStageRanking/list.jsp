<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
    <h3><fmt:message key="championshipStageRanking.model.description"/></h3>
    <h4>${championshipStage.championship.description} - ${championshipStage.description} - <fmt:formatDate value="${championshipStage.date.time}" pattern="dd/MM/yyyy"/></h4>
</div>

<div class="modal-body">
	<div style="width: 95%; margin: 0px auto;">
		<table class="table table-striped table-bordered"
			id="championshipStageList">
			<thead>
				<tr>
					<th role="columnheader"><fmt:message key="championshipStageRanking.position"/></th>
					<th role="columnheader"><fmt:message key="associate.name"/></th>
					
					<c:forEach var="i" begin="1" end="${championshipStage.championship.modality.maxSeries}" step="1">
						<th role="columnheader">S${i}</th>
					</c:forEach>
					
					<th role="columnheader"><fmt:message key="championshipStageRanking.penalty"/>*</th>
					<th role="columnheader"><fmt:message key="championshipStageRanking.total"/>*</th>
	   			</tr>
	 		</thead>
		   	<tbody>
		   		<c:forEach var="championshipStageRanking"
					items="${championshipStage.championshipStageRanking}" varStatus="iteration">
					<tr>
						<td>${championshipStageRanking.position}</td>
						<td>${championshipStageRanking.championshipEnrolled.associate.name}</td>
						
						<c:forEach var="i" begin="1" end="${championshipStage.championship.modality.maxSeries}" step="1">
							<c:set var="championshipStagesSeriePoint" value="0" scope="page" />
							
							<c:forEach var="championshipResult"
								items="${championshipStage.championshipResult}" varStatus="iteration">
							
								<c:if test="${championshipStageRanking.championshipEnrolled eq championshipResult.championshipEnrolled && i eq championshipResult.serie}">
									<c:set var="championshipStagesSeriePoint" value="${championshipResult.points}" scope="page" />
								</c:if>
							</c:forEach>
							
							<td>${championshipStagesSeriePoint}</td>
						</c:forEach>

						<td>${championshipStageRanking.penalty}</td>
						<td>${championshipStageRanking.total}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<p>* - <fmt:message	key="championshipStageRanking.considering"><fmt:param value="${championshipStage.championship.modality.qtySeries}"/></fmt:message></p>
	</div>
</div>

<div class="modal-footer">
  <button class="btn" data-dismiss="modal" aria-hidden="true"><fmt:message key="close" /></button>
</div>

<%@ include file="/include_js.jsp"%>
 
<script type="text/javascript" charset="utf-8">
	$.extend(true, $.fn.dataTable.defaults, {
		"oLanguage": {
            "sUrl": "<c:url value="/localization/messages_datatables_pt_BR.js" />",
            "codification": "UTF-8"
        }
    });
</script>