package br.com.bluesoft.desafio.integrate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import br.com.bluesoft.desafio.model.Fornecedor;

@Service
public class FornecedorIntegracao {
	
	@Value(value = "${endpoint.externo}")
	private String endpoint;

	private static final Logger LOG = Logger.getLogger(FornecedorIntegracao.class);

	public List<Fornecedor> listarFornecedorPorIdProduto(String gtin) {

		List<Fornecedor> listaFornecedores = new ArrayList<>();

		try {

			final RestTemplate restTemplate = new RestTemplate();

			final String uri = this.endpoint.concat(gtin);

			final Fornecedor[] fornecedoresArray = restTemplate.getForObject(uri, Fornecedor[].class);

			listaFornecedores = Arrays.asList(fornecedoresArray);

		} catch (Exception ex) {
			LOG.error("ERRO ao chamar API  para recuperar dados dos fornecedores: " + ex.getMessage(), ex);
		}

		return listaFornecedores;

	}

}
