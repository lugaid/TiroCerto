<%@ include file="/header-admin.jsp"%>

<c:if test="${not empty errors}">
	<div class="alert alert-error">
		<button type="button" class="close" data-dismiss="alert">×</button>
		<ul>
			<c:forEach items="${errors}" var="error">
				<li><strong><fmt:message
							key="modality.${error.category }" /></strong> - ${error.message }</li>
			</c:forEach>
		</ul>
	</div>
</c:if>

<form class="form-horizontal" method="post" id="configureForm"
	action="<c:url value="/admin/modality"/>">
	<fieldset>
		<legend>
			<fmt:message key="scoreboard.configure" />
		</legend>

		<input type="hidden" id="modality.id" name="modality.id"
			value="${modality.id}">

		<div class="control-group">
			<label class="control-label" for="modality.description"><fmt:message
					key="modality.description" /></label>
			<div class="controls">
				<input type="text" id="modality.description" name="modality.description"
					placeholder="<fmt:message key="modality.description" />"
					value="${modality.description}">
			</div>
		</div>
		
		<div id="stages">
			<legend>
				<fmt:message key="championship.championshipStages" />
			</legend>
		</div>
		
		<div class="control-group">
			<div class="controls">
				<a href="javascript:;" class="btn btn-primary" onclick="showScoreboard()">
					<fmt:message key="save" />
				</a>
			</div>
		</div>
	</fieldset>
</form>


<%@ include file="/include_js.jsp"%>

<!-- Activate form validator -->
<script type="text/javascript">
	$(document).ready(function() {

		$('#modalityForm').validate({
			rules : {
				'modality.description' : {
					minlength : 10,
					maxlength : 50,
					required : true
				}, 
				
				'modality.modalityPointType' : {
					required : true
				},
				
				'modality.maxSeries' : {
					min: 1,
					max : 100,
					required : true
				},
				
				'modality.qtySeries' : {
					min: 1,
					max : function(value, element) {
						return $('#modality\\.maxSeries').val();
					} ,
					required : true
				},
				
				'modality.targetXValue' : {
					min: 1,
					required : true
				},
				
				'modality.targetQtyDivisions' : {
					min: 1,
					max: 10,
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
	
	function showScoreboard() {
		window.open('<c:url value="/admin/scoreboard/show"/>', '_blank', 'status=0,titlebar=0,toolbar=0');
	}
</script>
<%@ include file="/footer-admin.jsp"%>

