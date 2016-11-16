<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="windows-1256"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1256">
<title>Login Page</title>
</head>

<body>
	<h2>Login</h2>
	<form action="login" method="post">
		Login: <input type="text" name="login" /> <br /> Senha: <input
			type="password" name="senha" /> <br /> <input type="submit"
			value="Autenticar" />
	</form>
	<%
		if (request.getAttribute("mensagem") != null) {
	%>
	<h2>
		<%=request.getAttribute("mensagem")%>
	</h2>
	<%
		}
	%>
	<a href="<c:url value="/usuario?funcao=formulario" />"><fmt:message key="mensagem.novoUsuario" /></a>
</body>
</html>