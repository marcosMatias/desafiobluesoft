package br.com.bluesoft.desafio.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import br.com.bluesoft.desafio.form.ProdutoForm;
import br.com.bluesoft.desafio.model.Fornecedor;
import br.com.bluesoft.desafio.model.Item;
import br.com.bluesoft.desafio.model.Pedido;
import br.com.bluesoft.desafio.model.PedidoEfetuado;
import br.com.bluesoft.desafio.model.Preco;
import br.com.bluesoft.desafio.model.Produto;

@Service
public class PedidoService {
	
	@Autowired
	private ProdutoService produtoService;
	@Autowired
	private FornecedorService fornecedorService;
	
	public static final List<Pedido> listaPedidos = new ArrayList<>();
	
	private static final Logger LOG = Logger.getLogger(PedidoService.class);
	
	
	private static int pedidoSequencia = 0;
	
	public List<Pedido> listarPedidosEfetuados() {
		
		return listaPedidos.stream().sorted((pedido01, pedido02) -> Integer.compare(pedido02.getId(), pedido01.getId()))
				.collect(Collectors.toList());
	}
	
	
	public List<Pedido> listarFornecedoresPorPedido(List<PedidoEfetuado> listaPedidosEfetuados) {
		

		List<Pedido> listaPedidos = new ArrayList<>();

		try {

			Map<Fornecedor, Pedido> fornecedorMap = new HashMap<>();

			for (PedidoEfetuado pedidoEfetuado : listaPedidosEfetuados) {

			
				List<Fornecedor> listaFornecedores = null;

				try {

					listaFornecedores = fornecedorService
							.listarFornecedoresPorProdutoId(pedidoEfetuado.getProduto().getGtin());

				} catch (Exception ex) {
					LOG.error("ERRO: " + ex.getMessage(), ex);
					continue; 
				}

				int quantidadeSolicitada = pedidoEfetuado.getQuantidade();

				Fornecedor fornecedorIdeal = null;

				double precoIdeal = 0.0;

				for (Fornecedor fornecedor : listaFornecedores) {

					for (Preco preco : fornecedor.getPrecos()) {

						if (quantidadeSolicitada >= preco.getQuantidadeMinima()) {

							if (precoIdeal == 0.0 || precoIdeal > preco.getPreco()) {
								
								precoIdeal = preco.getPreco();

								
								fornecedorIdeal = fornecedor;
							}

						}

					} 

				} 

				
				if (precoIdeal > 0.0) {

					Pedido pedidoFuturo = fornecedorMap.get(fornecedorIdeal);

					if (ObjectUtils.isEmpty(pedidoFuturo)) {
						fornecedorMap.put(fornecedorIdeal, new Pedido(fornecedorIdeal));
						pedidoFuturo = fornecedorMap.get(fornecedorIdeal);
						pedidoFuturo.setId(++PedidoService.pedidoSequencia);
					}

					
					pedidoFuturo.getItens()
							.add(new Item(pedidoEfetuado.getProduto(), quantidadeSolicitada, precoIdeal));

				}

			} 
			listaPedidos = fornecedorMap.values().stream().collect(Collectors.toList());

		
			PedidoService.listaPedidos.addAll(listaPedidos);

		} catch (Exception ex) {
			LOG.error("ERRO ao realizar agrupamento de fornecedores elegíveis: " + ex.getMessage(), ex);
		}

		return listaPedidos;
	}

	public List<PedidoEfetuado> definirPedidoEfetuado(List<ProdutoForm> listaProdutoForm) {
		

		List<PedidoEfetuado> pedidoEfetuado = new ArrayList<>();

		try {

			for (ProdutoForm pedidoForm : listaProdutoForm) {

				Produto produto = null;

				try {

					produto = produtoService.retornarProdutoPorId(pedidoForm.getProduto());
					

				} catch (Exception ex) {
					LOG.error(
							"ERRO - Produto [ " + pedidoForm.getProduto() + " ] nao encontrado: " + ex.getMessage(),
							ex);
					continue; 
				}

				pedidoEfetuado.add(new PedidoEfetuado(produto, pedidoForm.getQuantidade()));

			} 

		} catch (Exception ex) {
			LOG.error("ERRO ao definir pedido realizado a partir do formulario: " + ex.getMessage(), ex);
		}

		

		return pedidoEfetuado;
	}
	
}
