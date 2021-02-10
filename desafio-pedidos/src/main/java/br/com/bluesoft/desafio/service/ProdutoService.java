package br.com.bluesoft.desafio.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bluesoft.desafio.model.Produto;
import br.com.bluesoft.desafio.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	

	public List<Produto> listarProdutos() {

		return (ArrayList<Produto>) produtoRepository.findAll();
	}

	public Produto retornarProdutoPorId(String id) {

		return produtoRepository.findOne(id);
	}

}
