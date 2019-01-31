package com.cartao.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;

public class Principal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
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
	public Principal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 700);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnCliente = new JMenu("Cliente");
		menuBar.add(mnCliente);
		
		JMenuItem mntmCadastroCliente = new JMenuItem("Cadastro");
		mntmCadastroCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CadastroClienteUI cadCli = new CadastroClienteUI();
				contentPane.add(cadCli, 0);
				cadCli.setVisible(true);
			}
		});
		mnCliente.add(mntmCadastroCliente);
		
		JMenuItem mntmConsultaCliente = new JMenuItem("Consulta");
		mntmConsultaCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ConsultaClienteUI consultaCliente = new ConsultaClienteUI();
				contentPane.add(consultaCliente, 0);
				consultaCliente.setVisible(true);
			}
		});
		mnCliente.add(mntmConsultaCliente);
		
		JMenu mnRamoAtividade = new JMenu("Ramo Atividade");
		menuBar.add(mnRamoAtividade);
		
		JMenuItem mntmCadastroRA = new JMenuItem("Cadastro");
		mntmCadastroRA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CadastroRamoAtividadeUI cadRA = new CadastroRamoAtividadeUI();
				contentPane.add(cadRA, 0);
				cadRA.setVisible(true);
			}
		});
		mnRamoAtividade.add(mntmCadastroRA);
		
		JMenuItem mntmConsultaRA = new JMenuItem("Consulta");
		mntmConsultaRA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ConsultaRamoAtividadeUI consultaAtividade = new ConsultaRamoAtividadeUI();
				contentPane.add(consultaAtividade, 0);
				consultaAtividade.setVisible(true);
			}
		});
		mnRamoAtividade.add(mntmConsultaRA);
		
		JMenu mnConcorrente = new JMenu("Concorrente");
		menuBar.add(mnConcorrente);
		
		JMenuItem mntmCadastroConcorrente = new JMenuItem("Cadastro");
		mntmCadastroConcorrente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CadastroConcorrenteUI cadCon = new CadastroConcorrenteUI();
				contentPane.add(cadCon, 0);
				cadCon.setVisible(true);
			}
		});
		mnConcorrente.add(mntmCadastroConcorrente);
		
		JMenuItem mntmConsultaConcorrente = new JMenuItem("Consulta");
		mntmConsultaConcorrente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ConsultaConcorrenteUI consultaConcorrente = new ConsultaConcorrenteUI();
				contentPane.add(consultaConcorrente, 0);
				consultaConcorrente.setVisible(true);
			}
		});
		mnConcorrente.add(mntmConsultaConcorrente);
		
		JMenu mnSimulacao = new JMenu("Simula\u00E7\u00E3o");
		menuBar.add(mnSimulacao);
		
		JMenuItem mntmNovaSimulao = new JMenuItem("Nova");
		mntmNovaSimulao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastroPropostaUI cadProposta = new CadastroPropostaUI();
				contentPane.add(cadProposta, 0);
				cadProposta.setVisible(true);
			}
		});
		mnSimulacao.add(mntmNovaSimulao);
		
		JMenu mnProposta = new JMenu("Proposta");
		menuBar.add(mnProposta);
		
		JMenuItem mntmConsulta = new JMenuItem("Consulta");
		mntmConsulta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConsultaPropostaAceitaUI consultaProposta = new ConsultaPropostaAceitaUI();
				contentPane.add(consultaProposta, 0);
				consultaProposta.setVisible(true);
				
				
			}
		});
		mnProposta.add(mntmConsulta);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGap(0, 698, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGap(0, 460, Short.MAX_VALUE)
		);
		contentPane.setLayout(gl_contentPane);
	}
}
