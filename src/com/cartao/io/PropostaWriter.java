package com.cartao.io;

import java.io.FileWriter;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.joda.time.DateTime;

import com.cartao.model.Proposta;
import com.mysql.fabric.xmlrpc.base.Array;

public class PropostaWriter {
	private static final String SEPARADORCOLUNAS = "\n";
	private static final Object[] colunasArquivo = {"Concorrente", "Taxa Crédido", "Taxa Débito", "% de desconto Credito", 
			"% de desconto Debito", "Taxa Crédito Aceita", "Taxa Débito Aceita", "Data Aceite" };
	
	public void escrever(Path nomeArquivo,  DateTime dataInicial, DateTime dataFinal ) {
		CSVPrinter csvCompiladorDeArquivos = null;
		
		CSVFormat formatacaoCsv = CSVFormat.DEFAULT.withRecordSeparator(SEPARADORCOLUNAS).withDelimiter(',');
		try 
			(FileWriter escritorDeArquivos = new FileWriter(nomeArquivo.toFile())){
				
			csvCompiladorDeArquivos = new CSVPrinter(escritorDeArquivos, formatacaoCsv);
			Proposta proposta = new Proposta();
			List<Proposta> listaProposta = new ArrayList<>();
//			pro
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
}
