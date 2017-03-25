<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/bootstrap.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/estiloConsultar.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Listar / Consultar UG</title>
</head>

<body>
	<c:choose>
		<c:when test="${usuarioLogado.nivel=='ADM'}">

			<c:import url="../topo.jsp" />


			<br>
			<c:import url="../usuarioLogado.jsp" />

			<div id="formulario">
				<form action="ListarUG" class="form-inline">
					<h3>Consultar UG</h3>
					<div class="form-group">
						<label for="Nome">Nome da UG</label><br> <input type="text"
							class="form-control" name="nome" placeholder="Nome da UG"><br>
					</div>
					<br>
					<div class="form-group">
						<label for="Codigo">Código da UG</label><br> <input
							type="text" class="form-control" name="codigo"
							placeholder="Código da UG"><br> <br>
					</div>
					<br>
					<button type="submit" class="btn btn-primary">Consultar</button>
				</form>
				<br>
			</div>
			<br>
			<table id="tabela" class="table table-striped">
				<tr style='background-color: #E6E6E6; font-weight: bold;'>

					<td>Codigo</td>
					<td>Nome</td>
					<td>Data Cadastro</td>
					<td>Status</td>
					<td>Ações</td>


					<c:forEach var="UG" items="${ListarUG}">
						<tr>

							<td>${UG.codigo}</td>
							<td>${UG.nome}</td>
							<td><fmt:formatDate value="${UG.data}" pattern="dd/MM/yyyy" />
							</td>
							<td>${UG.situacao}</td>
							<td><a href="ExibirAlterarUG?codigo=${UG.codigo}">Alterar</a></td>

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