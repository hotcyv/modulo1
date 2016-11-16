package br.com.sematec.produtos.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import br.com.sematec.produtos.modelo.Produto;

public class ProdutoDAO {
	
	private static ProdutoDAO produto;
	
	private static void geraIdEAdiciona(Produto p) {
		String id = UUID.randomUUID().toString();
		p.setId(id);
		PRODUTOS.put(id, p);
	}

	public static synchronized ProdutoDAO getInstance() {
		if (produto == null) {
			produto = new ProdutoDAO();
		}
		return produto;
	}
	
	private final static Map<String, Produto> PRODUTOS = new HashMap<>();
	static {
		geraIdEAdiciona(new Produto("Caneta", "https://goo.gl/YLkbZm", new BigDecimal(5.5)));
		geraIdEAdiciona(new Produto("Livro", "https://goo.gl/G8EDI1", new BigDecimal(15.5)));
		geraIdEAdiciona(new Produto("Geladeira", "https://goo.gl/bk5FzT", new BigDecimal(25.5)));
		geraIdEAdiciona(new Produto("XBOX", "https://goo.gl/jG4mzZ", new BigDecimal(52.5)));
	}

	public void adiciona(Produto p) {
		geraIdEAdiciona(p);
	}

	public void atualiza(Produto p) {
		PRODUTOS.put(p.getId(), p);
	}

	public Produto find(String id) {
		return PRODUTOS.get(id);
	}

	public List<Produto> lista() {
		return new ArrayList<Produto>(PRODUTOS.values());
	}

	public void remove(String id) {
		PRODUTOS.remove(id);
	}

	public void remove(Produto p) {
		remove(p.getId());
	}
}