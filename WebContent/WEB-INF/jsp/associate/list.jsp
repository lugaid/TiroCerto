<%@ include file="/header-admin.jsp"%>

<legend>
	<fmt:message key="associate.model.description" />
</legend>
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
				"bSortable" : false,
				"bVisible" : false,
			}, {
				"sTitle" : "<fmt:message key="associate.cr" />",
				"mDataProp" : "cr",
				"bSortable" : true,
			}, {
				"sTitle" : "<fmt:message key="associate.name" />",
				"mDataProp" : "name",
				"bSortable" : true,
			}, {
				"sTitle" : "<fmt:message key="associate.email" />",
				"mDataProp" : "email",
				"bSortable" : false,
			}, {
				"sTitle" : "<fmt:message key="actions" />",
				"mDataProp": "id",
	            "fnRender": function (oObj) {
	                return '<div class="btn-group" data-toggle="buttons-checkbox">' +
	                '<a href="/TiroCerto/admin/associate/new" class="btn btn-warning"><fmt:message key="edit" /></a>' +
	                '<a href="/TiroCerto/admin/associate/new" class="btn btn-danger"><fmt:message key="delete" /></a>' +
	                '</div>';
	            }
			}],

			
			//<a href=Page/Edit/' + oObj.aData["id"] + '>' + oObj.aData["id"] + '</a>
			"bServerSide" : true,
			"sAjaxSource" : '<c:url value="/admin/associate/paginate"/>'
		});
	});
</script>

<a href="<c:url value="/admin/associate/new"/>" class="btn btn-primary"><fmt:message key="add.new" /></a>

<%@ include file="/footer-admin.jsp"%>