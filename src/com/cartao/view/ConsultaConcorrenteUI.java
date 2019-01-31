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
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.cartao.controller.concorrenteControl;
import com.cartao.dao.ConcorrenteDao;
import com.cartao.dao.RamoAtividadeDao;
import com.cartao.model.Concorrente;
import com.cartao.model.ConcorrenteTableModel;
import com.cartao.model.RamoAtividade;
import com.cartao.model.RamoAtividadeTableModel;

public class ConsultaConcorrenteUI extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField jtfPesquisa;
	private JTable jtListaConcorrente;
	private concorrenteControl concorrenteControl = new concorrenteControl();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsultaConcorrenteUI frame = new ConsultaConcorrenteUI();
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
	public ConsultaConcorrenteUI() {
		setTitle("Consulta Concorrente");
		setClosable(true);
		setBounds(100, 100, 700, 330);
		
		JPanel jpPesquisa = new JPanel();
		jpPesquisa.setBorder(new TitledBorder(null, "Pesquisa Concorrente", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		jtfPesquisa = new JTextField();
		jtfPesquisa.setColumns(10);
		
		JButton btnPesquisarAtualizar = new JButton("Pesquisar / Atualizar");
		btnPesquisarAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Concorrente> filtro = new concorrenteControl().pesquisarConcorrentePorNome(jtfPesquisa.getText());
				ConcorrenteTableModel modelConcorrrente = new ConcorrenteTableModel(filtro);
				jtListaConcorrente.setModel(modelConcorrrente);
				
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
		
		JScrollPane jspTabelaConcorrente = new JScrollPane();
		
		JButton btnNovo = new JButton("Novo Concorrente");
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastroConcorrenteUI cadConcorrente = new CadastroConcorrenteUI();
				getParent().add(cadConcorrente, 0);
				cadConcorrente.setVisible(true);
			}
		});
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int linhaSelecionada = jtListaConcorrente.getSelectedRow();
					Concorrente concorrente = new ConcorrenteTableModel(
							ConcorrenteDao.obterInstancia().listaConcorrentes
							).get(linhaSelecionada);
					concorrenteControl.remover(concorrente);
					
					JOptionPane.showMessageDialog(null, "Concorrente excluído com sucesso!");
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Selecione um Concorrente para excluir");
				}				
				ConcorrenteTableModel modelConcorrente = new ConcorrenteTableModel(concorrenteControl.listarTodos());
				jtListaConcorrente.setModel(modelConcorrente);
				jtfPesquisa.requestFocusInWindow();
				
			}
		});
		
		JButton btnFechar = new JButton("Fechar");
		btnFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					try {
						
						int linhaSelecionada = jtListaConcorrente.getSelectedRow();
						Concorrente concorrente = new ConcorrenteTableModel(
								ConcorrenteDao.obterInstancia().listaConcorrentes).get(linhaSelecionada);
						CadastroConcorrenteUI cadConcorrente = new CadastroConcorrenteUI();
						cadConcorrente.setConcorrenteParaEdicao(concorrente);
						getParent().add(cadConcorrente, 0);
						cadConcorrente.setVisible(true);
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
							.addComponent(jspTabelaConcorrente, GroupLayout.PREFERRED_SIZE, 655, GroupLayout.PREFERRED_SIZE)
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
					.addComponent(jspTabelaConcorrente, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
					.addGap(29)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnFechar)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnNovo)
							.addComponent(btnEditar))
						.addComponent(btnExcluir))
					.addGap(53))
		);
		
		jtListaConcorrente = new JTable();
		jtListaConcorrente.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		ConcorrenteTableModel modelCon = new ConcorrenteTableModel(
				new concorrenteControl().listarTodos()
				);
		jtListaConcorrente.setModel(modelCon);

		jspTabelaConcorrente.setViewportView(jtListaConcorrente);
		getContentPane().setLayout(groupLayout);

	}
	

}

