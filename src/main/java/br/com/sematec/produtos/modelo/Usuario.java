package br.com.sematec.produtos.modelo;

public class Usuario {
	private String nome;
	private String login;
	private String senha;

	public Usuario() {
		super();
	}

	public Usuario(String nome, String login, String senha) {
		this();
		this.nome = nome;
		this.login = login;
		this.senha = senha;
	}

	public String getLogin() {
		return login;
	}

	public String getNome() {
		return nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}
