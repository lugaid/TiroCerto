<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
    <h3>${championship.description}</h3>
</div>

<div class="modal-body">
	<table class="table table-striped table-bordered"
		id="championshipEnrolledList">
	</table>
</div>

<div class="modal-footer">
  <button class="btn" data-dismiss="modal" aria-hidden="true"><fmt:message key="close" /></button>
</div>


<%@ include file="/include_js.jsp"%>
  
<script type="text/javascript">
	$(document).ready(function() {

		$("#championshipEnrolledList").dataTable({

			"aoColumns" : [ {
				"sTitle" : "<fmt:message key="championship.id" />",
				"mDataProp" : "id",
				"bVisible" : false,
			}, {
				"sTitle" : "<fmt:message key="associate.id" />",
				"mDataProp" : "id",
				"bVisible" : false,
			}, {
				"sTitle" : "<fmt:message key="championshipEnrolled" />",
				"mDataProp": "enrolled",
				"bSortable" : false,
				"sWidth" : "10%",
	            "fnRender": function (oObj) {
	            	checkbox = '<input name="enrolled[' + oObj.aData['id'] +']" id="enrolled[' + oObj.aData['id'] +']" type=\"checkbox\" value="'+ oObj.aData['id'] +'"';
	            	
	            	if(oObj.aData['enrolled'] =="checked")
	            		checkbox += 'checked = "'+ oObj.aData['enrolled'] +'"';
	            	
	            	checkbox += '>';
	            	
	                return  checkbox; 
	            }
	        }, {
				"sTitle" : "<fmt:message key="associate.cr" />",
				"mDataProp" : "cr",
				"sDefaultContent" : ""
			}, {
				"sTitle" : "<fmt:message key="associate.name" />",
				"mDataProp" : "name",
				"sDefaultContent" : ""
			}, {
				"sTitle" : "<fmt:message key="associate.email" />",
				"mDataProp" : "email",
			}],

			"bAutoWidth": false,
			"bServerSide" : true,
			"sAjaxSource" : '<c:url value="/admin/championshipEnrolled/paginate/${championship.id}"/>',
			
			"fnInitComplete": function(oSettings, json) {
				$("input:regex(name,^enrolled\\[.*\\]\\$)").each(function() {
					$(this).change(function() {
						if($(this).is(':checked')) {
							method = '&_method=PUT';
							successMsg = '<fmt:message key="championshipEnrolled" /> - <fmt:message key="success.new" />';
						} else {
							method = '&_method=DELETE';
							successMsg = '<fmt:message key="championshipEnrolled" /> - <fmt:message key="success.delete" />';
						}

						$.ajax({
							url: '<c:url value="/admin/championshipEnrolled"/>',
				            data: 'championshipEnrolled.championship.id=${championship.id}&championshipEnrolled.associate.id=' + $(this).val() + method,
				            type: 'POST',
				            dataType: 'json',
				            success: function(data){
								alert(successMsg);
				            }
						});
					});
				});
			}
		});
	});
	
</script>

<script type="text/javascript" charset="utf-8">
	$.extend(true, $.fn.dataTable.defaults, {
		"oLanguage": {
            "sUrl": "<c:url value="/localization/messages_datatables_pt_BR.js" />",
            "codification": "UTF-8"
        }
    });
</script>