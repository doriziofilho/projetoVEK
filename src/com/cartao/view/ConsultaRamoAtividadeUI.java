package com.cartao.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.cartao.controller.ramoAtividadeControl;
import com.cartao.dao.RamoAtividadeDao;
import com.cartao.model.RamoAtividade;
import com.cartao.model.RamoAtividadeTableModel;
import javax.swing.ImageIcon;


public class ConsultaRamoAtividadeUI extends JInternalFrame {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField jtfPesquisa;
	private JTable jtListaRamoAtividade;
	private ramoAtividadeControl ramoAtividadeControl = new ramoAtividadeControl();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsultaRamoAtividadeUI frame = new ConsultaRamoAtividadeUI();
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
	public ConsultaRamoAtividadeUI() {
		setTitle("Consulta Ramo Atividade");
		setClosable(true);
		setBounds(100, 100, 700, 330);
		
		JPanel jpPesquisa = new JPanel();
		jpPesquisa.setBorder(new TitledBorder(null, "Pesquisa Ramo Atividade", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		jtfPesquisa = new JTextField();
		jtfPesquisa.setColumns(10);
		
		JButton btnPesquisarAtualizar = new JButton("Pesquisar / Atualizar");
		btnPesquisarAtualizar.setIcon(new ImageIcon(ConsultaRamoAtividadeUI.class.getResource("/img/refresh.png")));
		btnPesquisarAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<RamoAtividade> filtro = new ramoAtividadeControl().pesquisarRAporNome(jtfPesquisa.getText());
				RamoAtividadeTableModel modelRA = new RamoAtividadeTableModel(filtro);
				jtListaRamoAtividade.setModel(modelRA);
				
			}
		});
		GroupLayout gl_jpPesquisa = new GroupLayout(jpPesquisa);
		gl_jpPesquisa.setHorizontalGroup(
			gl_jpPesquisa.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_jpPesquisa.createSequentialGroup()
					.addGap(20)
					.addComponent(jtfPesquisa, GroupLayout.PREFERRED_SIZE, 402, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
					.addComponent(btnPesquisarAtualizar)
					.addGap(56))
		);
		gl_jpPesquisa.setVerticalGroup(
			gl_jpPesquisa.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_jpPesquisa.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_jpPesquisa.createParallelGroup(Alignment.BASELINE)
						.addComponent(jtfPesquisa, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnPesquisarAtualizar))
					.addContainerGap(42, Short.MAX_VALUE))
		);
		jpPesquisa.setLayout(gl_jpPesquisa);
		
		JScrollPane jspTabelaRamoAtividade = new JScrollPane();
		
		JButton btnNovo = new JButton("Novo Ramo Atividade");
		btnNovo.setIcon(new ImageIcon(ConsultaRamoAtividadeUI.class.getResource("/img/plus.png")));
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastroRamoAtividadeUI cadRa = new CadastroRamoAtividadeUI();
				getParent().add(cadRa, 0);
				cadRa.setVisible(true);
			}
		});
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setIcon(new ImageIcon(ConsultaRamoAtividadeUI.class.getResource("/img/cross.png")));
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int linhaSelecionada = jtListaRamoAtividade.getSelectedRow();
					RamoAtividade ramoAtividade = new RamoAtividadeTableModel(
							RamoAtividadeDao.obterInstancia().listaRamoAtividades
							).get(linhaSelecionada);
					ramoAtividadeControl.remover(ramoAtividade);
					
					JOptionPane.showMessageDialog(null, "Ramo de Atividade excluído com sucesso!");
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Selecione um Ramo de Atividade para excluir");
				}				
				RamoAtividadeTableModel modelRa = new RamoAtividadeTableModel(ramoAtividadeControl.listarRamoAtividade());
				jtListaRamoAtividade.setModel(modelRa);
				jtfPesquisa.requestFocusInWindow();
				
			}
		});
		
		JButton btnFechar = new JButton("Fechar");
		btnFechar.setIcon(new ImageIcon(ConsultaRamoAtividadeUI.class.getResource("/img/close.png")));
		btnFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.setIcon(new ImageIcon(ConsultaRamoAtividadeUI.class.getResource("/img/edit.png")));
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					try {
						
						int linhaSelecionada = jtListaRamoAtividade.getSelectedRow();
						System.out.println(linhaSelecionada + "linha");
						RamoAtividade ramoAtividade = new RamoAtividadeTableModel(
								RamoAtividadeDao.obterInstancia().listaRamoAtividades).get(linhaSelecionada);
						CadastroRamoAtividadeUI cadRA = new CadastroRamoAtividadeUI();
						cadRA.setRamoAtividadeParaEdicao(ramoAtividade);
						getParent().add(cadRA, 0);
						cadRA.setVisible(true);
						dispose();
						jtfPesquisa.requestFocusInWindow();
						
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, "Selecione um Ramo de Atividade para editar");
					}
			}
		});
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(jpPesquisa, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(18, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(jspTabelaRamoAtividade, GroupLayout.PREFERRED_SIZE, 655, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(19, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnNovo)
							.addGap(55)
							.addComponent(btnEditar)
							.addGap(97)
							.addComponent(btnExcluir)
							.addPreferredGap(ComponentPlacement.RELATED, 116, Short.MAX_VALUE)
							.addComponent(btnFechar)
							.addGap(54))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(15)
					.addComponent(jpPesquisa, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(jspTabelaRamoAtividade, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
					.addGap(29)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnFechar)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnNovo)
							.addComponent(btnEditar))
						.addComponent(btnExcluir))
					.addGap(53))
		);
		
		jtListaRamoAtividade = new JTable();
		jtListaRamoAtividade.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		RamoAtividadeTableModel modelRa = new RamoAtividadeTableModel(
				new ramoAtividadeControl().listarRamoAtividade()
				);
		jtListaRamoAtividade.setModel(modelRa);
		
		jspTabelaRamoAtividade.setViewportView(jtListaRamoAtividade);
		getContentPane().setLayout(groupLayout);

	}
	

}
