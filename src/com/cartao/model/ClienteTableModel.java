package com.cartao.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class ClienteTableModel extends AbstractTableModel {
	
	private static final long serialVersionUID = 1L;
	private static final int COL_NOME = 0;
	private static final int COL_TELEFONE = 1;
	private static final int COL_EMAIL = 2;
	private List<Cliente> valores;       

	public ClienteTableModel(List<Cliente> valores) {
		this.valores = new ArrayList<Cliente>(valores);
	}

	public int getRowCount() {
		return valores.size();
	}

	public int getColumnCount() {
		return 3;
	}

	public String getColumnName(int column) {
		if (column == COL_NOME) return "Nome";
		if (column == COL_TELEFONE) return "Telefone";
		if (column == COL_EMAIL) return "Email";
		
		return "";
	}

	public Object getValueAt(int row, int column) {
		Cliente cliente = valores.get(row);
		if (column == COL_NOME)
			return cliente.getNome();
		else
			if (column == COL_TELEFONE)
				return cliente.getTelefone();
			else
				if (column == COL_EMAIL)
					return cliente.getEmail();

		return ""; 
	}

	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		Cliente cliente =  valores.get(rowIndex);
		if (columnIndex == COL_NOME)
			cliente.setNome(aValue.toString());
		else
			if (columnIndex == COL_TELEFONE)
				cliente.setTelefone(aValue.toString());
			else
				if (columnIndex == COL_EMAIL)
					cliente.setEmail(aValue.toString());
	}
	public Class<?> getColumnClass(int columnIndex) {
		return String.class;
	}

	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return true;
	}
	public Cliente get(int row) {
		return valores.get(row);
	}
}

