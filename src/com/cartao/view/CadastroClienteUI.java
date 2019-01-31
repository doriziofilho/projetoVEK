package com.cartao.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import com.cartao.controller.clienteControl;
import com.cartao.controller.concorrenteControl;
import com.cartao.controller.ramoAtividadeControl;
import com.cartao.model.Cliente;
import com.cartao.model.Concorrente;
import com.cartao.model.RamoAtividade;

public class CadastroClienteUI extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField jtfNomeCliente;
	private JTextField jtfCPF;
	private JTextField jtfTelefone;
	private int posicaoParaEdicao;
	private concorrenteControl concorrenteControl = new concorrenteControl();
	private clienteControl clienteControl = new clienteControl();
	private ramoAtividadeControl ramoAtividadeControl = new ramoAtividadeControl();
	private JTextField jtfEmail;
	private Cliente clienteParaEdicao;
	private JComboBox jcbRamoAtividade;
	private JComboBox jcbConcorrente;
	private JTextField jtfRaEscolhido;
	private JTextField jtfConcoEscolhido;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroClienteUI frame = new CadastroClienteUI();
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
	public CadastroClienteUI() {
		setClosable(true);
		setTitle("Cadastro de Cliente");
		setBounds(100, 100, 721, 481);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Cliente", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		
		DefaultComboBoxModel<RamoAtividade> modelRA = new DefaultComboBoxModel<>();
		
		for (RamoAtividade ramoAtividade : ramoAtividadeControl.listarRamoAtividade()) {
			modelRA.addElement(ramoAtividade);
		}
		
		JComboBox<RamoAtividade> jcbRamoAtividadesEscolher = new JComboBox<>();
		jcbRamoAtividadesEscolher.setModel(modelRA);
		jcbRamoAtividadesEscolher.setSelectedItem(null);
		
		JButton btnAddRA = new JButton("Seleciona");
		btnAddRA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RamoAtividade ramoAtividade = (RamoAtividade) jcbRamoAtividadesEscolher.getSelectedItem();
				jtfRaEscolhido.setText(ramoAtividade.getNome());
				
				
			}
		});
		
		JLabel jlbListaConcorrente = new JLabel("Concorrentes:");
		DefaultComboBoxModel<Concorrente> modelConcorrente = new DefaultComboBoxModel<>();
		for(Concorrente concorrente : concorrenteControl.listarTodos()) {
			modelConcorrente.addElement(concorrente);
		}
		
		JComboBox<Concorrente> jcbConcorrentesEscolher = new JComboBox<>();
		jcbConcorrentesEscolher.setModel(modelConcorrente);
		jcbConcorrentesEscolher.setSelectedItem(null);
		
		JButton btnAddConc = new JButton("Seleciona");
		btnAddConc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Concorrente concorrente = (Concorrente) jcbConcorrentesEscolher.getSelectedItem();
				jtfConcoEscolhido.setText(concorrente.getNome());
				
				
			}
		});
				
				JButton btnSalvar = new JButton("Salvar");
				btnSalvar.setIcon(new ImageIcon(CadastroClienteUI.class.getResource("/img/save.png")));
				btnSalvar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							if(clienteParaEdicao == null) {
								
								RamoAtividade ramoAtividade = (RamoAtividade) jcbRamoAtividadesEscolher.getSelectedItem();
								Concorrente concorrente = (Concorrente) jcbConcorrentesEscolher.getSelectedItem();
								Cliente cliente = new Cliente();
								
								cliente.setNome(jtfNomeCliente.getText());
								cliente.setCpf(jtfCPF.getText());
								cliente.setTelefone(jtfTelefone.getText());
								cliente.setEmail(jtfEmail.getText());
								cliente.setRamoAtividade(ramoAtividade);
								cliente.setConcorrente(concorrente);
								
								clienteControl.salvar(cliente);
								
								JOptionPane.showMessageDialog(null, "Cliente Cadastrado com Sucesso!");
								
								jtfNomeCliente.setText("");
								jtfCPF.setText("");
								jtfTelefone.setText("");
								jtfEmail.setText("");
								jtfRaEscolhido.setText("");;
								jtfConcoEscolhido.setText("");
							} else {
								RamoAtividade ramoAtividade = (RamoAtividade) jcbRamoAtividadesEscolher.getSelectedItem();
								Concorrente concorrente = (Concorrente) jcbConcorrentesEscolher.getSelectedItem();
								
								clienteParaEdicao.setNome(jtfNomeCliente.getText());
								clienteParaEdicao.setCpf((jtfCPF.getText()));
								clienteParaEdicao.setTelefone(jtfTelefone.getText());
								clienteParaEdicao.setEmail(jtfEmail.getText());
								clienteParaEdicao.setRamoAtividade(ramoAtividade);
								clienteParaEdicao.setConcorrente(concorrente);
								clienteControl.editar(clienteParaEdicao);
								
								ConsultaClienteUI conCliente = new ConsultaClienteUI();
								getParent().add(conCliente, 0);
								conCliente.setVisible(true);
								
								dispose();
								JOptionPane.showMessageDialog(null, "Concorrente editado com sucesso!");
								
							}
						} catch (Exception e2){
							JOptionPane.showMessageDialog(null, "algum tipo de erro");
						}
					}
				});
		
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setIcon(new ImageIcon(CadastroClienteUI.class.getResource("/img/cancel.png")));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jtfNomeCliente.setText("");
				jtfCPF.setText("");
				jtfTelefone.setText("");
				jtfEmail.setText("");
				jtfRaEscolhido.setText("");;
				jtfConcoEscolhido.setText("");
			}
		});
		
		JButton btnFechar = new JButton("Fechar");
		btnFechar.setIcon(new ImageIcon(CadastroClienteUI.class.getResource("/img/close.png")));
		btnFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 685, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(42)
					.addComponent(btnSalvar)
					.addGap(144)
					.addComponent(btnCancelar)
					.addPreferredGap(ComponentPlacement.RELATED, 184, Short.MAX_VALUE)
					.addComponent(btnFechar)
					.addGap(72))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 374, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSalvar)
						.addComponent(btnFechar)
						.addComponent(btnCancelar))
					.addGap(24))
		);
		
		JLabel lblNome = new JLabel("Nome:");
		
		JLabel XPFTaxaDeCrdito = new JLabel("Cpf:");
		
		JLabel lblTelefone = new JLabel("Telefone:");
		
		jtfNomeCliente = new JTextField();
		jtfNomeCliente.setColumns(10);
		
		jtfCPF = new JTextField();
		jtfCPF.setColumns(10);
		
		jtfTelefone = new JTextField();
		jtfTelefone.setColumns(10);
		
		JLabel lblEmail = new JLabel("email:");
		
		jtfEmail = new JTextField();
		jtfEmail.setColumns(10);
		
		JLabel lblListaRamoAtividade = new JLabel("Ramos de Atividades:");
		
		jtfRaEscolhido = new JTextField();
		jtfRaEscolhido.setColumns(10);
		
		jtfConcoEscolhido = new JTextField();
		jtfConcoEscolhido.setColumns(10);
		
		
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(22)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(jtfRaEscolhido, GroupLayout.PREFERRED_SIZE, 274, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_panel.createSequentialGroup()
								.addComponent(jlbListaConcorrente)
								.addContainerGap())
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblListaRamoAtividade)
									.addContainerGap())
								.addGroup(gl_panel.createSequentialGroup()
									.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
										.addComponent(jcbConcorrentesEscolher, 0, 503, Short.MAX_VALUE)
										.addComponent(jcbRamoAtividadesEscolher, Alignment.LEADING, 0, 503, Short.MAX_VALUE)
										.addGroup(gl_panel.createSequentialGroup()
											.addPreferredGap(ComponentPlacement.RELATED)
											.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
												.addComponent(lblNome, Alignment.TRAILING)
												.addComponent(XPFTaxaDeCrdito, Alignment.TRAILING)
												.addComponent(lblTelefone, Alignment.TRAILING)
												.addComponent(lblEmail, Alignment.TRAILING))
											.addPreferredGap(ComponentPlacement.RELATED)
											.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
												.addComponent(jtfNomeCliente, GroupLayout.DEFAULT_SIZE, 453, Short.MAX_VALUE)
												.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
													.addComponent(jtfTelefone, Alignment.LEADING)
													.addComponent(jtfCPF, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE))
												.addComponent(jtfEmail, GroupLayout.PREFERRED_SIZE, 283, GroupLayout.PREFERRED_SIZE)))
										.addComponent(jtfConcoEscolhido, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 277, GroupLayout.PREFERRED_SIZE))
									.addGap(18)
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(btnAddRA)
										.addComponent(btnAddConc))
									.addGap(57))))))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNome)
						.addComponent(jtfNomeCliente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(XPFTaxaDeCrdito)
						.addComponent(jtfCPF, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTelefone)
						.addComponent(jtfTelefone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEmail)
						.addComponent(jtfEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(23)
					.addComponent(lblListaRamoAtividade)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(jcbRamoAtividadesEscolher, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnAddRA))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(jtfRaEscolhido, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(23)
					.addComponent(jlbListaConcorrente)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(jcbConcorrentesEscolher, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnAddConc))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(jtfConcoEscolhido, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(38))
		);
		panel.setLayout(gl_panel);
		getContentPane().setLayout(groupLayout);

	}
	public Cliente getClienteParaEdicao() {
		return clienteParaEdicao;
	}
	
	public void setClienteParaEdicao(Cliente clienteParaEdicao) {
		this.clienteParaEdicao = clienteParaEdicao;
		preencherCamposParaEdicao();
	}

	public int getPosicaoParaEdicao() {
		return posicaoParaEdicao;
	}
	
	public void setPosicaoParaEdicao(int posicaoParaEdicao) {
		this.posicaoParaEdicao = posicaoParaEdicao;
	}
	
	public void preencherCamposParaEdicao() {
		if(clienteParaEdicao != null) {
			jtfNomeCliente.setText(clienteParaEdicao.getNome());
			jtfCPF.setText(clienteParaEdicao.getCpf());
			jtfTelefone.setText(clienteParaEdicao.getTelefone());
			jtfEmail.setText(clienteParaEdicao.getEmail());
			jtfRaEscolhido.setText(clienteParaEdicao.getRamoAtividade().getNome());
			jtfConcoEscolhido.setText(clienteParaEdicao.getConcorrente().getNome());
		}
	}
}
