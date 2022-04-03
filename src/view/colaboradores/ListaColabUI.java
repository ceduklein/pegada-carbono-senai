package view.colaboradores;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EtchedBorder;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import controller.ColaboradorController;
import model.Colaborador;
import view.tables.ColabTableModel;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.ImageIcon;

public class ListaColabUI extends JInternalFrame {
	private JTable tblColaboradores;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListaColabUI frame = new ListaColabUI();
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
	public ListaColabUI() {
		setClosable(true);
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		setTitle("Colaboradores");
		setBounds(100, 100, 827, 348);
		
		JPanel jpListaColab = new JPanel();
		jpListaColab.setBorder(new TitledBorder(null, "Colaboradores Cadastrados", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(jpListaColab, GroupLayout.PREFERRED_SIZE, 801, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(12, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(jpListaColab, GroupLayout.PREFERRED_SIZE, 287, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(76, Short.MAX_VALUE))
		);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnCancelar.setBackground(new Color(220, 220, 220));
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Colaborador colab = new ColabTableModel(new ColaboradorController().listar()).get(tblColaboradores.getSelectedRow());
				
				int confirmacao = JOptionPane.showConfirmDialog(null, "Excluir o colaborador " + colab.getNome() + "?");
				if (confirmacao == 0) {
					try {
						new ColaboradorController().excluir(colab.getId());
						tblColaboradores.setModel(new ColabTableModel(new ColaboradorController().listar()));
						JOptionPane.showMessageDialog(null, "Colaborador excluído.");
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, "Erro ao excluir colaborador.");
					}
				}
			}
		});
		btnExcluir.setBackground(new Color(220, 220, 220));
		btnExcluir.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.setBackground(new Color(220, 220, 220));
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Colaborador colab = new ColabTableModel(new ColaboradorController().listar()).get(tblColaboradores.getSelectedRow());
				EditarColabUI editColab = new EditarColabUI();
				editColab.setColabEdit(colab);
				editColab.setVisible(true);
				getParent().add(editColab, 0);
			}
		});
		btnEditar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JButton btnAtualizar = new JButton("Atualizar Dados");
		btnAtualizar.setBackground(new Color(220, 220, 220));
		btnAtualizar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tblColaboradores.setModel(new ColabTableModel(new ColaboradorController().listar()));
			}
		});
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastroColabUI cadColab = new CadastroColabUI();
				cadColab.setVisible(true);
				getParent().add(cadColab, 0);
			}
		});
		btnCadastrar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnCadastrar.setBackground(new Color(220, 220, 220));
		GroupLayout gl_jpListaColab = new GroupLayout(jpListaColab);
		gl_jpListaColab.setHorizontalGroup(
			gl_jpListaColab.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_jpListaColab.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_jpListaColab.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 769, Short.MAX_VALUE)
						.addGroup(gl_jpListaColab.createSequentialGroup()
							.addComponent(btnAtualizar)
							.addPreferredGap(ComponentPlacement.RELATED, 274, Short.MAX_VALUE)
							.addComponent(btnCadastrar)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnEditar, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnExcluir, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnCancelar)))
					.addContainerGap())
		);
		gl_jpListaColab.setVerticalGroup(
			gl_jpListaColab.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_jpListaColab.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
					.addGap(18)
					.addGroup(gl_jpListaColab.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCancelar)
						.addComponent(btnExcluir)
						.addComponent(btnEditar)
						.addComponent(btnAtualizar)
						.addComponent(btnCadastrar))
					.addContainerGap())
		);
		
		tblColaboradores = new JTable();
		tblColaboradores.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tblColaboradores.setModel(new ColabTableModel(new ColaboradorController().listar()));
		scrollPane.setViewportView(tblColaboradores);
		jpListaColab.setLayout(gl_jpListaColab);
		getContentPane().setLayout(groupLayout);

	}
}
