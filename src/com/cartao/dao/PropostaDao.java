package com.cartao.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.cartao.model.Cliente;
import com.cartao.model.Concorrente;
import com.cartao.model.RamoAtividade;
import com.cartao.model.Proposta;

public class PropostaDao {

	private static PropostaDao instancia;
	private ArrayList<Proposta> listaProposta;
	private Connection con = com.cartao.util.ConnectionUtil.getConnection();
	
	//Singleton
	public static PropostaDao obterInstancia() {
		if(instancia == null) {
			instancia = new PropostaDao();
		}
		return instancia;
	}
	
	public void salvar(Proposta simulacao) {
		try {
			String sql = "INSERT INTO proposta (dataSimulacao, descontoCredito,"
					+ " descontoDebito, situacao, taxaFinalCredito, taxaFinalDebito, idCliente)  VALUES (?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setDate(1, Date.valueOf(simulacao.getDataSimulacao()));
			pstmt.setDouble(2, simulacao.getDescontoCredito());
			pstmt.setDouble(3, simulacao.getDescontoDebito());
			pstmt.setBoolean(4, simulacao.isSituacao());
			pstmt.setDouble(5, simulacao.getTaxaFinalCredito());
			pstmt.setDouble(6, simulacao.getTaxaFinalDebito());
			pstmt.setInt(7, simulacao.getCliente().getId());
			
			pstmt.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<Proposta> pesquisarPropostaPordata(LocalDate dataInicial, LocalDate dataFinal){
		try {
			String sql  = "SELECT  * FROM proposta p INNER JOIN cliente c ON c.idCliente = p.idCliente INNER JOIN concorrente cc"
					+ " ON cc.idConcorrente = c.idConcorrente WHERE dataSimulacao "
					+ "BETWEEN  ?  AND  ?  AND situacao = '1' ORDER BY p.idSimulacao";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setDate(1, Date.valueOf(dataInicial));
			pstmt.setDate(2, Date.valueOf(dataFinal));
			ResultSet rs = pstmt.executeQuery();
			listaProposta= new ArrayList<Proposta>();
			while(rs.next()) {
				
				Concorrente cc = new Concorrente();
					cc.setId(rs.getInt("idConcorrente"));
					cc.setNome(rs.getString("nomeConcorrente"));
					cc.setTaxaCredito(rs.getDouble("taxaCredito"));
					cc.setTaxaDebito(rs.getDouble("taxaDebito"));
				
				Cliente c= new Cliente();
					c.setId(rs.getInt("idCliente"));
					c.setNome(rs.getString("nomeCliente"));
					c.setCpf(rs.getString("cpf"));
					c.setTelefone(rs.getString("telefone"));
					c.setEmail(rs.getString("email"));
					c.setConcorrente(cc);
				
				Proposta proposta = new Proposta();
					proposta.setId(rs.getInt("idSimulacao"));
					proposta.setDataSimulacao(rs.getDate("dataSimulacao").toLocalDate());
					proposta.setDescontoCredito(rs.getDouble("descontoCredito"));
					proposta.setDescontoDebito(rs.getDouble("descontoDebito"));
					proposta.setSituacao(rs.getBoolean("situacao"));
					proposta.setTaxaFinalCredito(rs.getDouble("taxaFinalCredito"));
					proposta.setTaxaFinalDebito(rs.getDouble("taxaFinalDebito"));
					proposta.setCliente(c);
					
				listaProposta.add(proposta);
			}
		} catch (SQLException e) {
		e.printStackTrace();
		}
		return listaProposta;
	}
	
}
