package view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.ComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import controller.ColaboradorController;
import model.Colaborador;
import view.lists.ColabComboBoxModel;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.List;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EtchedBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class CriarChamadoUI extends JInternalFrame {
	
	private ColabComboBoxModel colabComboBoxModel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CriarChamadoUI frame = new CriarChamadoUI();
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
	public CriarChamadoUI() {
		setClosable(true);
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		colabComboBoxModel = new ColabComboBoxModel();
		List<Colaborador> colaboradores = new ColaboradorController().listar();
		colaboradores.forEach(c -> {
			colabComboBoxModel.addColaboradores(c);
		});
		
		
		setTitle("Chamados");
		setBounds(100, 100, 762, 382);
		
		JPanel jpCriarChamado = new JPanel();
		jpCriarChamado.setBorder(new TitledBorder(null, "Criar Chamado", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSalvar.setBackground(new Color(220, 220, 220));
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnSalvar)
						.addComponent(jpCriarChamado, GroupLayout.PREFERRED_SIZE, 722, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(14, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(jpCriarChamado, GroupLayout.PREFERRED_SIZE, 278, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnSalvar)
					.addContainerGap(35, Short.MAX_VALUE))
		);
		
		JComboBox cbxColab = new JComboBox();
		cbxColab.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cbxColab.setModel(colabComboBoxModel);
		
		JLabel lblColab = new JLabel("Colaborador");
		lblColab.setLabelFor(cbxColab);
		lblColab.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GroupLayout gl_jpCriarChamado = new GroupLayout(jpCriarChamado);
		gl_jpCriarChamado.setHorizontalGroup(
			gl_jpCriarChamado.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_jpCriarChamado.createSequentialGroup()
					.addGap(18)
					.addComponent(lblColab)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(cbxColab, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(427, Short.MAX_VALUE))
		);
		gl_jpCriarChamado.setVerticalGroup(
			gl_jpCriarChamado.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_jpCriarChamado.createSequentialGroup()
					.addGroup(gl_jpCriarChamado.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblColab)
						.addComponent(cbxColab, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(244, Short.MAX_VALUE))
		);
		jpCriarChamado.setLayout(gl_jpCriarChamado);
		getContentPane().setLayout(groupLayout);

	}
}
