<!DOCTYPE html>
<html>
	<head>
		
		<title>Comprobantes</title>
		<asset:stylesheet src="datatables/datatables.css"/>
		<asset:javascript src="datatables/datatables.js"/>
	</head>
	
	<body>
		<div class="container">
			
			<div class="row">
				<div class="col-md-12">
					<div class="well">
						<h2>Comprobantes fiscales (CFDI)</h2>
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
							<input type='text' id="emisorField" placeholder="Emisor" class="form-control">
						</div>
						<div class="btn-group">
							<input type='text' id="totalField" placeholder="Total" class="form-control" autofocus="autofocus">
						</div>
						
						<div class="btn-group">
							<g:link action="index" class="btn btn-default">
								<span class="glyphicon glyphicon-repeat"></span> Todos
							</g:link>
							
							<a href="#uploadCfdiDialog" data-toggle="modal" class="btn btn-default">
								<span class="glyphicon glyphicon-floppy-saved"></span> Nuevo
							</a>
						</div>
						<div class="btn-group">
							<button type="button" name="operaciones"
									class="btn btn-default dropdown-toggle" data-toggle="dropdown"
									role="menu">
									Operaciones <span class="caret"></span>
							</button>
							<ul class="dropdown-menu">
								<li>
									<a href="#multipleFileUploadDialog" data-toggle="modal" class="btn btn-default">
										Carga batch
									</a>
								</li>
							</ul>
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
			</div><!-- end .row 2 button panel -->

			<div class="row">
				<div class="col-md-12">
					<g:render template="gridPanel"/>
				</div>
			</div>

			<g:render template="uploadFileDialog"/>
			<g:render template="multipleFileUploadDialog"/>
			
		</div>
	
	<script type="text/javascript">
		$(function(){
			var table=$("#cfdiGrid").dataTable({
		        "paging":   false,
		        "ordering": false,
		        "info":     false,
		         "dom":'t'
				});
				
				$("#emisorField").keyup(function(){
  					table.DataTable().column(0).search( $(this).val() ).draw();
				});
				$("#totalField").keyup(function(){
  					table.DataTable().column(5).search( $(this).val() ).draw();
				});
				
		});
	</script>
		
	</body>
	
</html>
