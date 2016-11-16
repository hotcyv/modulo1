package br.com.sematec.produtos.controller;

import java.io.IOException;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import br.com.sematec.produtos.dao.CarrinhoDAO;
import br.com.sematec.produtos.dao.ProdutoDAO;
import br.com.sematec.produtos.modelo.Carrinho;
import br.com.sematec.produtos.modelo.Produto;
import br.com.sematec.produtos.modelo.Usuario;

@WebServlet(urlPatterns = "/carrinho")
public class CarrinhoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CarrinhoDAO dao;
	private final Logger LOGGER = Logger.getLogger(ProdutoServlet.class.getName());

	public CarrinhoServlet() {
		this.dao = CarrinhoDAO.getInstance();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String funcao = req.getParameter("funcao");
		switch (funcao) {
		// case "remove":
		// remove(req, resp);
		// break;
		case "lista":
			lista(req, resp);
			break;
		case "adicionaProduto":
			adicionaProduto(req, resp);
			break;
		case "removeProduto":
			removeProduto(req, resp);
			break;
		case "getTotal":
			getTotal(req, resp);
			break;
		default:
			LOGGER.warning("função desconhecida:" + funcao);
			break;
		}
	}
	// public List<Produto> lista() {
	// return dao.lista();
	// }

	private void lista(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(true);
		Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
		req.setAttribute("carrinhoUsuario", dao.adicionaCarrinho(usuario.getLogin()));
		String pagina = "/WEB-INF/jsp/carrinho/lista.jsp";
		RequestDispatcher dispatcher = req.getRequestDispatcher(pagina);
		dispatcher.forward(req, resp);
	}

	private void adicionaProduto(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession session = req.getSession(true);
		Carrinho carrinho = (Carrinho) session.getAttribute("carrinhoCompras");
		String text;
		if (req.getParameter("id") != null) {
			Produto produto = ProdutoDAO.getInstance().find(req.getParameter("id"));
			carrinho.adicionaProduto(produto);
			text = " Adicionado com sucesso.";
		} else {
			text = "id do item a ser removido não foi informado.";
		}
		resp.setContentType("text/plain");
		resp.setCharacterEncoding("UTF-8");
		resp.getWriter().write(text);
	}

	private void removeProduto(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException {
		HttpSession session = req.getSession(true);
		Carrinho carrinho = (Carrinho) session.getAttribute("carrinhoCompras");
		String id = req.getParameter("id");
		if (id != null) {
			carrinho.removeProduto(id);
		}
	}
	private void getTotal(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException {
		HttpSession session = req.getSession(true);
		Carrinho carrinho = (Carrinho) session.getAttribute("carrinhoCompras");
		String text = carrinho.getTotal().toString();
		resp.setContentType("text/plain");
		resp.setCharacterEncoding("UTF-8");
		resp.getWriter().write(text);
	}
}
