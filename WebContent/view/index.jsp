<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/bootstrap.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/estiloIndex.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Monitoria de Gastos</title>
</head>

<body>

	<div class="container-fluid">
		<div class="row">
		<div class="jumbotron well">
				<div class="page-header">
					<c:import url="topo2.jsp" />
				</div>
				
				
				<div class="jumbotron well">
					<c:choose>
						<c:when test="${usuarioLogado.nome!=null}">
							<h2>
								<p>Bem vindo, ${usuarioLogado.nome}</p>
							</h2>
						</c:when>
						<c:otherwise>
							<h2>
								<p>Bem vindo, Visitante</p>
							</h2>
						</c:otherwise>
					</c:choose>
					Objetivo é acompanhar as despesas, mantendo uma assídua orientação
					aos agentes públicos para o equilíbrio das contas, a manutenção dos
					serviços e das políticas públicas, ressaltando a importância de não
					elevar as despesas..
					</p>
					<p></p>
				</div>

				
				<div class="row">
					<div class="col-md-4">
						<center>
							<h2>Solicitação</h2>
						</center>
						


					</div>
					<div class="col-md-4">
						<center>
							<h2>Consulta</h2>
						</center>
						
					</div>
					<div class="col-md-4">
						<center>
							<h2>Análise</h2>
						</center>
						
					</div>
				</div>
			</div>
		</div>
	</div>
	
</body>
</html>