<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
    <h3><fmt:message key="championshipRanking"/></h3>
    <h4>${championship.description}</h4>
</div>

<div class="modal-body">
	<div style="width: 85%; margin: 0px auto;">
		<table class="table table-striped table-bordered"
			id="championshipRanking">
			<thead>
				<tr>
					<th role="columnheader"><fmt:message key="championshipRanking.position"/></th>
					<th role="columnheader"><fmt:message key="associate.name"/></th>
					<th role="columnheader"><fmt:message key="championshipRanking.points"/></th>
					<th role="columnheader"><fmt:message key="championshipRanking.penalty"/></th>
					<th role="columnheader"><fmt:message key="championshipRanking.total"/></th>
	   			</tr>
	 		</thead>
		   	<tbody>
		   		<c:forEach var="championshipRanking"
					items="${championshipRanking}" varStatus="iteration">
					<tr>
						<td>${championshipRanking.position}</td>
						<td>${championshipRanking.championshipEnrolled.associate.name}</td>
						<td>${championshipRanking.points}</td>
						<td>${championshipRanking.penalty}</td>
						<td>${championshipRanking.total}</td>
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