package view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.border.EtchedBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import controller.VeiculoController;
import model.Veiculo;
import view.tables.VeiculoTableModel;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.LayoutStyle.ComponentPlacement;

public class ListaVeiculoUI extends JInternalFrame {
	private JTable tblVeiculos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListaVeiculoUI frame = new ListaVeiculoUI();
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
	public ListaVeiculoUI() {
		setClosable(true);
		setTitle("Ve\u00EDculos");
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		setBounds(100, 100, 900, 385);
		
		JPanel jpListaVeiculo = new JPanel();
		jpListaVeiculo.setBorder(new TitledBorder(null, "Ve\u00EDculos Cadastrados", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(jpListaVeiculo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(11, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(jpListaVeiculo, GroupLayout.PREFERRED_SIZE, 333, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(31, Short.MAX_VALUE))
		);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBackground(new Color(220, 220, 220));
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Veiculo veiculo = new VeiculoTableModel(new VeiculoController().listar()).get(tblVeiculos.getSelectedRow());
				
				int confirmacao = JOptionPane.showConfirmDialog(null, "Deseja excluir o veículo " + veiculo.getModelo()
					+ " - Placa: " + veiculo.getPlaca() + "?");
				
				if (confirmacao == 0) {
					try {
						new VeiculoController().excluir(veiculo.getId());
						tblVeiculos.setModel(new VeiculoTableModel(new VeiculoController().listar()));
						JOptionPane.showMessageDialog(null, "Veículo excluído.");
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, "Erro ao excluir veículo.");
					}
				}
			}
		});
		btnExcluir.setBackground(new Color(220, 220, 220));
		btnExcluir.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.setBackground(new Color(220, 220, 220));
		btnEditar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GroupLayout gl_jpListaVeiculo = new GroupLayout(jpListaVeiculo);
		gl_jpListaVeiculo.setHorizontalGroup(
			gl_jpListaVeiculo.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_jpListaVeiculo.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_jpListaVeiculo.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_jpListaVeiculo.createSequentialGroup()
							.addComponent(btnEditar, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnExcluir, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnCancelar))
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 843, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_jpListaVeiculo.setVerticalGroup(
			gl_jpListaVeiculo.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_jpListaVeiculo.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 258, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_jpListaVeiculo.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCancelar)
						.addComponent(btnExcluir)
						.addComponent(btnEditar))
					.addContainerGap(16, Short.MAX_VALUE))
		);
		
		tblVeiculos = new JTable();
		tblVeiculos.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tblVeiculos.setModel(new VeiculoTableModel(new VeiculoController().listar()));
		scrollPane.setViewportView(tblVeiculos);
		jpListaVeiculo.setLayout(gl_jpListaVeiculo);
		getContentPane().setLayout(groupLayout);

	}
}
