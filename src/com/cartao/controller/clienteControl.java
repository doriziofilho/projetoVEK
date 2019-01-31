package com.cartao.controller;

import java.util.List;

import com.cartao.dao.ClienteDao;
import com.cartao.model.Cliente;

public class clienteControl {

	public void salvar(Cliente	cliente) {
		ClienteDao.obterInstancia().salvar(cliente);
	}
	
	public void editar(Cliente	cliente) {
		ClienteDao.obterInstancia().editar(cliente);
	}
	
	public List<Cliente> pesquisarClientePorNome(String nome){
		return ClienteDao.obterInstancia().pesquisarClientePorNome(nome.trim());
	}

	public List<Cliente> listarTodos(){
		return ClienteDao.obterInstancia().listarTodos();
	}

	public void remover(Cliente	cliente) {
		ClienteDao.obterInstancia().excluir(cliente.getId());
	}
}
