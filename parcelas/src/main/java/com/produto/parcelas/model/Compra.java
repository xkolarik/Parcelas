package com.produto.parcelas.model;

import lombok.Data;

@Data
public class Compra {
	
	private Produto produto;
	private CondicaoPagamento condicaoPagamento;
	
}
