package com.cartao.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import org.omg.Messaging.SyncScopeHelper;

import com.cartao.controller.ramoAtividadeControl;
import com.cartao.model.RamoAtividade;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.ImageIcon;

public class CadastroRamoAtividadeUI extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField jtfNome;
	private JTextField jtfTxCredito;
	private JTextField jtfDebito;
	private RamoAtividade ramoAtividadeParaEdicao;
	private int posicaoParaEdicao;
	private ramoAtividadeControl ramoAtividadeControl = new ramoAtividadeControl();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroRamoAtividadeUI frame = new CadastroRamoAtividadeUI();
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
	public CadastroRamoAtividadeUI() {
		setClosable(true);
		setTitle("Cadastro Ramo de Atividade");
		setToolTipText("Ramo de Atividade");
		setBounds(100, 100, 400, 300);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setIcon(new ImageIcon(CadastroRamoAtividadeUI.class.getResource("/img/save.png")));
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(ramoAtividadeParaEdicao == null) {
						RamoAtividade ramoAtividade = new RamoAtividade();
						ramoAtividade.setNome(jtfNome.getText());
						ramoAtividade.setTaxaMinimaCredito(Double.parseDouble(jtfTxCredito.getText()));
						ramoAtividade.setTaxaMinimaDebito(Double.parseDouble(jtfDebito.getText()));
						
						ramoAtividadeControl.salvar(ramoAtividade);
						JOptionPane.showMessageDialog(null, "Ramo de Atividade Cadastrado com Sucesso!");
						
						jtfNome.setText("");
						jtfTxCredito.setText("");
						jtfDebito.setText("");
						
					} else {
						ramoAtividadeParaEdicao.setNome(jtfNome.getText());
						ramoAtividadeParaEdicao.setTaxaMinimaCredito(Double.parseDouble(jtfTxCredito.getText()));
						ramoAtividadeParaEdicao.setTaxaMinimaDebito(Double.parseDouble(jtfDebito.getText()));
						ramoAtividadeControl.editar(ramoAtividadeParaEdicao);
						ConsultaRamoAtividadeUI conRA = new ConsultaRamoAtividadeUI();
						getParent().add(conRA, 0);
						conRA.setVisible(true);
						
						dispose();
						JOptionPane.showMessageDialog(null, "Ramo de Atividade editado com sucesso!");
						
					}
				} catch (NumberFormatException e2) {
					JOptionPane.showMessageDialog(null, "Informe valores numéricos para os campos das taxas!");
				} catch (Exception e2){
					JOptionPane.showMessageDialog(null, e2.getMessage());
				}
			}
		});
		
		JButton btnNewButton = new JButton("Cancelar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jtfNome.setText("");
				jtfDebito.setText("");
				jtfTxCredito.setText("");
			}
		});
		btnNewButton.setIcon(new ImageIcon(CadastroRamoAtividadeUI.class.getResource("/img/cancel.png")));
		
		JButton btnNewButton_1 = new JButton("Fechar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_1.setIcon(new ImageIcon(CadastroRamoAtividadeUI.class.getResource("/img/close.png")));
		
		JPanel panel = new JPanel();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 349, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(25, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(18)
					.addComponent(btnSalvar, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
					.addGap(35)
					.addComponent(btnNewButton)
					.addGap(26)
					.addComponent(btnNewButton_1, GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
					.addGap(17))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(34)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnSalvar)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnNewButton_1)
							.addComponent(btnNewButton)))
					.addContainerGap(34, Short.MAX_VALUE))
		);
		
		JLabel lblRamoDeAtividade = new JLabel("Ramo de Atividade:");
		
		jtfNome = new JTextField();
		jtfNome.setColumns(10);
		
		JLabel lblNome = new JLabel("Nome:");
		
		jtfDebito = new JTextField();
		jtfDebito.setColumns(10);
		
		jtfTxCredito = new JTextField();
		jtfTxCredito.setColumns(10);
		
		JLabel lblTaxaMinimaDe = new JLabel("Taxa M\u00EDnima de Cr\u00E9dito:");
		
		JLabel lblTaxaMnmiaDe = new JLabel("Taxa M\u00EDnima de De\u00E9bito:");
		
		JLabel label = new JLabel("%");
		
		JLabel label_1 = new JLabel("%");
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNome, Alignment.TRAILING)
						.addComponent(lblTaxaMinimaDe, Alignment.TRAILING)
						.addComponent(lblTaxaMnmiaDe, Alignment.TRAILING))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(jtfTxCredito, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(jtfDebito, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(label)
						.addComponent(label_1))
					.addContainerGap(96, Short.MAX_VALUE))
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap(18, Short.MAX_VALUE)
					.addComponent(lblRamoDeAtividade)
					.addGap(237))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(146)
					.addComponent(jtfNome, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE)
					.addGap(47))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblRamoDeAtividade)
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNome)
						.addComponent(jtfNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTaxaMinimaDe)
						.addComponent(jtfTxCredito, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label, GroupLayout.PREFERRED_SIZE, 12, GroupLayout.PREFERRED_SIZE))
					.addGap(13)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTaxaMnmiaDe)
						.addComponent(jtfDebito, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_1))
					.addContainerGap(42, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		getContentPane().setLayout(groupLayout);

	}
	public RamoAtividade getRamoAtividadeParaEdicao() {
		return ramoAtividadeParaEdicao;
	}
	
	public void setRamoAtividadeParaEdicao(RamoAtividade ramoAtividadeParaEdicao) {
		this.ramoAtividadeParaEdicao = ramoAtividadeParaEdicao;
		preencherCamposParaEdicao();
	}

	public int getPosicaoParaEdicao() {
		return posicaoParaEdicao;
	}
	
	public void setPosicaoParaEdicao(int posicaoParaEdicao) {
		this.posicaoParaEdicao = posicaoParaEdicao;
	}
	
	public void preencherCamposParaEdicao() {
		if(ramoAtividadeParaEdicao != null) {
			jtfNome.setText(ramoAtividadeParaEdicao.getNome());
			jtfDebito.setText(Double.toString(ramoAtividadeParaEdicao.getTaxaMinimaDebito()));
			jtfTxCredito.setText(Double.toString(ramoAtividadeParaEdicao.getTaxaMinimaCredito()));
		}
	}
}
