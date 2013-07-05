<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
    <h3><fmt:message key="championship.championshipStages"/> - ${championship.description}</h3>
</div>

<div class="modal-body">
	<div style="width: 85%; margin: 0px auto;">
		<table class="table table-striped table-bordered"
			id="championshipStageList">
			<thead>
				<tr>
					<th class="sorting" role="columnheader"><fmt:message key="championshipStage.description"/></th>
					<th class="sorting" role="columnheader"><fmt:message key="championshipStage.date"/></th>
					<th class="sorting_disabled" role="columnheader"  style="width: 22%;"><fmt:message key="actions"/></th>
	   			</tr>
	 		</thead>
		   	<tbody>
		   		<c:forEach var="championshipStage"
					items="${championship.championshipStages}" varStatus="iteration">
					<tr>
						<td>${championshipStage.description}</td>
						<td><fmt:formatDate value="${championshipStage.date.time}" pattern="dd/MM/yyyy"/></td>
						<td style="width: 11%;"><a href="<c:url value="/admin/championshipResult/"/>${championshipStage.id}" class="btn btn-mini btn-info"><fmt:message key="championshipResult.edit" /></a></td>
						<td style="width: 11%;"><a href="<c:url value="/championshipStageRanking/"/>${championshipStage.id}" class="btn btn-mini btn-primary"><fmt:message key="championshipStageRanking" /></a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
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