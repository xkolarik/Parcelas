package com.produto.parcelas.model;

import lombok.Data;

@Data
public class Item {
	
	int codigo;
	String nome;
	double valor;
	double valorEntrada;
	int qtdeParcelas;
	
}
