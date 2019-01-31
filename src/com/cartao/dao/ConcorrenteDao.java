package com.cartao.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.cartao.model.Concorrente;
import com.cartao.util.ConnectionUtil;	

public class ConcorrenteDao {

	private static ConcorrenteDao instancia;
	public ArrayList<Concorrente> listaConcorrentes;
	private Connection con = ConnectionUtil.getConnection();
	
	//Singleton
	public static ConcorrenteDao obterInstancia() {
		if(instancia == null) {
			instancia = new ConcorrenteDao();
		}
		return instancia;
	}
	
	public void salvar(Concorrente concorrente) {
		try {
			String sql = "INSERT INTO concorrente (nomeConcorrente, taxaCredito, taxaDebito) VALUES (?, ?, ?)";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, concorrente.getNome());
			pstmt.setDouble(2, concorrente.getTaxaCredito());
			pstmt.setDouble(3, concorrente.getTaxaDebito());
			
			pstmt.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<Concorrente> listarTodos(){
		listaConcorrentes= new ArrayList<>();
		try {
			Statement stmt = con.createStatement();
			String sql = "SELECT * FROM concorrente";
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				Concorrente concorrente = new Concorrente();
				concorrente.setId(rs.getInt("idConcorrente"));
				concorrente.setNome(rs.getString("nomeConcorrente"));
				concorrente.setTaxaCredito(rs.getDouble("taxaCredito"));
				concorrente.setTaxaDebito(rs.getDouble("taxaDebito"));
				
				listaConcorrentes.add(concorrente);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaConcorrentes;
	}
	
	public void editar(Concorrente concorrente) {
		try {
			String sql = "UPDATE concorrente set nomeConcorrente = ?, taxaCredito = ?, taxaDebito = ? WHERE idconcorrente = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1,  concorrente.getNome());
			pstmt.setDouble(2,  concorrente.getTaxaCredito());
			pstmt.setDouble(3,  concorrente.getTaxaDebito());
			pstmt.setInt(4, concorrente.getId());
			
			pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void excluir(int id) {
		try {
			String sql = "DELETE FROM concorrente WHERE idconcorrente = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			
			pstmt.execute();
		} catch (SQLException e) {
		e.printStackTrace();
		}
	}
	public List<Concorrente> pesquisarConcorrentePorNome(String nome){
		try {
			String sql  = "SELECT * FROM concorrente WHERE nomeConcorrente LIKE ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + nome + "%");
			ResultSet rs = pstmt.executeQuery();
			listaConcorrentes = new ArrayList<Concorrente>();
			while(rs.next()) {
				Concorrente concorrente = new Concorrente();
				concorrente.setId(rs.getInt("idconcorrente"));
				concorrente.setNome(rs.getString("nomeConcorrente"));
				concorrente.setTaxaCredito(rs.getDouble("TaxaCredito"));
				concorrente.setTaxaDebito(rs.getDouble("taxaDebito"));
				
				listaConcorrentes.add(concorrente);
			}
		} catch (SQLException e) {
		e.printStackTrace();
		}
		return listaConcorrentes;
	}
}
