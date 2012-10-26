<%@ include file="/header.jsp"%>

<div class="form-actions">
	<form class="form-horizontal" method="post" id="associateForm" action="<c:url value="/associate"/>">
		<fieldset>
			<legend>
				<fmt:message key="associate.model.description" />
			</legend>
			
			<input type="hidden" id="associate.id" name="associate.id" value="${associate.id}">
			
			<div class="control-group">
				<label class="control-label" for="associate.cr"><fmt:message
						key="associate.cr" /></label>
				<div class="controls">
					<input type="text" id="associate.cr" name="associate.cr"
						placeholder="<fmt:message key="associate.cr" />"
						value="${associate.cr}">
				</div>
			</div>

			<div class="control-group">
				<label class="control-label" for="associate.name"><fmt:message
						key="associate.name" /></label>
				<div class="controls">
					<input type="text" id="associate.name" name="associate.name"
						placeholder="<fmt:message key="associate.name"/>"
						value="${associate.name}">
				</div>
			</div>

			<div class="control-group input-prepend input-append">
				<label class="control-label" for="associate.email"><fmt:message
						key="associate.email" /></label>
				<div class="controls">
					<span class="add-on">@</span> <input type="text"
						id="associate.email" name="associate.email"
						placeholder="<fmt:message key="associate.email"/>"
						value="${associate.email}">
				</div>
			</div>

			<div class="control-group">
				<label class="control-label" for="associate.associateType"><fmt:message
						key="associate.associateType" /></label>
				<div class="controls">
					<select class="combobox" id="associate.associateType"
						name="associate.associateType">
						<option></option>
						<c:forEach var="AssociateType" items="${AssociateTypes}">
							<option value="${AssociateType}" <c:if test="${AssociateType == associate.associateType}">selected="selected"</c:if>>
								<fmt:message key="associate.associateType.${AssociateType}" />
							</option>
						</c:forEach>
					</select>
				</div>
			</div>

			<div class="control-group">
				<label class="control-label" for="associate.password"><fmt:message
						key="associate.password" /></label>
				<div class="controls">
					<input type="password" id="associate.password"
						name="associate.password"
						placeholder="<fmt:message key="associate.password" />">
				</div>
			</div>

			<div class="control-group">
				<label class="control-label" for="associate.confirmPassword"><fmt:message
						key="associate.confirmPassword" /></label>
				<div class="controls">
					<input type="password" id="associate.confirmPassword"
						name="associate.confirmPassword"
						placeholder="<fmt:message key="associate.confirmPassword" />">
				</div>
			</div>
			
			<div class="control-group">
				<div class="controls">
					<button type="submit" class="btn btn-primary"
						<c:if test="${mode == 'delete'}">name="_method" value="DELETE"</c:if>
						<c:if test="${empty mode}">name="_method" value="PUT"</c:if>>
						<fmt:message key="save" />
					</button>
					<button type="reset" class="btn">
						<fmt:message key="clear" />
					</button>
				</div>
			</div>
		</fieldset>
	</form>
</div>

<%@ include file="/include_js.jsp"%>

<!-- Activate form validator -->
<script type="text/javascript">
	$(document).ready(function() {

		$('#associateForm').validate({
			rules : {
				'associate.cr' : {
					minlength : 2,
					maxlength : 15,
					required : true
				},

				'associate.name' : {
					minlength : 10,
					maxlength : 50,
					required : true
				},

				'associate.email' : {
					required : true,
					email : true
				},

				'associate.associateType' : {
					required : true
				},
				
				'associate.password' : {
					minlength : 10,
					maxlength : 50,
					required : true
				},

				'associate.confirmPassword' : {
					minlength : 10,
					maxlength : 50,
					required : true,
					equalTo: "#associate\\.password"
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

<!-- Activate bootstrap-combo -->
<!-- <script type="text/javascript">
   $(document).ready(function(){
     $('.combobox').combobox();
   });
</script> -->

<%@ include file="/footer.jsp"%>

