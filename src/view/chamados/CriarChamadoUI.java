package view.chamados;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.border.EtchedBorder;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import controller.ChamadoController;
import controller.ColaboradorController;
import controller.VeiculoController;
import model.Chamado;
import model.Colaborador;
import model.Veiculo;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.awt.event.ActionEvent;

public class CriarChamadoUI extends JInternalFrame {
	private JTextField txtData;
	private JTextField txtDistancia;
	private JTextField txtEndereco;
	
	JComboBox<Colaborador> cmbxColab = new JComboBox();
	JComboBox<Veiculo> cmbxVeiculo = new JComboBox();
	
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
		ColaboradorController colabController = new ColaboradorController();
		VeiculoController veiculoController = new VeiculoController();
		ChamadoController chamadoController = new ChamadoController();
		
		setTitle("Chamados");
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		setBounds(100, 100, 542, 280);
		
		DefaultComboBoxModel<Colaborador> modelColab = new DefaultComboBoxModel<Colaborador>();
		List<Colaborador> colaboradores = colabController.listar();
		colaboradores.forEach(c -> modelColab.addElement(c));
		cmbxColab.setModel(modelColab);
		
		DefaultComboBoxModel<Veiculo> modelVeiculo = new DefaultComboBoxModel<Veiculo>();
		List<Veiculo> veiculos = veiculoController.listar();
		veiculos.forEach(v -> modelVeiculo.addElement(v));
		cmbxVeiculo.setModel(modelVeiculo);
		
		
		JPanel jpCriarChamado = new JPanel();
		jpCriarChamado.setBorder(new TitledBorder(null, "Criar Chamado", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCancelar.setBackground(new Color(220, 220, 220));
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Colaborador colab = (Colaborador) cmbxColab.getSelectedItem();
				Veiculo v = (Veiculo) cmbxVeiculo.getSelectedItem();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				try {
					LocalDate data = LocalDate.parse(txtData.getText(), formatter);
					Chamado c = new Chamado();
					c.setColaborador(colab);
					c.setVeiculo(v);
					c.setDataInicio(data);
					c.setDistancia(Double.parseDouble(txtDistancia.getText()));
					c.setEndereco(txtEndereco.getText());
					c.calcularPegadaCarbono();
					chamadoController.criarChamado(c);
					dispose();
					JOptionPane.showMessageDialog(null, "Chamado registrado.");
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Erro ao criar o chamado.");
				}
			}
		});
		btnSalvar.setBackground(new Color(220, 220, 220));
		btnSalvar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnSalvar, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnCancelar))
						.addComponent(jpCriarChamado, GroupLayout.PREFERRED_SIZE, 515, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(13, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(jpCriarChamado, GroupLayout.PREFERRED_SIZE, 193, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCancelar)
						.addComponent(btnSalvar))
					.addContainerGap(38, Short.MAX_VALUE))
		);
		
		JLabel lblData = new JLabel("Data:");
		lblData.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		txtData = new JTextField();
		lblData.setLabelFor(txtData);
		txtData.setColumns(10);
		
		JLabel lblDistancia = new JLabel("Distancia:");
		lblDistancia.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		txtDistancia = new JTextField();
		lblDistancia.setLabelFor(txtDistancia);
		txtDistancia.setColumns(10);
		
		JLabel lblEndereco = new JLabel("Endere\u00E7o:");
		lblEndereco.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		txtEndereco = new JTextField();
		txtEndereco.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEndereco.setLabelFor(txtEndereco);
		txtEndereco.setColumns(10);
		
		JLabel lblColab = new JLabel("Colaborador:");
		lblColab.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		lblColab.setLabelFor(cmbxColab);
		cmbxColab.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblVeiculo = new JLabel("Ve\u00EDculo:");
		lblVeiculo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		cmbxVeiculo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GroupLayout gl_jpCriarChamado = new GroupLayout(jpCriarChamado);
		gl_jpCriarChamado.setHorizontalGroup(
			gl_jpCriarChamado.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_jpCriarChamado.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_jpCriarChamado.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_jpCriarChamado.createSequentialGroup()
							.addComponent(lblData)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtData, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)
							.addGap(29)
							.addComponent(lblDistancia)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtDistancia, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_jpCriarChamado.createParallelGroup(Alignment.TRAILING, false)
							.addGroup(Alignment.LEADING, gl_jpCriarChamado.createSequentialGroup()
								.addComponent(lblVeiculo)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(cmbxVeiculo, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addGroup(Alignment.LEADING, gl_jpCriarChamado.createSequentialGroup()
								.addComponent(lblColab)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(cmbxColab, GroupLayout.PREFERRED_SIZE, 389, GroupLayout.PREFERRED_SIZE))
							.addGroup(Alignment.LEADING, gl_jpCriarChamado.createSequentialGroup()
								.addComponent(lblEndereco)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(txtEndereco))))
					.addContainerGap(81, Short.MAX_VALUE))
		);
		gl_jpCriarChamado.setVerticalGroup(
			gl_jpCriarChamado.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_jpCriarChamado.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_jpCriarChamado.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblData)
						.addComponent(txtData, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblDistancia)
						.addComponent(txtDistancia, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_jpCriarChamado.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtEndereco, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblEndereco))
					.addGap(18)
					.addGroup(gl_jpCriarChamado.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblColab)
						.addComponent(cmbxColab, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_jpCriarChamado.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblVeiculo)
						.addComponent(cmbxVeiculo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(23, Short.MAX_VALUE))
		);
		jpCriarChamado.setLayout(gl_jpCriarChamado);
		getContentPane().setLayout(groupLayout);

	}
}
