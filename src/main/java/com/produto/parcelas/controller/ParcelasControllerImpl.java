package com.produto.parcelas.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.produto.parcelas.controller.impl.ParcelasController;
import com.produto.parcelas.model.Compra;
import com.produto.parcelas.model.Parcela;
import com.produto.parcelas.service.impl.CalculaParcelas;

@RestController
public class ParcelasControllerImpl implements ParcelasController {

	@Autowired
	private CalculaParcelas calcula;

	@Override
	public ResponseEntity<?> listaParcelas(Compra compra) throws Exception {
		
		List<Parcela> listaParcelas = new ArrayList<Parcela>();
			listaParcelas = calcula.listParcelas(compra);
		
		if (listaParcelas != null) {
			return new ResponseEntity<>(listaParcelas, HttpStatus.OK);	
		}
		return new ResponseEntity<>("Houve um erro em sua requisição, verifique!", HttpStatus.EXPECTATION_FAILED);
	}
}