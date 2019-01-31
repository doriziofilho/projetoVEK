package com.cartao.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.cartao.controller.propostaControl;
import com.cartao.model.Proposta;
import com.cartao.model.PropostaTableModel;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.ImageIcon;

public class ConsultaPropostaAceitaUI extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField jtfDataIncial;
	private JTextField jtfDataFinal;
	private JTable jtListaProposta;
	private propostaControl propostaControl = new propostaControl();
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsultaPropostaAceitaUI frame = new ConsultaPropostaAceitaUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ConsultaPropostaAceitaUI() {
		setTitle("Consulta de Propostas");
		setClosable(true);
		setBounds(100, 100, 818, 425);
		
		JPanel jpConsultaProposta = new JPanel();
		jpConsultaProposta.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Consultar Proposta:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(jpConsultaProposta, GroupLayout.PREFERRED_SIZE, 770, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(22, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(jpConsultaProposta, GroupLayout.DEFAULT_SIZE, 374, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		JPanel panel = new JPanel();
		
		JScrollPane jspProposta = new JScrollPane();
		
		jtfDataIncial = new JTextField();
		jtfDataIncial.setColumns(10);
		
		JLabel label = new JLabel("Data Inicial: ");
		
		JLabel lblDataFinal = new JLabel("Data Final :");
		
		jtfDataFinal = new JTextField();
		jtfDataFinal.setColumns(10);
		
		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.setIcon(new ImageIcon(ConsultaPropostaAceitaUI.class.getResource("/img/magnifier.png")));
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				LocalDate dataInical = LocalDate.parse(jtfDataIncial.getText(),dtf);
				LocalDate dataFinal = LocalDate.parse(jtfDataFinal.getText(),dtf);
				
			List<Proposta> filtro = new propostaControl().pesquisarPropostaPorData(dataInical, dataFinal);
				PropostaTableModel modelProspota = new PropostaTableModel(filtro);
				jtListaProposta.setModel(modelProspota);
				
				
				
			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(21)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(label, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(jtfDataIncial, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblDataFinal, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(jtfDataFinal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
					.addComponent(btnPesquisar)
					.addGap(24))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(19)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addGap(3)
									.addComponent(label))
								.addComponent(jtfDataIncial, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(11)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addGap(3)
									.addComponent(lblDataFinal))
								.addComponent(jtfDataFinal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(33)
							.addComponent(btnPesquisar)))
					.addContainerGap(23, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		
		JButton btnGerarCsv = new JButton("Gerar CSV");
		btnGerarCsv.setIcon(new ImageIcon(ConsultaPropostaAceitaUI.class.getResource("/img/save.png")));
		
		JButton btnFechar = new JButton("Sair");
		btnFechar.setIcon(new ImageIcon(ConsultaPropostaAceitaUI.class.getResource("/img/close.png")));
		btnFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		GroupLayout gl_jpConsultaProposta = new GroupLayout(jpConsultaProposta);
		gl_jpConsultaProposta.setHorizontalGroup(
			gl_jpConsultaProposta.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_jpConsultaProposta.createSequentialGroup()
					.addGroup(gl_jpConsultaProposta.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_jpConsultaProposta.createSequentialGroup()
							.addGap(10)
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 318, GroupLayout.PREFERRED_SIZE)
							.addGap(153)
							.addGroup(gl_jpConsultaProposta.createParallelGroup(Alignment.LEADING, false)
								.addComponent(btnFechar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnGerarCsv, GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)))
						.addGroup(gl_jpConsultaProposta.createSequentialGroup()
							.addContainerGap()
							.addComponent(jspProposta, GroupLayout.PREFERRED_SIZE, 735, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(13, Short.MAX_VALUE))
		);
		gl_jpConsultaProposta.setVerticalGroup(
			gl_jpConsultaProposta.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_jpConsultaProposta.createSequentialGroup()
					.addGroup(gl_jpConsultaProposta.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_jpConsultaProposta.createSequentialGroup()
							.addGap(19)
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_jpConsultaProposta.createSequentialGroup()
							.addGap(30)
							.addComponent(btnGerarCsv)
							.addGap(18)
							.addComponent(btnFechar)))
					.addGap(27)
					.addComponent(jspProposta, GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
					.addGap(19))
		);
		
		jtListaProposta = new JTable();
		jtListaProposta.setModel(new DefaultTableModel(
			new Object[][] {
				{null, "", null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
			},
			new String[] {
				"PROPOSTA", "CLIENTE", "DATA ACEITE", "DESC. D\u00C9BITO", "DESC. CR\u00C9DITO", "TAXA DEBITO", "TAXA CREDITO"
			}
		));
		jspProposta.setViewportView(jtListaProposta);
		jpConsultaProposta.setLayout(gl_jpConsultaProposta);
		getContentPane().setLayout(groupLayout);

	}
}
