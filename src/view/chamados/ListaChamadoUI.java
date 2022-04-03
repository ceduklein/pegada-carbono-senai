package view.chamados;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.border.EtchedBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;

import controller.ChamadoController;
import model.Chamado;
import view.tables.ChamadoTableModel;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class ListaChamadoUI extends JInternalFrame {
	private JTable tblChamado;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListaChamadoUI frame = new ListaChamadoUI();
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
	public ListaChamadoUI() {
		ChamadoController chamadoController = new ChamadoController();
		
		setTitle("Chamados");
		setClosable(true);
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		setBounds(100, 100, 1032, 358);
		
		JPanel jpListaChamado = new JPanel();
		jpListaChamado.setBorder(new TitledBorder(null, "Relat\u00F3rio de Chamados", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBackground(new Color(220, 220, 220));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int confirmacao = JOptionPane.showConfirmDialog(null, "Excluir o chamado selecionado?");
				if (confirmacao == 0) {
					int row = tblChamado.getSelectedRow();
					int id = Integer.parseInt(tblChamado.getValueAt(row, 0).toString());
					try {
						Chamado c = chamadoController.listById(id);
						chamadoController.excluir(c);
						JOptionPane.showMessageDialog(null, "Chamado excluído.");
						tblChamado.setModel(new ChamadoTableModel(chamadoController.listar()));
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, "Erro ao excluir o chamado selecionado.");
					}
				}	
			}
		});
		btnExcluir.setBackground(new Color(220, 220, 220));
		btnExcluir.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JButton btnEncerrar = new JButton("Encerrar");
		btnEncerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int confirmacao = JOptionPane.showConfirmDialog(null, "Encerrar o chamado selecionado?");
				if (confirmacao == 0) {
					int row = tblChamado.getSelectedRow();
					int id = Integer.parseInt(tblChamado.getValueAt(row, 0).toString());
					try {
						Chamado c = chamadoController.listById(id);
						c.setConcluido(true);
						chamadoController.encerrarChamado(c);
						JOptionPane.showMessageDialog(null, "Chamado encerrado.");
						tblChamado.setModel(new ChamadoTableModel(chamadoController.listar()));
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, "Erro ao encerrar o chamado selecionado.");
					}
				}	
			}
		});
		btnEncerrar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnEncerrar.setBackground(new Color(220, 220, 220));
		
		JButton btnCriar = new JButton("Criar Chamado");
		btnCriar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CriarChamadoUI criarChamado = new CriarChamadoUI();
				criarChamado.setVisible(true);
				getParent().add(criarChamado, 0);
			}
		});
		btnCriar.setBackground(new Color(220, 220, 220));
		btnCriar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JButton btnAtualizar = new JButton("Atualizar Dados");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tblChamado.setModel(new ChamadoTableModel(chamadoController.listar()));
			}
		});
		btnAtualizar.setBackground(new Color(220, 220, 220));
		btnAtualizar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = tblChamado.getSelectedRow();
				int id = Integer.parseInt(tblChamado.getValueAt(row, 0).toString());
				
				EditChamadoUI editChamado = new EditChamadoUI();
				try {
					Chamado c = chamadoController.listById(id);
					editChamado.setChamadoEdit(c);
					editChamado.setVisible(true);
					getParent().add(editChamado, 0);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Erro ao selecionar o cahamado para edição.");
				}
			}
		});
		btnEditar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnEditar.setBackground(new Color(220, 220, 220));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(jpListaChamado, GroupLayout.DEFAULT_SIZE, 1008, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnAtualizar)
							.addPreferredGap(ComponentPlacement.RELATED, 401, Short.MAX_VALUE)
							.addComponent(btnCriar)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnEditar, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnEncerrar, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnExcluir, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnCancelar)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(jpListaChamado, GroupLayout.PREFERRED_SIZE, 263, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCancelar)
						.addComponent(btnExcluir)
						.addComponent(btnAtualizar)
						.addComponent(btnEncerrar)
						.addComponent(btnEditar)
						.addComponent(btnCriar))
					.addContainerGap(24, Short.MAX_VALUE))
		);
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_jpListaChamado = new GroupLayout(jpListaChamado);
		gl_jpListaChamado.setHorizontalGroup(
			gl_jpListaChamado.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_jpListaChamado.createSequentialGroup()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 870, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_jpListaChamado.setVerticalGroup(
			gl_jpListaChamado.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_jpListaChamado.createSequentialGroup()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 235, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(16, Short.MAX_VALUE))
		);
		
		DefaultTableCellRenderer centro = new DefaultTableCellRenderer();
		centro.setHorizontalAlignment(SwingConstants.CENTER);
		
		DefaultTableCellRenderer esquerda = new DefaultTableCellRenderer();
		esquerda.setHorizontalAlignment(SwingConstants.LEFT);
		
		tblChamado = new JTable();
		tblChamado.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tblChamado.setModel(new ChamadoTableModel(chamadoController.listar()));
		tblChamado.getColumnModel().getColumn(0).setPreferredWidth(8);
		tblChamado.getColumnModel().getColumn(0).setCellRenderer(centro);
		tblChamado.getColumnModel().getColumn(1).setPreferredWidth(40);
		tblChamado.getColumnModel().getColumn(1).setCellRenderer(centro);;
		tblChamado.getColumnModel().getColumn(2).setPreferredWidth(50);
		tblChamado.getColumnModel().getColumn(2).setCellRenderer(centro);
		tblChamado.getColumnModel().getColumn(3).setPreferredWidth(310);
		tblChamado.getColumnModel().getColumn(3).setCellRenderer(esquerda);
		tblChamado.getColumnModel().getColumn(4).setPreferredWidth(15);
		tblChamado.getColumnModel().getColumn(4).setCellRenderer(centro);
		tblChamado.getColumnModel().getColumn(5).setPreferredWidth(80);
		tblChamado.getColumnModel().getColumn(5).setCellRenderer(centro);
		tblChamado.getColumnModel().getColumn(6).setPreferredWidth(120);
		tblChamado.getColumnModel().getColumn(6).setCellRenderer(esquerda);
		tblChamado.getColumnModel().getColumn(7).setPreferredWidth(45);
		tblChamado.getColumnModel().getColumn(7).setCellRenderer(esquerda);
		scrollPane.setViewportView(tblChamado);
		jpListaChamado.setLayout(gl_jpListaChamado);
		getContentPane().setLayout(groupLayout);

	}
}
