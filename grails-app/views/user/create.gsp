<html>
<head>
	<meta charset="UTF-8">
	<meta name="layout" content="createForm"/>
	<title>Alta de usuario</title>
</head>
<body>

	<content tag="header">
		<h3>${"Usuario nuevo"}</h3>
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
	
	<content tag="formTitle">Usuario nuevo</content>
	
	<content tag="form">
		
		<g:hasErrors bean="${usuarioInstance}">
            <div class="alert alert-danger">
                <g:renderErrors bean="${usuarioInstance}" as="list" />
            </div>
        </g:hasErrors>
		
		
		<form class="form-horizontal" >
			
			<f:with bean="${usuarioInstance}">
				<f:field property="username" input-class="form-control"/>
				<f:field property="apellidoPaterno" input-class="form-control"/>
				<f:field property="apellidoMaterno" input-class="form-control"/>
				<f:field property="nombres" input-class="form-control"/>
				<f:field property="enabled" input-class="form-control" label="Activo"/>
			</f:with>

			<div class="form-group">
		    	<div class="col-sm-offset-9 col-sm-2">
		    		<g:link action="index" class="btn btn-default">
  						<span class="glyphicon glyphicon-arrow-left"></span> Cancelar
  			    	</g:link>
		      		<button type="submit" class="btn btn-default">
		      			<span class="glyphicon glyphicon-floppy-save"></span> Salvar
		      		</button>
		    	</div>
		  	</div>
		</form>

		

	</content>
	
</body>
</html>