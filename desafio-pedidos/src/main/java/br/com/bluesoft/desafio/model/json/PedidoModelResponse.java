package br.com.bluesoft.desafio.model.json;

import java.util.List;

import br.com.bluesoft.desafio.model.Pedido;

public class PedidoModelResponse {

	private List<Pedido> listarPedidos;

	private String mensagem;

	public List<Pedido> getPedidos() {
		return listarPedidos;
	}

	public void setPedidos(List<Pedido> listarPedidos) {
		this.listarPedidos = listarPedidos;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mensagem == null) ? 0 : mensagem.hashCode());
		result = prime * result + ((listarPedidos == null) ? 0 : listarPedidos.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PedidoModelResponse other = (PedidoModelResponse) obj;
		if (mensagem == null) {
			if (other.mensagem != null)
				return false;
		} else if (!mensagem.equals(other.mensagem))
			return false;
		if (listarPedidos == null) {
			if (other.listarPedidos != null)
				return false;
		} else if (!listarPedidos.equals(other.listarPedidos))
			return false;
		return true;
	}

}
