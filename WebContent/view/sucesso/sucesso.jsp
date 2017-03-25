<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SUCESSO!</title>
</head>
<body>
	<div id="topo">
		<c:import url="../topo.jsp" />
	</div>
	<br />
	<br />
	<br />
	<br />
	<br />
	<div id="msg_sucesso">
		<center>
			<h2>
				${msgSucesso}
				<%-- global --%>
			</h2>
		</center>
	</div>



</body>
</html>