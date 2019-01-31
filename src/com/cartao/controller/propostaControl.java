package com.cartao.controller;

import java.time.LocalDate;
import java.util.List;

import com.cartao.dao.PropostaDao;
import com.cartao.model.Proposta;

public class propostaControl {

	public void salvar(Proposta	proposta) {
		PropostaDao.obterInstancia().salvar(proposta);
	}
	
	public List<Proposta> pesquisarPropostaPorData(LocalDate dataInicial, LocalDate dataFinal){
		return PropostaDao.obterInstancia().pesquisarPropostaPordata(dataInicial, dataFinal);
	}

}
