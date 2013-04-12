<%@ include file="/header-admin.jsp"%>

<c:if test="${not empty errors}">
	<div class="alert alert-error">
		<button type="button" class="close" data-dismiss="alert">×</button>
		<ul>
			<c:forEach items="${errors}" var="error">
				<li><strong><fmt:message
							key="championship.${error.category }" /></strong> - ${error.message }</li>
			</c:forEach>
		</ul>
	</div>
</c:if>

<form class="form-horizontal" method="post" id="championshipForm"
	action="<c:url value="/admin/championship"/>">
	<fieldset>
		<legend>
			<fmt:message key="championship.model.description" />
		</legend>

		<input type="hidden" id="championship" name="championship.id"
			value="${championship.id}">

		<div class="control-group">
			<label class="control-label" for="championship.description"><fmt:message
					key="championship.description" /></label>
			<div class="controls">
				<input type="text" id="championship.description"
					name="championship.description"
					placeholder="<fmt:message key="championship.description" />"
					value="${championship.description}">
			</div>
		</div>

		<div class="control-group">
			<label class="control-label" for="championship.modality.id"><fmt:message
					key="championship.modality" /></label>
			<div class="controls">
				<select class="combobox" id="championship.modality.id"
					name="championship.modality.id">
					<option></option>
					<c:forEach var="Modality" items="${Modalities}">
						<option value="${Modality.id}"
							<c:if test="${Modality.id == championship.modality.id}">selected="selected"</c:if>>
							${Modality.description}</option>
					</c:forEach>
				</select>
			</div>
		</div>

		<div id="stages">
			<legend>
				<fmt:message key="championship.championshipStages" />
			</legend>

			<a href="#" id="addChampionshipStage"><fmt:message key="add.new" /></a>

			<c:set var="championshipStagesQty" value="0" scope="page" />

			<div id="championshipStages">
				<c:forEach var="championshipStage"
					items="${championship.championshipStages}" varStatus="iteration">

					<div id="championshipStage" class="control-group">
						<input type="hidden" id="championship.championshipStages[${iteration.index}].id"
							name="championship.championshipStages[${iteration.index}].id"
							value="${championshipStage.id}">

						<div id="controls" class="controls form-inline">
							<label
								for="championship.championshipStages[${iteration.index}].date"><fmt:message
									key="championshipStage.date" /></label>

							<div class="input-append date"
								id="championship.championshipStages[${iteration.index}].datePicker"
								name="championship.championshipStages[${iteration.index}].datePicker"
								 data-date-format="dd/mm/yyyy" data-date-language="pt-BR"
								 data-date="<fmt:formatDate value="${championshipStage.date.time}" pattern="dd/MM/yyyy"/>">
								<input class="span2" size="16" type="text"
									id="championship.championshipStages[${iteration.index}].date"
									name="championship.championshipStages[${iteration.index}].date"
									readonly="" value="<fmt:formatDate value="${championshipStage.date.time}" pattern="dd/MM/yyyy"/>"><span class="add-on"><i
									class="icon-calendar"></i></span>
							</div>
							
							<c:if test="${iteration.index != 0}">
								<a href="#" onclick="$(this).closest('#championshipStage').remove();"><i class="icon-remove-sign"></i></a>
							</c:if>
						</div>
					</div>

					<c:set var="championshipStagesQty" value="${iteration.index}"
						scope="page" />
				</c:forEach>

				<c:if test="${empty championship.championshipStages}">
					<div id="championshipStage" class="control-group">
						<input type="hidden" id="championship.championshipStages[0].id"
							name="championship.championshipStages[0].id">

						<div id="controls" class="controls form-inline">
							<label
								for="championship.championshipStages[0].date"><fmt:message
									key="championshipStage.date" /></label>

							<div class="input-append date"
								id="championship.championshipStages[0].datePicker"
								name="championship.championshipStages[0].datePicker"
								 data-date-format="dd/mm/yyyy" data-date-language="pt-BR">
								<input class="span2" size="16" type="text"
									id="championship.championshipStages[0].date"
									name="championship.championshipStages[0].date"
									readonly=""><span class="add-on"><i
									class="icon-calendar"></i></span>
							</div>
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
	function addValidateStages() {
		$("input:regex(name,^championship\\.championshipStages\\[.*\\]\\.date$)").each(function() {
		    $(this).rules("add", {
				required : true,
				date: true});
		});
		
		$("div:regex(id,^championship\\.championshipStages\\[.*\\]\\.datePicker$)").each(function() {
		    $(this).datepicker();
		});
	}
	
	$(document).ready(function() {

		$('#championshipForm').validate({
			rules : {
				'championship.description' : {
					minlength : 10,
					maxlength : 50,
					required : true
				}, 
				
				'championship.modality.id' : {
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
		
		addValidateStages();
	});

	<!-- Check-box click action -->
	var championshipStagesQty = ${championshipStagesQty};

	$(document).ready(function(){
        $("#addChampionshipStage").click(function(){
        	championshipStagesQty += 1;

            var newMod = '<div id="championshipStage" class="control-group">';
            newMod += '<input type="hidden" id="championship.championshipStages[' + championshipStagesQty + '].id" name="championship.championshipStages[' + championshipStagesQty + '].id">';
           	newMod += '<div id="controls" class="controls form-inline">';
       		newMod += '<label for="championship.championshipStages[' + championshipStagesQty + '].date"><fmt:message key="championshipStage.date" /></label>';
			newMod += '<div class="input-append date" id="championship.championshipStages[' + championshipStagesQty + '].datePicker" name="championship.championshipStages[' + championshipStagesQty + '].datePicker" data-date-format="dd/mm/yyyy" data-date-language="pt-BR">'; 
			newMod += '<input id="championship.championshipStages[' + championshipStagesQty + '].date" name="championship.championshipStages[' + championshipStagesQty + '].date"class="span2" size="16" type="text" readonly=""><span class="add-on"><i class="icon-calendar"></i></span>';
			newMod += '</div>';
			newMod += '<a href="#" onclick="$(this).closest(\'#championshipStage\').remove();"><i class="icon-remove-sign"></i></a>';
			newMod += '</div>';
            newMod += '</div>';
            
            $("#championshipStages").append(newMod);
            
            addValidateStages();
        });
	 });
</script>

<%@ include file="/footer-admin.jsp"%>

