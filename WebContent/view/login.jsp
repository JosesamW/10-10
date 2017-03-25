<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/bootstrap.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/estiloLogin.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Monitoria-Login</title>
</head>
<body>
	<div class="container-fluid">
		<div class="row">


			<div class="jumbotron well">
				<div class="page-header">
					<c:import url="topo2.jsp" />
				</div>
			</div>

			
			<div id="formulario">
				<form class="form-inline" action="efetuarLogin" method="post">
					<br> <br>
					<h3>LOGIN:</h3>

					<div class="form-group">
						<input type="text" class="form-control" id="cpf" name="cpf"
							placeholder="Seu CPF" maxlength="11" minlength="11" required>
					</div>
					<br> <br>
					<div class="form-group">
						<input type="password" class="form-control" id="senha"
							name="senha" placeholder="Sua Senha" maxlength="20" minlength="6"
							required>
					</div>
					<br> <br>
					<button type="submit" class="btn btn-default">Entrar</button>
				</form>
			</div>

		</div>
	</div>


</body>
</html>