<li class="dropdown">
	<sec:ifLoggedIn>
		<a class="dropdown-toggle" data-toggle="dropdown" href="#">
    		<i class="glyphicon glyphicon-user"></i>
    		<sec:loggedInUserInfo field="username"/><b class="caret"></b>
		</a>
		<ul class="dropdown-menu" role="menu">
			<li>
				<g:form controller="logout" class="navbar-form navbar-left" role="search">
  
  					<button type="submit" class="btn btn-default"> <i class="fa fa-power-off"></i> Cerrar sesión</button>
				</g:form>
				%{-- <g:form controller="logout" class="form-inline">
					
					<button type="submit" class="btn btn-default"> Salir</button>
				</g:form> --}%
				%{-- <g:link controller="logout" method="POST"><i class="fa fa-power-off"></i> Salir</g:link> --}%
			</li>
		</ul>
	</sec:ifLoggedIn>
</li>
