<!DOCTYPE html>
<html>
	<head>
		
		<title>CFDI</title>
	</head>
	
	<body>
		<div class="container">
			
			<div class="row">
				<div class="col-md-12">
					
					<div class="well">
						<form class="form-horizontal" role="form">

						  	<div class="form-group">
						  		<label class="col-sm-2 control-label">Emisor</label>
						  		<div class="col-sm-4">
						    		<p class="form-control-static">${cfdiInstance.emisor}</p>
						    	</div>
						    		<label class="col-sm-2 control-label">Receptor</label>
						    	<div class="col-sm-4">
						    		<p class="form-control-static">${cfdiInstance.receptor}</p>
						    	</div>
						  	</div>
						  	
						  	<div class="form-group">
						  		<label class="col-sm-2 control-label">RFC</label>
						  		<div class="col-sm-4">
						    		<p class="form-control-static">${cfdiInstance.emisorRfc}</p>
						    	</div>
						    		<label class="col-sm-2 control-label">RFC</label>
						    	<div class="col-sm-4">
						    		<p class="form-control-static">${cfdiInstance.receptorRfc}</p>
						    	</div>
						  	</div>

					  		<div class="form-group">
					  			<label class="col-sm-2 control-label">Serie</label>
					  			<div class="col-sm-4">
					  	  			<p class="form-control-static">${cfdiInstance.serie}</p>
					  	  		</div>
					  	  			<label class="col-sm-2 control-label">Folio</label>
					  	  		<div class="col-sm-4">
					  	  			<p class="form-control-static">${cfdiInstance.folio}</p>
					  	  		</div>
					  		</div>
						  
					  		<div class="form-group">
					  			<label class="col-sm-2 control-label">Fecha</label>
					  			<div class="col-sm-4">
					  	  			<p class="form-control-static">
										<g:formatDate date="${cfdiInstance.fecha}" format="dd/MM/yyyy"/>
									</p>
					  	  		</div>
					  	  			<label class="col-sm-2 control-label">Total</label>
					  	  		<div class="col-sm-4">
					  	  			<p class="form-control-static">
					  	  				<g:formatNumber number="${cfdiInstance.total}" type="currency"/>
					  	  			</p>
					  	  		</div>
					  		</div>
					  		<div class="form-group">
					  			<label class="col-sm-2 control-label">UUID</label>
					  			<div class="col-sm-4">
					  	  			<p class="form-control-static">${cfdiInstance.uuid}</p>
					  	  		</div>
					  	  			<label class="col-sm-2 control-label">Acuse (SAT)</label>
					  	  		<div class="col-sm-4">
					  	  			<p class="form-control-static">${cfdiInstance.acuseCodigoEstatus}</p>
					  	  		</div>
					  		</div>
					  		
						    
						</form>
					</div>

				</div>
			</div><!-- end .row 1 -->
			
			<div class="row">
				<div class="col-md-12">
					<div class="button-panel">
						<div class="btn-group">
							<g:link action="index" class="btn btn-default">
								<span class="glyphicon glyphicon-list"></span> Comprobantes
							</g:link>
							<a href="#uploadCfdiDialog" data-toggle="modal" class="btn btn-default">
								<span class="glyphicon glyphicon-floppy-saved"></span> Nuevo
							</a>
							<g:link action="descargarXml" class="btn btn-default" id="${cfdiInstance.id}">
								<i class="fa fa-download fa-lg"></i></i> XML
							</g:link>
							<g:link action="mostrarXml" class="btn btn-default" id="${cfdiInstance.id}">
								<i class="fa fa-file-text-o fa-lg"></i> Ver
							</g:link>
						</div>
						<div class="btn-group">
							<button type="button" name="reportes"
									class="btn btn-default dropdown-toggle" data-toggle="dropdown"
									role="menu">
									Operaciones <span class="caret"></span>
							</button>
							<ul class="dropdown-menu">
								<li><a href="#">Carga PDF</a></li>
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
							</ul>
						</div>
						
					</div>
					
				</div>
			</div><!-- end .row 2 button panel -->
			
			<div class="row">
				<div class="col-md-12">
					<g:render template="conceptosGrid"/>
				</div>
			</div>
			
			<g:render template="uploadFileDialog"/>
			
			
		</div>

		
	</body>
	
</html>
