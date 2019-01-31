package com.cartao.controller;

import java.util.List;

import com.cartao.dao.ConcorrenteDao;
import com.cartao.model.Concorrente;

public class concorrenteControl {

	public void salvar(Concorrente concorrente) {
		ConcorrenteDao.obterInstancia().salvar(concorrente);
	}
	
	public void editar(Concorrente concorrente) {
		ConcorrenteDao.obterInstancia().editar(concorrente);
	}
	
	public List<Concorrente> pesquisarConcorrentePorNome(String nome){
		return ConcorrenteDao.obterInstancia().pesquisarConcorrentePorNome(nome.trim());
	}

	public List<Concorrente> listarTodos(){
		return ConcorrenteDao.obterInstancia().listarTodos();
	}

	public void remover(Concorrente concorrente) {
		ConcorrenteDao.obterInstancia().excluir(concorrente.getId());
	}
}
