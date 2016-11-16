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
<title>Lista de produtos</title>
</head>
<body>
	<script type="text/javascript">
		function addProdutoCarrinho(id) {
			$.get('carrinho?funcao=adicionaProduto&id=' + id, function(data) {
				$("#mensagem").text(data);
			});
		}
	</script>
	<c:import url="../cabecalho.jsp" />
	<h3>
		<fmt:message key="mensagem.bemvindo" />
	</h3>
	<h1>Produtos</h1>
	<div id="mensagem"></div>
	<table width="100%" border="1">
		<tr>
			<td>Imagem</td>
			<td width="20%">Nome</td>
			<td>Preco</td>
			<td width="20%">Adicionar ao carrinho?</td>
		</tr>
		<c:forEach var="p" items="${produtoList}" varStatus="st">
			<tr id="produto${p.id}">
				<td><img height="80" src="${p.imagem}"></td>
				<td>${p.nome}</td>
				<td><fmt:formatNumber value="${p.preco}" type="currency" /></td>
				<td><a href="#" onclick="return addProdutoCarrinho('${p.id}')">Adicionar</a></td>
			</tr>
		</c:forEach>
	</table>
	<c:import url="../rodape.jsp" />
</body>
</html>
