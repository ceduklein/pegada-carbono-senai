package view.chamados;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.border.EtchedBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import controller.ChamadoController;
import model.Chamado;
import model.Colaborador;
import model.Veiculo;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;

public class EditChamadoUI extends JInternalFrame {
	private JTextField txtId;
	private JTextField txtData;
	private JTextField txtCarbono;
	private JTextField txtEndereco;
	private JTextField txtDistancia;
	private JTextField txtSituacao;
	private JTextField txtColab;
	private JTextField txtVeiculo;
	private JTextField txtPlaca;
	
	private Chamado chamado;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditChamadoUI frame = new EditChamadoUI();
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
	public EditChamadoUI() {
		ChamadoController chamadoController = new ChamadoController();
		
		setClosable(true);
		setTitle("Chamados");
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		setBounds(100, 100, 561, 324);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Editar, Encerrar ou Excluir Chamado", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBackground(new Color(220, 220, 220));
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setBackground(new Color(220, 220, 220));
		btnExcluir.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int confirmacao = JOptionPane.showConfirmDialog(null, "Deseja excluir este chamado?");
				
				if (confirmacao == 0 ) {
					try {
						chamadoController.excluir(chamado);
						dispose();
						JOptionPane.showMessageDialog(null, "Chamado excluído.");
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, "Erro ao excluir chamado.");
					}
				}
			}
		});
		
		JButton btnEncerrar = new JButton("Encerrar");
		btnEncerrar.setBackground(new Color(220, 220, 220));
		btnEncerrar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnEncerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chamado.setConcluido(true);
				try {
					chamadoController.encerrarChamado(chamado);
					dispose();
					JOptionPane.showMessageDialog(null, "Chamado encerrado.");
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Erro ao encerrar chamado.");
				}
			}
		});
		
		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chamado.setEndereco(txtEndereco.getText());
				chamado.setDistancia(Double.parseDouble(txtDistancia.getText()));
				chamado.calcularPegadaCarbono();
				
				try {
					chamadoController.atualizar(chamado);
					dispose();
					JOptionPane.showMessageDialog(null, "Informações atualizadas.");
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Erro ao atualizar as informações do chamado.");
				}
			}
		});
		btnAtualizar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAtualizar.setBackground(new Color(220, 220, 220));
		
		JLabel lblId = new JLabel("Id:");
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		txtId = new JTextField();
		lblId.setLabelFor(txtId);
		txtId.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtId.setEditable(false);
		txtId.setColumns(10);
		
		JLabel lblData = new JLabel("Data:");
		lblData.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		txtData = new JTextField();
		lblData.setLabelFor(txtData);
		txtData.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtData.setEditable(false);
		txtData.setColumns(10);
		
		JLabel lblCarbono = new JLabel("Emiss\u00E3o de CO\u00B2:");
		lblCarbono.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		txtCarbono = new JTextField();
		lblCarbono.setLabelFor(txtCarbono);
		txtCarbono.setEditable(false);
		txtCarbono.setColumns(10);
		
		JLabel lblEndereco = new JLabel("Endere\u00E7o:");
		lblEndereco.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		txtEndereco = new JTextField();
		txtEndereco.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtEndereco.setColumns(10);
		
		JLabel lblDistancia = new JLabel("Dist\u00E2ncia:");
		lblDistancia.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		txtDistancia = new JTextField();
		lblDistancia.setLabelFor(txtDistancia);
		txtDistancia.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtDistancia.setColumns(10);
		
		JLabel lblSituacao = new JLabel("Situa\u00E7\u00E3o:");
		lblSituacao.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		txtSituacao = new JTextField();
		lblSituacao.setLabelFor(txtSituacao);
		txtSituacao.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtSituacao.setEditable(false);
		txtSituacao.setColumns(10);
		
		JLabel lblColab = new JLabel("Colaborador:");
		lblColab.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		txtColab = new JTextField();
		lblColab.setLabelFor(txtColab);
		txtColab.setEditable(false);
		txtColab.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtColab.setColumns(10);
		
		JLabel lblVeiculo = new JLabel("Ve\u00EDculo:");
		lblVeiculo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		txtVeiculo = new JTextField();
		lblVeiculo.setLabelFor(txtVeiculo);
		txtVeiculo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtVeiculo.setEditable(false);
		txtVeiculo.setColumns(10);
		
		JLabel lblPlaca = new JLabel("Placa:");
		lblPlaca.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		txtPlaca = new JTextField();
		lblPlaca.setLabelFor(txtPlaca);
		txtPlaca.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtPlaca.setEditable(false);
		txtPlaca.setColumns(10);
		
		JLabel lblKm = new JLabel("km");
		lblKm.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
						.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
							.addComponent(lblId)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtId, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblData)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtData, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblSituacao)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtSituacao))
						.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
							.addComponent(lblColab)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(txtColab))
						.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
							.addComponent(lblEndereco)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtEndereco))
						.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
							.addComponent(lblDistancia)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtDistancia, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblKm)
							.addGap(29)
							.addComponent(lblCarbono)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtCarbono, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblVeiculo)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtVeiculo, GroupLayout.PREFERRED_SIZE, 266, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblPlaca)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtPlaca, 0, 0, Short.MAX_VALUE)))
					.addContainerGap(48, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblId)
						.addComponent(txtId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblData)
						.addComponent(txtData, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSituacao)
						.addComponent(txtSituacao, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEndereco)
						.addComponent(txtEndereco, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDistancia)
						.addComponent(txtDistancia, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblKm)
						.addComponent(lblCarbono)
						.addComponent(txtCarbono, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(26)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblColab)
						.addComponent(txtColab, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblVeiculo)
						.addComponent(txtVeiculo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtPlaca, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPlaca))
					.addContainerGap(56, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnAtualizar)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnEncerrar)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnExcluir, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnCancelar))
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 533, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(48, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 242, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCancelar)
						.addComponent(btnExcluir)
						.addComponent(btnEncerrar)
						.addComponent(btnAtualizar))
					.addGap(63))
		);
		getContentPane().setLayout(groupLayout);

	}
	
	public void setChamadoEdit(Chamado chamado) {
		this.chamado = chamado;
		preencherForm();
	}
	
	private void preencherForm() {
		LocalDate data = chamado.getDataInicio();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String dataFormatada = data.format(formatter);
		
		DecimalFormat df = new DecimalFormat("#.##");
		
		txtId.setText(String.valueOf(chamado.getId()));
		txtData.setText(dataFormatada);
		txtEndereco.setText(chamado.getEndereco());
		txtDistancia.setText(df.format(chamado.getDistancia()).toString());
		txtCarbono.setText(chamado.getPegadaCarbono().toString());
		if (chamado.isConcluido())
			txtSituacao.setText("Encerrado");
		else
			txtSituacao.setText("Em progresso");
		
		txtVeiculo.setText(chamado.getVeiculo().getModelo());
		txtPlaca.setText(chamado.getVeiculo().getPlaca());
		
		txtColab.setText(chamado.getColaborador().getNome());
	}
}
