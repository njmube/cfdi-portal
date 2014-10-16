<html>
<head>
	<meta charset="UTF-8">
	<meta name="layout" content="createForm"/>
	<title>Proveedor: ${proveedorInstance.id}</title>
</head>
<body>

	<content tag="header">
		<h3>${proveedorInstance.nombre}</h3>
	</content>
	
	<content tag="operaciones">
		<ul class="nav nav-pills nav-stacked">
			<li><g:link action="edit" id="${proveedorInstance.id}">
  					<span class="glyphicon glyphicon-pencil"></span> Editar
  			    </g:link>
  			</li>
  			<li><g:link action="comprobantes" id="${proveedorInstance.id}">
  					<i class="fa fa-database fa-lg"></i> Comprobantes
  			    </g:link>
  			</li>
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
		
		<fieldset disabled>
		<form class="form-horizontal" >
			
			<f:with bean="${proveedorInstance}">
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
		</form>
		</fieldset>
		

	</content>
	
</body>
</html>