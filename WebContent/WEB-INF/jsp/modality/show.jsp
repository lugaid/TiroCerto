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

<form class="form-horizontal" method="post" id="modalityForm"
	action="<c:url value="/admin/modality"/>">
	<fieldset>
		<legend>
			<fmt:message key="modality.model.description" />
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

		<div class="control-group">
			<label class="control-label" for="modality.modalityPointType"><fmt:message
					key="modality.modalityPointType" /></label>
			<div class="controls">
				<select class="combobox" id="modality.modalityPointType"
					name="modality.modalityPointType">
					<option></option>
					<c:forEach var="ModalityPointType" items="${ModalityPointTypes}">
						<option value="${ModalityPointType}"
							<c:if test="${ModalityPointType == modality.modalityPointType}">selected="selected"</c:if>>
							<fmt:message key="modality.modalityPointType.${ModalityPointType}" />
						</option>
					</c:forEach>
				</select>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label" for="modality.maxSeries"><fmt:message
					key="modality.maxSeries" /></label>
			<div class="controls">
				<input type="text" id="modality.maxSeries" name="modality.maxSeries"
					placeholder="<fmt:message key="modality.maxSeries" />"
					value="${modality.maxSeries}">
			</div>
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

