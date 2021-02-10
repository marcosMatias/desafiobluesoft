package br.com.bluesoft.desafio.api;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import br.com.bluesoft.desafio.form.ProdutoForm;
import br.com.bluesoft.desafio.model.Pedido;
import br.com.bluesoft.desafio.model.PedidoEfetuado;
import br.com.bluesoft.desafio.model.json.PedidoModelRequest;
import br.com.bluesoft.desafio.model.json.PedidoModelResponse;
import br.com.bluesoft.desafio.service.PedidoService;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

	@Autowired
	private PedidoService pedidoService;

	@Value(value = "${mensagem.erro.fornecedor.naoencontrado}")
	private String mensagem;
	
	private static final Logger LOG = Logger.getLogger(PedidoController.class);

	@GetMapping
	public PedidoModelResponse retornarPedidos() {
		PedidoModelResponse pedidoModelResponse = new PedidoModelResponse();
		pedidoModelResponse.setPedidos(pedidoService.listarPedidosEfetuados());
		return pedidoModelResponse;
	}

	private List<ProdutoForm> definirProdutoForm(List<PedidoModelRequest> listaPedidoRequisicao) {
		List<ProdutoForm> listaProdutoForm = new ArrayList<>();

		for (PedidoModelRequest pedidoModelRequest : listaPedidoRequisicao) {
			listaProdutoForm.add(new ProdutoForm(pedidoModelRequest.getGtin(), pedidoModelRequest.getQuantidade()));
		}

		return listaProdutoForm;
	}

	@PostMapping
	public ResponseEntity<PedidoModelResponse> adicionarPedido(
			@RequestBody List<PedidoModelRequest> listaPedidoRequisicao) {

		PedidoModelResponse pedidoModelResponse = new PedidoModelResponse();
	

		try {

			List<PedidoEfetuado> listaPedidoEfetuado = pedidoService
					.definirPedidoEfetuado(definirProdutoForm(listaPedidoRequisicao));

			List<Pedido> listaPedido = pedidoService.listarFornecedoresPorPedido(listaPedidoEfetuado);

			if (listaPedido.size() > 0) {

				pedidoModelResponse.setPedidos(listaPedido);

			} else { 
				throw new Exception(mensagem);
			}

		} catch (Exception ex) {
			LOG.error("ERRO ao realizar pedido: " + ex.getMessage(), ex);
			pedidoModelResponse.setMensagem(ex.getMessage());
			ResponseEntity.notFound().build();

		}

		return new ResponseEntity<PedidoModelResponse>(pedidoModelResponse, HttpStatus.OK);
		

	}
}
