package com.cartao.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.cartao.model.RamoAtividade;
import com.cartao.util.ConnectionUtil;


public class RamoAtividadeDao {

	private static RamoAtividadeDao instancia;
	public ArrayList<RamoAtividade> listaRamoAtividades;
	private Connection con = ConnectionUtil.getConnection();
	
	//Singleton
	public static RamoAtividadeDao obterInstancia() {
		if(instancia == null) {
			instancia = new RamoAtividadeDao();
		}
		return instancia;
	}
	
	public void salvar(RamoAtividade ramoAtividade) {
		try {
			String sql = "INSERT INTO ramoatividade (nomeAtividade, taxaMinimaCredito, taxaMinimaDebito) VALUES (?, ?, ?)";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, ramoAtividade.getNome());
			pstmt.setDouble(2, ramoAtividade.getTaxaMinimaCredito());
			pstmt.setDouble(3, ramoAtividade.getTaxaMinimaDebito());
			
			pstmt.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<RamoAtividade> listarRamoAtividade(){
		listaRamoAtividades = new ArrayList<>();
		try {
			Statement stmt = con.createStatement();
			String sql = "SELECT * FROM ramoatividade";
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				RamoAtividade ramoAtividade = new RamoAtividade();
				ramoAtividade.setId(rs.getInt("idRamoAtividade"));
				ramoAtividade.setNome(rs.getString("nomeAtividade"));
				ramoAtividade.setTaxaMinimaCredito(rs.getDouble("taxaMinimaCredito"));
				ramoAtividade.setTaxaMinimaDebito(rs.getDouble("taxaMinimaDebito"));
				
				listaRamoAtividades.add(ramoAtividade);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaRamoAtividades;
	}
	
	public void editar(RamoAtividade ramoAtividade) {
		try {
			String sql = "UPDATE ramoatividade set nomeAtividade = ?, taxaMinimaCredito = ?, taxaMinimaDebito = ?"
					+ " WHERE idRamoAtividade = ? ";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1,  ramoAtividade.getNome());
			pstmt.setDouble(2,  ramoAtividade.getTaxaMinimaCredito());
			pstmt.setDouble(3,  ramoAtividade.getTaxaMinimaDebito());
			pstmt.setInt(4, ramoAtividade.getId());
			
			pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void excluir(int id) {
		try {
			String sql = "DELETE FROM ramoatividade WHERE idRamoAtividade = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			
			pstmt.execute();
		} catch (SQLException e) {
		e.printStackTrace();
		}
	}
	
	public List<RamoAtividade> pesquisarRAporNome(String nome){
		try {
			String sql  = "SELECT * FROM ramoatividade WHERE nomeAtividade LIKE ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + nome + "%");
			ResultSet rs = pstmt.executeQuery();
			listaRamoAtividades = new ArrayList<RamoAtividade>();
			while(rs.next()) {
				RamoAtividade ramoAtividade = new RamoAtividade();
				ramoAtividade.setId(rs.getInt("idRamoAtividade"));
				ramoAtividade.setNome(rs.getString("nomeAtividade"));
				ramoAtividade.setTaxaMinimaCredito(rs.getDouble("taxaMinimaCredito"));
				ramoAtividade.setTaxaMinimaDebito(rs.getDouble("taxaMinimaDebito"));
				
				listaRamoAtividades.add(ramoAtividade);
			}
		} catch (SQLException e) {
		e.printStackTrace();
		}
		return listaRamoAtividades;
	}
}
