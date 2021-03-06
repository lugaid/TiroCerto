<%@ include file="/header-admin.jsp"%>

<c:if test="${not empty errors}">
	<div class="alert alert-error">
		<button type="button" class="close" data-dismiss="alert">�</button>
		<ul>
			<c:forEach items="${errors}" var="error">
				<li><strong><fmt:message
							key="scoreboard.${error.category }" /></strong> - ${error.message }</li>
			</c:forEach>
		</ul>
	</div>
</c:if>

<form class="form-horizontal" method="post" id="scoreboardForm"
	action="<c:url value="/admin/scoreboard"/>">
	<fieldset>
		<legend>
			<fmt:message key="scoreboard.model.description" />
		</legend>

		<input type="hidden" id="scoreboard.id" name="scoreboard.id"
			value="${scoreboard.id}">

		<div class="control-group">
			<label class="control-label" for="scoreboard.description"><fmt:message
					key="scoreboard.description" /></label>
			<div class="controls">
				<input type="text" id="scoreboard.description" name="scoreboard.description"
					placeholder="<fmt:message key="scoreboard.description" />"
					value="${scoreboard.description}">
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label" for="scoreboard.timeToRefresh"><fmt:message
					key="scoreboard.timeToRefresh" /></label>
			<div class="controls">
				<input type="text" id="scoreboard.timeToRefresh" name="scoreboard.timeToRefresh"
					placeholder="<fmt:message key="scoreboard.timeToRefresh" />"
					value="${scoreboard.timeToRefresh}">
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label" for="scoreboard.qtyRows"><fmt:message
					key="scoreboard.qtyRows" /></label>
			<div class="controls">
				<input type="text" id="scoreboard.qtyRows" name="scoreboard.qtyRows"
					placeholder="<fmt:message key="scoreboard.qtyRows" />"
					value="${scoreboard.qtyRows}">
			</div>
		</div>
		
		<div id="stages">
			<legend>
				<fmt:message key="championship.championshipStages" />
			</legend>
			
			<table class="table table-striped table-bordered" id="championshipStages">
				<thead>
					<tr>
						<th>#</th>
						<th><fmt:message key="championship" /></th>
						<th><fmt:message key="modality" /></th>
						<th><fmt:message key="championshipStage" /></th>
					</tr>
				</thead>
				<tbody>
				<c:forEach var="championshipStage"
					items="${championshipStages}" varStatus="iteration">
					
					<c:set var="checked" value="" scope="page" />
					<c:forEach var="scoreboardStage" items="${scoreboard.scoreboardStage}">
						<c:if test="${championshipStage eq scoreboardStage.championshipStage}">
							<c:set var="checked" value="checked" scope="page" />
						</c:if>
					</c:forEach>
					
					<tr>
						<td><input type="checkbox" name="scoreboard.scoreboardStage[${iteration.index}].championshipStage.id" id="scoreboard.scoreboardStage[${iteration.index}].championshipStage.id" value="${championshipStage.id}" <c:if test="${checked eq 'checked'}">checked="checked"</c:if>/></td>
						<td>${championshipStage.championship.description}</td>
						<td>${championshipStage.championship.modality.description}</td>
						<td>${championshipStage.description}</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
		</div>
		
		<div class="control-group">
			<div class="controls">
				<button type="submit" class="btn btn-primary"
					<c:if test="${mode == 'delete'}">name="_method" value="DELETE"</c:if>
					<c:if test="${empty mode}">name="_method" value="PUT"</c:if>>
					<fmt:message key="save" />
				</button>
			</div>
		</div>
	</fieldset>
</form>


<%@ include file="/include_js.jsp"%>

<!-- Activate form validator -->
<script type="text/javascript">
	$(document).ready(function() {
		$('#championshipStages').dataTable({"bAutoWidth": true});
		
		$('#scoreboardForm').validate({
			rules : {
				'scoreboard.description' : {
					minlength : 10,
					maxlength : 50,
					required : true
				},

				'scoreboard.timeToRefresh' : {
					min: 1,
					max : 99999999,
					required : true
				},

				'scoreboard.qtyRows' : {
					min: 1,
					max : 99999999,
					required : true
				}
			},

			highlight : function(label) {
				$(label).closest('.control-group').removeClass('success');
				$(label).closest('.control-group').addClass('error');
			},

			success : function(label) {
				$(label).closest('.control-group').removeClass('error');
				$(label).closest('.control-group').addClass('success');
			}
		});

	});
</script>

<%@ include file="/footer-admin.jsp"%>

