package br.com.sematec.produtos.modelo;

import java.math.BigDecimal;
import java.util.UUID;

import br.com.sematec.produtos.dao.ProdutoDAO;

public class Item {
	private String id;
	private String idProduto;
	private Integer quantidade;
	private BigDecimal total;
	
	public Item(Produto produto, Integer quantidade){
		this.id = UUID.randomUUID().toString();
		this.idProduto = produto.getId();
		this.quantidade = quantidade;
		this.total= produto.getPreco().multiply(BigDecimal.valueOf(quantidade));
	}
	
	public String getId() {
		return id;
	}
	public String getNomeProduto(){
		return ProdutoDAO.getInstance().find(idProduto).getNome();
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIdProduto() {
		return idProduto;
	}
	public void setIdProduto(String idProduto) {
		this.idProduto = idProduto;
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
}
