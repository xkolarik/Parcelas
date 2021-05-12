package com.produto.parcelas;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.produto.parcelas.model.Compra;
import com.produto.parcelas.model.CondicaoPagamento;
import com.produto.parcelas.model.Parcela;
import com.produto.parcelas.model.Produto;
import com.produto.parcelas.service.CalculaParcelasImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
class ParcelasApplicationTests {

	@Autowired
	private CalculaParcelasImpl calculaParcelas;
	
	@Test
	public void testaCalculoParcela() throws Exception // Testa se o calculo da parcela esta sendo efetuado.
	{

		Produto produto = new Produto();
		
		produto.setCodigo(123);
		produto.setNome("Nome do Produto");
		produto.setValor(9999.99);
		
		CondicaoPagamento condicaoPagamento = new CondicaoPagamento();
		condicaoPagamento.setValorEntrada(9999.99);
		condicaoPagamento.setQtdeParcelas(999);
		
		
		Compra compra = new Compra();
		
		compra.setProduto(produto);
		compra.setCondicaoPagamento(condicaoPagamento);

		List<Parcela> listaParcelas = calculaParcelas.listParcelas(compra);
		assertThat(listaParcelas.size()).isNotEqualTo(0);
		assertThat(listaParcelas.size()).isEqualTo(999);
		
	}
}
