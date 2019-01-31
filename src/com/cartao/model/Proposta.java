package com.cartao.model;

import java.time.LocalDate;
import java.util.Date;

public class Proposta {
	
	private int id;
	private LocalDate dataSimulacao;
	private Double descontoCredito;
	private Double descontoDebito;
	private Double taxaFinalCredito;
	private Double taxaFinalDebito;
	private boolean situacao;
	private Cliente cliente;
	private Concorrente concorrente;
	
	public Proposta() {
	}	
	public Proposta(LocalDate dataSimulacao, Double descontoCredito, Double descontoDebito, Double taxaFinalCredito,
			Double taxaFinalDebito, boolean situacao, Cliente cliente, Concorrente concorrente) {
		this.dataSimulacao = dataSimulacao;
		this.descontoCredito = descontoCredito;
		this.descontoDebito = descontoDebito;
		this.taxaFinalCredito = taxaFinalCredito;
		this.taxaFinalDebito = taxaFinalDebito;
		this.situacao = situacao;
		this.cliente = cliente;
		this.concorrente = concorrente;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public LocalDate getDataSimulacao() {
		return dataSimulacao;
	}
	public void setDataSimulacao(LocalDate dataSimulacao) {
		this.dataSimulacao = dataSimulacao;
	}
	public Double getDescontoCredito() {
		return descontoCredito;
	}
	public void setDescontoCredito(Double descontoCredito) {
		this.descontoCredito = descontoCredito;
	}
	public Double getDescontoDebito() {
		return descontoDebito;
	}
	public void setDescontoDebito(Double descontoDebito) {
		this.descontoDebito = descontoDebito;
	}
	public boolean isSituacao() {
		return situacao;
	}
	public void setSituacao(boolean situacao) {
		this.situacao = situacao;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Concorrente getConcorrente() {
		return concorrente;
	}
	public void setConcorrente(Concorrente concorrente) {
		this.concorrente = concorrente;
	}
	public Double getTaxaFinalCredito() {
		return taxaFinalCredito;
	}
	public void setTaxaFinalCredito(Double taxaFinalCredito) {
		this.taxaFinalCredito = taxaFinalCredito;
	}
	public Double getTaxaFinalDebito() {
		return taxaFinalDebito;
	}
	public void setTaxaFinalDebito(Double taxaFinalDebito) {
		this.taxaFinalDebito = taxaFinalDebito;
	}
	
	

}
