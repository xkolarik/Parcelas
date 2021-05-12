package com.produto.parcelas.model;

import lombok.Data;

@Data
public class Parcela {

	private int numeroParcela; // Número da parcela.
	private String valor; // Valor da parcela com juros calculado.
	private String taxaJurosAoMes; // Taxa de juros ao mês com retorno sem ponto decimal.

}
