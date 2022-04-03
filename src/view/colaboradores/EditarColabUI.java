package view.colaboradores;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import controller.ColaboradorController;
import model.Colaborador;

import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditarColabUI extends JInternalFrame {
	
	private JTextField txtId;
	private JTextField txtNome;
	private JCheckBox chckbxHabilitado;
	private Colaborador colaborador;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditarColabUI frame = new EditarColabUI();
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
	public EditarColabUI() {
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		setClosable(true);
		setTitle("Colaboradores");
		setBounds(100, 100, 688, 192);
		
		JPanel jpEditarColab = new JPanel();
		jpEditarColab.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Editar ou Excluir Colaborador", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBackground(new Color(220, 220, 220));
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {		
				int confirmacao = JOptionPane.showConfirmDialog(null, "Excluir o colaborador " + colaborador.getNome() + "?");
				if (confirmacao == 0) {
					try {
						new ColaboradorController().excluir(colaborador.getId());
						dispose();
						JOptionPane.showMessageDialog(null, "Colaborador excluído.");
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, "Erro ao excluir o cadastro.");
					}
				}
			}
		});
		btnExcluir.setBackground(new Color(220, 220, 220));
		btnExcluir.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				colaborador.setNome(txtNome.getText());
				colaborador.setHabilitado(chckbxHabilitado.isSelected());
				
				try {
					new ColaboradorController().atualizar(colaborador);
					dispose();
					JOptionPane.showMessageDialog(null, "Cadastro atualizado.");
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Erro ao atualizar o cadastro.");
				}
			}
		});
		btnAtualizar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAtualizar.setBackground(new Color(220, 220, 220));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnAtualizar, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnExcluir, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnCancelar))
						.addComponent(jpEditarColab, GroupLayout.PREFERRED_SIZE, 649, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(13, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(jpEditarColab, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnCancelar)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnExcluir)
							.addComponent(btnAtualizar)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		JLabel lbld = new JLabel("Id: ");
		lbld.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		txtId = new JTextField();
		lbld.setLabelFor(txtId);
		txtId.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtId.setEditable(false);
		txtId.setColumns(10);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		txtNome = new JTextField();
		lblNome.setLabelFor(txtNome);
		txtNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtNome.setColumns(10);
		
		chckbxHabilitado = new JCheckBox("Habilitado");
		chckbxHabilitado.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GroupLayout gl_jpEditarColab = new GroupLayout(jpEditarColab);
		gl_jpEditarColab.setHorizontalGroup(
			gl_jpEditarColab.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_jpEditarColab.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_jpEditarColab.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_jpEditarColab.createSequentialGroup()
							.addComponent(lbld)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtId, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
							.addGap(31)
							.addComponent(lblNome)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtNome, GroupLayout.PREFERRED_SIZE, 411, GroupLayout.PREFERRED_SIZE))
						.addComponent(chckbxHabilitado))
					.addContainerGap(53, Short.MAX_VALUE))
		);
		gl_jpEditarColab.setVerticalGroup(
			gl_jpEditarColab.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_jpEditarColab.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_jpEditarColab.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbld)
						.addComponent(lblNome)
						.addComponent(txtNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(chckbxHabilitado)
					.addContainerGap(24, Short.MAX_VALUE))
		);
		jpEditarColab.setLayout(gl_jpEditarColab);
		getContentPane().setLayout(groupLayout);

	}
	
	public void setColabEdit(Colaborador colab) {
		this.colaborador = colab;
		preencherForm();
	}
	
	private void preencherForm() {
		if (colaborador != null) {
			txtId.setText(String.valueOf(colaborador.getId()));
			txtNome.setText(colaborador.getNome());
			if (colaborador.isHabilitado())
				chckbxHabilitado.setSelected(true);
			else 
				chckbxHabilitado.setSelected(false);
		}
	}
}
