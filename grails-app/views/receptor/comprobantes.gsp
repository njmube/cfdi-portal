<!DOCTYPE html>
<html>
	<head>
		
		<title>${receptorInstance.rfc}</title>
		<asset:stylesheet src="datatables/datatables.css"/>
		<asset:javascript src="datatables/datatables.js"/>
	</head>
	
	<body>
		<div class="container">
			
			<div class="row">
				<div class="col-md-12">
					<div class="well">
						<h3>${receptorInstance.nombre} <small>(Cfdis recibidos)</small></h3>
						<g:if test="${flash.message}">
							<span class="label label-warning text-center">${flash.message}</span>
						</g:if>
					</div>
				</div>
				
			</div><!-- end .row 1 -->
			
			<div class="row">
				<div class="col-md-12">
					<div class="button-panel">
						<div class="btn-group">
							<input type='text' id="totalField" placeholder="Total" class="form-control" >
						</div>

						<div class="btn-group">
							<input type='text' id="uuidField" placeholder="UUID" class="form-control" >
						</div>
						
						<div class="btn-group">
							<g:link action="show" class="btn btn-default" id="${receptorInstance.id}">
								<i class="fa fa-arrow-left fa-lg"></i></span> Regresar
							</g:link>
						</div>
						<div class="btn-group">
							<button type="button" name="reportes"
									class="btn btn-default dropdown-toggle" data-toggle="dropdown"
									role="menu">
									Reportes <span class="caret"></span>
							</button>
							<ul class="dropdown-menu">
								<li><a href="#">Reporte 1</a></li>
							    <li><a href="#">Reporte 2</a></li>
							    <li class="divider"></li>
							    <li><a href="#">Reporte 3</a></li>
							</ul>
						</div>
						
					</div>
					
				</div>
			</div>

			<div class="row">
				<div class="col-md-12">
					<g:render template="cfdisDeReceptorGrid"/>
				</div>
			</div>

			
		
		</div>

			<script type="text/javascript">
				$(function(){
					var table=$("#comprobantesTable").dataTable({
				        "paging":   false,
				        "ordering": false,
				        "info":     false,
				         "dom":'t'
						});
						
						$("#uuidField").keyup(function(){
		  					table.DataTable().column(3).search( $(this).val() ).draw();
						});
						$("#totalField").keyup(function(){
		  					table.DataTable().column(6).search( $(this).val() ).draw();
						});
						
				});
			</script>
	</body>
	
</html>
