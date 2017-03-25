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
				<form action="listarHistorico" class="form-inline">
					<h3>Pesquisar Alterações</h3>
					<br>
					<div class="form-group">
						<label for="Nome">CPF</label><br> <input
							type="text" class="form-control" name="cpfU"
							placeholder="cpf" maxlength="11">
					</div>
					
					
						<label for="Nome">Nome Usuario</label> <input
							type="text" class="form-control" name="nomeU"
							placeholder="Nome" maxlength="30"><br>
					
						<br>
					
						<label for="Nome">Periodo</label><br> <input
							type="text" class="form-control" name="datah"
							placeholder="data1" maxlength="10">
							<label for="Nome"> a </label>
							<input
							type="text" class="form-control" name="dataH"
							placeholder="data2" maxlength="10" ><br>
					
					<br>
						<div class="form-group">

						<label for="UG">Unidade Gestora</label><select
							name="uGestoraU" class="form-control">
							<option value="">Selecione a UG</option>
							<c:forEach items="${listarUGestora}" var="ug">
								<c:if test="${ug.situacao=='ATIVO'}">
									<option value="${ug.codigo}">${ug.nome}</option>
								</c:if>
							</c:forEach>
						</select>
						</div>
						
						
						<label for="Objetos">Objeto Alterado</label>
						<select name="objetoAlterado" class="form-control">
						<option value="">Selecione</option>
						<option value="Cargo">Cargo</option>
						<option value="Unidade Gestora">Unidade Gestora</option>
						<option value="Usuario">Usuario</option>
						<option value="Sugestao Diaria">Sugestão diaria</option>
						</select>
						
					
					<button type="submit" class="btn btn-primary">Consultar</button>
				</form>
				<br>
			</div>

			<table id="tabela" class="table table-striped">
				<tr style='background-color: #E6E6E6; font-weight: bold;'>

					
					<td>Usuario Autor</td>
					<td>Data Alteração</td>
					<td>Campo</td>



					<c:forEach var="hs" items="${historico}">
						<tr>

							
							<td>${hs.idUsuarioAutor.nome}</td>
							<td><fmt:formatDate value="${hs.dataAlteracao}"
									pattern="dd/MM/yyyy" /></td>
							<td>${hs.campo}</td>

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