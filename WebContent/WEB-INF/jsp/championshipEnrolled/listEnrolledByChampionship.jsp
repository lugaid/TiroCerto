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
				"mDataProp" : "championship_id",
				"bVisible" : false,
				"bSearchable" : false,
				"bSortable" : false
			}, {
				"sTitle" : "<fmt:message key="associate.id" />",
				"mDataProp" : "associate_id",
				"bVisible" : false,
				"bSearchable" : false,
				"bSortable" : false
			}, {
				"sTitle" : "<fmt:message key="select" />",
				"mDataProp": "associate_id",
				"sWidth" : "10%",
				"bSearchable" : false,
				"bSortable" : false,
	            "fnRender": function (oObj) {
	            	checkbox = '<i class="icon-ok-sign" data-dismiss="modal" aria-hidden="true" onclick="returnAssociate(' + oObj.aData['championship_id']  + ',' + oObj.aData['associate_id']  + ',\'' + oObj.aData['associate_name']  + '\');"></i>';
	            	
	                return  checkbox; 
	            }
	        }, {
				"sTitle" : "<fmt:message key="associate.cr" />",
				"mDataProp" : "associate_cr",
				"sDefaultContent" : "",
				"bSearchable" : false,
				"bSortable" : false
			}, {
				"sTitle" : "<fmt:message key="associate.name" />",
				"mDataProp" : "associate_name",
				"sDefaultContent" : ""
			}, {
				"sTitle" : "<fmt:message key="associate.email" />",
				"mDataProp" : "associate_email",
			}],

			"bAutoWidth": false,
			"bServerSide" : true,
			"sAjaxSource" : '<c:url value="/admin/championshipEnrolled/paginateEnrolledByChampionship/${championship.id}"/>',
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