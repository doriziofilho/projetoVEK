package com.cartao.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class RamoAtividadeTableModel extends AbstractTableModel {
	
	private static final long serialVersionUID = 1L;
	private static final int COL_NOMEATIVIDADE = 0;
	private static final int COL_TAXAMINIMACREDITO = 1;
	private static final int COL_TAXAMINIMADEBITO = 2;
	
	private List<RamoAtividade> valores;       

	public RamoAtividadeTableModel(List<RamoAtividade> valores) {
		this.valores = new ArrayList<RamoAtividade>(valores);
	}

	public int getRowCount() {
		return valores.size();
	}

	public int getColumnCount() {
		return 3;
	}

	public String getColumnName(int column) {
		if (column == COL_NOMEATIVIDADE) return "Ramo de Atividade";
		if (column == COL_TAXAMINIMACREDITO) return "Taxa Mínima Crédito";
		if (column == COL_TAXAMINIMADEBITO) return "Taxa Mínima Débito";
		
		return "";
	}

	public Object getValueAt(int row, int column) {
		RamoAtividade ramoAtividade = valores.get(row);
		if (column == COL_NOMEATIVIDADE)
			return ramoAtividade.getNome();
		else
			if (column == COL_TAXAMINIMACREDITO)
				return ramoAtividade.getTaxaMinimaCredito();
			else
				if (column == COL_TAXAMINIMADEBITO)
					return ramoAtividade.getTaxaMinimaDebito();
		return ""; 
	}

	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		RamoAtividade ramoAtividade =  valores.get(rowIndex);
		if (columnIndex == COL_NOMEATIVIDADE)
			ramoAtividade.setNome(aValue.toString());
		else
			if (columnIndex == COL_TAXAMINIMACREDITO)
				ramoAtividade.setTaxaMinimaCredito(Double.parseDouble(aValue.toString()));
			else
				if (columnIndex == COL_TAXAMINIMADEBITO)
					ramoAtividade.setTaxaMinimaDebito(Double.parseDouble(aValue.toString()));
	}

	public Class<?> getColumnClass(int columnIndex) {
		return String.class;
	}

	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return true;
	}
	public RamoAtividade get(int row) {
		return valores.get(row);
	}
}

