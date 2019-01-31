package com.cartao.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import com.cartao.model.Cliente;
import com.cartao.model.Concorrente;
import com.cartao.model.RamoAtividade;
import com.cartao.util.ConnectionUtil;



public class ClienteDao {
	
	private static ClienteDao instancia;
	public ArrayList<Cliente> listaClientes;
	private Connection con = ConnectionUtil.getConnection();
	
	//Singleton
	public static ClienteDao obterInstancia() {
		if(instancia == null) {
			instancia = new ClienteDao();
		}
		return instancia;
	}
	
	public void salvar(Cliente cliente) {
		try {
			String sql = "INSERT INTO cliente (nomeCliente, cpf, telefone, email, idRamoAtividade, idConcorrente) VALUES (?, ?, ?, ?, ?, ?)";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, cliente.getNome());
			pstmt.setString(2, cliente.getCpf());
			pstmt.setString(3, cliente.getTelefone());
			pstmt.setString(4, cliente.getEmail());
			pstmt.setInt(5, cliente.getConcorrente().getId());
			pstmt.setInt(6, cliente.getRamoAtividade().getId());
			
			pstmt.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<Cliente> listarTodos(){
		listaClientes = new ArrayList<>();
		try {
			Statement stmt = con.createStatement();
			String sql = "SELECT * FROM cliente c "
					+ "INNER JOIN concorrente cc "
					+ "ON c.idConcorrente = cc.idConcorrente "
					+ "INNER JOIN ramoatividade r "
					+ "ON r.idRamoAtividade = c.idRamoAtividade";
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				RamoAtividade r = new RamoAtividade();
					r.setId(rs.getInt("idRamoAtividade"));
					r.setNome(rs.getString("nomeAtividade"));
					r.setTaxaMinimaCredito(rs.getDouble("taxaMinimaCredito"));
					r.setTaxaMinimaDebito(rs.getDouble("taxaMinimaDebito"));
				
				Concorrente c = new Concorrente();
					c.setId(rs.getInt("idConcorrente"));
					c.setNome(rs.getString("nomeConcorrente"));
					c.setTaxaCredito(rs.getDouble("taxaCredito"));
					c.setTaxaDebito(rs.getDouble("taxaDebito"));
				
				Cliente cliente = new Cliente();
					
				cliente.setId(rs.getInt("idCliente"));
				cliente.setNome(rs.getString("nomeCliente"));
				cliente.setCpf(rs.getString("cpf"));
				cliente.setTelefone(rs.getString("telefone"));
				cliente.setEmail(rs.getString("email"));
				cliente.setRamoAtividade(r);
				cliente.setConcorrente(c);
	
				listaClientes.add(cliente);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaClientes;
	}
	
	public void editar(Cliente cliente) {
		try {
			String sql = "UPDATE cliente SET nomeCliente = ?, cpf = ?, telefone = ?, email = ?, idRamoAtividade = ?, idConcorrente = ? WHERE idCliente = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1,  cliente.getNome());
			pstmt.setString(2,  cliente.getCpf());
			pstmt.setString(3,  cliente.getTelefone());
			pstmt.setString(4,  cliente.getEmail());
			pstmt.setInt(5, cliente.getRamoAtividade().getId());
			pstmt.setInt(6, cliente.getConcorrente().getId());
			pstmt.setInt(7, cliente.getId());
			
			pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void excluir(int id) {
		try {
			String sql = "DELETE FROM cliente WHERE idCliente = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			
			pstmt.execute();
		} catch (SQLException e) {
		e.printStackTrace();
		}
	}
	public List<Cliente> pesquisarClientePorNome(String nome){
		try {
			String sql  = "SELECT * FROM cliente WHERE nomeCliente LIKE ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + nome + "%");
			ResultSet rs = pstmt.executeQuery();
			listaClientes= new ArrayList<Cliente>();
			while(rs.next()) {
				Cliente cliente= new Cliente();
				Concorrente concorrente = new Concorrente();
				RamoAtividade ramoAtividade = new RamoAtividade();
				
				cliente.setId(rs.getInt("idCliente"));
				cliente.setNome(rs.getString("nomeCliente"));
				cliente.setCpf(rs.getString("cpf"));
				cliente.setTelefone(rs.getString("telefone"));
				cliente.setEmail(rs.getString("email"));
				ramoAtividade.setId(rs.getInt("idRamoAtividade"));
				cliente.setRamoAtividade(ramoAtividade);
				concorrente.setId(rs.getInt("idConcorrente"));
				cliente.setConcorrente(concorrente);
				
				listaClientes.add(cliente);
			}
		} catch (SQLException e) {
		e.printStackTrace();
		}
		return listaClientes;
	}

}
