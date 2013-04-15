<%@ include file="/header-admin.jsp"%>

<legend>
	<p style="text-align: center;">${championshipStage.championship.description} - <fmt:formatDate value="${championshipStage.date.time}" pattern="dd/MM/yyyy"/></p>
	<fmt:message key="championshipResult.model.description" />
</legend>

<c:if test="${not empty success}">
	<div class="alert alert-success">
		<button type="button" class="close" data-dismiss="alert">×</button>
		<ul>
			<li><strong><fmt:message key="championshipResult" /></strong> - <fmt:message key="success.${success}" /></li>
		</ul>
	</div>
</c:if>

<c:if test="${not empty errors}">
	<div class="alert alert-error">
		<button type="button" class="close" data-dismiss="alert">×</button>
		<ul>
			<c:forEach items="${errors}" var="error">
				<li><strong><fmt:message
							key="${error.category}" /></strong> - ${error.message }</li>
			</c:forEach>
		</ul>
	</div>
</c:if>
		
<table class="table table-striped table-bordered"
	id="championshipResultList">

</table>

<%@ include file="/include_js.jsp"%>

<script type="text/javascript">
	$(document).ready(function() {

		$("#championshipResultList").dataTable({

			"aoColumns" : [ {
				"sTitle" : "<fmt:message key="championshipResult.id" />",
				"mDataProp" : "id",
				"bVisible" : false,
			}, {
				"sTitle" : "<fmt:message key="championshipResult.serie" />",
				"mDataProp" : "serie",
				"sDefaultContent" : ""
			}, {
				"sTitle" : "<fmt:message key="associate.cr" />",
				"mDataProp" : "associate_cr",
				"sDefaultContent" : ""
			}, {
				"sTitle" : "<fmt:message key="associate.name" />",
				"mDataProp" : "associate_name",
			}, {
				"sTitle" : "<fmt:message key="actions" />",
				"mDataProp": "id",
				"bSortable" : false,
				"sWidth" : "10%",
	            "fnRender": function (oObj) {
	                return '<div class="btn-group">' +
	                '<a href="<c:url value="/admin/championshipResult/${championshipStage.id}/edit/"/>' + oObj.aData['id'] + '" class="btn btn-mini btn-warning"><fmt:message key="edit" /></a>' +
	                '<a href="<c:url value="/admin/championshipResult/${championshipStage.id}/delete/"/>' + oObj.aData['id'] + '" class="btn btn-mini btn-danger"><fmt:message key="delete" /></a>' +
	                '</div>';
	            }
			}],

			"bAutoWidth": false,
			"bServerSide" : true,
			"sAjaxSource" : '<c:url value="/admin/championshipResult/paginateByChampionshipStage/${championshipStage.id}"/>'
		});
	});
</script>

<a href="<c:url value="/admin/championshipResult/${championshipStage.id}/new"/>" class="btn btn-primary"><fmt:message key="add.new" /></a>

<%@ include file="/footer-admin.jsp"%>