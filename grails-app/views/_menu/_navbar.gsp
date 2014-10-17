<nav class="navbar navbar-default navbar-fixed-top navbar-inverse" role="navigation">
	<div class="container">
		<div class="navbar-header">
			<button class="navbar-toggle" type="button" data-toggle="collapse" data-target="#mainMenu">
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<g:link controller="home" action="index" class="navbar-brand">
				<i class="fa fa-home fa-lg"></i> Luxor CFDI Portal
			</g:link>
		</div>
<%--		<sec:ifLoggedIn>--%>
		
		<div class="collapse navbar-collapse" id="mainMenu">
			
			<%-- Catalogos --%>
			<ul class="nav navbar-nav">
			
				<%-- Receptores --%>
				<li class="">
	          		<g:link controller="receptor" action="index"> Receptores</g:link>
	        	</li>
				
				<%-- Proveedores --%>
				<li class="">
	          		<g:link controller="proveedor" action="index"> Proveedores</g:link>
	        	</li>
	        	
	        	<%-- CFDIs --%>
				<li class="">
	          		<g:link controller="cfdi" action="index"> Comprobantes</g:link>
	        	</li>

	        	<li class="">
	          		<a href="#" class="" >Configuración</a>
	        	</li>

        	</ul>
        	

			%{-- <nav:menu class="nav navbar-nav" scope="user/" /> --}%
			<ul class="nav navbar-nav navbar-right">
<%--				<g:render template="/_menu/user"/>--%>
				
			</ul>
			
		</div>
		
<%--		</sec:ifLoggedIn>--%>
	</div>
	
</nav>