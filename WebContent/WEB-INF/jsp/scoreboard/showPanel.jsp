<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<!-- saved from url=(0060)http://getbootstrap.com/2.3.2/examples/starter-template.html -->
<html lang="en"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <title></title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="Emerson Rancoletta">

    <!-- Le styles -->
    <link href="<c:url value="/css/bootstrap.min.css" />" rel="stylesheet">
    <style>
      body {
        padding-top: 60px; /* 60px to make the container go all the way to the bottom of the topbar */
      }
    </style>
  </head>

  <body>

	<div id="loading" class="modal hide fade">
		<img alt="" src="<c:url value="/images/ajax-loader.gif" />">
	</div> <!-- /loading -->
	
	<div id="scoreboard">
	    <div class="navbar navbar-inverse navbar-fixed-top">
	      <div class="navbar-inner">
	        <div class="container">
	          <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
	            <span class="icon-bar"></span>
	            <span class="icon-bar"></span>
	            <span class="icon-bar"></span>
	          </button>
	          <div class="nav-collapse collapse">
	          	<h3 align="center" id="header-text">CAMPEONATO ANUAL - CARABINA 38 - ITU 14/04/2013</h3>
	          </div><!--/.nav-collapse -->
	        </div>
	      </div>
	    </div>
	
	    <div class="container" id="container">
			<br>
			<table class="table table-striped table-bordered" id="tbRanking">
	             <thead>
	               <tr>
	                 <th><fmt:message key="championshipStageRanking.position"/></th>
	                 <th id="associate_name"><fmt:message key="associate.name"/></th>
	                 <th id="serie_"></th>
	                 <th id="serie_"></th>
	                 <th id="serie_"></th>
	                 <th><fmt:message key="championshipStageRanking.penalty"/>*</th>
	                 <th><fmt:message key="championshipStageRanking.total"/>*</th>
	               </tr>
	             </thead>
	             <tbody>
	             </tbody>
	           </table>
	    </div> <!-- /container -->
	</div> <!-- /scoreboard -->
	
    <!-- Le javascript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <%@ include file="/include_js.jsp"%>
    
	<script>
		//default time to refresh
		var timeToRefresh = 15000;
		var rowIni = 0;
		var rowFin = 0;
		var iterations = 0;
		var timeFade = 0;
		
		$(document).ready(function() {
			//disable on press esc and click
			$('#loading').modal({
			  backdrop: 'static',
			  keyboard: false
			});
			
			startRefresh();
		});
		
		
		function startRefresh() {
			//hide page content
			$('#scoreboard').hide();

			//show loading
		    $('#loading').modal('show').css({
                'margin-top': function () {
                    return -($(this).height() / 2);
                },
                'margin-left': function () {
                    return -($(this).width() / 2);
                }
            });
			
			//refresh informations
			refreshInformations();
		}
		
		function refreshInformations() {
	        $.ajax({
				url: '<c:url value="/admin/scoreboard/refresh"/>/${scoreboard_id}',
				type: "GET",
		        dataType: "JSON",
		        success: function(data){
		        	refreshScreen(data);
	            },
	            error: function(data){
	            	$('#loading').modal('hide');
	            	$('#scoreboard').show('fast');
	            	
	            	setTimeout(startRefresh, timeToRefresh);
	            }
			});  
		}
		
		function refreshScreen(data) {
			//set title
			$("title").text(data.description);
			
			//set header text
			$('#header-text').text(data.descriptionTitle);

			//refresh
			refreshTable(data);
			
			//hide loading
			$('#loading').modal('hide');
			
			//show content
			$('#scoreboard').show('fast', function() {
				rowIni = 0;
				rowFin = 0;
				iterations = 0;
				timeFade = 0;
				
				refreshRows(data);
				
				//start refresh
				if(data.timeToRefresh > 0) {
					timeToRefresh = data.timeToRefresh * 1000;
				}
				
				var timetoRefreshPaginated = timeToRefresh * Math.ceil(data.championshipStageRankings.length / data.qtyRows);
				
				console.log("Atualizando dados em " + timetoRefreshPaginated);
				
			    setTimeout(startRefresh, timetoRefreshPaginated);
			});
		}
		
		function refreshTable(data) {
			//remove serie columns
			$("#tbRanking").find("th:regex(id,^serie_.*$)").remove();
			
			//create column series
			for (i=data.modalityMaxSeries;i>=1;i--) {
				var row = '<th id="serie_' + i + '">S' + i + '</th>';
				$("#tbRanking thead tr:first th:eq(1)").after(row);
			}
		}
		
		function refreshRows(data) {
			if(data.championshipStageRankings.length == 0) {
				return;	
			}
			
			if(iterations > 0 ) {
				rowIni = rowFin + 1;
				timeFade = 5000;
			}
			
			rowFin = rowIni + data.qtyRows - 1;
			
			if(rowFin > (data.championshipStageRankings.length - 1)) {
				rowFin = (data.championshipStageRankings.length - 1);
			}

			console.log("Mostrando linha de " + rowIni + ' até ' + rowFin + ' em ' + new Date());
			
			//remove all rows
			$("#tbRanking").find("tr:gt(0)").remove();
			
			//for each all rankings to make the row
			for (i=rowIni;i<=rowFin;i++) {
				var row = '<tr>';
				row += '<td>' + data.championshipStageRankings[i].position + '</td>';
				row += '<td>' + data.championshipStageRankings[i].associateName + '</td>';
				
				//for each all series
				for (u=0;u<data.championshipStageRankings[i].championshipResult.length;u++) {
					row += '<td>' + data.championshipStageRankings[i].championshipResult[u].total + '</td>';
				}
				
				row += '<td>' + data.championshipStageRankings[i].penalty + '</td>';
				row += '<td>' + data.championshipStageRankings[i].total + '</td>';
				row += '</tr>';
				
				$("#tbRanking > tbody").append(row);
			}
			
			iterations++;
			
			if(rowFin < (data.championshipStageRankings.length - 1)) {
				console.log("Atualizando linhas em " + timeToRefresh);
				setTimeout(function(){refreshRows(data);}, timeToRefresh);
			}
		}
	</script>
</body>

<style type="text/css">
	#loading {
		width: 100px; /* SET THE WIDTH OF THE MODAL */
	}
	</style>
</html>