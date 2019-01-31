package com.cartao.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class PropostaTableModel extends AbstractTableModel {
	
	private static final long serialVersionUID = 1L;
	private static final int COL_PROPOSTA = 0;
	private static final int COL_CLIENTE = 1;
	private static final int COL_DATAPROPOSTA = 2;
	private static final int COL_DESCDEBITO = 3;
	private static final int COL_DESCCREDITO = 4;
	private static final int COL_TAXADEBITO = 5;
	private static final int COL_TAXACREDITO = 6;
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	private List<Proposta> valores;       

	public PropostaTableModel(List<Proposta> valores) {
		this.valores = new ArrayList<Proposta>(valores);
	}

	public int getRowCount() {
		return valores.size();
	}

	public int getColumnCount() {
		return 7;
	}

	public String getColumnName(int column) {
		if (column == COL_PROPOSTA) return "PROPOSTA";
		if (column == COL_CLIENTE) return "CLIENTE";
		if (column == COL_DATAPROPOSTA) return "DATA ACEITE";
		if (column == COL_DESCDEBITO) return "DESC. DÉBITO";
		if (column == COL_DESCCREDITO) return "DESC. CRÉDITO";
		if (column == COL_TAXADEBITO) return "TAXA DÉBITO";
		if (column == COL_TAXACREDITO) return "TAXA CRÉDITO";
		
		return "";
	}

	public Object getValueAt(int row, int column) {
		Proposta proposta = valores.get(row);
		if (column == COL_PROPOSTA)
			return proposta.getId();
		else
			if (column == COL_CLIENTE)
				return proposta.getCliente().getNome();
			else
				if (column == COL_DATAPROPOSTA)
					return proposta.getDataSimulacao();
				else
					if (column == COL_DESCDEBITO)
						return proposta.getDescontoDebito();
					else
						if (column == COL_DESCCREDITO)
							return proposta.getDescontoCredito();
						else
							if (column == COL_TAXADEBITO)
								return proposta.getTaxaFinalDebito();
							else
								if (column == COL_TAXACREDITO)
									return proposta.getTaxaFinalCredito();
		return ""; 
	}

	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		Proposta proposta =  valores.get(rowIndex);
		if (columnIndex == COL_PROPOSTA)
			proposta.setId(Integer.parseInt(aValue.toString()));
		else
			if (columnIndex == COL_CLIENTE)
				proposta.getCliente();
			else
				if (columnIndex == COL_DATAPROPOSTA)
					proposta.setDataSimulacao(LocalDate.parse(aValue.toString(), dtf));
				else
					if (columnIndex == COL_DESCDEBITO)
						proposta.getDescontoDebito();
					else
						if (columnIndex == COL_DESCCREDITO)
							proposta.getDescontoCredito();
						else
							if (columnIndex == COL_TAXADEBITO)
								proposta.getTaxaFinalDebito();
							else
								if (columnIndex == COL_TAXACREDITO)
									proposta.getTaxaFinalCredito();
	}
	
	public Class<?> getColumnClass(int columnIndex) {
		return String.class;
	}

	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return true;
	}
	public Proposta get(int row) {
		return valores.get(row);
	}
}

