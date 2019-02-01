package com.cartao.io;

import java.io.FileWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import com.cartao.controller.propostaControl;
import com.cartao.model.Proposta;

public class PropostaWriter {
	private static final String SEPARADORCOLUNAS = "\n";
	private static final Object[] colunasArquivo = {"PROPOSTA Nº", "CLIENTE", "CPF", "TELEFONE", "EMAIL", "RAMO ATIVIDADE", "TAXA MIN CRÉDITO",
			"TAXA MIN DÉBITO", "CONCORRENTE", "TAXA CRÉDITO", "TAX DÉBITO", "DESC. CRÉDITO", "DESC. DÉBITO",
			"TAXA CRÉDITO ACEITA", "TAXA DÉBITO ACEITA", "DATA ACEITE" };
	
	public void escrever(String nomeArquivo,  LocalDate dataInicial, LocalDate dataFinal ) {
		CSVPrinter csvCompiladorDeArquivos = null;
		
		CSVFormat formatacaoCsv = CSVFormat.DEFAULT.withRecordSeparator(SEPARADORCOLUNAS).withDelimiter(';');
		try 
			(FileWriter escritorDeArquivos = new FileWriter(nomeArquivo)){
				
			propostaControl propostaControl = new propostaControl();
			
			csvCompiladorDeArquivos = new CSVPrinter(escritorDeArquivos, formatacaoCsv);
			List<Proposta> listaProposta =  new LinkedList<>();
			
			listaProposta = propostaControl.pesquisarPropostaPorData(dataInicial, dataFinal);
			
			
			Iterator<Proposta> it = listaProposta.iterator();
			
			csvCompiladorDeArquivos.printRecord(colunasArquivo);			
			while(it.hasNext()) {

				Proposta proposta = it.next();
					csvCompiladorDeArquivos.printRecord(
							proposta.getId(),
							proposta.getCliente().getNome(),
							proposta.getCliente().getCpf(),
							proposta.getCliente().getTelefone(),
							proposta.getCliente().getEmail(),
							proposta.getCliente().getRamoAtividade().getNome(),
							proposta.getCliente().getRamoAtividade().getTaxaMinimaCredito() +  " %",
							proposta.getCliente().getRamoAtividade().getTaxaMinimaDebito() +  " %",
							proposta.getCliente().getConcorrente().getNome(),
							proposta.getCliente().getConcorrente().getTaxaCredito() + " %",
							proposta.getCliente().getConcorrente().getTaxaDebito() + " %",
							proposta.getDescontoCredito() + " %",
							proposta.getDescontoDebito() + " %",
							proposta.getTaxaFinalCredito() + " %",
							proposta.getTaxaFinalDebito() + " %",
							proposta.getDataSimulacao()
					);
					
			}
				csvCompiladorDeArquivos.printRecord(listaProposta.size() + " <--------- Total de propostas do perido selecionado.");
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
}
