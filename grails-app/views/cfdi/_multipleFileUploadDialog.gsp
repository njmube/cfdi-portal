<div class="modal fade" id="multipleFileUploadDialog" tabindex="-1">
	<div class="modal-dialog ">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">Cargar Batch de CFDIs</h4>
			</div>
			
			<g:uploadForm name="batchUploadForm" class="form" action="batchUpload" >
				<div class="modal-body">
					
					<div class="form-group">
					    <label for="inputFile">Archivos XML</label>
					    <input type="file" id="inputFile" name="xmls[]" accept="application/xml" multiple="true">
					    	<p class="help-block">Archivos a cargar</p>
					 </div>
					 <div class="form-group">
					    <label for="inputFile">Referencia</label>
					    <input type="string" id="referenciaField" name="referencia" >
					    <p class="help-block">Referencia de control interno</p>
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
