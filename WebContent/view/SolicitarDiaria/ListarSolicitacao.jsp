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
	href="${pageContext.request.contextPath}/css/estiloCadastrar.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Listar Sugestão</title>
</head>
<body>
	<c:choose>
		<c:when test="${usuarioLogado.nivel=='ADM'}">

			<c:import url="../topo.jsp" />
			<br>
			<c:import url="../usuarioLogado.jsp" />
			<%-- form filtro --%>

			<div id="formularioHistorico">
				<form action="listarSolicitacao" method="post" class="form-inline">
					<h3>Pesquisar Alterações de Diarias</h3>
					<br>

					<div class="form-group">
						<label for="Nome">CPF</label><br> <input type="text"
							class="form-control" name="cpfU" placeholder="cpf" maxlength="11">
					</div>

					<div class="form-group">
						<label for="Nome">Nome Usuario</label><br>
						<input type="text" class="form-control" name="nomeU"
							placeholder="Nome" maxlength="30">
					</div>
					<br>
					<div class="form-group">
					<label for="Nome">Periodo</label><br>
					<input type="text" class="form-control" name="dataadm"
						placeholder="data1" maxlength="10"> <label for="Nome">
						a </label> <input type="text" class="form-control" name="dataAdm"
						placeholder="data2" maxlength="10"><br> <br>
						</div>
					<div class="form-group">
</div>
<br>
<div class="form-group">
						<label for="UG">Unidade Gestora</label><br><select name="uGestoraU"
							class="form-control">
							<option value="">Selecione a UG</option>
							<c:forEach items="${listarUGestora}" var="ug">
								<c:if test="${ug.situacao=='ATIVO'}">
									<option value="${ug.codigo}">${ug.nome}</option>
								</c:if>
							</c:forEach>
						</select>
					</div>
					<br><br>
<button type="submit" class="btn btn-primary">Consultar</button>
</div>
					
				</form>
				<br>
			</div>


<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
			<table id="tabela" class="table table-striped">
				<tr style='background-color: #E6E6E6; font-weight: bold;'>


					<td>UG</td>
					<td>Usuario solicitante</td>
					<td>Ida(Cidade)</td>
					<td>Ida(Data)</td>
					<td>Volta(Cidade)</td>
					<td>Volta(Data)</td>
					<td>Gestor</td>
					<td>Situação</td>



				</tr>
				<c:forEach var="sd" items="${listarSolicitacao}">
					<tr>

						<td>${sd.unidadeGestora.nome}</td>
						<td>${sd.idUsuario.nome}</td>
						<td>${sd.cidDestino.nome}</td>
						<td><fmt:formatDate value="${sd.dataIda}"
								pattern="dd/MM/yyyy" /></td>
						<td>${sd.cidOrigem.nome}</td>
						<td><fmt:formatDate value="${sd.dataVolta}"
								pattern="dd/MM/yyyy" /></td>
						<td>${sd.idGestor.nome}</td>
						<td>${sd.def}</td>

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