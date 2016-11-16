<%@page import="br.com.sematec.produtos.modelo.Produto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
<script type="text/javascript" src="<c:url value="/js/jquery.js"/>"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Lista Usuários</title>
</head>
<body>
	<h3>Usuários</h3>
	<div id="mensagem"></div>
	<table width="100%" border="1">
		<tr>
			<td>No.</td>
			<td width="20%">Nome</td>
			<td>Login</td>
		</tr>
		<c:forEach var="u" items="${usuarioList}" varStatus="st">
			<tr>
				<td>${st.count}</td>
				<td>${u.nome}</td>
				<td>${u.login}</td>
			</tr>
		</c:forEach>
	</table>
	<c:import url="../rodape.jsp" />
</body>
</html>
