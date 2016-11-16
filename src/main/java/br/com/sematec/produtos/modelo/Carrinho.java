package br.com.sematec.produtos.modelo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;


public class Carrinho {

	private ArrayList<Item> itens;
	private BigDecimal total;

	public Carrinho() {
		this(new ArrayList<Item>(), new BigDecimal(0.0));
	}

	public Carrinho(ArrayList<Item> itens, BigDecimal total) {
		this.itens = itens;
		this.total = total;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public ArrayList<Item> getItens() {
		return itens;
	}

	public void setItens(ArrayList<Item> itens) {
		this.itens = itens;
	}

	public void adicionaProduto(Produto produto) {
		Item item = new Item(produto, 1);
		itens.add(item);
		total = total.add(item.getTotal());
	}

	public void removeProduto(String id) {
		Item item = buscaItem(id);
		if (item != null) {
			this.itens.remove(item);
			total = total.subtract(item.getTotal());
		}
	}

	private Item buscaItem(String id) {
		for (Iterator<Item> iterator = itens.iterator(); iterator.hasNext();) {
			Item item = (Item) iterator.next();
			if (item.getId().equals(id)) {
				return item;
			}
		}
		return null;
	}

}
