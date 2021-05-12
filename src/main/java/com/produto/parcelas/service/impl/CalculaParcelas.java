package com.produto.parcelas.service.impl;

import java.util.List;

import com.produto.parcelas.model.Compra;
import com.produto.parcelas.model.Parcela;

public interface CalculaParcelas {
	
	List<Parcela> listParcelas(Compra compra);

}
