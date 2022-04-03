package view.veiculos;

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
		VeiculoController veiculoController = new VeiculoController();
		
		setClosable(true);
		setTitle("Ve\u00EDculos");
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		setBounds(100, 100, 900, 339);
		
		JPanel jpListaVeiculo = new JPanel();
		jpListaVeiculo.setBorder(new TitledBorder(null, "Ve\u00EDculos Cadastrados", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(jpListaVeiculo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(jpListaVeiculo, GroupLayout.PREFERRED_SIZE, 291, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(54, Short.MAX_VALUE))
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
				Veiculo veiculo = new VeiculoTableModel(veiculoController.listar()).get(tblVeiculos.getSelectedRow());
				
				int confirmacao = JOptionPane.showConfirmDialog(null, "Deseja excluir o veículo " + veiculo.getModelo()
					+ " - Placa: " + veiculo.getPlaca() + "?");
				
				if (confirmacao == 0) {
					try {
						veiculoController.excluir(veiculo.getId());
						tblVeiculos.setModel(new VeiculoTableModel(veiculoController.listar()));
						JOptionPane.showMessageDialog(null, "Veículo excluído.");
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, "Erro ao excluir o cadastro do veículo.");
					}
				}
			}
		});
		btnExcluir.setBackground(new Color(220, 220, 220));
		btnExcluir.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Veiculo v = new VeiculoTableModel(veiculoController.listar()).get(tblVeiculos.getSelectedRow());
				EditVeiculoUI editVeiculo = new EditVeiculoUI();
				editVeiculo.setVeiculoEdit(v);
				editVeiculo.setVisible(true);
				getContentPane().add(editVeiculo, 0);
			}
		});
		btnEditar.setBackground(new Color(220, 220, 220));
		btnEditar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JButton btnAtualizar = new JButton("Atualizar Dados");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tblVeiculos.setModel(new VeiculoTableModel(veiculoController.listar()));
			}
		});
		btnAtualizar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAtualizar.setBackground(new Color(220, 220, 220));
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastroVeiculoUI cadVeiculo = new CadastroVeiculoUI();
				cadVeiculo.setVisible(true);
				getParent().add(cadVeiculo, 0);
			}
		});
		btnCadastrar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCadastrar.setBackground(new Color(220, 220, 220));
		GroupLayout gl_jpListaVeiculo = new GroupLayout(jpListaVeiculo);
		gl_jpListaVeiculo.setHorizontalGroup(
			gl_jpListaVeiculo.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_jpListaVeiculo.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_jpListaVeiculo.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 856, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_jpListaVeiculo.createSequentialGroup()
							.addComponent(btnAtualizar)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnCadastrar)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnEditar, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnExcluir, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnCancelar)))
					.addContainerGap())
		);
		gl_jpListaVeiculo.setVerticalGroup(
			gl_jpListaVeiculo.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_jpListaVeiculo.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 211, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_jpListaVeiculo.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCancelar)
						.addComponent(btnExcluir)
						.addComponent(btnEditar)
						.addComponent(btnAtualizar)
						.addComponent(btnCadastrar))
					.addContainerGap(63, Short.MAX_VALUE))
		);
		
		tblVeiculos = new JTable();
		tblVeiculos.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tblVeiculos.setModel(new VeiculoTableModel(veiculoController.listar()));
		scrollPane.setViewportView(tblVeiculos);
		jpListaVeiculo.setLayout(gl_jpListaVeiculo);
		getContentPane().setLayout(groupLayout);

	}
}
