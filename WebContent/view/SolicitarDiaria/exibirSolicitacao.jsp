<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Alterar Sugestao</title>
</head>
<body>
	<c:choose>
		<c:when test="${usuarioLogado.nivel=='GESTORUG'}">
			<c:import url="../topo.jsp" />
			<br>
			<c:import url="../usuarioLogado.jsp" />

			<center>
				<br> <br>
				<h3>Alterar Solicitacao</h3>
				<div id="formulario">
					<form class="form-inline" action="alterarSolicitacao" method="post">

						<input type="hidden" class="form-control" name="codSD"
							value="${exibirSolicitacao.codSD}">
						<%-- id solicitacão --%>

						<input type="hidden" class="form-control" name="idGestor"
							value="${usuarioLogado.idUser}">
						<%-- id Usuario Logado --%>

						<div class="form-group">

							<label for="justificativaGestor">Justificativa</label><br>
							<textarea name="justificativaGestor" required="true"
								maxlength="40" minlength="10"> </textarea>
							<br>
							<form:errors path="solicitarDiaria.justificativaGestor"
								cssStyle="color:red" />

						</div>
						<br>
						<div class="form-group">
							<label for="Deferimento">Aceitar</label> <input type="radio"
								name="def" value="Aceitado" required="true"> <label
								for="Deferimento">Recusar</label> <input type="radio" name="def"
								value="Recusado" required="true">
						</div>

						<br> <br>
						<button type="submit" class="btn btn-primary">Alterar</button>
					</form>
				</div>
			</center>

			<br>
		</c:when>
		<c:otherwise>
			<jsp:forward page="../index.jsp" />
		</c:otherwise>
	</c:choose>
</body>
</html>