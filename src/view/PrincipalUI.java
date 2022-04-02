package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
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
		setBounds(100, 100, 1085, 544);
		
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
