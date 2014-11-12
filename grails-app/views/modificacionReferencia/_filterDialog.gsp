<%@page expressionCodec="none"%>
<div class="modal fade" id="filterDialog" tabindex="-1">
	<div class="modal-dialog">
		<div class="modal-content">
			
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">Filtrar</h4>
				<span class="label label-info">Puede usar el comodin %</span>
			</div>

			<g:form class="form-horizontal" action="search" >
				<div class="modal-body">
					<div class="form-group">
						<label for="nombreField" class="col-sm-2 control-label">Emisor</label>
						<div class="col-sm-10">
							<g:field id="emisorField" value="${session.search?.emisor}"
								type="text" 
								placeholder="Emisor" 
								name="emisor" 
								class="form-control"  />
						</div>
					</div>
					
					<div class="form-group">
						<label for="referencia" class="col-sm-2 control-label">Referencia</label>
						<div class="col-sm-10">
							<g:field  type="text" value="${session.search?.referencia}"
							name="referencia" class="form-control"  />
						</div>
					</div>

					<div class="form-group">
						<label for="fechaInicial" class="col-sm-2 control-label">Fecha Inicial</label>
						<div class="col-sm-10">
							<g:field  type="text" name="fechaInicial" class="form-control dateField"  
							value="${session.search?.fechaInicial?session.search.fechaInicial.format('dd/MM/yyyy'):(new Date()-90).format('dd/MM/yyyy')}"/>
						</div>
					</div>
					<div class="form-group">
						<label for="fechaFinal" class="col-sm-2 control-label">Fecha Final</label>
						<div class="col-sm-10">
							<g:field  type="text" name="fechaFinal" class="form-control dateField"  
							value="${session.search?.fechaFinal?session.search.fechaFinal.format('dd/MM/yyyy'):(new Date()-90).format('dd/MM/yyyy')}"/>
						</div>
					</div>

					<div class="form-group">
						<label for="folio" class="col-sm-2 control-label">Folio</label>
						<div class="col-sm-10">
							<g:field  type="text" name="folio" class="form-control"  />
						</div>
					</div>					

					<div class="form-group">
						<label for="total" class="col-sm-2 control-label">Total</label>
						<div class="col-sm-10">
							<g:field  type="text" name="total" class="form-control"  />
						</div>
					</div>

					<div class="form-group">
						<label for="max" class="col-sm-2 control-label">Registros</label>
						<div class="col-sm-10">
							<g:field  type="text" name="max" class="form-control" value="50" />
						</div>
					</div>
					
				</div>	
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
					<g:submitButton class="btn btn-primary" name="aceptar"
							value="Filtrar" />
				</div>
			</g:form>

		</div>
		<!-- moda-content -->
	</div>
	<!-- modal-di -->
<script type="text/javascript">
	$(function(){
		$('#filterDialog').on('shown.bs.modal', function (e) {
	    	$('#emisorField').focus();
	    	console.log("Modal visible:...."+field.text());
		});
	});
</script>
</div>
