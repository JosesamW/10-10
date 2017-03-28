<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Solicitar Diaria</title>
</head>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/estiloCadastrar.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadastro Sugestão</title>
<script type="text/javascript" src="js/jquery-3.1.0.min.js"></script>
<script type="text/javascript" src="js/jquery.validate.min.js"></script>
<script src="js/jquery.maskedinput.js"></script>
<script type="text/javascript" src="js/jquery.validate.min.js"></script>
<script>
	$(document).ready(function() {

		$("#estado").change(function() {
			var uf = $('#estado').val();
			$.get("listarCidade", {
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
			$.get("listarCidade2", {
				'cod_cidade' : uf
			}, function(dados) {
				$('#cidade2').html(dados);
			});
		});
	});
</script>
<script>
	$(document).ready(function() {

		$("#gerarValor").click(function() {
			var origem = $('#CidOrigem').val();
			var destino = $('#CidDestino').val();
			$.get("exibirValor", {
				'origem' : origem,
				'destino' : destino
			}, function(dados) {
				$('#valor').html(dados);
			});
		});
	});
</script>
<script type="text/javascript">
	
</script>
<body>
	<c:import url="../topo.jsp" />
	<br>
	<c:import url="../usuarioLogado.jsp" />
	<div id="formSugestao">
		<h3>Cadastrar Solicitação Diaria</h3>
		<br>
		<form action="cadastarSolicitacao" method="post" id="form" class="form-inline">
			<%--Listar cidades --%>
			<input type="hidden" name="usuarioId" value="${usuarioLogado.idUser}">
			<input type="hidden" name="unidadeGestora" value="${usuarioLogado.uGestora.codigo}">
			
			<div class="form-group">
				<p id="msgIda"></p>
				<p id="msgVolta"></p>

				<div class="form-group">
					<label for="DataIda">Data Partida</label><br> <input
						type="text" name="DataIda" id="DataIda" required="true"
						maxlength="10" minlength="10" class="form-control">

					<form:errors path="solicitarDiaria.DataIda" cssStyle="color:red" />
				</div>

				<div class="form-group">
					<label for="DataVolta">Data Volta</label><br> <input
						type="text" name="DataVolta" id="DataVolta" required="true"
						maxlength="10" minlength="10" class="form-control">
					<form:errors path="solicitarDiaria.DataVolta" cssStyle="color:red" />
				</div>
<br>
				<div class="form-group">
				<label>Tipos de Diarias</label><br>
					<input type="radio" name="tipoDiaria" value="P" required="true">
					<label for="Nome">Parcial</label> <input type="radio"
						name="tipoDiaria" required="true" value="I"> <label
						for="Nome">Integral</label>
				</div>
<br>
				<div class="form-group">
					<label for="Justificativa">Justificativa</label><br>
					<textarea name="Justificativa" required="true" maxlength="50"
						minlength="10" class="form-control"> </textarea>
					<br>
					<form:errors path="solicitarDiaria.Justificativa"
						cssStyle="color:red" />

				</div>
<br>
				<div class="form-group">
					<label>Origem</label> <br> <select id="estado" required="true">
						<%--Lista os estados --%>
						<option value="">Selecione a UF</option>
						<c:forEach var="uf" items="${ListarEstados}">

							<option value="${uf.cod_cidade}">${uf.UF}</option>

						</c:forEach>
					</select>

				</div>
				<div class="form-group" id="cidade"></div>

				<br>
				<div class="form-group">
					<%--Lista os estados --%>
					 <label>Destino</label> <br> <select id="estado2"
						required="true">
						<option value="">Selecione a UF</option>
						<c:forEach var="uf" items="${ListarEstados}">

							<option value="${uf.cod_cidade}">${uf.UF}</option>

						</c:forEach>
					</select>

				</div>
				<div class="form-group" id="cidade2"></div>

				<br>
			<br>
			<div class="form-group">
			
			<button type="button" id="gerarValor" >Consultar Diaria</button><br><div class="form-group" id="valor"></div>
			</div>
			<br><br>
			<button type="submit" class="btn btn-primary">Solicitar</button>
			
		</form>

	</div>
	<script type="text/javascript">
		jQuery(function($) {
			$("#DataVolta").mask("99/99/9999");
			$("#DataIda").mask("99/99/9999");

		})
	</script>
</body>
</html>