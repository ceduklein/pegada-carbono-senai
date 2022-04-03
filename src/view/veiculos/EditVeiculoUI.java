package view.veiculos;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.border.EtchedBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import controller.VeiculoController;
import model.Veiculo;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditVeiculoUI extends JInternalFrame {
	private JTextField txtId;
	private JTextField txtModelo;
	private JTextField txtPlaca;
	private JTextField txtKmLitro;
	private JCheckBox chckbxDisponivel;
	private Veiculo veiculo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditVeiculoUI frame = new EditVeiculoUI();
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
	public EditVeiculoUI() {
		VeiculoController veiculoController = new VeiculoController();
		
		setClosable(true);
		setTitle("Ve\u00EDculos");
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		setBounds(100, 100, 605, 209);
		
		JPanel jpEditVeiculo = new JPanel();
		jpEditVeiculo.setBorder(new TitledBorder(null, "Editar ou Excluir Ve\u00EDculo", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCancelar.setBackground(new Color(220, 220, 220));
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setBackground(new Color(220, 220, 220));
		btnExcluir.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int confirmacao = JOptionPane.showConfirmDialog(null, "Excluir o veículo " + veiculo.getModelo() 
					+ " - Placa: " + veiculo.getPlaca() + "?");
				
				if (confirmacao == 0) {
					try {
						veiculoController.excluir(veiculo.getId());
						dispose();
						JOptionPane.showMessageDialog(null, "Veículo excluído.");
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, "Erro ao excluir o cadastro do véiculo.");
					}
				}
			}
		});
		
		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				veiculo.setModelo(txtModelo.getText());
				veiculo.setPlaca(txtPlaca.getText());
				veiculo.setKmLitro(Double.parseDouble(txtKmLitro.getText()));
				veiculo.setDisponivel(chckbxDisponivel.isSelected());
				
				try {
					veiculoController.atualizar(veiculo);
					dispose();
					JOptionPane.showMessageDialog(null, "Cadastro do veículo atualizado.");
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Erro ao atualizar o cadastro.");
				}
			}
		});
		btnAtualizar.setBackground(new Color(220, 220, 220));
		btnAtualizar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(jpEditVeiculo, GroupLayout.PREFERRED_SIZE, 581, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(311, Short.MAX_VALUE)
					.addComponent(btnAtualizar)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnExcluir, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnCancelar)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(jpEditVeiculo, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnCancelar)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnExcluir)
							.addComponent(btnAtualizar)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		JLabel lblId = new JLabel("Id:");
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		txtId = new JTextField();
		lblId.setLabelFor(txtId);
		txtId.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtId.setEditable(false);
		txtId.setColumns(10);
		
		JLabel lblModelo = new JLabel("Modelo:");
		lblModelo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		txtModelo = new JTextField();
		lblModelo.setLabelFor(txtModelo);
		txtModelo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtModelo.setColumns(10);
		
		chckbxDisponivel = new JCheckBox("Dispon\u00EDvel");
		chckbxDisponivel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblPlaca = new JLabel("Placa:");
		lblPlaca.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		txtPlaca = new JTextField();
		lblPlaca.setLabelFor(txtPlaca);
		txtPlaca.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtPlaca.setColumns(10);
		
		JLabel lblConsumo = new JLabel("Consumo:");
		lblConsumo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		txtKmLitro = new JTextField();
		txtKmLitro.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtKmLitro.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("km/litro");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GroupLayout gl_jpEditVeiculo = new GroupLayout(jpEditVeiculo);
		gl_jpEditVeiculo.setHorizontalGroup(
			gl_jpEditVeiculo.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_jpEditVeiculo.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_jpEditVeiculo.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_jpEditVeiculo.createSequentialGroup()
							.addComponent(lblId)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtId, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
							.addGap(39)
							.addComponent(lblModelo))
						.addGroup(gl_jpEditVeiculo.createSequentialGroup()
							.addComponent(lblPlaca)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtPlaca)))
					.addGroup(gl_jpEditVeiculo.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_jpEditVeiculo.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(txtModelo, GroupLayout.PREFERRED_SIZE, 237, GroupLayout.PREFERRED_SIZE)
							.addGap(38)
							.addComponent(chckbxDisponivel))
						.addGroup(gl_jpEditVeiculo.createSequentialGroup()
							.addGap(42)
							.addComponent(lblConsumo)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtKmLitro, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblNewLabel)))
					.addContainerGap(11, Short.MAX_VALUE))
		);
		gl_jpEditVeiculo.setVerticalGroup(
			gl_jpEditVeiculo.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_jpEditVeiculo.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_jpEditVeiculo.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblId)
						.addComponent(txtId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(chckbxDisponivel)
						.addComponent(txtModelo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblModelo))
					.addGap(31)
					.addGroup(gl_jpEditVeiculo.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPlaca)
						.addComponent(txtPlaca, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblConsumo)
						.addComponent(txtKmLitro, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel))
					.addContainerGap(109, Short.MAX_VALUE))
		);
		jpEditVeiculo.setLayout(gl_jpEditVeiculo);
		getContentPane().setLayout(groupLayout);

	}
	
	public void setVeiculoEdit(Veiculo veiculo) {
		this.veiculo = veiculo;
		preencherForm();
	}
	
	private void preencherForm() {
		txtId.setText(String.valueOf(veiculo.getId()));
		txtModelo.setText(veiculo.getModelo());
		txtPlaca.setText(veiculo.getPlaca());
		txtKmLitro.setText(veiculo.getKmLitro().toString());
		chckbxDisponivel.setSelected(veiculo.isDisponivel());
	}
}
