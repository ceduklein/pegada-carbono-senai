package view.colaboradores;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.border.TitledBorder;

import controller.ColaboradorController;
import model.Colaborador;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.border.CompoundBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;

public class CadastroColabUI extends JInternalFrame {
	private JTextField txtNome;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroColabUI frame = new CadastroColabUI();
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
	public CadastroColabUI() {
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		setClosable(true);
		setTitle("Colaboradores");
		setBounds(100, 100, 641, 216);
		
		JPanel jpCadastroColab = new JPanel();
		jpCadastroColab.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Cadastrar Colaborador", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		JLabel jlNome = new JLabel("Nome:");
		jlNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		txtNome = new JTextField();
		txtNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtNome.setColumns(10);
		
		JCheckBox chckbxHabilitado = new JCheckBox("Habilitado");
		chckbxHabilitado.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GroupLayout gl_jpCadastroColab = new GroupLayout(jpCadastroColab);
		gl_jpCadastroColab.setHorizontalGroup(
			gl_jpCadastroColab.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_jpCadastroColab.createSequentialGroup()
					.addGap(18)
					.addComponent(jlNome, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtNome, GroupLayout.PREFERRED_SIZE, 359, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(chckbxHabilitado)
					.addContainerGap(40, Short.MAX_VALUE))
		);
		gl_jpCadastroColab.setVerticalGroup(
			gl_jpCadastroColab.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_jpCadastroColab.createSequentialGroup()
					.addGap(33)
					.addGroup(gl_jpCadastroColab.createParallelGroup(Alignment.BASELINE)
						.addComponent(jlNome)
						.addComponent(txtNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(chckbxHabilitado))
					.addContainerGap(310, Short.MAX_VALUE))
		);
		jpCadastroColab.setLayout(gl_jpCadastroColab);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Colaborador colab = new Colaborador();
					colab.setNome(txtNome.getText());
					colab.setHabilitado(chckbxHabilitado.isSelected());
					
					new ColaboradorController().salvar(colab);
					dispose();
					JOptionPane.showMessageDialog(null, "Cadastro efetuado com sucesso.");
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Erro ao cadastrar colaborador.");
				}
			}
		});
		btnSalvar.setBackground(new Color(220, 220, 220));
		btnSalvar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setForeground(new Color(255, 0, 0));
		btnCancelar.setBackground(new Color(220, 220, 220));
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(32)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(jpCadastroColab, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnCancelar)
							.addGap(17)
							.addComponent(btnSalvar, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)))
					.addGap(24))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(jpCadastroColab, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnSalvar)
						.addComponent(btnCancelar))
					.addContainerGap(22, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);

	}
}
