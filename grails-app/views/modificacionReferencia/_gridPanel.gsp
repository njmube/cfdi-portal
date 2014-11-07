
<table id="cfdiGrid" class="table table-striped table-bordered table-condensed">
	<thead>
		<tr>
			<th>Receptor</th>
			<th>Emisor</th>
			<th>Folio</th>
			<th>Fecha</th>
			<th>Ref</th>
			
		</tr>
	</thead>
	<tbody>
		<g:each in="${cfdiInstanceList}" var="row">
			<tr id="${row.id}">
				<td>${org.apache.commons.lang.StringUtils.substring(row.receptor,0,10)}</td>
				<td>${org.apache.commons.lang.StringUtils.substring(row.emisor,0,50)}</td>
				<td>${row.folio}</td>
				<td><g:formatDate date="${row.fecha}" format="dd-MM-yyyy"/></td>
				<td data-referencia="${row.id}">${row.referencia}</td>
			</tr>
		</g:each>
	</tbody>
</table>


