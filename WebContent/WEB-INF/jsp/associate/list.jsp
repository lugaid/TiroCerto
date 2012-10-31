<%@ include file="/header.jsp"%>

<table style="width: 100%" class="table table-striped table-bordered"
	id="associateList" width=100%>

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
			} ],

			"oTableTools" : {
				"sRowSelect" : "single"
			},

			"bServerSide" : true,
			"sAjaxSource" : '<c:url value="/associate/paginate"/>'
		});
	});
</script>

<%@ include file="/footer.jsp"%>