package com.cartao.controller;

import java.util.List;

import com.cartao.dao.RamoAtividadeDao;
import com.cartao.model.RamoAtividade;

public class ramoAtividadeControl {

	public void salvar(RamoAtividade ramoAtividade) {
		RamoAtividadeDao.obterInstancia().salvar(ramoAtividade);
	}
	
	public void editar(RamoAtividade ramoAtividade) {
		RamoAtividadeDao.obterInstancia().editar(ramoAtividade);
	}
	
	public List<RamoAtividade> pesquisarRAporNome(String nome){
		return RamoAtividadeDao.obterInstancia().pesquisarRAporNome(nome.trim());
	}

	public List<RamoAtividade> listarRamoAtividade(){
		return RamoAtividadeDao.obterInstancia().listarRamoAtividade();
	}

	public void remover(RamoAtividade ramoAtividade) {
		RamoAtividadeDao.obterInstancia().excluir(ramoAtividade.getId());
	}
}
