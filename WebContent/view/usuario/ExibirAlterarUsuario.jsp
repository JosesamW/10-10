<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
<title>Alterar Usuario</title>
</head>
<body>
	<c:choose>
		<c:when test="${usuarioLogado.nivel=='ADM'}">
			<c:import url="../topo.jsp" />
			<br>
			<c:import url="../usuarioLogado.jsp" />

			<div id="formulario">
				<form class="form-inline" action="AlterarUsuario" method="post">

					<h3>Alterar Usuário</h3>
					<div class="form-group">
						<input type="hidden" name="idUser" value="${exibirUsuario.idUser}">
						<input type="hidden" name="UsuarioLogado"
							value="${usuarioLogado.idUser}">
					</div>

					<div class="form-group">

						<br /> <label for="Nome">Nome*</label><br> <input
							type="text" class="form-control" name="nome"
							placeholder="Apenas Letras" size="35" Maxlength="60"
							Minlength="8" required value="${exibirUsuario.nome}"><br>
						<form:errors path="usuario.nome" cssStyle="color:red" />
						<br>
					</div>
					<br>
					<div class="form-group">
						<form:errors path="usuario.endereco" cssStyle="color:red" />
						<br /> <label for="Endereco">Endereço</label><br> <input
							type="text" class="form-control" name="endereco"
							value="${exibirUsuario.endereco}" size="35" Maxlength="60"
							Minlength="10"><br>
					</div>
					<br>
					<div class="form-group">
						<br>
						<div id="constrain_error">${msgErrorPkUser}</div>
						<label for="Email">E-mail*</label><br> <input type="text"
							class="form-control" name="email"
							value="${exibirUsuario.email}" size="38" Maxlength="100"
							Minlength="13" required><br>
						<form:errors path="usuario.email" cssStyle="color:red" />

					</div>
					<br>
					<div class="form-group">
						<form:errors path="usuario.dataNascimento" cssStyle="color:red" />
						<br /> <label for="dataNascimento">Data de Nascimento</label><br>
						<input type="text" class="form-control" name="dataNascimento"
							value="${exibirUsuario.dataNascimento}" size="8" Maxlength="10"
							Minlength="10"><br>
					</div>
					<br>
					<div class="form-group">
						<form:errors path="usuario.cargo" cssStyle="color:red" />
						<br /> <label for="Cargo">Cargo</label><br> <select
							name="cargo" required="true" class="form-control">
							<option value="${exibirUsuario.cargo.id}">${exibirUsuario.cargo.nome}</option>
							<c:forEach items="${listarCargoUsuario}" var="cargo">
								<option value="${cargo.id}">${cargo.nome}</option>
							</c:forEach>
						</select>
					</div>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<div class="form-group">
						<form:errors path="usuario.uGestora" cssStyle="color:red" />
						<br /> <label for="UG">Unidade Gestora</label><br> <select
							name="uGestora" required="true" class="form-control">
							<option value="${exibirUsuario.uGestora.codigo}">${exibirUsuario.uGestora.nome}</option>
							<c:forEach items="${listarUGestora}" var="ug">
								<option value="${ug.codigo}">${ug.nome}</option>
							</c:forEach>
						</select>
					</div>
					<br> <br>
					<%--  Exibir radio ja com o value do banco --%>
					<div class="form-group">
						<label for="Status">Situação de Usuario*:</label><br> <label
							for="Status">Ativo</label>
						<c:choose>
							<c:when test="${exibirUsuario.situacao=='ATIVO'}">
								<input type="radio" class="form-control" name="Situacao"
									value="ATIVO" checked="true">
								<br>
							</c:when>
							<c:otherwise>
								<input type="radio" class="form-control" name="Situacao"
									value="ATIVO">
							</c:otherwise>
						</c:choose>
						<label for="Status">Inativo</label>
						<c:choose>
							<c:when test="${exibirUsuario.situacao=='INATIVO'}">
								<input type="radio" class="form-control" name="Situacao"
									value="INATIVO" checked="true">
								<br>
							</c:when>
							<c:otherwise>
								<input type="radio" class="form-control" name="Situacao"
									value="INATIVO">
							</c:otherwise>
						</c:choose>
					</div>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<%--  Exibir radio ja com o value do banco --%>
					<div class="form-group">
						<label for="Status">Tipo de Usuario*:</label><br> <label
							for="Status">Administrador</label>
						<c:choose>
							<c:when test="${exibirUsuario.nivel=='ADM'}">
								<input type="radio" class="form-control" name="Nivel"
									value="ADM" checked="true">
								<br>
							</c:when>
							<c:otherwise>
								<input type="radio" class="form-control" name="Nivel"
									value="ADM">
							</c:otherwise>
						</c:choose>
						<label for="Status">Usuario comum</label>
						<c:choose>
							<c:when test="${exibirUsuario.nivel=='USUARIO'}">
								<input type="radio" class="form-control" name="Nivel"
									value="USUARIO" checked="true">
								<br>
							</c:when>
							<c:otherwise>
								<input type="radio" class="form-control" name="Nivel"
									value="USUARIO">
							</c:otherwise>
						</c:choose>
						<label for="Status">Unidade Gestora</label>
						<c:choose>
							<c:when test="${exibirUsuario.nivel=='GESTORUG'}">
								<input type="radio" class="form-control" name="Nivel"
									value="GESTORUG" checked="true">
								<br>
							</c:when>
							<c:otherwise>
								<input type="radio" class="form-control" name="Nivel"
									value="GESTORUG">
							</c:otherwise>
						</c:choose>
					</div>
					<br> <br>
					<button type="submit" class="btn btn-primary">Alterar</button>
				</form>
			</div>

			<br>

		</c:when>
		<c:otherwise>
			<jsp:forward page="../index.jsp" />
		</c:otherwise>
	</c:choose>
</body>
</html>