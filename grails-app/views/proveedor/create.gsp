<html>
<head>
	<meta charset="UTF-8">
	<meta name="layout" content="createForm"/>
	<title>Alta de Proveedor</title>
</head>
<body>

	<content tag="header">
		<h3>Registro de proveedor nuevo</h3>
	</content>
	
	<content tag="operaciones">
		<ul class="nav nav-pills nav-stacked">
  			<li><g:link action="index">
  					<span class="glyphicon glyphicon-arrow-left"></span> Catálogo
  			    </g:link>
  			</li>
  			<li><g:link action="create">
  					<span class="glyphicon glyphicon-plus"></span> Nuevo
  			    </g:link>
  			</li>
		</ul>
	</content>
	
	<content tag="formTitle">Datos generales</content>
	
	<content tag="form">
		
		<g:hasErrors bean="${proveedorInstance}">
            <div class="alert alert-danger">
                <g:renderErrors bean="${proveedorInstance}" as="list" />
            </div>
        </g:hasErrors>
		
		<g:form class="form-horizontal" action="save">
			
			<f:with bean="${proveedorInstance }">
				<f:field property="nombre" input-class="form-control"/>
				<f:field property="rfc" input-class="form-control"/>
				<f:field property="cfdiEmail" input-class="form-control"/>
				<g:render template="direccion"/>
				<div class="col-md-6">
					<f:field property="email1" input-class="form-control"/>	
					<f:field property="telefono1" input-class="form-control"/>
				</div>
				<div class="col-md-6">
					<f:field property="email2" input-class="form-control"/>
					<f:field property="telefono2" input-class="form-control"/>
				</div>
			</f:with>
			
			<div class="form-group">
		    	<div class="col-sm-offset-9 col-sm-2">
		      		<button type="submit" class="btn btn-default">
		      			<span class="glyphicon glyphicon-floppy-save"></span> Salvar
		      		</button>
		    	</div>
		  	</div>
		</g:form>
		
		<r:script>
			
		</r:script>

	</content>
	
</body>
</html>