<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/bootstrap.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/estiloCadastrar.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Alterar Sugestao</title>
</head>
<body>
	<c:choose>
		<c:when test="${usuarioLogado.nivel=='ADM'}">
			<c:import url="../topo.jsp" />
						<br>
			<c:import url="../usuarioLogado.jsp" />

			
			
				<div id="formulario">
					<h3>Alterar Sugestão de Diaria</h3>
					<form class="form-inline" action="alterarSugestao" method="post">
					          
					          <input type="hidden" name="idUsuario" value="${usuarioLogado.idUser}">
							<input type="hidden"  name="idSD"
								value="${exibirSugestao.idSD}">
						
						<br>
						<div class="form-group">

							<br /> <label for="Cargo">Cargo</label><br> <select
								name="cargo" required="true" class="form-control">
								<option value="${exibirSugestao.cargo.id}">${exibirSugestao.cargo.nome}</option>
								<c:forEach items="${listarCargoUsuario}" var="cargo">
									<option value="${cargo.id}">${cargo.nome}</option>
								</c:forEach>
							</select> <br>
						</div>

						<div class="form-group">

							<br /> <label for="UG">Unidade Gestora</label><br> <select
								name="ug" required="true" class="form-control">
								<option value="${exibirSugestao.ug.codigo}">${exibirSugestao.ug.nome}</option>
								<c:forEach items="${listarUGestora}" var="ug">
									<option value="${ug.codigo}">${ug.nome}</option>
								</c:forEach>
							</select>
						</div>
						<br>
<br>
						<div class="form-group">
							<form:errors path="sugestaoDiaria.valores" cssStyle="color:red" />
							<label for="Nome">Valores</label><br> <input type="text"
								class="form-control" name="valores" size="10" minlength="2"
								maxlength="8" required="true" value="${exibirSugestao.valores}"
								required="true"><br>
						</div>
						
						<br> <br>
						<button type="submit" class="btn btn-primary">Alterar</button>
					</form>
		

			<br>
			<center></center>
		</c:when>
		<c:otherwise>
			<jsp:forward page="../index.jsp" />
		</c:otherwise>
	</c:choose>
</body>
</html>