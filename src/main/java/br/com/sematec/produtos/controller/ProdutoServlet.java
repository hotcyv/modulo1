package br.com.sematec.produtos.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.sematec.produtos.dao.ProdutoDAO;
import br.com.sematec.produtos.modelo.Produto;

@WebServlet(urlPatterns = "/produto")
public class ProdutoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProdutoDAO dao;
	private final Logger LOGGER = Logger.getLogger(ProdutoServlet.class.getName());

	public ProdutoServlet() {
		this.dao = new ProdutoDAO();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getSession().getAttribute("usuarioLogado") != null) {
			String funcao = req.getParameter("funcao");
			switch (funcao) {
			case "formulario":
				formulario(req, resp);
				break;
			case "remove":
				remove(req, resp);
				break;
			case "lista":
				lista(req, resp);
				break;
			default:
				LOGGER.warning("função desconhecida:" + funcao);
				break;
			}
		} else {
			login(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String imagem = req.getParameter("imagem");
		String nome = req.getParameter("nome");
		BigDecimal preco = req.getParameter("preco") != null ? new BigDecimal(req.getParameter("preco"))
				: new BigDecimal(0.0);
		Produto produto = new Produto(nome, imagem, preco);
		dao.adiciona(produto);
		req.setAttribute("produtoList", dao.lista());
		String pagina = "/WEB-INF/jsp/produto/lista.jsp";
		RequestDispatcher dispatcher = req.getRequestDispatcher(pagina);
		dispatcher.forward(req, resp);
	}

	private void formulario(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String pagina = "/WEB-INF/jsp/produto/formulario.jsp";
		RequestDispatcher dispatcher = req.getRequestDispatcher(pagina);
		dispatcher.forward(req, resp);
	}

	public List<Produto> lista() {
		return dao.lista();
	}

	private void lista(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("produtoList", dao.lista());
		String pagina = "/WEB-INF/jsp/produto/lista.jsp";
		RequestDispatcher dispatcher = req.getRequestDispatcher(pagina);
		dispatcher.forward(req, resp);
	}

	private void remove(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String text;
		if (req.getParameter("id") != null) {
			String id = req.getParameter("id");
			Produto produto = dao.find(id);
			dao.remove(id);
			text = produto.getNome() + " removido com sucesso.";
		} else {
			text = "id do item a ser removido não foi informado.";
		}
		resp.setContentType("text/plain");
		resp.setCharacterEncoding("UTF-8");
		resp.getWriter().write(text);
	}

	public void remove(Produto produto) {
		dao.remove(produto);
	}

	private void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String pagina = "/index.jsp";
		req.setAttribute("mensagem", "Ação restrita a usuários logados!");
		RequestDispatcher dispatcher = req.getRequestDispatcher(pagina);
		dispatcher.forward(req, resp);
	}
}
