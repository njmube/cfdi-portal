
<table id="cfdiGrid" class="table table-striped table-bordered table-condensed">
	<thead>
		<tr>
			<th>Receptor</th>
			<th>Emisor</th>
			<th>Fecha</th>
			<th>UUID</th>
			<th>Serie</th>
			<th>Folio</th>
			<th>Total</th>
			<th>XML</th>
			<th>Acuse</th>
			<th>Referencia</th>
		</tr>
	</thead>
	<tbody>
		<g:each in="${cfdiInstanceList}" var="row">
			<tr>
				<td>
					<g:link action="show" id="${row.id}">
						<abbr title="${row.receptor}">
							${org.apache.commons.lang.StringUtils.substring(row.receptor,0,10)}
						</abbr>
					</g:link>
				</td>
				<td>
					<g:link action="show" id="${row.id}">
						<abbr title="${row.emisor}">
							${org.apache.commons.lang.StringUtils.substring(row.emisor,0,20)}
						</abbr>
					</g:link>
				</td>
				<td><g:formatDate date="${row.fecha}" format="dd-MM-yyyy"/></td>
				<td>
					<abbr title="${row.uuid}">
					${org.apache.commons.lang.StringUtils.substringAfterLast(row.uuid,'-')}
					</abbr>
				</td>
				<td>${row.serie}</td>
				<td>${row.folio}</td>
				<td><g:formatNumber number="${row.total}" type="currency"/></td>
				<td>
					<g:link action="descargarXml" id="${row.id}">
						<i class="fa fa-file-code-o fa-lg"></i>
					</g:link>
					<g:link action="mostrarXml" id="${row.id}">
						<i class="fa fa-file-text-o fa-lg"></i>
					</g:link>
					
				</td>
				<td>
					<g:link action="verAcuse" id="${row.id}">
						<g:if test="${row.acuseCodigoEstatus=='S - Comprobante obtenido satisfactoriamente.'}">
							OK
						</g:if>
						<g:else>
							ERROR
						</g:else>
					</g:link>
				</td>
				<td>${row.referencia}</td>
				
				
			</tr>
		</g:each>
	</tbody>
</table>
<div class="pagination">
	<g:paginate total="${cfdiInstanceCount ?: 0}" />
</div>

