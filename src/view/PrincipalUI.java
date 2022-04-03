package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.ChamadoController;
import controller.ColaboradorController;
import controller.VeiculoController;
import model.Chamado;
import model.Colaborador;
import model.Veiculo;
import view.chamados.CriarChamadoUI;
import view.chamados.EditChamadoUI;
import view.chamados.ListaChamadoUI;
import view.colaboradores.CadastroColabUI;
import view.colaboradores.EditarColabUI;
import view.colaboradores.ListaColabUI;
import view.veiculos.CadastroVeiculoUI;
import view.veiculos.EditVeiculoUI;
import view.veiculos.ListaVeiculoUI;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PrincipalUI extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrincipalUI frame = new PrincipalUI();
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
	public PrincipalUI() {
		setTitle("Sistema de Registro de Chamados");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1220, 544);
		
		JMenuBar jmbPrincipal = new JMenuBar();
		setJMenuBar(jmbPrincipal);
		
		JMenu jmColab = new JMenu("Colaboradores");
		jmbPrincipal.add(jmColab);
		
		JMenuItem jmiCadastroColab = new JMenuItem("Cadastrar");
		jmiCadastroColab.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastroColabUI cadastroColab = new CadastroColabUI();
				cadastroColab.setVisible(true);
				contentPane.add(cadastroColab, 0);
			}
		});
		jmColab.add(jmiCadastroColab);
		
		JMenuItem jmiListaColab = new JMenuItem("Listar");
		jmiListaColab.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListaColabUI listaColab = new ListaColabUI();
				listaColab.setVisible(true);
				contentPane.add(listaColab, 0);
			}
		});
		jmColab.add(jmiListaColab);
		
		JMenuItem jmiBuscaColab = new JMenuItem("Buscar");
		jmiBuscaColab.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = Integer.parseInt(JOptionPane.showInputDialog("Informe o id do colaborador"));
				
				try {
					Colaborador colab = new ColaboradorController().listById(id);
					EditarColabUI editColab = new EditarColabUI();
					editColab.setColabEdit(colab);
					editColab.setVisible(true);
					contentPane.add(editColab, 0);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Colaborador não encontrado.");
				}
			}
		});
		jmColab.add(jmiBuscaColab);
		
		JMenu jmVeiculo = new JMenu("Veiculos");
		jmbPrincipal.add(jmVeiculo);
		
		JMenuItem jmiCadastroVeiculo = new JMenuItem("Cadastrar");
		jmiCadastroVeiculo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastroVeiculoUI cadVeiculo = new CadastroVeiculoUI();
				cadVeiculo.setVisible(true);
				contentPane.add(cadVeiculo, 0);
			}
		});
		jmVeiculo.add(jmiCadastroVeiculo);
		
		JMenuItem jmiListaVeiculo = new JMenuItem("Listar");
		jmiListaVeiculo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListaVeiculoUI listaVeiculo = new ListaVeiculoUI();
				listaVeiculo.setVisible(true);
				contentPane.add(listaVeiculo, 0);
			}
		});
		jmVeiculo.add(jmiListaVeiculo);
		
		JMenuItem jmiBuscaVeiculo = new JMenuItem("Buscar");
		jmiBuscaVeiculo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = Integer.parseInt(JOptionPane.showInputDialog("Informe o id do veículo"));
				
				try {
					Veiculo v = new VeiculoController().listById(id);
					EditVeiculoUI editVeiculo = new EditVeiculoUI();
					editVeiculo.setVeiculoEdit(v);
					editVeiculo.setVisible(true);
					contentPane.add(editVeiculo ,0);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Veículo não encontrado.");
				}
			}
		});
		jmVeiculo.add(jmiBuscaVeiculo);
		
		JMenu jmChamado = new JMenu("Chamados");
		jmbPrincipal.add(jmChamado);
		
		JMenuItem jmiCriarChamado = new JMenuItem("Criar");
		jmiCriarChamado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CriarChamadoUI criarChamado = new CriarChamadoUI();
				criarChamado.setVisible(true);
				contentPane.add(criarChamado, 0);
			}
		});
		jmChamado.add(jmiCriarChamado);
		
		JMenuItem jmiListaChamado = new JMenuItem("Listar");
		jmiListaChamado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListaChamadoUI listaChamado = new ListaChamadoUI();
				listaChamado.setVisible(true);
				contentPane.add(listaChamado, 0);
			}
		});
		jmChamado.add(jmiListaChamado);
		
		JMenuItem jmiBuscaChamado = new JMenuItem("Buscar");
		jmiBuscaChamado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = Integer.parseInt(JOptionPane.showInputDialog("Informe o id do chamado"));
				
				try {
					Chamado c = new ChamadoController().listById(id);
					EditChamadoUI editChamado = new EditChamadoUI();
					editChamado.setChamadoEdit(c);
					editChamado.setVisible(true);
					contentPane.add(editChamado, 0);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Chamado não encontrado.");
				}
				
			}
		});
		jmChamado.add(jmiBuscaChamado);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGap(0, 424, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGap(0, 251, Short.MAX_VALUE)
		);
		contentPane.setLayout(gl_contentPane);
	}

}
