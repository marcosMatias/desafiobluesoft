package br.com.bluesoft.desafio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.bluesoft.desafio.integrate.FornecedorIntegracao;
import br.com.bluesoft.desafio.model.Fornecedor;

@Service
public class FornecedorService {

	@Autowired
	private FornecedorIntegracao fornecedorIntegracao;

	public List<Fornecedor> listarFornecedoresPorProdutoId(String gtin) {

		return fornecedorIntegracao.listarFornecedorPorIdProduto(gtin);
	}
}
