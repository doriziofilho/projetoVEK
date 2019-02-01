package com.cartao.controller;

import java.time.LocalDate;
import java.util.List;

import com.cartao.dao.PropostaDao;
import com.cartao.io.PropostaWriter;
import com.cartao.model.Proposta;

public class propostaControl {

	public void salvar(Proposta	proposta) {
		PropostaDao.obterInstancia().salvar(proposta);
	}
	
	public List<Proposta> pesquisarPropostaPorData(LocalDate dataInicial, LocalDate dataFinal){
		return PropostaDao.obterInstancia().pesquisarPropostaPordata(dataInicial, dataFinal);
	}
	
	public List<Proposta> listarTodos(){
		return PropostaDao.obterInstancia().listarTodos();
	}

	public void exportarCSV(String path, LocalDate dataInicio, LocalDate dataFinal) {
		PropostaWriter proposta = new PropostaWriter();
		proposta.escrever(path, dataInicio, dataFinal);	
	}
}
