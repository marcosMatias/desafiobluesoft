package br.com.bluesoft.desafio.model.json;

public class PedidoModelRequest {
	
	private String gtin;

	private int quantidade;

	public String getGtin() {
		return gtin;
	}

	public void setGtin(String gtin) {
		this.gtin = gtin;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
}
