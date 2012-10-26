<%@ include file="/header.jsp"%>

<div class="form-actions">
	<form class="form-horizontal"  method="post" id="registerHere">
	<fieldset>
		<legend><fmt:message key="associate.model.description" /></legend>
		<div class="control-group">
			<label class="control-label" for="associate.cr"><fmt:message key="associate.cr" /></label>
			<div class="controls">
				<input type="text" id="associate.cr" name="associate.cr" placeholder="<fmt:message key="associate.cr" />">
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label" for="associate.name"><fmt:message key="associate.name" /></label>
			<div class="controls">
				<input type="text" id="associate.name" name="associate.name" placeholder="<fmt:message key="associate.name" />">
			</div>
		</div>
		
		<div class="control-group input-prepend input-append">
			<label class="control-label" for="associate.email"><fmt:message key="associate.email" /></label>
			<div class="controls">
				<span class="add-on">@</span>
				<input type="text" id="associate.email" name="associate.email" placeholder="<fmt:message key="associate.email" />">
			</div>
		</div>

		<div class="control-group">
			<label class="control-label" for="associate.associateType"><fmt:message key="associate.associateType" /></label>
			<div class="controls">
				<select class="combobox" id="associate.associateType" name="associate.associateType">
					<option></option>
					<c:forEach var="AssociateType" items="${AssociateTypes}">
						<option value="${AssociateType}"><fmt:message key="associate.associateType.${AssociateType}" /></option>
					</c:forEach>
				</select>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label" for="associate.password"><fmt:message key="associate.password" /></label>
			<div class="controls">
				<input type="password" id="associate.password" name="associate.password" placeholder="<fmt:message key="associate.password" />">
			</div>
		</div>
	
		<div class="control-group">
			<div class="controls">
				<button type="submit" class="btn btn-primary"><fmt:message key="save" /></button>
				<button type="submit" class="btn"><fmt:message key="cancel" /></button>
			</div>
		</div>
		</fieldset>
	</form>
</div>

<%@ include file="/footer.jsp"%>

<script type="text/javascript">
  $(document).ready(function(){
    $('.combobox').combobox();
  });
</script>