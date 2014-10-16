
<table id="proveedoresGrid" class="table table-striped table-bordered table-condensed">
	<thead>
		<tr>
			<th>Nombre</th>
			<th>Rfc</th>
			<th>Email (cfdi)</th>
			<th>Modificado</th>
			
		</tr>
	</thead>
	<tbody>
		<g:each in="${proveedorInstanceList}" var="row">
			<tr>
				<td>
					<g:link action="show" id="${row.id}">
						%{-- <abbr title="${row.nombre}">
							${org.apache.commons.lang.StringUtils.substring(row.nombre,0,35)}
						</abbr> --}%
						${fieldValue(bean:row,field:"nombre")}
					</g:link>
				</td>
				<td>${fieldValue(bean:row,field:"rfc")}</td>
				<td>${fieldValue(bean:row,field:"cfdiEmail")}</td>
				<td><g:formatDate date="${row.lastUpdated}" format="dd-MM-yyyy mm:HH"/></td>
				
			</tr>
		</g:each>
	</tbody>
</table>
<div class="pagination">
	<g:paginate total="${proveedorInstanceCount ?: 0}" />
</div>
