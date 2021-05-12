package com.produto.parcelas.controller.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.produto.parcelas.model.Compra;
import com.produto.parcelas.model.Parcela;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "Simulação Selic", produces = "application/json")
@RequestMapping("simular")
public interface ParcelasController {
	
	@ApiOperation(value = "Listar parcelas da simulação")
	@GetMapping(consumes = "application/json", produces = "application/json")
	
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Retorna a lista de parcelas",response = Parcela.class),
		    @ApiResponse(code = 401, message = "Você não possui credenciais de autenticação válidas"),
		    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
		    @ApiResponse(code = 404, message = "O servidor não pôde encontrar a sua solicitação"),
		    @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
		})
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> listaParcelas(@RequestBody Compra compra) throws Exception;

}
