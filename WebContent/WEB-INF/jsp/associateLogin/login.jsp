<%@ include file="/header.jsp"%>

<div class="offset3 span6">
	<c:if test="${not empty errors}">
		<div class="alert alert-error">
			<button type="button" class="close" data-dismiss="alert">×</button>
			<ul>
				<c:forEach items="${errors}" var="error">
					<li><strong><fmt:message key="${error.category}" /></strong>
						- ${error.message}</li>
				</c:forEach>
			</ul>
		</div>
	</c:if>

	<form class="form-horizontal"
		action="<c:url value="/admin/associate/login" />" method="post"
		id="loginForm">
		<fieldset>
			<legend>
				<fmt:message key="login.complete" />
			</legend>
		</fieldset>
		<div class="control-group input-prepend input-append">
			<label class="control-label" for="associate.email"><fmt:message
					key="associate.email" /></label>
			<div class="controls">
				<span class="add-on">@</span> <input type="text"
					id="associate.email" name="associate.email"
					placeholder="<fmt:message key="associate.email"/>">
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
			<div class="controls">
				<button type="submit" class="btn btn-primary">
					<fmt:message key="login" />
				</button>
			</div>
		</div>
	</form>
</div>

<%@ include file="/include_js.jsp"%>

<script type="text/javascript">
	$(document).ready(function() {

		$('#loginForm').validate({
			rules : {
				'associate.email' : {
					minlength : 5,
					maxlength : 50,
					required : true,
					email : true
				},

				'associate.password' : {
					minlength : 5,
					maxlength : 25,
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

<%@ include file="/footer.jsp"%>