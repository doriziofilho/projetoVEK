package com.cartao.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.TitledBorder;

import com.cartao.controller.concorrenteControl;
import com.cartao.model.Concorrente;
import com.cartao.model.RamoAtividade;

import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CadastroConcorrenteUI extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField jtfNome;
	private JTextField jtfTaxaCredito;
	private JTextField jtfTaxaDebito;
	private Concorrente concorrenteParaEdicao;
	private int posicaoParaEdicao;
	private concorrenteControl concorrenteControl = new concorrenteControl();
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroConcorrenteUI frame = new CadastroConcorrenteUI();
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
	public CadastroConcorrenteUI() {
		setClosable(true);
		setTitle("Cadastro de Concorrente");
		setBounds(100, 100, 368, 267);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Concorrente", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setIcon(new ImageIcon(CadastroConcorrenteUI.class.getResource("/img/save.png")));
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(concorrenteParaEdicao == null) {
						Concorrente concorrente = new Concorrente();
						concorrente.setNome(jtfNome.getText());
						concorrente.setTaxaCredito(Double.parseDouble(jtfTaxaCredito.getText()));
						concorrente.setTaxaDebito(Double.parseDouble(jtfTaxaDebito.getText()));
						
						concorrenteControl.salvar(concorrente);
						JOptionPane.showMessageDialog(null, "Concorrente Cadastrado com Sucesso!");
						
						jtfNome.setText("");
						jtfTaxaCredito.setText("");
						jtfTaxaDebito.setText("");
						
					} else {
						concorrenteParaEdicao.setNome(jtfNome.getText());
						concorrenteParaEdicao.setTaxaCredito(Double.parseDouble(jtfTaxaCredito.getText()));
						concorrenteParaEdicao.setTaxaDebito(Double.parseDouble(jtfTaxaDebito.getText()));
						concorrenteControl.editar(concorrenteParaEdicao);
						ConsultaConcorrenteUI conConcorrente = new ConsultaConcorrenteUI();
						getParent().add(conConcorrente, 0);
						conConcorrente.setVisible(true);
						
						dispose();
						JOptionPane.showMessageDialog(null, "Concorrente editado com sucesso!");
						
					}
				} catch (NumberFormatException e2) {
					JOptionPane.showMessageDialog(null, "Informe valores numéricos para os campos das taxas!");
				} catch (Exception e2){
					JOptionPane.showMessageDialog(null, e2.getMessage());
				}
			}
		});
		
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setIcon(new ImageIcon(CadastroConcorrenteUI.class.getResource("/img/cancel.png")));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jtfNome.setText("");
				jtfTaxaCredito.setText("");
				jtfTaxaDebito.setText("");
			}
		});
		
		JButton btnFechar = new JButton("Fechar");
		btnFechar.setIcon(new ImageIcon(CadastroConcorrenteUI.class.getResource("/img/close.png")));
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
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 341, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(1, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(22)
					.addComponent(btnSalvar)
					.addGap(22)
					.addComponent(btnCancelar)
					.addPreferredGap(ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
					.addComponent(btnFechar)
					.addGap(29))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 161, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnSalvar)
						.addComponent(btnCancelar)
						.addComponent(btnFechar))
					.addContainerGap(23, Short.MAX_VALUE))
		);
		
		JLabel lblNome = new JLabel("Nome:");
		
		JLabel lblTaxaDeCrdito = new JLabel("Taxa de Cr\u00E9dito:");
		
		JLabel lblTaxaDeDbito = new JLabel("Taxa de D\u00E9bito:");
		
		jtfNome = new JTextField();
		jtfNome.setColumns(10);
		
		jtfTaxaCredito = new JTextField();
		jtfTaxaCredito.setColumns(10);
		
		jtfTaxaDebito = new JTextField();
		jtfTaxaDebito.setText("");
		jtfTaxaDebito.setColumns(10);
		
		JLabel label = new JLabel("%");
		
		JLabel label_1 = new JLabel("%");
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(lblNome)
						.addComponent(lblTaxaDeCrdito)
						.addComponent(lblTaxaDeDbito))
					.addGap(10)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(jtfTaxaDebito, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(label))
						.addGroup(gl_panel.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(jtfNome, GroupLayout.PREFERRED_SIZE, 165, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(jtfTaxaCredito, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(label_1)))
					.addContainerGap(127, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(17)
							.addComponent(jtfNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(15)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(jtfTaxaCredito, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(label_1))
							.addGap(15)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(jtfTaxaDebito, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(label)))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(20)
							.addComponent(lblNome)
							.addGap(21)
							.addComponent(lblTaxaDeCrdito)
							.addGap(21)
							.addComponent(lblTaxaDeDbito)))
					.addContainerGap(31, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		getContentPane().setLayout(groupLayout);

	}
	public Concorrente getConcorrenteParaEdicao() {
		return concorrenteParaEdicao;
	}
	
	public void setConcorrenteParaEdicao(Concorrente concorrenteParaEdicao) {
		this.concorrenteParaEdicao = concorrenteParaEdicao;
		preencherCamposParaEdicao();
	}

	public int getPosicaoParaEdicao() {
		return posicaoParaEdicao;
	}
	
	public void setPosicaoParaEdicao(int posicaoParaEdicao) {
		this.posicaoParaEdicao = posicaoParaEdicao;
	}
	
	public void preencherCamposParaEdicao() {
		if(concorrenteParaEdicao != null) {
			jtfNome.setText(concorrenteParaEdicao.getNome());
			jtfTaxaDebito.setText(Double.toString(concorrenteParaEdicao.getTaxaDebito()));
			jtfTaxaCredito.setText(Double.toString(concorrenteParaEdicao.getTaxaCredito()));
		}
	}
}
