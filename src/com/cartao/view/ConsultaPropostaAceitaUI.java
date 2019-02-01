package com.cartao.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.cartao.controller.propostaControl;
import com.cartao.io.FileTypeFilter;
import com.cartao.io.PropostaWriter;
import com.cartao.model.Proposta;
import com.cartao.model.PropostaTableModel;
import javax.swing.SwingConstants;

public class ConsultaPropostaAceitaUI extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField jtfDataIncial;
	private JTextField jtfDataFinal;
	private JTable jtListaProposta;
	private propostaControl propostaControl = new propostaControl();
	private Path arquivo = null; 
	
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
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel lblDataFinal = new JLabel("Data Final :");
		lblDataFinal.setHorizontalAlignment(SwingConstants.RIGHT);
		
		jtfDataFinal = new JTextField();
		jtfDataFinal.setColumns(10);
		
		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.setIcon(new ImageIcon(ConsultaPropostaAceitaUI.class.getResource("/img/magnifier.png")));
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				LocalDate dataInicial = LocalDate.parse(jtfDataIncial.getText(),dtf);
				LocalDate dataFinal = LocalDate.parse(jtfDataFinal.getText(),dtf);
				
			List<Proposta> filtro = new propostaControl().pesquisarPropostaPorData(dataInicial, dataFinal);
				PropostaTableModel modelProspota = new PropostaTableModel(filtro);
				jtListaProposta.setModel(modelProspota);
				
				
				
			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblDataFinal, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
						.addComponent(label, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE))
					.addGap(10)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(jtfDataIncial, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(jtfDataFinal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(31)
					.addComponent(btnPesquisar, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
					.addGap(25))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(19)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(jtfDataIncial, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel.createSequentialGroup()
									.addGap(3)
									.addComponent(label)))
							.addGap(11)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addGap(3)
									.addComponent(lblDataFinal))
								.addComponent(jtfDataFinal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(32)
							.addComponent(btnPesquisar)))
					.addContainerGap(23, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		
		JButton btnGerarCsv = new JButton("Gerar CSV");
		btnGerarCsv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				LocalDate dataInicial = LocalDate.parse(jtfDataIncial.getText(),dtf);
				LocalDate dataFinal = LocalDate.parse(jtfDataFinal.getText(),dtf);
				
				JFileChooser chooser = new JFileChooser(new File("."));
				chooser.setDialogTitle("Salvar Arquivo");
				chooser.setFileFilter(new FileTypeFilter(".csv", "CSV file"));
				
				chooser.showSaveDialog(null);
				File fi = chooser.getSelectedFile();

				propostaControl.exportarCSV(fi.getPath(), dataInicial, dataFinal);
						
					 JOptionPane.showMessageDialog(null, "Arquivo CSV gerado com sucesso!");
				
				
			}
		});
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
					.addContainerGap()
					.addGroup(gl_jpConsultaProposta.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_jpConsultaProposta.createSequentialGroup()
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 358, GroupLayout.PREFERRED_SIZE)
							.addGap(113)
							.addGroup(gl_jpConsultaProposta.createParallelGroup(Alignment.LEADING, false)
								.addComponent(btnFechar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnGerarCsv, GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)))
						.addComponent(jspProposta, GroupLayout.PREFERRED_SIZE, 735, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(13, Short.MAX_VALUE))
		);
		gl_jpConsultaProposta.setVerticalGroup(
			gl_jpConsultaProposta.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_jpConsultaProposta.createSequentialGroup()
					.addGroup(gl_jpConsultaProposta.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_jpConsultaProposta.createSequentialGroup()
							.addGap(30)
							.addComponent(btnGerarCsv)
							.addGap(18)
							.addComponent(btnFechar))
						.addGroup(gl_jpConsultaProposta.createSequentialGroup()
							.addGap(19)
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(27)
					.addComponent(jspProposta, GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
					.addGap(19))
		);
		
		jtListaProposta = new JTable();
		jtListaProposta.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		PropostaTableModel modelProposta = new PropostaTableModel(
					new propostaControl().listarTodos()
				);
		jtListaProposta.setModel(modelProposta);
		jspProposta.setViewportView(jtListaProposta);
		jpConsultaProposta.setLayout(gl_jpConsultaProposta);
		getContentPane().setLayout(groupLayout);

	}
}
