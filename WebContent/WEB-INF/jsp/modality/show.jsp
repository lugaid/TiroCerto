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

		<div id="targetDivisions">
			<legend>
				<fmt:message key="modality.modalityTargetDivisions" />
			</legend>
			
			<a href="#" id="addModalityTargetDivision"><fmt:message key="add.new" /></a>
			
			<c:set var="modalityTargetDivisionsQty" value="0" scope="page"/> 

			
			<div id="modalityTargetDivisions">
				<c:forEach var="modalityTargetDivision" items="${modality.modalityTargetDivisions}"  varStatus="iteration">
					<div id="modalityTargetDivision" class="control-group">
							<input type="hidden" id="modality.modalityTargetDivisions[${iteration.index}].id" name="modality.modalityTargetDivisions[${iteration.index}].id"
								value="${modalityTargetDivision.id}">
								
					        <div id="controls" class="controls form-inline">
					        	<label for="modalityTargetDivision.points"><fmt:message
									key="modalityTargetDivision.points" /></label>
								<input type="text" class="input-small" id="modality.modalityTargetDivisions[${iteration.index}].points" name="modality.modalityTargetDivisions[${iteration.index}].points"
									placeholder="<fmt:message key="modalityTargetDivision.points" />"
									value="${modalityTargetDivision.points}">
									
							 	<c:if test="${iteration.index != 0}">
					        		<a href="#" onclick="$(this).closest('#modalityTargetDivision').remove();"><i class="icon-remove-sign"></i></a>
					        	</c:if>
					        </div>
					        
					        <c:set var="modalityTargetDivisionsQty" value="${iteration.index}" scope="page"/> 
					</div>
				</c:forEach>
				
				<c:if test="${empty modality.modalityTargetDivisions}">
					<div id="modalityTargetDivision" class="control-group">
						<input type="hidden" id="modality.modalityTargetDivisions[0].id" name="modality.modalityTargetDivisions[0].id">
							
				        <div id="controls" class="controls form-inline">
				        	<label for="modalityTargetDivision.points"><fmt:message
								key="modalityTargetDivision.points" /></label>
							<input type="text" class="input-small" id="modality.modalityTargetDivisions[0].points" name="modality.modalityTargetDivisions[0].points"
								placeholder="<fmt:message key="modalityTargetDivision.points" />">
				        </div>
					</div>
				</c:if>
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
		
		$("input:regex(name,^modality\\.modalityTargetDivisions\\[.*\\]\\.points$)").each(function() {
		    $(this).rules("add", { required : true });
		});
	});
</script>

<!-- Check-box click action -->
<script type="text/javascript">
	var modalityTargetDivisionsQty = ${modalityTargetDivisionsQty};

	$(document).ready(function() {
		$('#modality\\.modalityPointType').change(function() {
			if ($('#modality\\.modalityPointType').val() == "TARGET") {
				$('#targetDivisions').show();
			} else {
				$('#targetDivisions').hide();
			}
		}).change();
	});
	
	$(document).ready(function(){
        $("#addModalityTargetDivision").click(function(){
        	modalityTargetDivisionsQty += 1;
            
            var newMod = '<div id="modalityTargetDivision" class="control-group">';
            newMod += '<input type="hidden" id="modality.modalityTargetDivisions[' + modalityTargetDivisionsQty + '].id" name="modality.modalityTargetDivisions[' + modalityTargetDivisionsQty + '].id">';
           	newMod += '<div id="controls" class="controls form-inline">';
            newMod += '<label for="modalityTargetDivision.points"><fmt:message key="modalityTargetDivision.points" /></label>';
            newMod += '<input type="text" class="input-small" id="modality.modalityTargetDivisions[' + modalityTargetDivisionsQty + '].points" name="modality.modalityTargetDivisions[' + modalityTargetDivisionsQty + '].points"';
            newMod += 'placeholder="<fmt:message key="modalityTargetDivision.points" />">';
            newMod += '<a href="#" onclick="$(this).closest(\'#modalityTargetDivision\').remove();"><i class="icon-remove-sign"></i></a>';
            newMod += '</div>';
            newMod += '</div>';
            
            $("#modalityTargetDivisions").append(newMod);
            
            $("input:regex(name,^modality\\.modalityTargetDivisions\\[.*\\]\\.points$)").each(function() {
    		    $(this).rules("add", { required : true });
    		});
        });
	 });
</script>

<%@ include file="/footer-admin.jsp"%>

