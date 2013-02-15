<%@ include file="/header-admin.jsp"%>

<legend>
	<fmt:message key="championship.model.description" />
</legend>

<c:if test="${not empty success}">
	<div class="alert alert-success">
		<button type="button" class="close" data-dismiss="alert">×</button>
		<ul>
			<li><strong><fmt:message key="championshipEnrolled" /></strong> - <fmt:message key="success.${success}" /></li>
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
	id="championshipEnrolledList">
</table>


<div class="modal hide fade" id="res-modal"></div>


<%@ include file="/include_js.jsp"%>

<script type="text/javascript">
	$(document).ready(function() {

		$("#championshipEnrolledList").dataTable({

			"aoColumns" : [ {
				"sTitle" : "<fmt:message key="championship.id" />",
				"mDataProp" : "championship.id",
				"bVisible" : false,
			}, {
				"sTitle" : "<fmt:message key="associate.id" />",
				"mDataProp" : "associate.id",
				"bVisible" : false,
			}, {
				"sTitle" : "<fmt:message key="associate.name" />",
				"mDataProp" : "description",
				"sDefaultContent" : ""
			}, {
				"sTitle" : "<fmt:message key="actions" />",
				"mDataProp": "id",
				"bSortable" : false,
				"sWidth" : "10%",
	            "fnRender": function (oObj) {
	                return '<div class="btn-group">' +
	                '<a href="#" id="championshipEnrolledEdit" class="btn btn-mini btn-inverse" data-backdrop="true" data-controls-modal="res-modal" data-keyboard="true" url="<c:url value="/admin/championshipEnrolled/edit/"/>' + oObj.aData['id'] + '"><fmt:message key="championshipEnrolled.edit" /></a>' +
	                '<a href="<c:url value="/admin/championship/edit/"/>' + oObj.aData['id'] + '" class="btn btn-mini btn-warning"><fmt:message key="edit" /></a>' +
	                '<a href="<c:url value="/admin/championship/delete/"/>' + oObj.aData['id'] + '" class="btn btn-mini btn-danger"><fmt:message key="delete" /></a>' +
	                '</div>';
	            }
			}],

			"bAutoWidth": false,
			"bServerSide" : true,
			"sAjaxSource" : '<c:url value="/admin/championshipEnrolled/paginate/${championship.id}"/>'
		});
	});
</script>

<a href="<c:url value="championship/new"/>" class="btn btn-primary"><fmt:message key="add.new" /></a>

<%@ include file="/footer-admin.jsp"%>