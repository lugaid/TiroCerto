<%@ include file="/header-admin.jsp"%>

<legend>
	<fmt:message key="scoreboard.model.description" />
</legend>

<c:if test="${not empty success}">
	<div class="alert alert-success">
		<button type="button" class="close" data-dismiss="alert">×</button>
		<ul>
			<li><strong><fmt:message key="scoreboard" /></strong> - <fmt:message key="success.${success}" /></li>
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
	id="scoreboardList">

</table>

<%@ include file="/include_js.jsp"%>

<script type="text/javascript">
	$(document).ready(function() {

		$("#scoreboardList").dataTable({

			"aoColumns" : [ {
				"sTitle" : "<fmt:message key="scoreboard.id" />",
				"mDataProp" : "id",
				"bVisible" : false,
				"bSearchable" : false
			}, {
				"sTitle" : "<fmt:message key="scoreboard.description" />",
				"mDataProp" : "description",
				"sDefaultContent" : "",
			}, {
				"sTitle" : "<fmt:message key="actions" />",
				"mDataProp": "id",
				"bSortable" : false,
				"sWidth" : "10%",
				"bSearchable" : false,
	            "fnRender": function (oObj) {
	                return '<div class="btn-group">' +
	                '<button onclick="showPanel(' + oObj.aData['id'] + ')" type="button" class="btn btn-mini btn-info"><fmt:message key="scoreboard.showPanel" /></button>' +
	                '<a href="<c:url value="/admin/scoreboard/edit/"/>' + oObj.aData['id'] + '" class="btn btn-mini btn-warning"><fmt:message key="edit" /></a>' +
	                '<a href="<c:url value="/admin/scoreboard/delete/"/>' + oObj.aData['id'] + '" class="btn btn-mini btn-danger"><fmt:message key="delete" /></a>' +
	                '</div>';
	            }
			}],

			"bAutoWidth": false,
			"bServerSide" : true,
			"sAjaxSource" : '<c:url value="/admin/scoreboard/paginate"/>'
		});
	});
	
	function showPanel(scoreboardId) {
		window.open('<c:url value="/admin/scoreboard/showPanel/"/>' + scoreboardId, '_blank', 'status=0,titlebar=0,toolbar=0');
	}
</script>

<a href="<c:url value="/admin/scoreboard/new"/>" class="btn btn-primary"><fmt:message key="add.new" /></a>

<%@ include file="/footer-admin.jsp"%>