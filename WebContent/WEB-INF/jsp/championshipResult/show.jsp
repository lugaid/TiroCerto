<%@ include file="/header-admin.jsp"%>

<c:if test="${not empty errors}">
	<div class="alert alert-error">
		<button type="button" class="close" data-dismiss="alert">×</button>
		<ul>
			<c:forEach items="${errors}" var="error">
				<li><strong><fmt:message
							key="championshipResult.${error.category }" /></strong> - ${error.message
					}</li>
			</c:forEach>
		</ul>
	</div>
</c:if>

<div class="modal hide fade" id="res-modal">

</div>

<form class="form-horizontal" method="post" id="championshipResultForm"
	action="<c:url value="/admin/championshipResult"/>">
	<fieldset>
		<legend>
			<p style="text-align: center;">
				${championshipStage.championship.description} -
				<fmt:formatDate value="${championshipStage.date.time}"
					pattern="dd/MM/yyyy" />
			</p>
			<fmt:message key="championshipResult.model.description" />
		</legend>

		<input type="hidden" id="championshipResult.id"
			name="championshipResult.id" value="${championshipResult.id}">

		<input type="hidden" id="championshipResult.championshipStage.id"
			name="championshipResult.championshipStage.id"
			value="${championshipStage.id}">

		<div class="control-group">
			<label class="control-label" for="associate.name"><fmt:message
					key="associate" /></label>
			<div class="controls">
				<div class="input-append">
					<span id="searchAssociate" class="add-on"><i class="icon-search"></i></span> <input
						type="text" id="associate.name" name="associate.name"
						placeholder="<fmt:message key="associate.name"/>"
						value="${championshipResult.championshipEnrolled.associate.name}"
						readonly="readonly"> <input type="hidden"
						id="championshipResult.championshipEnrolled.associate.id"
						name="championshipResult.championshipEnrolled.associate.id"
						value="${championshipResult.championshipEnrolled.associate.id}">
						<input type="hidden"
						id="championshipResult.championshipEnrolled.championship.id"
						name="championshipResult.championshipEnrolled.championship.id"
						value="${championshipResult.championshipEnrolled.championship.id}">
				</div>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label" for="championshipResult.serie"><fmt:message
					key="championshipResult.serie" /></label>
			<div class="controls">
				<select class="combobox" id="championshipResult.serie"
					name="championshipResult.serie">
					<option></option>
					<c:forEach var="i" begin="1"
						end="${championshipStage.championship.modality.maxSeries}"
						step="1" varStatus="status">
						<option value="${i}"
							<c:if test="${i == championshipResult.serie}">selected="selected"</c:if>>
							<fmt:message key="championshipResult.serie" />
							${i}
						</option>
					</c:forEach>
				</select>
			</div>
		</div>

		<c:set var="targetQtyDivisions"
			value="${championshipStage.championship.modality.targetQtyDivisions}"
			scope="page" />
		<c:if
			test="${championshipStage.championship.modality.modalityPointType == 'TARGET'}">
			<legend>
				<fmt:message key="modality.modalityPointType.TARGET" />
			</legend>

			<table>
				<tbody>
					<tr>
						<td>
							<div class="control-group">
								<label class="control-label"
									for="championshipResult.targetDivisionX"><fmt:message
										key="championshipResult.targetDivisionX" />
									(${championshipStage.championship.modality.targetXValue} <fmt:message
										key="championshipResult.points" />)</label>
								<div class="controls">
									<input class="input-mini" type="text"
										id="championshipResult.targetDivisionX"
										name="championshipResult.targetDivisionX"
										placeholder="<fmt:message key="championshipResult.targetDivisionX"/>"
										value="${championshipResult.targetDivisionX}">
								</div>
							</div>
						</td>

						<c:if test="${targetQtyDivisions >= 1}">
							<td>
								<div class="control-group">
									<label class="control-label"
										for="championshipResult.targetDivision10"><fmt:message
											key="championshipResult.targetDivision10" /></label>
									<div class="controls">
										<input class="input-mini" type="text"
											id="championshipResult.targetDivision10"
											name="championshipResult.targetDivision10"
											placeholder="<fmt:message key="championshipResult.targetDivision10"/>"
											value="${championshipResult.targetDivision10}">
									</div>
								</div>
							</td>
						</c:if>

						<c:if test="${targetQtyDivisions >= 2}">
							<td>
								<div class="control-group">
									<label class="control-label"
										for="championshipResult.targetDivision9"><fmt:message
											key="championshipResult.targetDivision9" /></label>
									<div class="controls">
										<input class="input-mini" type="text"
											id="championshipResult.targetDivision9"
											name="championshipResult.targetDivision9"
											placeholder="<fmt:message key="championshipResult.targetDivision9"/>"
											value="${championshipResult.targetDivision9}">
									</div>
								</div>
							</td>
						</c:if>
					</tr>

					<tr>
						<c:if test="${targetQtyDivisions >= 3}">
							<td>
								<div class="control-group">
									<label class="control-label"
										for="championshipResult.targetDivision8"><fmt:message
											key="championshipResult.targetDivision8" /></label>
									<div class="controls">
										<input class="input-mini" type="text"
											id="championshipResult.targetDivision8"
											name="championshipResult.targetDivision8"
											placeholder="<fmt:message key="championshipResult.targetDivision8"/>"
											value="${championshipResult.targetDivision8}">
									</div>
								</div>
							</td>
						</c:if>

						<c:if test="${targetQtyDivisions >= 4}">
							<td>
								<div class="control-group">
									<label class="control-label"
										for="championshipResult.targetDivision7"><fmt:message
											key="championshipResult.targetDivision7" /></label>
									<div class="controls">
										<input class="input-mini" type="text"
											id="championshipResult.targetDivision7"
											name="championshipResult.targetDivision7"
											placeholder="<fmt:message key="championshipResult.targetDivision7"/>"
											value="${championshipResult.targetDivision7}">
									</div>
								</div>
							</td>
						</c:if>

						<c:if test="${targetQtyDivisions >= 5}">
							<td>
								<div class="control-group">
									<label class="control-label"
										for="championshipResult.targetDivision6"><fmt:message
											key="championshipResult.targetDivision6" /></label>
									<div class="controls">
										<input class="input-mini" type="text"
											id="championshipResult.targetDivision6"
											name="championshipResult.targetDivision6"
											placeholder="<fmt:message key="championshipResult.targetDivision6"/>"
											value="${championshipResult.targetDivision6}">
									</div>
								</div>
							</td>
						</c:if>

					</tr>

					<tr>
						<c:if test="${targetQtyDivisions >= 6}">
							<td>
								<div class="control-group">
									<label class="control-label"
										for="championshipResult.targetDivision5"><fmt:message
											key="championshipResult.targetDivision5" /></label>
									<div class="controls">
										<input class="input-mini" type="text"
											id="championshipResult.targetDivision5"
											name="championshipResult.targetDivision5"
											placeholder="<fmt:message key="championshipResult.targetDivision5"/>"
											value="${championshipResult.targetDivision5}">
									</div>
								</div>
							</td>
						</c:if>
						<c:if test="${targetQtyDivisions >= 7}">
							<td>
								<div class="control-group">
									<label class="control-label"
										for="championshipResult.targetDivision4"><fmt:message
											key="championshipResult.targetDivision4" /></label>
									<div class="controls">
										<input class="input-mini" type="text"
											id="championshipResult.targetDivision4"
											name="championshipResult.targetDivision4"
											placeholder="<fmt:message key="championshipResult.targetDivision4"/>"
											value="${championshipResult.targetDivision4}">
									</div>
								</div>
							</td>
						</c:if>

						<c:if test="${targetQtyDivisions >= 8}">
							<td>
								<div class="control-group">
									<label class="control-label"
										for="championshipResult.targetDivision3"><fmt:message
											key="championshipResult.targetDivision3" /></label>
									<div class="controls">
										<input class="input-mini" type="text"
											id="championshipResult.targetDivision3"
											name="championshipResult.targetDivision3"
											placeholder="<fmt:message key="championshipResult.targetDivision3"/>"
											value="${championshipResult.targetDivision3}">
									</div>
								</div>
							</td>
						</c:if>
					</tr>

					<tr>
						<c:if test="${targetQtyDivisions >= 9}">
							<td>
								<div class="control-group">
									<label class="control-label"
										for="championshipResult.targetDivision2"><fmt:message
											key="championshipResult.targetDivision2" /></label>
									<div class="controls">
										<input class="input-mini" type="text"
											id="championshipResult.targetDivision2"
											name="championshipResult.targetDivision2"
											placeholder="<fmt:message key="championshipResult.targetDivision2"/>"
											value="${championshipResult.targetDivision2}">
									</div>
								</div>
							</td>
						</c:if>

						<c:if test="${targetQtyDivisions >= 10}">
							<td>
								<div class="control-group">
									<label class="control-label"
										for="championshipResult.targetDivision1"><fmt:message
											key="championshipResult.targetDivision1" /></label>
									<div class="controls">
										<input class="input-mini" type="text"
											id="championshipResult.targetDivision1"
											name="championshipResult.targetDivision1"
											placeholder="<fmt:message key="championshipResult.targetDivision1"/>"
											value="${championshipResult.targetDivision1}">
									</div>
								</div>
							</td>
						</c:if>
					</tr>
				</tbody>
			</table>
		</c:if>

		<legend>
			<fmt:message key="championshipResult.points" />
		</legend>

		<table>
			<tbody>
				<tr>
					<td>
						<div class="control-group">
							<label class="control-label" for="championshipResult.points"><fmt:message
									key="championshipResult.points" /></label>
							<div class="controls">
								<input class="input-mini" type="text"
									id="championshipResult.points" name="championshipResult.points"
									placeholder="<fmt:message key="championshipResult.points"/>"
									value="${championshipResult.points}"
									<c:if test="${championshipStage.championship.modality.modalityPointType == 'TARGET'}">
										readonly="readonly"
									</c:if> />
							</div>
						</div>
					</td>
					<td>
						<div class="control-group">
							<label class="control-label" for="championshipResult.penalty"><fmt:message
									key="championshipResult.penalty" /></label>
							<div class="controls">
								<input class="input-mini" type="text"
									id="championshipResult.penalty"
									name="championshipResult.penalty"
									placeholder="<fmt:message key="championshipResult.penalty"/>"
									value="${championshipResult.penalty}" />
							</div>
						</div>
					</td>
					<td>
						<div class="control-group">
							<label class="control-label" for="championshipResult.total"><fmt:message
									key="championshipResult.total" /></label>
							<div class="controls">
								<input class="input-mini" type="text"
									id="championshipResult.total" name="championshipResult.total"
									placeholder="<fmt:message key="championshipResult.total"/>"
									value="${championshipResult.total}" readonly="readonly" />
							</div>
						</div>
					</td>
				</tr>
			</tbody>
		</table>

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

		$('#championshipResultForm').validate({
			rules : {
				'championshipResult.serie' : {
					min : 1,
					required : true
				},

				'associate.name' : {
					required : true
				},

				'championshipResult.targetDivisionX' : {
					min : 0
				},

				'championshipResult.targetDivision10' : {
					min : 0
				},

				'championshipResult.targetDivision9' : {
					min : 0
				},

				'championshipResult.targetDivision8' : {
					min : 0
				},

				'championshipResult.targetDivision7' : {
					min : 0
				},

				'championshipResult.targetDivision6' : {
					min : 0
				},

				'championshipResult.targetDivision5' : {
					min : 0
				},

				'championshipResult.targetDivision4' : {
					min : 0
				},

				'championshipResult.targetDivision3' : {
					min : 0
				},

				'championshipResult.targetDivision2' : {
					min : 0
				},

				'championshipResult.targetDivision1' : {
					min : 0
				},

				'championshipResult.penalty' : {
					min : 0,
					required : true
				},

				'championshipResult.point' : {
					min : 0
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

	function totalRecalc() {
		<c:if test="${championshipStage.championship.modality.modalityPointType == 'TARGET'}">
		var point = $('#championshipResult\\.targetDivisionX').val() * ${championshipStage.championship.modality.targetXValue};
		point += $('#championshipResult\\.targetDivision10').val() * 10;
		point += $('#championshipResult\\.targetDivision9').val() * 9;
		point += $('#championshipResult\\.targetDivision8').val() * 8;
		point += $('#championshipResult\\.targetDivision7').val() * 7;
		point += $('#championshipResult\\.targetDivision6').val() * 6;
		point += $('#championshipResult\\.targetDivision5').val() * 5;
		point += $('#championshipResult\\.targetDivision4').val() * 4;
		point += $('#championshipResult\\.targetDivision3').val() * 3;
		point += $('#championshipResult\\.targetDivision2').val() * 2;
		point += $('#championshipResult\\.targetDivision1').val() * 1;

		$('#championshipResult\\.points').val(point);
		</c:if>

		var total = $('#championshipResult\\.points').val()
				- $('#championshipResult\\.penalty').val();

		$('#championshipResult\\.total').val(total);
	}

	$(document).ready(
			function() {
				$('#championshipResult\\.targetDivisionX').on(
						"change keyup paste", function() {
							totalRecalc();
						}).change();

				$('#championshipResult\\.targetDivision10').on(
						"change keyup paste", function() {
							totalRecalc();
						}).change();

				$('#championshipResult\\.targetDivision9').on(
						"change keyup paste", function() {
							totalRecalc();
						}).change();

				$('#championshipResult\\.targetDivision8').on(
						"change keyup paste", function() {
							totalRecalc();
						}).change();

				$('#championshipResult\\.targetDivision7').on(
						"change keyup paste", function() {
							totalRecalc();
						}).change();

				$('#championshipResult\\.targetDivision6').on(
						"change keyup paste", function() {
							totalRecalc();
						}).change();

				$('#championshipResult\\.targetDivision5').on(
						"change keyup paste", function() {
							totalRecalc();
						}).change();

				$('#championshipResult\\.targetDivision4').on(
						"change keyup paste", function() {
							totalRecalc();
						}).change();

				$('#championshipResult\\.targetDivision3').on(
						"change keyup paste", function() {
							totalRecalc();
						}).change();

				$('#championshipResult\\.targetDivision2').on(
						"change keyup paste", function() {
							totalRecalc();
						}).change();

				$('#championshipResult\\.targetDivision1').on(
						"change keyup paste", function() {
							totalRecalc();
						}).change();

				$('#championshipResult\\.points').on("change keyup paste",
						function() {
							totalRecalc();
						}).change();

				$('#championshipResult\\.penalty').on("change keyup paste",
						function() {
							totalRecalc();
						}).change();
				
				$('#searchAssociate').on('click', function() { 	
			    	var url = '<c:url value="/admin/championshipEnrolled/enrolledByChampionship/${championshipStage.championship.id}"/>';
			        $("#res-modal").load(url).modal('show');
			   });
			});
	
	function returnAssociate(championshipId, associateId, name) {
		$("#championshipResult\\.championshipEnrolled\\.championship\\.id").val(championshipId);
		$("#championshipResult\\.championshipEnrolled\\.associate\\.id").val(associateId);
		$("#associate\\.name").val(name);
	}
</script>

<style type="text/css">
#res-modal {
	width: 700px; /* SET THE WIDTH OF THE MODAL */
	margin: -250px 0 0 -350px; /* CHANGE MARGINS TO ACCOMODATE THE NEW WIDTH (original = margin: -250px 0 0 -280px;) */
}
</style>

<%@ include file="/footer-admin.jsp"%>
