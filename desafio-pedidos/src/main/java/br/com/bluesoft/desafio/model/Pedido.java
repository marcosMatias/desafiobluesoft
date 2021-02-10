package br.com.bluesoft.desafio.model;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
	private int id;

	private Fornecedor fornecedor;

	private List<Item> listaItens = new ArrayList<>();

	public Pedido() {
		
	}

	public Pedido(int id, Fornecedor fornecedor, List<Item> listaItens) {
	
		this.id = id;
		this.fornecedor = fornecedor;
		this.listaItens = listaItens;
	}
	
	public Pedido(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public List<Item> getItens() {
		return listaItens;
	}

	public void setItens(List<Item> itens) {
		this.listaItens = itens;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fornecedor == null) ? 0 : fornecedor.hashCode());
		result = prime * result + id;
		result = prime * result + ((listaItens == null) ? 0 : listaItens.hashCode());
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
		Pedido other = (Pedido) obj;
		if (fornecedor == null) {
			if (other.fornecedor != null)
				return false;
		} else if (!fornecedor.equals(other.fornecedor))
			return false;
		if (id != other.id)
			return false;
		if (listaItens == null) {
			if (other.listaItens != null)
				return false;
		} else if (!listaItens.equals(other.listaItens))
			return false;
		return true;
	}
	
}
