<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/bootstrap.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/estiloConsultar.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Listar Sugestão</title>
</head>
<body>
	<c:choose>
		<c:when test="${usuarioLogado.nivel=='ADM'}">

			<c:import url="../topo.jsp" />
						<br>
			<c:import url="../usuarioLogado.jsp" />
		<div id="formulario">
				<form action="listarSugestao" class="form-inline">
					<h3>Listar Sugestão</h3>
					<div class="form-group">
						<label for="Nome">Valor diaria</label><br> <input type="text"
							class="form-control" name="Valordiaria" placeholder="00.00" maxlength="15"><br>
					</div>
					<br><br>
					<button type="submit" class="btn btn-primary">Consultar</button>
				</form>
				<br>
			</div>
			<br>
			<table id="tabela" class="table table-striped">
				<tr style='background-color: #E6E6E6; font-weight: bold;'>

					<td>Id</td>
					<td>Origem</td>
					<td>Destino</td>
					<td>UG</td>
					<td>Cargo</td>
					<td>Valor</td>
					<td>Ações</td>

				</tr>
				<c:forEach var="sd" items="${listarSD}">
					<tr>
						<td>${sd.idSD}</td>
						<td>${sd.origem.nome}</td>
						<td>${sd.destino.nome}</td>
						<td>${sd.ug.nome}</td>
						<td>${sd.cargo.nome}</td>
						<td>${sd.valores}</td>
						<td><a href="exibirAlterarSugestao?idSugestao=${sd.idSD}">Alterar</a></td>
					</tr>
				</c:forEach>
			</table>
		</c:when>
		<c:otherwise>
			<jsp:forward page="../index.jsp" />
		</c:otherwise>
	</c:choose>
</body>
</html>