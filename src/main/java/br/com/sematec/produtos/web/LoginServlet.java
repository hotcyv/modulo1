package br.com.sematec.produtos.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.sematec.produtos.dao.CarrinhoDAO;
import br.com.sematec.produtos.dao.ProdutoDAO;
import br.com.sematec.produtos.dao.UsuarioDAO;
import br.com.sematec.produtos.modelo.Usuario;

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String funcao = req.getParameter("funcao");
		switch (funcao) {
		case "logout":
			logout(req, resp);
			break;
		default:
			System.out.println("funcao desconhecida" + funcao);
			break;
		}
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, java.io.IOException {
		try {
			Usuario usuario = new Usuario();
			usuario.setLogin(req.getParameter("login"));
			usuario.setSenha(req.getParameter("senha"));
			usuario = new UsuarioDAO().buscaUsuarioPorLoginESenha(usuario);
			if (usuario != null) {
				HttpSession session = req.getSession(true);
				session.setAttribute("carrinhoCompras", CarrinhoDAO.getInstance().adicionaCarrinho(usuario.getLogin()));
				session.setAttribute("usuarioLogado", usuario);
				req.setAttribute("produtoList", new ProdutoDAO().lista());
				String pagina = "/WEB-INF/jsp/produto/lista.jsp";
				RequestDispatcher dispatcher = req.getRequestDispatcher(pagina);
				dispatcher.forward(req, resp);
			} else {
				req.setAttribute("mensagem", "email ou senha inválidos.");
				String pagina = "/index.jsp";
				RequestDispatcher dispatcher = req.getRequestDispatcher(pagina);
				dispatcher.forward(req, resp);
			}
		} catch (Throwable theException) {
			System.out.println(theException);
		}
	}
	
	private void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getSession().removeAttribute("usuarioLogado");
		req.setAttribute("mensagem", "logout realizado");
		String pagina = "/index.jsp";
		RequestDispatcher dispatcher = req.getRequestDispatcher(pagina);
		dispatcher.forward(req, resp);
	}
}
