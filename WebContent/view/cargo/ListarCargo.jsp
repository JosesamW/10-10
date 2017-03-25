<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/bootstrap.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/estiloConsultar.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Listar Cargo</title>
</head>
<body>
	<c:choose>
		<c:when test="${usuarioLogado.nivel=='ADM'}">

			<c:import url="../topo.jsp" />
						<br>
			<c:import url="../usuarioLogado.jsp" />

			<div id="formulario">
				<form action="listarCargo" class="form-inline">
					<h3>Consultar Cargo</h3><br>
					<div class="form-group">
						<label for="Nome">Nome do Cargo</label><br> <input
							type="text" class="form-control" name="nome"
							placeholder="Nome do Cargo "><br>
					</div>
					<br>
					<div class="form-group">
						<label for="Nome"> Id </label><br> <input type="text"
							class="form-control" name="id" placeholder="Id do cargo "><br>
					</div>
					<br> <br>
					<button type="submit" class="btn btn-primary">Consultar</button>
				</form>
				<br>
			</div>

			<table id="tabela" class="table table-striped">
				<tr style='background-color: #E6E6E6; font-weight: bold;'>

					<td>Id</td>
					<td>Cargo</td>
					<td>Data Cadastro</td>
					<td>Status</td>
					<td>Ações</td>


					<c:forEach var="cargo" items="${ListarCargo}">
						<tr>

							<td>${cargo.id}</td>
							<td>${cargo.nome}</td>
							<td><fmt:formatDate value="${cargo.data_cadastro}"
									pattern="dd/MM/yyyy" /></td>
							<td>${cargo.situacao}</td>
							<td><a href="exibirCargo?id=${cargo.id}">Alterar</a></td>

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