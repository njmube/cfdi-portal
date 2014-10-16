
<table id="cfdiGrid" class="table table-striped table-bordered table-condensed">
	<thead>
		<tr>
			<th>Cantidad</th>
			<th>Descripcion</th>
			<th>Unidad</th>
			<th>Valor Unitario</th>
			<th>Importe</th>
			<th>Aduana Info</th>
		</tr>
	</thead>
	<tbody>
		<g:each in="${cfdiInstance.getComprobante().getConceptos().getConceptoArray()}" var="row">
			<tr>
				<td>${row.getCantidad() }</td>
				<td>${row.getDescripcion() }</td>
				<td>${row.getUnidad() }</td>
				<td>${row.getValorUnitario() }</td>
				<td>${row.getImporte() }</td>
				<td>
					<g:if test="${row.informacionAduaneraArray }">
						${res.PEDIMENTO_FECHA=cc.informacionAduaneraArray[0]?.fecha.getTime()}
						${res.PEDIMENTO=cc.informacionAduaneraArray[0]?.numero}
						${res.ADUANA=cc.informacionAduaneraArray[0]?.aduana }
					</g:if>
				</td>
			<%--
				<td><g:fieldValue bean="row" field="cantidad"/></td>
				<td><g:fieldValue bean="row" field="descripcion"/></td>
				<td><g:fieldValue bean="row" field="unidad"/></td>
				<td><g:formatNumber number="${row.valorUnitario}" format="###,###.####"/></td>
				<td><g:formatNumber number="${row.importe}" format="###,###.####"/></td>
			--%></tr>
		</g:each>
	</tbody>
</table>

