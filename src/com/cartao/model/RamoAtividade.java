package com.cartao.model;

public class RamoAtividade {
	
	private int id;
	private String nome;
	private double taxaMinimaCredito;
	private double taxaMinimaDebito;
	
	public RamoAtividade() {
	}
	
	public RamoAtividade(String nome, double taxaMinimaCredito, double taxaMinimaDebito) {
		this.nome = nome;
		this.taxaMinimaCredito = taxaMinimaCredito;
		this.taxaMinimaDebito = taxaMinimaDebito;
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
	public double getTaxaMinimaCredito() {
		return taxaMinimaCredito;
	}
	public void setTaxaMinimaCredito(Double taxaMinimaCredito) {
		this.taxaMinimaCredito = taxaMinimaCredito;
	}
	public double getTaxaMinimaDebito() {
		return taxaMinimaDebito;
	}
	public void setTaxaMinimaDebito(Double taxaMinimaDebito) {
		this.taxaMinimaDebito = taxaMinimaDebito;
	}

	@Override
	public String toString() {
		return nome + " - taxa min. Crédito: " + taxaMinimaCredito + " % - taxa min débito " + taxaMinimaDebito + " %";
	}
	
	
	
	

}
