
<nav class="navbar navbar-default navbar-fixed-bottom navbar-inverse" role="navigation">
	<div class="container">
		
		<sec:ifLoggedIn>
			<p class="navbar-text navbar-left"> 
			<strong>
				Empresa (Receptor): "${session.receptor?:'NO DISPONIBLE'}"
			</strong>
			</p>
			<p class="navbar-text navbar-right">Usuario: 
				<a href="#" class="navbar-link">
					<sec:loggedInUserInfo field="username"/>
				</a>
			</p>
		</sec:ifLoggedIn>
		<sec:ifNotLoggedIn>
			<p class="navbar-text navbar-left"> Ingreso al sistema</p>
		</sec:ifNotLoggedIn>
	</div>
</nav>