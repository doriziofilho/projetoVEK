package com.cartao.model;

public class Cliente {

	private int id;
	private String nome;
	private String cpf;
	private String telefone;
	private String email;
	private RamoAtividade ramoAtividade;
	private Concorrente concorrente;
	
	public Cliente() {
	}
	public Cliente(String nome, String cpf, String telefone, String email, RamoAtividade ramoAtividade,
			Concorrente concorrente) {
		this.nome = nome;
		this.cpf = cpf;
		this.telefone = telefone;
		this.email = email;
		this.ramoAtividade = ramoAtividade;
		this.concorrente = concorrente;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public RamoAtividade getRamoAtividade() {
		return ramoAtividade;
	}
	public void setRamoAtividade(RamoAtividade ramoAtividade) {
		this.ramoAtividade = ramoAtividade;
	}
	public Concorrente getConcorrente() {
		return concorrente;
	}
	public void setConcorrente(Concorrente concorrente) {
		this.concorrente = concorrente;
	}
	@Override
	public String toString() {
		return  nome ;
	}

}
