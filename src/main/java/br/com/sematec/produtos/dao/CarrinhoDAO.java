package br.com.sematec.produtos.dao;

import java.util.HashMap;
import java.util.Map;

import br.com.sematec.produtos.modelo.Carrinho;

public class CarrinhoDAO {

	private static CarrinhoDAO carrinho;

	private final static Map<String, Carrinho> CARRINHOS = new HashMap<>();

	private CarrinhoDAO() {
		super();
	}

	public static synchronized CarrinhoDAO getInstance() {
		if (carrinho == null) {
			carrinho = new CarrinhoDAO();
		}
		return carrinho;
	}

	private Carrinho buscaCarrinho(String idUsuario) {
		Carrinho carrinho = null;
		carrinho = CARRINHOS.get(idUsuario);
		return carrinho;
	}

	public Carrinho adicionaCarrinho(String idUsuario) {
		Carrinho carrinho = this.buscaCarrinho(idUsuario);
		if (carrinho == null) {
			carrinho = new Carrinho();
			if (!CARRINHOS.containsKey(idUsuario)) {
				CARRINHOS.put(idUsuario, carrinho);
			}
		}
		return carrinho;
	}

}
