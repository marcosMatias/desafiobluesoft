package br.com.bluesoft.desafio.model;

public class PedidoEfetuado {

	private Produto produto;

	private int quantidade;

	public PedidoEfetuado() {

	}

	public PedidoEfetuado(Produto produto, int quantidade) {
		this.produto = produto;
		this.quantidade = quantidade;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
}
