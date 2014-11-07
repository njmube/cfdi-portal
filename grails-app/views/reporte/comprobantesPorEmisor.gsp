<html>
<head>
<meta charset="UTF-8">
<meta name="layout" content="reportes"/>
<title>CFDIs por emisor</title>
<asset:stylesheet src="jquery-ui.css"/>
<asset:javascript src="jquery-ui/autocomplete.js"/>
</head>
<body>

	<content tag="reporteTitle">
		Comprobantes digitales por proveedor y remisión
	</content>
	<content tag="reportForm">
		<g:hasErrors bean="${reportCommand}">
            <div class="alert alert-danger">
                <g:renderErrors bean="${reportCommand}" as="list" />
            </div>
        </g:hasErrors>
		<div class="col-sm-12">
		
		<g:form action="comprobantesPorEmisor" class="form-horizontal">
			<g:hiddenField name="reportName" value="ComprobantesPorEmisorReferencia"/>
			<g:hiddenField id="proveedorId" name="proveedor.id"  />
			<fieldset>
						<legend> Parámetros</legend>
						<f:with bean="${reportCommand}">
							<div class="form-group">
								<label class="col-sm-3 control-label">Proveedor</label>
								<div class="col-sm-9">
									%{-- <g:select class="form-control"  
										name="proveedor"
										from="${com.luxsoft.cfdi.Proveedor.findAll()}" 
										optionKey="id"
											
									/> --}%
									<input id="nombre" type="text" name="nombre" autocomplete="off" required class="form-control" autofocus>
								</div>
								
							</div>
							<f:field property="referencia" input-class="form-control"/>
							
						</f:with>
					</fieldset>
			<div class="form-group">
		    	<div class="col-sm-offset-3 col-sm-4">
		      		<button type="submit" class="btn btn-primary">
		      			<span class="glyphicon glyphicon-cog"></span> Ejecutar
		      		</button>
		    	</div>
		  	</div>
		</g:form>
		</div>
		<script type="text/javascript">
		$(function(){
			$("#nombre").autocomplete({
				
				source:'<g:createLink controller="proveedor" action="getProveedoresAsJSON"/>',
				minLength:3,
				select:function(e,ui){
					console.log('Valor seleccionado: '+ui.item.id);
					$("#proveedorId").val(ui.item.id);
				}
			});
			
		});
		</script>
	</content>
	
</body>

</html>

%{-- <div class="modal fade" id="reportePorEmisorDialog" tabindex="-1">
	<div class="modal-dialog ">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">CFDI's por Emisor</h4>
			</div>
			
			<g:form  class="form-horizontal" controller="reporte" action="comprobantesPorEmisor" >
				<div class="modal-body">
					<g:hiddenField name="reportName" value="CfdisPorEmisor"/>
					
				</div>

				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
					<button type="submit" class="btn btn-default">
		      			<span class="glyphicon glyphicon-cog"></span> Ejecutar
		      		</button>
				</div>
			</g:form>


		</div>
		<!-- moda-content -->
	</div>
	<!-- modal-di -->
</div> --}%
