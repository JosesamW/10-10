<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/estiloCadastrar.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadastro Sugestão</title>
<script type="text/javascript" src="js/jquery-3.1.1.js"></script>
<script>
	$(document).ready(function() {

		$("#estado").change(function() {
			var uf = $('#estado').val();
			$.get("exibirCidade", {
				'cod_cidade' : uf
			}, function(dados) {
				$('#cidade').html(dados);
			});
		});
	});
</script>
<script>
	$(document).ready(function() {

		$("#estado2").change(function() {
			var uf = $('#estado2').val();
			$.get("exibirCidade2", {
				'cod_cidade' : uf
			}, function(dados) {
				$('#cidade2').html(dados);
			});
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
			<div id="formulario">
				<h3>Cadastrar Sugestão de Diaria</h3><br>
				<form action="cadastrarSugestao" method="post" class="form-inline">

					<div class="form-group">
						
						<label for="Cargo">Cargo:</label><br> <select name="cargo"
							required="true" class="form-control">
							<option value="">Selecione o cargo</option>
							<%-- listar cargos no select --%>
							<c:forEach items="${listarCargoUsuario}" var="cargo">
								<c:if test="${cargo.situacao=='ATIVO'}">
									<option value="${cargo.id}">${cargo.nome}</option>
								</c:if>
							</c:forEach>
						</select>
					</div>


					<div class="form-group">
						
						<label for="UG">Unidade Gestora</label><br> <select name="ug"
							required="true" class="form-control">
							<%-- listar ug no select --%>
							<option value="">Selecione a UG</option>
							<c:forEach items="${listarUGestora}" var="ug">
								<c:if test="${ug.situacao=='ATIVO' }">
									<option value="${ug.codigo}">${ug.nome}</option>
								</c:if>
							</c:forEach>
						</select>
					</div>
<br><br>

					<div class="form-group">
						<label>Origem</label> <br> <select id="estado"
							required="true" class="form-control">
							<%--Lista os estados --%>
							<option value="">Selecione a UF</option>
							<c:forEach var="uf" items="${ListarEstados}">

								<option value="${uf.cod_cidade}">${uf.UF}</option>

							</c:forEach>
						</select>
					</div><br>
					<div class="form-group" id="cidade">
						<%--Listar cidades --%>

					</div>
<br>
					<div class="form-group">
						<%--Lista os estados --%><br>
						<label>Destino</label> <br> <select id="estado2"
							required="true" class="form-control" >
							<option value="">Selecione a UF</option>
							<c:forEach var="uf" items="${ListarEstados}">

								<option value="${uf.cod_cidade}">${uf.UF}</option>


							</c:forEach>
						</select>

					</div>
					<br>
					<%--Listar cidades --%>
					<div class="form-group" id="cidade2"></div>

<br><br>
					<div class="form-group">
						<form:errors path="sugestaoDiaria.valores" cssStyle="color:red" />
						<label for="Nome">Valores</label><br> <input type="text"
							class="form-control" name="valores" size="10" minlength="2"
							maxlength="8" required="true" placeholder="00.00" required="true"><br>
					</div>
<br> <br>
					<button type="submit" class="btn btn-primary">Sugerir</button>
				</form>

			</div>

		</c:when>
		<c:otherwise>
		<jsp:forward page="../index.jsp" />
		</c:otherwise>
	</c:choose>
</body>
</html>