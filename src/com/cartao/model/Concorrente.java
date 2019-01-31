package com.cartao.model;

public class Concorrente {
	
	private int id;
	private String nome;
	private Double taxaCredito;
	private Double taxaDebito;
	
	public Concorrente() {
	}
	public Concorrente(String nome, Double taxaCredito, Double taxaDebito) {
		this.nome = nome;
		this.taxaCredito = taxaCredito;
		this.taxaDebito = taxaDebito;
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
	public Double getTaxaCredito() {
		return taxaCredito;
	}
	public void setTaxaCredito(Double taxaCredito) {
		this.taxaCredito = taxaCredito;
	}
	public Double getTaxaDebito() {
		return taxaDebito;
	}
	public void setTaxaDebito(Double taxaDebito) {
		this.taxaDebito = taxaDebito;
	}
	@Override
	public String toString(){
		return nome + " - taxa Crédito " + taxaCredito + " % - taxa Débito " + taxaDebito + " %";
		
	}

	
}
