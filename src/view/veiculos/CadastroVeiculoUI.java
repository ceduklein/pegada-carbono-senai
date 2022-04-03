package view.veiculos;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EtchedBorder;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import controller.VeiculoController;
import model.Veiculo;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class CadastroVeiculoUI extends JInternalFrame {
	private JTextField txtModelo;
	private JTextField txtPlaca;
	private JTextField txtKmLitro;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroVeiculoUI frame = new CadastroVeiculoUI();
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
	public CadastroVeiculoUI() {
		setClosable(true);
		setTitle("Ve\u00EDculos");
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		setBounds(100, 100, 533, 225);
		
		JPanel lblConsumo = new JPanel();
		lblConsumo.setBorder(new TitledBorder(null, "Cadastrar Ve\u00EDculo", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setBackground(new Color(220, 220, 220));
		btnSalvar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Veiculo v = new Veiculo();
					v.setModelo(txtModelo.getText());
					v.setPlaca(txtPlaca.getText());
					v.setKmLitro(Double.parseDouble(txtKmLitro.getText()));
					
					new VeiculoController().salvar(v);
					dispose();
					JOptionPane.showMessageDialog(null, "Cadastro efetuado com sucesso.");
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Erro ao cadastrar o veículo.");
				}
			}
		});
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setForeground(new Color(0, 0, 0));
		btnCancelar.setBackground(new Color(220, 220, 220));
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(325)
							.addComponent(btnCancelar)
							.addGap(18)
							.addComponent(btnSalvar, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblConsumo, GroupLayout.PREFERRED_SIZE, 503, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(16, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblConsumo, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCancelar)
						.addComponent(btnSalvar))
					.addContainerGap(13, Short.MAX_VALUE))
		);
		
		JLabel lblModelo = new JLabel("Modelo:");
		lblModelo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		txtModelo = new JTextField();
		lblModelo.setLabelFor(txtModelo);
		txtModelo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtModelo.setColumns(10);
		
		JLabel lblPlaca = new JLabel("Placa:");
		lblPlaca.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		txtPlaca = new JTextField();
		txtPlaca.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPlaca.setLabelFor(txtPlaca);
		txtPlaca.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Consumo:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		txtKmLitro = new JTextField();
		lblNewLabel.setLabelFor(txtKmLitro);
		txtKmLitro.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtKmLitro.setColumns(10);
		
		JLabel lblKmLitro = new JLabel("km/litro");
		lblKmLitro.setLabelFor(txtKmLitro);
		lblKmLitro.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GroupLayout gl_lblConsumo = new GroupLayout(lblConsumo);
		gl_lblConsumo.setHorizontalGroup(
			gl_lblConsumo.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_lblConsumo.createSequentialGroup()
					.addGap(20)
					.addGroup(gl_lblConsumo.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_lblConsumo.createSequentialGroup()
							.addComponent(lblModelo)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtModelo))
						.addGroup(gl_lblConsumo.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblPlaca)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtPlaca, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)
							.addGap(27)
							.addComponent(lblNewLabel)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtKmLitro, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblKmLitro)))
					.addContainerGap(155, Short.MAX_VALUE))
		);
		gl_lblConsumo.setVerticalGroup(
			gl_lblConsumo.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_lblConsumo.createSequentialGroup()
					.addGap(19)
					.addGroup(gl_lblConsumo.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblModelo)
						.addComponent(txtModelo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(35)
					.addGroup(gl_lblConsumo.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(txtKmLitro, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblKmLitro)
						.addComponent(lblPlaca)
						.addComponent(txtPlaca, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(190, Short.MAX_VALUE))
		);
		lblConsumo.setLayout(gl_lblConsumo);
		getContentPane().setLayout(groupLayout);

	}
}
