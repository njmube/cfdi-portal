<div class="modal fade" id="reportePorEmisorDialog" tabindex="-1">
	<div class="modal-dialog ">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">CFDI's por Emisor</h4>
			</div>
			
			<g:form  class="form-horizontal" controller="reporte" action="comprobantesPorEmisor" >
				<div class="modal-body">
					<g:hiddenField name="reportName" value="ComprobantesDigitales"/>
					<fieldset>
						<legend> Parámetros</legend>
						<f:with bean="${new com.luxsoft.cfdi.PorEmisorCommand()}">
							<div class="form-group">
								<label class="col-sm-3 control-label">Proveedor</label>
								<div class="col-sm-9">
									<g:select class="form-control"  
										name="proveedor"
										from="${com.luxsoft.cfdi.Proveedor.findAll()}" 
										optionKey="id"
											
									/>
								</div>
								
							</div>
							<f:field property="fechaInicial" input-class="form-control" value="${new Date()}"/>
							<f:field property="fechaFinal" input-class="form-control" value="${new Date()}"/>
							<f:field property="referencia" input-class="form-control"/>
							
						</f:with>
					</fieldset>
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
</div>
