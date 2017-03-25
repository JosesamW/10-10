<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<style>
#nomeUsuario{
 position: absolute;
    left: 35%;
    top: 2%;
    z-index: 1;
     font-family: Arial, Helvetica, sans-serif;
    }
</style>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h4 id="nomeUsuario">Logado como  , ${usuarioLogado.nivel} : ${usuarioLogado.nome}</h4>
</body>
</html>