<div class="modal fade" id="multipleFileUploadDialog" tabindex="-1">
	<div class="modal-dialog ">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">Cargar CFDI desde archivo XML</h4>
			</div>
			
			<g:uploadForm name="batchUploadForm" class="form" action="batchUpload" >
				<div class="modal-body">
					
					<div class="form-group">
					    <label for="inputFile" class="col-sm-4 control-label"> CFDIs</label>
					    <div class="col-sm-8">
					    	<input type="file" id="inputFile" name="xmls[]" accept="application/xml" multiple="true">
					    	<p class="help-block">Seleccione los archivos XML</p>
					    </div>
					 </div>
					 
					 <div class="form-group">
					    <label for="ireferencia" class="col-sm-4 control-label">Referencia</label>
					    <div class="col-sm-8">
					    	<input  name="referencia" >
					    	<p class="help-block">Referencia de control interno</p>
					    </div>
					 </div>
					 
					 <div class="form-group">
					    <label for="inputFile" class="col-sm-4 control-label">Grupo</label>
					    <div class="col-sm-8">
					    	<g:select id="type" name="grupo" class="form-control"
    							from="['COMPRAS','GASTOS']">
    						</g:select>
					    	<p class="help-block">Grupo o departamento  de control interno</p>
					    </div>
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



	%{-- <div class="modal-dialog ">
		<div class="modal-content">
			
			
			<g:uploadForm name="batchUploadForm" class="form" action="batchUpload" >
				<div class="modal-body">
					
					
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
 --}%