<%@ include file="/header-admin.jsp"%>

<legend>
	<fmt:message key="associate.model.description" />
</legend>

<c:if test="${not empty success}">
	<div class="alert alert-success">
		<button type="button" class="close" data-dismiss="alert">×</button>
		<ul>
			<li><strong><fmt:message key="associate" /></strong> - <fmt:message key="success.${success}" /></li>
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
	id="associateList">

</table>

<%@ include file="/include_js.jsp"%>

<script type="text/javascript">
	$(document).ready(function() {

		$("#associateList").dataTable({

			"aoColumns" : [ {
				"sTitle" : "<fmt:message key="associate.id" />",
				"mDataProp" : "id",
				"bVisible" : false,
				"bSearchable" : false
			}, {
				"sTitle" : "<fmt:message key="associate.cr" />",
				"mDataProp" : "cr",
				"sDefaultContent" : "",
				"bSearchable" : false,
				"bSortable" : false
			}, {
				"sTitle" : "<fmt:message key="associate.name" />",
				"mDataProp" : "name",
			}, {
				"sTitle" : "<fmt:message key="associate.email" />",
				"mDataProp" : "email"
			}, {
				"sTitle" : "<fmt:message key="actions" />",
				"mDataProp": "id",
				"sWidth" : "10%",
				"bSearchable" : false,
				"bSortable" : false,
	            "fnRender": function (oObj) {
	                return '<div class="btn-group">' +
	                '<a href="<c:url value="/admin/associate/edit/"/>' + oObj.aData['id'] + '" class="btn btn-mini btn-warning"><fmt:message key="edit" /></a>' +
	                '<a href="<c:url value="/admin/associate/delete/"/>' + oObj.aData['id'] + '" class="btn btn-mini btn-danger"><fmt:message key="delete" /></a>' +
	                '</div>';
	            }
			}],

			"bAutoWidth": false,
			"bServerSide" : true,
			"sAjaxSource" : '<c:url value="/admin/associate/paginate"/>'
		});
	});
</script>

<a href="<c:url value="/admin/associate/new"/>" class="btn btn-primary"><fmt:message key="add.new" /></a>

<%@ include file="/footer-admin.jsp"%>