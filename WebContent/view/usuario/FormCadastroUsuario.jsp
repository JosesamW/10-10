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
<title>Cadastro usuario</title>


<script type="text/javascript" src="js/jquery-3.1.0.min.js"></script>
<script type="text/javascript" src="js/jquery.validate.min.js"></script>
<script src="js/jquery.maskedinput.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		$('#formulario').validate({
			rules : {

				senha : {

				},
				senha2 : {
					equalTo : "#senha"
				}
			},
			messages : {}
		});
	});
</script>
</head>
<body>
	<c:choose>
		<c:when test="${usuarioLogado.nivel=='ADM'}">

			<c:import url="../topo.jsp" />
			<br>
			<c:import url="../usuarioLogado.jsp" />

			<div id="formUsuario">
				
				<form action="cadastrarUsuario" method="post" id="formulario"
					class="form-inline">
<h3>Cadastro de Usuário</h3>
					<div class="form-group">


						<div id="constrain_error">${msgErrorPkUser}</div>
						<label for="Cpf">CPF*</label><br> <input type="text"
							class="form-control" name="cpf" id="cpf"
							placeholder="Apenas Numeros" size="11" Maxlength="11"
							Minlength="11" required><br>
						<form:errors path="usuario.cpf" cssStyle="color:red" />

					</div>

					<div class="form-group">

						<br /> <label for="Nome">Nome*</label><br> <input
							type="text" class="form-control" name="nome"
							placeholder="Apenas Letras" size="35" Maxlength="60"
							Minlength="8" required><br>
						<form:errors path="usuario.nome" cssStyle="color:red" />
						<br>
					</div>
					<br>
					<div class="form-group">

						<br />
						<div id="constrain_error">${msgErrorPkUser}</div>
						<label for="Email">E-mail*</label><br> <input type="text"
							class="form-control" name="email"
							placeholder="exemplo@exemplo.com" size="38" Maxlength="100"
							Minlength="13" required><br>
						<form:errors path="usuario.email" cssStyle="color:red" />
						<br>
					</div>

					<div class="form-group">

						<br /> <label for="dataNascimento">Data de Nascimento*</label><br>
						<input type="text" class="form-control" name="dataNascimento"
							id="datanascimento" placeholder="00/00/0000" size="8"
							Maxlength="10" Minlength="10" required><br>
						<form:errors path="usuario.dataNascimento" cssStyle="color:red" />
						<br>
					</div>

					<br>
					<div class="form-group">

						<br /> <label for="Endereco">Endereço</label><br> <input
							type="text" class="form-control" name="endereco"
							placeholder="exemplo exemplo exemplo" size="51"><br>
						<br>
					</div>
					<br>
					<p id="result" cssStyle="color:red"></p>
					<div class="form-group">

						<label for="Senha">Senha*</label><br> <input type="password"
							class="form-control" name="senha" id="senha" placeholder="senha"
							Maxlength="20" Minlength="6" required size="23"><br>

						<form:errors path="usuario.senha" cssStyle="color:red" />

					</div>

					<div class="form-group">

						<br> <label for="Senha">Confirmar Senha*</label><br> <input
							type="password" class="form-control" name="senha2"
							placeholder="senha" Maxlength="20" Minlength="6" required
							size="23"><br>
						<form:errors path="usuario.senha" cssStyle="color:red" />

						<br>
					</div>


					<br>
					<div class="form-group">

						<br /> <label for="Cargo">Cargo*</label><br> <select
							name="cargo" required="true" class="form-control">
							<option value="">Selecione o cargo</option>
							<c:forEach items="${listarCargoUsuario}" var="cargo">
								<c:if test="${cargo.situacao=='ATIVO'}">
									<option value="${cargo.id}">${cargo.nome}</option>
								</c:if>
							</c:forEach>
						</select>
						<form:errors path="usuario.cargo" cssStyle="color:red" />
					</div>
					&nbsp;&nbsp;
					<div class="form-group">

						<br /> <label for="UG">Unidade Gestora*</label><br> <select
							name="uGestora" required="true" class="form-control">
							<option value="">Selecione a UG</option>
							<c:forEach items="${listarUGestora}" var="ug">
								<c:if test="${ug.situacao=='ATIVO'}">
									<option value="${ug.codigo}">${ug.nome}</option>
								</c:if>
							</c:forEach>
						</select>
						<form:errors path="usuario.uGestora" cssStyle="color:red" />
					</div>
					<br>
					<div class="form-group">

						<label for="Status">Situação de Usuario*:</label><br> <input
							type="radio" class="form-control" name="Situacao" value="ATIVO"
							required>
						<form:errors path="usuario.situacao" cssStyle="color:red" />

						<label for="Status">Ativo</label> <input type="radio"
							class="form-control" name="Situacao" value="INATIVO" required>
						<label for="Status">Inativo</label>

					</div>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<div class="form-group">

						<br> <br> <label for="Status">Tipo de Usuario*:</label><br>
						<input type="radio" class="form-control" name="Nivel" value="ADM"
							required> <label for="Status">Administrador</label> <input
							type="radio" class="form-control" name="Nivel" value="USUARIO"
							required> <label for="Status">Usuario comum*</label> <br>

						<input type="radio" class="form-control" name="Nivel"
							value="GESTORUG" required> <label for="Status">Gestor
							UG*</label> <br>
					</div>
					<br>
					<button type="submit" class="btn btn-primary">Cadastrar</button>
				</form>
			</div>
		</c:when>
		<c:otherwise>
			<jsp:forward page="../index.jsp" />
		</c:otherwise>
	</c:choose>
	<script type="text/javascript">
		jQuery(function($) {
			
			$("#datanascimento").mask("99/99/9999");

		})
	</script>
</body>
</html>

