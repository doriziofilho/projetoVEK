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

import org.omg.Messaging.SyncScopeHelper;

import com.cartao.controller.clienteControl;
import com.cartao.controller.concorrenteControl;
import com.cartao.dao.ClienteDao;
import com.cartao.dao.ConcorrenteDao;
import com.cartao.dao.RamoAtividadeDao;
import com.cartao.model.Cliente;
import com.cartao.model.ClienteTableModel;
import com.cartao.model.Concorrente;
import com.cartao.model.ConcorrenteTableModel;
import com.cartao.model.RamoAtividade;
import com.cartao.model.RamoAtividadeTableModel;

import javax.swing.UIManager;
import java.awt.Color;

public class ConsultaClienteUI extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField jtfPesquisa;
	private JTable jtListaCliente;
	private clienteControl clienteControl = new clienteControl();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsultaClienteUI frame = new ConsultaClienteUI();
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
	public ConsultaClienteUI() {
		setTitle("Consulta Cliente");
		setClosable(true);
		setBounds(100, 100, 700, 330);
		
		JPanel jpPesquisa = new JPanel();
		jpPesquisa.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Pesquisa Cliente", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		jtfPesquisa = new JTextField();
		jtfPesquisa.setColumns(10);
		
		JButton btnPesquisarAtualizar = new JButton("Pesquisar / Atualizar");
		btnPesquisarAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Cliente> filtro = new clienteControl().pesquisarClientePorNome(jtfPesquisa.getText());
				ClienteTableModel modelCliente = new ClienteTableModel(filtro);
				jtListaCliente.setModel(modelCliente);
				
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
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton btnNovo = new JButton("Novo Cliente");
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastroClienteUI cadCliente = new CadastroClienteUI();
				getParent().add(cadCliente, 0);
				cadCliente.setVisible(true);
			}
		});
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int linhaSelecionada = jtListaCliente.getSelectedRow();
					Cliente cliente = new ClienteTableModel(
							ClienteDao.obterInstancia().listarTodos()
							).get(linhaSelecionada);
					clienteControl.remover(cliente);
					
					JOptionPane.showMessageDialog(null, "Cliente excluído com sucesso!");
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Selecione um Cliente para excluir");
				}				
				ClienteTableModel modelCliente = new ClienteTableModel(clienteControl.listarTodos());
				jtListaCliente.setModel(modelCliente);
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
						
						int linhaSelecionada = jtListaCliente.getSelectedRow();
						Cliente cliente = new ClienteTableModel(
								ClienteDao.obterInstancia().listaClientes).get(linhaSelecionada);
						CadastroClienteUI cadCliente = new CadastroClienteUI();
						cadCliente.setClienteParaEdicao(cliente);
						getParent().add(cadCliente, 0);
						cadCliente.setVisible(true);
						dispose();
						
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, "Selecione um Cliente para editar.");
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
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 655, GroupLayout.PREFERRED_SIZE)
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
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
					.addGap(29)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnFechar)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnNovo)
							.addComponent(btnEditar))
						.addComponent(btnExcluir))
					.addGap(53))
		);
		
		jtListaCliente = new JTable();
		jtListaCliente.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		ClienteTableModel modelCli = new ClienteTableModel(
				new clienteControl().listarTodos()
				);
		jtListaCliente.setModel(modelCli);
		
		scrollPane.setViewportView(jtListaCliente);
		getContentPane().setLayout(groupLayout);

	}
	

}

