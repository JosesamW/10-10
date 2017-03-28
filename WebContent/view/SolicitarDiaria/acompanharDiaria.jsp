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


	<c:import url="../topo.jsp" />
	<br>
	<c:import url="../usuarioLogado.jsp" />
	<c:choose>
		<c:when test="${usuarioLogado.nivel=='GESTORUG'}">
	<div id="formularioHistorico">
		<form action="acompanharSolicitacaoGestor" method="post"
			class="form-inline">
			<h3>Pesquisar Alterações asdasd Diaria</h3>
			<br>
			<div class="form-group">
				<label for="Nome">CPF</label><br> <input type="text"
					class="form-control" name="cpfU" placeholder="cpf" maxlength="11">
			</div>
		
			<div class="form-group">
			<label for="Nome">Nome Uasuario</label><br><input type="text"
				class="form-control" name="nomeU" placeholder="Nome" maxlength="30"><br>
			</div>
				
			<br> <label for="Nome">Periodo</label><br> <input
				type="text" class="form-control" name="data1" placeholder="datagestor"
				maxlength="10" > <label for="Nome"> a </label> <input
				type="text" class="form-control" name="data2" placeholder="dataGestor"
				maxlength="10" ><br> <br>
			
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
		</form>
		<br>
	</div>
	<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
			<table id="tabela" class="table table-striped">
				<tr style='background-color: #E6E6E6; font-weight: bold;'>


					<td>Motivo</td>
					<td>Usuario solicitante</td>
					<td>Ida(Cidade)</td>
					<td>Ida(Data)</td>
					<td>Volta(Cidade)</td>
					<td>Volta(Data)</td>
					<td>Tipo diaria</td>
					<td>Valor diaria</td>
					<td>Justificativa Gestor</td>
					<td>Gestor</td>
					<td>Situação</td>
					<td>Ação</td>

				</tr>
				<c:forEach var="sd" items="${ListarSolicitacaoGestor}">
					<tr>

						<td>${sd.justificativa}</td>
						<td>${sd.idUsuario.nome}</td>
						<td>${sd.cidDestino.nome}</td>
						<td><fmt:formatDate value="${sd.dataIda}"
								pattern="dd/MM/yyyy" /></td>
						<td>${sd.cidOrigem.nome}</td>
						<td><fmt:formatDate value="${sd.dataVolta}"
								pattern="dd/MM/yyyy" /></td>
						<td>${sd.tipoDiaria}</td>
						<td>${sd.valorDiaria}</td>
						<td>${sd.justificativaGestor}</td>
						<td>${sd.idGestor.nome}</td>
						<c:choose>
							<c:when test="${sd.def=='Aceitado'}">
								<td bgcolor="3CB371">${sd.def}</td>
							</c:when>
							<c:when test="${sd.def=='Recusado'}">
								<td bgcolor="FF7F50">${sd.def}</td>
							</c:when>
							<c:otherwise>
								<td bgcolor="87CEFA">${sd.def}</td>
							</c:otherwise>
						</c:choose>
						<td><a
							href="ExibiralterarSolicitacao?idSolicitacao=${sd.codSD}">Alterar</a></td>
				</c:forEach>
			</table>
		</c:when>
		<c:otherwise>
		
			<table id="tabela" class="table table-striped">
				<tr style='background-color: #E6E6E6; font-weight: bold;'>


					<td>Motivo</td>
					<td>Origem</td>
					<td>Destino</td>
					<td>Partida</td>
					<td>Volta</td>
					<td>Tipo diaria</td>
					<td>Valor diaria</td>
					<td>Justificativa Gestor</td>
					<td>Nome Gestor</td>
					<td>Status</td>

				</tr>
				<c:forEach var="sd" items="${acompanharSolicitacao}">
					<tr>

						<td>${sd.justificativa}</td>
						<td>${sd.cidOrigem.nome}</td>
						<td>${sd.cidDestino.nome}</td>
						<td><fmt:formatDate value="${sd.dataIda}"
								pattern="dd/MM/yyyy" /></td>
						<td><fmt:formatDate value="${sd.dataVolta}"
								pattern="dd/MM/yyyy" /></td>
						<td>${sd.tipoDiaria}</td>
						<td>${sd.valorDiaria}</td>
						<td>${sd.justificativaGestor}</td>
						<td>${sd.idGestor.nome}</td>
						<c:choose>
							<c:when test="${sd.def=='Aceitado'}">
								<td bgcolor="3CB371">${sd.def}</td>
							</c:when>
							<c:when test="${sd.def=='Recusado'}">
								<td bgcolor="FF7F50">${sd.def}</td>
							</c:when>
							<c:otherwise>
								<td bgcolor="87CEFA">${sd.def}</td>
							</c:otherwise>
						</c:choose>
				</c:forEach>
			</table>
		</c:otherwise>
	</c:choose>
</body>
</html>