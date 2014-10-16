<div class="modal fade" id="uploadCfdiDialog" tabindex="-1">
	<div class="modal-dialog ">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">Cargar CFDI desde archivo XML</h4>
			</div>
			
			<g:uploadForm name="uploadCfdi" class="form" action="uploadCfdi" >
				<div class="modal-body">
					
					<div class="form-group">
					    <label for="inputFile">Comprobante fiscal digital CFDI</label>
					    <input type="file" id="inputFile" name="xml" accept="application/xml">
					    <p class="help-block">Seleccion el archico XML a cargar</p>
					 </div> 
				</div>

				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
					<g:submitButton class="btn btn-primary" name="aceptar"value="Aceptar" />
				</div>
			</g:uploadForm>


		</div>
		<!-- moda-content -->
	</div>
	<!-- modal-di -->
</div>
