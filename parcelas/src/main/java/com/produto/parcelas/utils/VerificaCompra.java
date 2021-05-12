package com.produto.parcelas.utils;

import com.produto.parcelas.model.Compra;

public class VerificaCompra {

	public static boolean dadosEntrada(Compra compra) {
		
		if(compra.equals(null)) {
			return false;
		}
		
		if (compra.getProduto().equals(null)) {
			return false;
		}

		if (compra.getCondicaoPagamento().equals(null)) {
			return false;
		}
		
		if (compra.getProduto().getValor() > 9999.99) {
			return false;
			
		}
		if (compra.getProduto().getValor() < 0) {
			return false;
		}
		    
		if (compra.getCondicaoPagamento().getValorEntrada() > compra.getProduto().getValor()) {
			return false;
		}

		if (compra.getCondicaoPagamento().getQtdeParcelas() > 999) {
			return false;
		}
		
		if (compra.getCondicaoPagamento().getQtdeParcelas() < 1) {
			return false;
		}
		
	return true;	
	}
}
