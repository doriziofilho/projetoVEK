package com.cartao.view;

import java.awt.EventQueue;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.TitledBorder;

import org.omg.Messaging.SyncScopeHelper;

import com.cartao.controller.clienteControl;
import com.cartao.controller.propostaControl;
import com.cartao.model.Cliente;
import com.cartao.model.ClienteTableModel;
import com.cartao.model.Proposta;
import com.cartao.model.RamoAtividade;

import javax.swing.SwingConstants;
import java.awt.Component;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;



public class CadastroPropostaUI extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField jtfClienteEscolhido;
	private JTextField jtfDataProposta;
	private JTextField jtfDescontoDebito;
	private JTextField jtfDescontoCredito;
	private clienteControl clienteControl = new clienteControl();
	private propostaControl propostaControl = new propostaControl();
	private Double valorCreditoProposta = 0.0;
	private Double valorDebitoProposta = 0.0;
	private Double seuValorCredito = 0.0;
	private Double seuValorDebito = 0.0;
	JLabel jlbSuaTaxaCredito = new JLabel("");
	JLabel jlbSuaTaxaDebito = new JLabel("");
	private boolean credito;
	private boolean debito;
	private Proposta propostaParaEdicao;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroPropostaUI frame = new CadastroPropostaUI();
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
	public CadastroPropostaUI() {
		setClosable(true);
		setTitle("Simulacao de Proposta ao Cliente");
		setBounds(100, 100, 833, 414);
		
		JPanel jpDadosSimulacao = new JPanel();
		jpDadosSimulacao.setBorder(new TitledBorder(null, "Dados da Simula\u00E7\u00E3o", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		DefaultComboBoxModel<Cliente> modelCliente = new DefaultComboBoxModel<>();
		
		for (Cliente cliente : clienteControl.listarTodos()) {
			modelCliente.addElement(cliente);
		}
		
		JComboBox<Cliente> jcbClienteEscolher = new JComboBox<>();
		jcbClienteEscolher.setModel(modelCliente);
		jcbClienteEscolher.setSelectedItem(null);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(18)
					.addComponent(jpDadosSimulacao, GroupLayout.PREFERRED_SIZE, 754, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(45, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(jpDadosSimulacao, GroupLayout.PREFERRED_SIZE, 334, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(73, Short.MAX_VALUE))
		);
		
		JLabel lblCliente = new JLabel("Cliente:");
		
		
		
		JLabel lblClienteResposta = new JLabel("cliente");
			lblClienteResposta.setText("");
		
		JLabel lblConcorrenteResposta = new JLabel("cocorrente");
			lblConcorrenteResposta.setText("");
			
		JLabel lblTaxaCreditoResposta = new JLabel("txCredito");
			lblTaxaCreditoResposta.setText("");
			
		JLabel lblTaxaDebitoResposta = new JLabel("txDebito");
			lblTaxaDebitoResposta.setText("");
		
		JLabel lblRatividadeResposta = new JLabel("ratividade");
			lblRatividadeResposta.setText("");
			//		lblTaxasAbaixoMinimo.setText("");
				
				JLabel lblTaxaDeCreditoMR = new JLabel("Taxa de Cr\u00E9dito m\u00EDnima:");
			//		lblTaxaDeCreditoMR.setText("");
				
				JLabel lblTaxaDeDebito = new JLabel("Taxa de D\u00E9bito m\u00EDnima:");
			//		lblTaxaDeDebito.setText("");
				
				JLabel lblValorRCreMi = new JLabel("");
			//		lblValorRCreMi.setText("");
				
				JLabel lblValorRDeMi = new JLabel("");
			//		lblValorRDeMi.setText("");
				
//				JLabel jlbSuaTxCredito = new JLabel("valor");
			//		jlbSuaTxCredito.setText("");
				
				JLabel lblSuaTaxaCredito = new JLabel("Sua taxa:");
				lblSuaTaxaCredito.setHorizontalAlignment(SwingConstants.RIGHT);
			//		lblSuaTaxaCredito.setText("");
				
				JLabel lblSuaTaxaDebito = new JLabel("Sua taxa:");
				lblSuaTaxaDebito.setHorizontalAlignment(SwingConstants.RIGHT);
//					lblSuaTaxaDebito.setText("");
				
//				JLabel jlbSuaTaxaDebito = new JLabel("valor");
//				jlbSuaTaxaDebito.setText("Valor");
					
				JLabel jlbRAatividadeEscolhido = new JLabel("");
				
		JButton btnSeleciona = new JButton("Seleciona");
		btnSeleciona.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Cliente clienteEscolhido = (Cliente) jcbClienteEscolher.getSelectedItem();
				jtfClienteEscolhido.setText(clienteEscolhido.getNome());
				
				lblClienteResposta.setText(clienteEscolhido.getNome());
				lblRatividadeResposta.setText(clienteEscolhido.getRamoAtividade().getNome());
				lblConcorrenteResposta.setText(clienteEscolhido.getConcorrente().getNome());
				lblTaxaCreditoResposta.setText(clienteEscolhido.getConcorrente().getTaxaCredito().toString()+ " %");
				lblTaxaDebitoResposta.setText(clienteEscolhido.getConcorrente().getTaxaDebito().toString()+ " %");
				lblValorRCreMi.setText(clienteEscolhido.getRamoAtividade().getTaxaMinimaCredito() + " %");
				lblValorRDeMi.setText(clienteEscolhido.getRamoAtividade().getTaxaMinimaDebito() + " %");
				jlbRAatividadeEscolhido.setText(clienteEscolhido.getRamoAtividade().getNome());
				jlbSuaTaxaDebito.setText("");
				jlbSuaTaxaCredito.setText("");
				
			}
		});
		
		jtfClienteEscolhido = new JTextField();
		jtfClienteEscolhido.setText("");
		jtfClienteEscolhido.setColumns(10);
		
		JLabel lblData = new JLabel("Data:");
		
		jtfDataProposta = new JTextField();
		jtfDataProposta.setColumns(10);
		
		JPanel jpDadosCliente = new JPanel();
		jpDadosCliente.setBorder(new TitledBorder(null, "Dados do Cliente", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JLabel lblDescotoDbito = new JLabel("Descoto D\u00E9bito:");
		
		jtfDescontoDebito = new JTextField();
		jtfDescontoDebito.setColumns(10);
		
		JLabel lblDescontoCrdito = new JLabel("Desconto Cr\u00E9dito:");
		
		jtfDescontoCredito = new JTextField();
		jtfDescontoCredito.setColumns(10);
		
		JPanel jpNegativo = new JPanel();
		jpNegativo.setBorder(new TitledBorder(null, "Negocia\u00E7\u00E3o:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JButton btnSimular = new JButton("Simular");
		
		btnSimular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				Cliente clienteEscolhido = (Cliente) jcbClienteEscolher.getSelectedItem();
				
				valorCreditoProposta = Double.parseDouble(jtfDescontoCredito.getText());
				valorDebitoProposta = Double.parseDouble(jtfDescontoDebito.getText());
				double descontoCreditoCliente = calcularDescontoCredito(clienteEscolhido, valorCreditoProposta);
				double descontoDebitoCliente = calcularDescontoDebito(clienteEscolhido, valorDebitoProposta);
				calcularDescontoDebito(clienteEscolhido, valorDebitoProposta);
				calcularDescontoCredito(clienteEscolhido, valorCreditoProposta);
				
				verificarTaxaMinimaCredito(clienteEscolhido, descontoCreditoCliente);
				verificarTaxaMinimaDebito(clienteEscolhido, descontoDebitoCliente);
				
				if (credito && debito) {
					Object[] options = {"Sim", "Não"};
					int teste1 = JOptionPane.showOptionDialog(null, "Cliente Aceitou a proposta?", "Proposta finalizada!", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
					if (teste1 == 0){
						Cliente cliente = (Cliente) jcbClienteEscolher.getSelectedItem();
						Proposta proposta = new Proposta();
						
						DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
						proposta.setDataSimulacao(LocalDate.parse(jtfDataProposta.getText(),dtf));
						proposta.setDescontoCredito(Double.parseDouble(jtfDescontoCredito.getText()));
						proposta.setDescontoDebito(Double.parseDouble(jtfDescontoDebito.getText()));
						proposta.setTaxaFinalCredito(calcularDescontoDebito(clienteEscolhido, valorCreditoProposta));
						proposta.setTaxaFinalDebito(calcularDescontoDebito(clienteEscolhido, valorDebitoProposta));
						proposta.setSituacao(true);
						proposta.setCliente(cliente);
						
						propostaControl.salvar(proposta);
						JOptionPane.showMessageDialog(null, "Proposta cadastrada com sucesso!");
					}else if (teste1 == 1){
						Cliente cliente = (Cliente) jcbClienteEscolher.getSelectedItem();
						Proposta proposta = new Proposta();
						
						DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
						proposta.setDataSimulacao(LocalDate.parse(jtfDataProposta.getText(),dtf));
						proposta.setDescontoCredito(Double.parseDouble(jtfDescontoCredito.getText()));
						proposta.setDescontoDebito(Double.parseDouble(jtfDescontoDebito.getText()));
						proposta.setTaxaFinalCredito(calcularDescontoDebito(clienteEscolhido, valorCreditoProposta));
						proposta.setTaxaFinalDebito(calcularDescontoDebito(clienteEscolhido, valorDebitoProposta));
						proposta.setSituacao(false);
						proposta.setCliente(cliente);
						
						propostaControl.salvar(proposta);
						JOptionPane.showMessageDialog(null, "Proposta não confirmada, cadastrada como  com sucesso!");
					}
					
				} else {
					
				}
				
				
			}
		});
		
		JLabel label = new JLabel("%");
		
		JLabel label_1 = new JLabel("%");
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				jtfClienteEscolhido.setText("");
				jcbClienteEscolher.setSelectedItem(null);
				lblClienteResposta.setText("");
				lblRatividadeResposta.setText("");
				lblConcorrenteResposta.setText("");
				lblTaxaCreditoResposta.setText("");
				lblTaxaDebitoResposta.setText("");
				lblValorRCreMi.setText("");
				lblValorRDeMi.setText("");
				jlbRAatividadeEscolhido.setText("");
				jlbSuaTaxaDebito.setText("");
				jlbSuaTaxaCredito.setText("");
				jtfDescontoCredito.setText("");
				jtfDescontoDebito.setText("");
				jtfDataProposta.setText("");
			}
		});
		
		JButton btnFechar = new JButton("Fechar");
		btnFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		GroupLayout gl_jpDadosSimulacao = new GroupLayout(jpDadosSimulacao);
		gl_jpDadosSimulacao.setHorizontalGroup(
			gl_jpDadosSimulacao.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_jpDadosSimulacao.createSequentialGroup()
					.addGroup(gl_jpDadosSimulacao.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_jpDadosSimulacao.createSequentialGroup()
							.addGap(10)
							.addGroup(gl_jpDadosSimulacao.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_jpDadosSimulacao.createSequentialGroup()
									.addGroup(gl_jpDadosSimulacao.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_jpDadosSimulacao.createSequentialGroup()
											.addGap(66)
											.addGroup(gl_jpDadosSimulacao.createParallelGroup(Alignment.TRAILING, false)
												.addComponent(jtfClienteEscolhido, Alignment.LEADING)
												.addComponent(jcbClienteEscolher, Alignment.LEADING, 0, 344, Short.MAX_VALUE)))
										.addGroup(gl_jpDadosSimulacao.createSequentialGroup()
											.addGap(10)
											.addComponent(lblDescotoDbito)
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addComponent(jtfDescontoDebito, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(label, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
											.addGap(54)
											.addComponent(lblDescontoCrdito)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(jtfDescontoCredito, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)))
									.addPreferredGap(ComponentPlacement.RELATED))
								.addGroup(gl_jpDadosSimulacao.createSequentialGroup()
									.addGap(19)
									.addGroup(gl_jpDadosSimulacao.createParallelGroup(Alignment.TRAILING)
										.addComponent(lblData)
										.addComponent(lblCliente))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(jtfDataProposta, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 118, Short.MAX_VALUE)
									.addComponent(btnSeleciona)
									.addGap(34)))
							.addComponent(jpDadosCliente, GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE))
						.addGroup(gl_jpDadosSimulacao.createSequentialGroup()
							.addGap(37)
							.addComponent(jpNegativo, GroupLayout.PREFERRED_SIZE, 686, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_jpDadosSimulacao.createSequentialGroup()
							.addGap(132)
							.addComponent(btnSimular)
							.addGap(108)
							.addComponent(btnCancelar)
							.addGap(88)
							.addComponent(btnFechar)))
					.addContainerGap())
		);
		gl_jpDadosSimulacao.setVerticalGroup(
			gl_jpDadosSimulacao.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_jpDadosSimulacao.createSequentialGroup()
					.addGroup(gl_jpDadosSimulacao.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_jpDadosSimulacao.createSequentialGroup()
							.addGap(8)
							.addGroup(gl_jpDadosSimulacao.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblCliente)
								.addComponent(jcbClienteEscolher, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(jtfClienteEscolhido, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_jpDadosSimulacao.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblData)
								.addComponent(btnSeleciona)
								.addComponent(jtfDataProposta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(26)
							.addGroup(gl_jpDadosSimulacao.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblDescotoDbito)
								.addComponent(jtfDescontoDebito, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblDescontoCrdito)
								.addComponent(jtfDescontoCredito, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(label)
								.addComponent(label_1)))
						.addGroup(gl_jpDadosSimulacao.createSequentialGroup()
							.addContainerGap()
							.addComponent(jpDadosCliente, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addComponent(jpNegativo, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_jpDadosSimulacao.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSimular)
						.addComponent(btnCancelar)
						.addComponent(btnFechar))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		JLabel lblRamoAtividade = new JLabel("Ramo Atividade: ");
		
		
		
	
		
		GroupLayout gl_jpNegativo = new GroupLayout(jpNegativo);
		gl_jpNegativo.setHorizontalGroup(
			gl_jpNegativo.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_jpNegativo.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_jpNegativo.createParallelGroup(Alignment.LEADING)
						.addComponent(lblTaxaDeCreditoMR, Alignment.TRAILING)
						.addComponent(lblRamoAtividade, Alignment.TRAILING)
						.addComponent(lblTaxaDeDebito, Alignment.TRAILING))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_jpNegativo.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_jpNegativo.createSequentialGroup()
							.addGroup(gl_jpNegativo.createParallelGroup(Alignment.LEADING)
								.addComponent(lblValorRDeMi, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblValorRCreMi, GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_jpNegativo.createParallelGroup(Alignment.LEADING)
								.addComponent(lblSuaTaxaCredito, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblSuaTaxaDebito, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_jpNegativo.createParallelGroup(Alignment.LEADING)
								.addComponent(jlbSuaTaxaDebito, GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
								.addComponent(jlbSuaTaxaCredito, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)))
						.addComponent(jlbRAatividadeEscolhido))
					.addContainerGap())
		);
		gl_jpNegativo.setVerticalGroup(
			gl_jpNegativo.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_jpNegativo.createSequentialGroup()
					.addGroup(gl_jpNegativo.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblRamoAtividade)
						.addComponent(jlbRAatividadeEscolhido))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_jpNegativo.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_jpNegativo.createSequentialGroup()
							.addGroup(gl_jpNegativo.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblSuaTaxaCredito)
								.addComponent(jlbSuaTaxaCredito))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_jpNegativo.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblSuaTaxaDebito)
								.addComponent(jlbSuaTaxaDebito)))
						.addGroup(gl_jpNegativo.createSequentialGroup()
							.addGroup(gl_jpNegativo.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblTaxaDeCreditoMR)
								.addComponent(lblValorRCreMi))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_jpNegativo.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblTaxaDeDebito)
								.addComponent(lblValorRDeMi))))
					.addGap(14))
		);
		jpNegativo.setLayout(gl_jpNegativo);
		
		JLabel lblClienteNomeDCliente = new JLabel("Cliente:");
		
		JLabel lblRamoDeAtividade = new JLabel("R. de Atividade:");
		
		JLabel lblConcorrenteDCliente = new JLabel("Concorrente:");
		
		JLabel lblTaxacreditoDCliente = new JLabel("Tx de Cr\u00E9dito:");
		
		JLabel lblTaxadebitoDCliente = new JLabel("Tx de Debito:");
		
		
		
		
		
		GroupLayout gl_jpDadosCliente = new GroupLayout(jpDadosCliente);
		gl_jpDadosCliente.setHorizontalGroup(
			gl_jpDadosCliente.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_jpDadosCliente.createSequentialGroup()
					.addGroup(gl_jpDadosCliente.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_jpDadosCliente.createSequentialGroup()
							.addGap(51)
							.addComponent(lblClienteNomeDCliente))
						.addGroup(gl_jpDadosCliente.createSequentialGroup()
							.addGap(24)
							.addComponent(lblConcorrenteDCliente))
						.addGroup(gl_jpDadosCliente.createSequentialGroup()
							.addGap(23)
							.addComponent(lblTaxadebitoDCliente))
						.addGroup(gl_jpDadosCliente.createSequentialGroup()
							.addGap(19)
							.addComponent(lblTaxacreditoDCliente))
						.addGroup(gl_jpDadosCliente.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblRamoDeAtividade)))
					.addGap(18)
					.addGroup(gl_jpDadosCliente.createParallelGroup(Alignment.LEADING)
						.addComponent(lblClienteResposta)
						.addComponent(lblRatividadeResposta, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblConcorrenteResposta, GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
						.addComponent(lblTaxaCreditoResposta)
						.addComponent(lblTaxaDebitoResposta))
					.addContainerGap())
		);
		gl_jpDadosCliente.setVerticalGroup(
			gl_jpDadosCliente.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_jpDadosCliente.createSequentialGroup()
					.addGap(11)
					.addGroup(gl_jpDadosCliente.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_jpDadosCliente.createSequentialGroup()
							.addComponent(lblClienteResposta)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblRatividadeResposta)
							.addGap(11)
							.addComponent(lblConcorrenteResposta)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblTaxaCreditoResposta)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblTaxaDebitoResposta))
						.addGroup(gl_jpDadosCliente.createSequentialGroup()
							.addComponent(lblClienteNomeDCliente)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblRamoDeAtividade)
							.addGap(11)
							.addComponent(lblConcorrenteDCliente)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblTaxacreditoDCliente)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblTaxadebitoDCliente)))
					.addGap(22))
		);
		jpDadosCliente.setLayout(gl_jpDadosCliente);
		jpDadosSimulacao.setLayout(gl_jpDadosSimulacao);
		getContentPane().setLayout(groupLayout);

	}
	public double calcularDescontoCredito(Cliente clienteEscolhido, Double credito) {
		double taxaCLienteCredito = clienteEscolhido.getConcorrente().getTaxaCredito();
		
		return	valorCreditoProposta = taxaCLienteCredito - (taxaCLienteCredito/100 * credito);
		
		
	};
	public double calcularDescontoDebito(Cliente clienteEscolhido,  Double  debito) {
		double taxaClienteDebito = clienteEscolhido.getConcorrente().getTaxaDebito();

		return valorDebitoProposta = taxaClienteDebito - (taxaClienteDebito/100 * debito);
	};

	public void verificarTaxaMinimaDebito(Cliente clienteEscolhido, Double taxaConcedida) {
		double taxaMinimaDebito = clienteEscolhido.getRamoAtividade().getTaxaMinimaDebito();
		
		if (taxaMinimaDebito <= taxaConcedida) {
			jlbSuaTaxaDebito.setText(taxaConcedida + " %");
			debito = true;
		} else {
			jlbSuaTaxaDebito.setText(taxaConcedida + " %");
				JOptionPane.showMessageDialog(null, "Refaça a proposta! \n" 
						+ "Taxa de Débito Mínima: " + taxaMinimaDebito + " %.\n"
						+ "Taxa Ofertada: " + taxaConcedida + " %.\n"
						+ "Diferença: " + (Math.abs(taxaConcedida - taxaMinimaDebito)) + " %") ;
				
				valorDebitoProposta = Double.parseDouble(jtfDescontoDebito.getText());
				debito = false;
		}
	}
	
	public void verificarTaxaMinimaCredito(Cliente clienteEscolhido, Double taxaConcedida) {
		double taxaMinimaCredito = clienteEscolhido.getRamoAtividade().getTaxaMinimaCredito();
		
		if (taxaMinimaCredito <= taxaConcedida) {
			jlbSuaTaxaCredito.setText(taxaConcedida + " %");
			credito = true;
		} else {
			jlbSuaTaxaCredito.setText(taxaConcedida + " %");
				JOptionPane.showMessageDialog(null, "Refaça a proposta! \n" 
						+ "Taxa de Crédito Mínima: " + taxaMinimaCredito + " %.\n"
						+ "Taxa Ofertada: " + taxaConcedida + " %.\n"
						+ "Diferença: " + (Math.abs(taxaConcedida - taxaMinimaCredito)) + " %" );
				valorCreditoProposta = Double.parseDouble(jtfDescontoCredito.getText());
			credito = false;	
		}
	}



}
