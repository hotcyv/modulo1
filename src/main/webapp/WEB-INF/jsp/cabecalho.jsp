<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<h1>Loja</h1>
<a href="<c:url value="produto?funcao=lista" />"><fmt:message key="mensagem.home" /></a>
<a href="<c:url value="usuario?funcao=lista" />"><fmt:message key="mensagem.usuarios" /></a>
<a href="<c:url value="carrinho?funcao=lista" />"><fmt:message key="mensagem.carrinho" /></a>
<a href="<c:url value="login?funcao=logout" />"><fmt:message key="mensagem.logout" /></a>
<hr />