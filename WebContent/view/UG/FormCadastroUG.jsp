<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/bootstrap.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/estiloCadastrar.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadastro UG</title>
</head>

<body>
	<c:choose>
		<c:when test="${usuarioLogado.nivel=='ADM'}">

			<c:import url="../topo.jsp" />
			<br>
			<c:import url="../usuarioLogado.jsp" />



			<div id="formulario">
				<h3>Cadastrar UG</h3>
				<div id="constrain_error">${msgErrorPkUG}</div>
				<form action="CadastroUG" method="post" class="form-inline">

					<div class="form-group">
						<form:errors path="unidadeGestora.nome" cssStyle="color:red" />
						<br />
						<!-- bean validation  -->
						<label for="Nome">Nome da UG</label><br> <input type="text"
							class="form-control" name="nome" placeholder="Nome da UG" maxlength="40" minlength="10"><br>
					</div>
					<br>
					<div class="form-group">
						<form:errors path="unidadeGestora.codigo" cssStyle="color:red" />
						<br />
						<!-- bean validation  -->
						<label for="Codigo">C�digo</label><br> <input type="text"
							class="form-control" name="codigo" placeholder="C�digo da UG"
							maxlength="9" minlength="1" required="true"><br> <br>
					</div>
					<br>
					<button type="submit" class="btn btn-primary">Cadastrar</button>
				</form>
			</div>
		</c:when>
		<c:otherwise>
		]<jsp:forward page="../index.jsp" />
		</c:otherwise>
	</c:choose>

</body>
</html>