package com.produto.parcelas.service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.produto.parcelas.model.Compra;
import com.produto.parcelas.model.Item;
import com.produto.parcelas.model.Parcela;
import com.produto.parcelas.model.Selic;
import com.produto.parcelas.service.impl.CalculaParcelas;
import com.produto.parcelas.utils.VerificaCompra;

@Service
public class CalculaParcelasImpl implements CalculaParcelas {
	
	public List<Parcela> listParcelas(Compra compra) {
		Item item = new Item();
		
		if (VerificaCompra.dadosEntrada(compra)) {
			
			item.setCodigo(compra.getProduto().getCodigo());
			item.setNome(compra.getProduto().getNome());
			item.setQtdeParcelas(compra.getCondicaoPagamento().getQtdeParcelas());
			item.setValor(compra.getProduto().getValor());
			item.setValorEntrada(compra.getCondicaoPagamento().getValorEntrada());
		}
		
		ArrayList<Parcela> parcelas = new ArrayList<Parcela>();
		
		double valorParcela;
		float juros;
		String taxaJuros;
		float valorJuros;

		if (item.getQtdeParcelas() <= 6) {
			juros = (float) 1.1500; // juros fixo
			taxaJuros = "1150";
		} else {
			juros = consultaSelic(); // consulta o web service do governo
			taxaJuros = String.valueOf(juros).toString().replaceAll("\\.","").substring(0,4);
		}

		valorParcela = (item.getValor() - item.getValorEntrada()); 
		valorJuros = (float) ((valorParcela * juros) / 100);
		valorParcela = (valorParcela + valorJuros) / item.getQtdeParcelas(); 
		
		DecimalFormat dfVp = new DecimalFormat("########.00");
		
		String valorParcelaFormatada = dfVp.format( valorParcela).replaceAll("\\,",".");
		
		for (int i = 0; i < item.getQtdeParcelas(); i++) {
			Parcela parcela = new Parcela();
			parcelas.add(parcela);
			parcelas.get(i).setNumeroParcela(i + 1);
			parcelas.get(i).setValor(valorParcelaFormatada);			
			parcelas.get(i).setTaxaJurosAoMes(taxaJuros);
		}

		return parcelas;
	}

	public float consultaSelic() {
		RestTemplate restTemplate = new RestTemplate();

		float juroSelic = 0; 

		UriComponents uri = UriComponentsBuilder.newInstance()
				.scheme("https")
				.host("api.bcb.gov.br")
				.path("dados/serie/bcdata.sgs.11/dados/ultimos/30")
				.queryParam("formato", "json")
				.build();

		try {
			ResponseEntity<Selic[]> entity = restTemplate.getForEntity(uri.toUriString(), Selic[].class);
			
			for (Selic selic : entity.getBody()) {
				juroSelic += Float.valueOf(selic.getValor()); // soma o juro nos 30 ultimos dias.
			}

		} catch (Exception e) {
			System.out.print(e);
		}

		return juroSelic;

	}

}
