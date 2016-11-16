<%@page import="br.com.sematec.produtos.modelo.Produto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
		function delProdutoCarrinho(id) {
			$.get('carrinho?funcao=removeProduto&id=' + id, function(data) {
				$("#mensagem").text(data);
			});
			$("#iten" + id).remove();
		}
	</script>
	<c:import url="../cabecalho.jsp" />
	<h2>
		<fmt:message key="mensagem.bemvindo" />
	</h2>
	<h1>Carrinho</h1>
	<div id="mensagem"></div>
	<table width="100%" border="1">
		<tr>
			<td>No.</td>
			<td width="20%">Produto</td>
			<td>Qtd.</td>
			<td>Preco</td>
			<td>Remover do carrinho?</td>
		</tr>
		<c:forEach var="i" items="${carrinhoUsuario.getItens()}"
			varStatus="st">
			<tr id="iten${i.id}">
				<td>${st.count}</td>
				<td>${i.getNomeProduto()}</td>
				<td>${i.quantidade}</td>
				<td><fmt:formatNumber value="${i.total}" type="currency" /></td>
				<td><a href="#" onclick="return delProdutoCarrinho('${i.id}')">Remover</a></td>
			</tr>
		</c:forEach>
		<tr>
		<td>Total:</td>
			<td id="total"><fmt:formatNumber value="${carrinhoUsuario.getTotal()}" type="currency" /></td>
		</tr>
	</table>

	<c:import url="../rodape.jsp" />
</body>
</html>
