package br.com.sematec.produtos.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import br.com.sematec.produtos.modelo.Usuario;

public class UsuarioDAO {

	private static void geraIdEAdiciona(Usuario u) {
		USUARIOS.put(u.getLogin(), u);
	}

	private final static Map<String, Usuario> USUARIOS = new HashMap<>();
	static {

		geraIdEAdiciona(new Usuario("Victor Gonçalves", "victor", "victor"));
	}

	public Usuario buscaPorEmailESenha(String email, String senha) {
		if (!USUARIOS.containsKey(email)) {
			return null;
		}
		Usuario usuario = USUARIOS.get(email);
		if (usuario.getSenha().equals(senha)) {
			return usuario;
		}
		return null;
	}
	
	public void adiciona(Usuario u) {
		geraIdEAdiciona(u);
	}

	public Usuario buscaUsuarioPorLoginESenha(Usuario usuario) {
		if (!USUARIOS.containsKey(usuario.getLogin())) {
			return null;
		}
		String login = usuario.getLogin();
		String senha = usuario.getSenha();
		usuario = USUARIOS.get(login);
		if (usuario.getSenha().equals(senha)) {
			return usuario;
		}
		return null;
	}
	public List<Usuario> lista() {
		return new ArrayList<Usuario>(USUARIOS.values());
	}
}
