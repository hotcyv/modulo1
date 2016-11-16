package br.com.sematec.produtos.modelo;

import java.math.BigDecimal;

public class Produto {
	private String id;
	private String nome;
	private String imagem;
	private BigDecimal preco;

	public Produto(){
		super();
	}
	public Produto(String nome, String imagem, BigDecimal preco) {
		this();
		this.nome = nome;
		this.imagem = imagem;
		this.preco = preco;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}
}
