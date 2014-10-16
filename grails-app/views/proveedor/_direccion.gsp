<fieldset>
	<legend>Dirección  </legend>
	<div class="form-group">
		<label for="calle" class="col-sm-2 control-label">Calle </label>
		<div class="col-sm-4">
			<input name="direccion.calle" 
				value="${proveedorInstance?.direccion?.calle}"
				type="text" class="form-control mayusculas" 
				placeholder="Calle">
		</div>
	</div>

	<div class="form-group">
		<label for="numeroExterior" class="col-sm-2 control-label">No Exterior</label>
		<div class="col-sm-4">
				<input name="direccion.numeroExterior" 
				value="${proveedorInstance?.direccion?.numeroExterior}"
				type="text" class="form-control mayusculas"  
				placeholder="# exterior">
		</div>
		<label for="numeroInterior" class="col-sm-2 control-label">No Interior</label>
		<div class="col-sm-4">
				<input name="direccion.numeroInterior" 
				value="${proveedorInstance?.direccion?.numeroInterior}"
				type="text" class="form-control mayusculas"  
				placeholder="# interior">
		</div>
	</div>

	<div class="form-group">
		<label for="colonia" class="col-sm-2 control-label">Colonia</label>
		<div class="col-sm-4">
				<input name="direccion.colonia" 
				value="${proveedorInstance?.direccion?.colonia}"
				type="text" class="form-control mayusculas" 
				placeholder="Colonia">
		</div>
		<label for="colonia" class="col-sm-2 control-label">Delegación</label>
		<div class="col-sm-4">
				<input name="direccion.municipio" 
				value="${proveedorInstance?.direccion?.municipio}"
				type="text" class="form-control mayusculas" 
				placeholder="Delegación / Municipio">
		</div>
	</div>


	<div class="form-group">
	<label for="estado" class="col-sm-2 control-label">Estado</label>
		<div class="col-sm-4">
				<input name="direccion.estado" 
				value="${proveedorInstance?.direccion?.estado}"
				type="text" class="form-control mayusculas"  
				placeholder="Estado">
		</div>
		<label for="pais" class="col-sm-2 control-label">País</label>
		<div class="col-sm-4">
				<input name="direccion.pais" 
				value="${proveedorInstance?.direccion?.pais}"
				type="text" class="form-control mayusculas" 
				placeholder="pais">
		</div>
	</div>

	<div class="form-group">
		<label for="codigoPostal" class="col-sm-2 control-label">C.P.</label>
		<div class="col-sm-4">
				<input name="direccion.codigoPostal" 
				value="${proveedorInstance?.direccion?.codigoPostal}"
				type="text" class="form-control mayusculas"  
				placeholder="# Código postal">
		</div>
	</div>

</fieldset>