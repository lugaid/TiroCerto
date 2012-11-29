<%@ include file="/header-admin.jsp"%>

<legend>
	<fmt:message key="modality.model.description" />
</legend>

<c:if test="${not empty success}">
	<div class="alert alert-success">
		<button type="button" class="close" data-dismiss="alert">×</button>
		<ul>
			<li><strong><fmt:message key="modality" /></strong> - <fmt:message key="success.${success}" /></li>
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
	id="modalityList">

</table>

<%@ include file="/include_js.jsp"%>

<script type="text/javascript">
	$(document).ready(function() {

		$("#modalityList").dataTable({

			"aoColumns" : [ {
				"sTitle" : "<fmt:message key="modality.id" />",
				"mDataProp" : "id",
				"bVisible" : false,
			}, {
				"sTitle" : "<fmt:message key="modality.description" />",
				"mDataProp" : "description",
				"sDefaultContent" : ""
			}, {
				"sTitle" : "<fmt:message key="actions" />",
				"mDataProp": "id",
				"bSortable" : false,
				"sWidth" : "10%",
	            "fnRender": function (oObj) {
	                return '<div class="btn-group">' +
	                '<a href="<c:url value="/admin/modality/edit/"/>' + oObj.aData['id'] + '" class="btn btn-mini btn-warning"><fmt:message key="edit" /></a>' +
	                '<a href="<c:url value="/admin/modality/delete/"/>' + oObj.aData['id'] + '" class="btn btn-mini btn-danger"><fmt:message key="delete" /></a>' +
	                '</div>';
	            }
			}],

			"bAutoWidth": false,
			"bServerSide" : true,
			"sAjaxSource" : '<c:url value="/admin/modality/paginate"/>'
		});
	});
</script>

<a href="<c:url value="modality/new"/>" class="btn btn-primary"><fmt:message key="add.new" /></a>

<%@ include file="/footer-admin.jsp"%>