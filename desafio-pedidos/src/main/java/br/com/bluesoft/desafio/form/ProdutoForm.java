package br.com.bluesoft.desafio.form;

public class ProdutoForm {
	
	private String produto;

	private int quantidade;
	
	
	public ProdutoForm(String produto, int quantidade) {
		this.produto = produto;
		this.quantidade = quantidade;
	}

	public String getProduto() {
		return produto;
	}

	public int getQuantidade() {
		return quantidade;
	}

	
	
	
	public void setProduto(String produto) {
		this.produto = produto;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PedidoForm [produto=");
		builder.append(produto);
		builder.append(", quantidade=");
		builder.append(quantidade);
		builder.append("]");
		return builder.toString();
	}

	

}
