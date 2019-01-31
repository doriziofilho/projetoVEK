package com.cartao.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class ConcorrenteTableModel extends AbstractTableModel {
	
	private static final long serialVersionUID = 1L;
	private static final int COL_NOME = 0;
	private static final int COL_TAXACREDITO = 1;
	private static final int COL_TAXADEBITO = 2;
	
	private List<Concorrente> valores;       

	public ConcorrenteTableModel(List<Concorrente> valores) {
		this.valores = new ArrayList<Concorrente>(valores);
	}

	public int getRowCount() {
		return valores.size();
	}

	public int getColumnCount() {
		return 3;
	}

	public String getColumnName(int column) {
		if (column == COL_NOME) return "Concorrente";
		if (column == COL_TAXACREDITO) return "Taxa Crédito";
		if (column == COL_TAXADEBITO) return "Taxa Débito";
		
		return "";
	}

	public Object getValueAt(int row, int column) {
		Concorrente concorrente = valores.get(row);
		if (column == COL_NOME)
			return concorrente.getNome();
		else
			if (column == COL_TAXACREDITO)
				return concorrente.getTaxaCredito();
			else
				if (column == COL_TAXADEBITO)
					return concorrente.getTaxaDebito();
		return ""; 
	}

	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		Concorrente concorrente =  valores.get(rowIndex);
		if (columnIndex == COL_NOME)
			concorrente.setNome(aValue.toString());
		else
			if (columnIndex == COL_TAXACREDITO)
				concorrente.setTaxaCredito(Double.parseDouble(aValue.toString()));
			else
				if (columnIndex == COL_TAXADEBITO)
					concorrente.setTaxaDebito(Double.parseDouble(aValue.toString()));
	}

	public Class<?> getColumnClass(int columnIndex) {
		return String.class;
	}

	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return true;
	}
	public Concorrente get(int row) {
		return valores.get(row);
	}
}

