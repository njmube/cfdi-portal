<!DOCTYPE html>
<html>
<head>
	
	<title>Comprobantes</title>
	
	<asset:stylesheet src="datatables/datatables.css"/>
	<asset:javascript src="datatables/datatables.js"/>
</head>
	
<body>
	<div class="container">
		
		<div class="row">
			<div class="col-md-12">
				<div class="alert alert-info">
					<h2>Mantenimiento de referencias para (CFDI)</h2>
					<g:if test="${flash.message}">
						<span class="label label-warning text-center">${flash.message}</span>
					</g:if>
				</div>

			</div>
		</div><!-- end .row 1 -->
		
		<div class="row">
			<div class="col-md-12">
				<div class="toolbar-panel">
					<div class="btn-group">
						<input type='text' id="emisorField" placeholder="Emisor" class="form-control" autofocus="autofocus">
					</div>
					<div class="btn-group">
						<g:link action="index" class="btn btn-default">
							<span class="glyphicon glyphicon-repeat"></span> Refrescar
						</g:link>

						<button id="modificarBtn" data-toggle="modal" class="btn btn-default">
							<i class="fa fa-wrench"></i> Modificar
						</button>
						
						<a href="#filterDialog" data-toggle="modal" class="btn btn-default">
							<span class="glyphicon glyphicon-filter"></span> Busqueda avanzda
						</a>

					</div>
					
					<div class="btn-group">
						<button type="button" name="reportes"
								class="btn btn-default dropdown-toggle" data-toggle="dropdown"
								role="menu">
								Reportes <span class="caret"></span>
						</button>
						<ul class="dropdown-menu">
							<li><g:link controller="reporte" action="comprobantesPorEmisor"> Modificaciones</g:link></li>
						</ul>
					</div>
					
				</div>
				
			</div>
		</div><!-- end .row 2 button panel -->

		<div class="row">
			<div class="col-md-12">
				<g:render template="gridPanel"/>
			</div>
		</div>


		<g:render template="filterDialog"/>
		
		
		<div class="modal fade" id="modificacionDialog" tabindex="-1">
			<div class="modal-dialog ">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">Nueva referencia</h4>
					</div>
					
					<form id="updateForm" class="form-horizontal"  >
						<div class="modal-body">
							
							
							 <div class="form-group">
							    <label for="ireferencia" class="col-sm-4 control-label">Referencia</label>
							    <div class="col-sm-8">
							    	<input  id="referenciaField" name="referencia" >
							    	<p class="help-block">Referencia para control interno</p>
							    </div>
							 </div>
							 
							 
						</div>

						<div class="modal-footer">
							<button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
							<g:submitButton class="btn btn-primary" name="aceptar"value="Aceptar" />
						</div>
					<form>

				</div>
				<!-- moda-content -->
			</div>
			<!-- modal-di -->
		</div>
		
	</div>

<script type="text/javascript">
	$(function(){
		var table=$("#cfdiGrid").dataTable({
	        "paging":   false,
	        "ordering": false,
	        "info":     false,
	         "dom":'t'
		});
			
		$("#emisorField").keyup(function(){
				table.DataTable().search( $(this).val() ).draw();
		});

		$("#cfdiGrid tbody tr").on('click',function(){
			$(this).toggleClass("success selected");
		});

		function selectedRows(){
			var res=[];
			var data=$("#cfdiGrid  .selected").each(function(){
				var tr=$(this);
				res.push(tr.attr("id"));
			});
			return res;
		}

		$("#modificarBtn").on('click',function(e){
			var res=selectedRows();
			if(res.length===0){
				alert("Debe seleccionar al menos un registro");
	 			return;
			}else{
				console.log('Seleccionando : '+res.length);
				$("#modificacionDialog").modal('show');
			}
			
		});

		$("#updateForm").submit(function(e){
			e.preventDefault();
			console.log("Modificando la POST ");
			
			$form = $(this);
			var referencia=$("#referenciaField").val()
			var res=selectedRows();
			$.ajax({
				type: $form.attr('method'),
				url:"${g.createLink(action:'modificar') }",
				data:{referencia:referencia,partidas:JSON.stringify(res)},
				success: function(data) {
             		console.log('Yay! Form sent Data :'+data.comprobantes);
         //     		var grid = $('#cfdiGrid').DataTable();
         //     		for (var i = 0; i < data.comprobantes.length; i++) {
         //     			var cfdi=data.comprobantes[i];
         //     			console.log("Actualizando cfdi: "+cfdi.id+ 'Valor: '+cfdi.referencia);
         //     			var selector="#cfdiGrid  td[data-referencia="+"\'"+cfdi.referencia+"\'"+"]"
             			
         //     			var cell=$(selector);
         //     			cell.html(cfdi.referencia);
         //     			grid.draw();
         //     			//var cell = grid.cell($(selector));
    					// //cell.data( cfdi.referencia).draw();
             			
         //     			console.log("Cell: "+cell.text());
         //     		};
             		window.location.href='${createLink(controller:'modificacionReferencia',action:'index')}';
         		},
         		error:function(){
	 				alert('Error asignando referencia')
	 			}
			});
			return false;
		});

	});

	// $(function(){
	
	// 	$("#asignar").click(function(){
	// 		var res=selectedRows();
	// 		if(res==""){
	// 			alert("Debe seleccionar al menos un registro");
	// 			return;
	// 		}
	// 		var ok=confirm("Asignar "+res.length+" contenedores ?");
	// 		if(!ok)
	// 			return
	// 		console.log('Asignando: '+res)
	// 		var sucursal=$("#sucursal").val();
	// 		$.ajax({
	// 			url:"${g.createLink(controller:'distribucion',action:'asignarContenedor') }",
	// 			data:{distribucionId:${distribucionId},partidas:JSON.stringify(res),sucursal:sucursal},
	// 			success:function(response){
	// 				window.location.href='${createLink(controller:'distribucion',action:'edit',params:[id:distribucionId])}';
	// 			},
	// 			error:function(){
	// 				alert('Error asignando contenedor..')
	// 			}
	// 		});
			
	// 	});
		
	// });

</script>
	
</body>
	
</html>
