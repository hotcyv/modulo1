package br.com.sematec.produtos.controller;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import br.com.sematec.produtos.dao.UsuarioDAO;
import br.com.sematec.produtos.modelo.Usuario;

@WebServlet(urlPatterns = "/usuario")
public class UsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UsuarioDAO dao;
	private final Logger LOGGER = Logger.getLogger(UsuarioServlet.class.getName());

	public UsuarioServlet() {
		this.dao = new UsuarioDAO();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			String funcao = req.getParameter("funcao");
			switch (funcao) {
			case "formulario":
				formulario(req, resp);
				break;
			case "lista":
				lista(req, resp);
				break;
			default:
				LOGGER.warning("função desconhecida:" + funcao);
				break;
			}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String login = req.getParameter("login");
		String nome = req.getParameter("nome");
		String senha = req.getParameter("senha");
		Usuario usuario = new Usuario(nome, login, senha);
		dao.adiciona(usuario);
		//req.setAttribute("produtoList", dao.lista());
		String pagina = "/index.jsp";
		RequestDispatcher dispatcher = req.getRequestDispatcher(pagina);
		dispatcher.forward(req, resp);
	}

	private void formulario(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String pagina = "/WEB-INF/jsp/usuario/formulario.jsp";
		RequestDispatcher dispatcher = req.getRequestDispatcher(pagina);
		dispatcher.forward(req, resp);
	}

	public List<Usuario> lista() {
		return dao.lista();
	}

	private void lista(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("usuarioList", dao.lista());
		String pagina = "/WEB-INF/jsp/usuario/lista.jsp";
		RequestDispatcher dispatcher = req.getRequestDispatcher(pagina);
		dispatcher.forward(req, resp);
	}
}
