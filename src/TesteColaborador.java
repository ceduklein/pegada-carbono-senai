import java.util.List;

import controller.ColaboradorController;
import model.Colaborador;

public class TesteColaborador {
	
	public static void testeGeral() {
		
		Colaborador c1 = new Colaborador("Carlos Klein");
		c1.setId(1);
		c1.setHabilitado(true);
		
		Colaborador c2 = new Colaborador("Henrique");
		c2.setId(2);
		c2.setHabilitado(true);
		
		Colaborador c3 = new Colaborador("Funcionário Fantasma");
		c2.setId(3);
		
		ColaboradorController controller = new ColaboradorController();
		try {
			controller.salvar(c1);
			controller.salvar(c2);
			controller.salvar(c3);
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
		}
		
		try {
			List<Colaborador> colaboradores = controller.listar();
			colaboradores.forEach(c -> {
				System.out.println("Colaborador: " + c.getNome());
				System.out.println("Habilitado: " + c.isHabilitado());
				System.out.println("----");
			});
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		System.out.println("---------------------------------------------------");
	}
	
	public static void testeListaSemColaboradores() {
		ColaboradorController controller = new ColaboradorController();
		
		try {
			controller.listar();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void testeEspecificoColaborador() {
		System.out.println("---- Criando c1, c2 e c3 e listando todos ----");
		
		Colaborador c1 = new Colaborador("Carlos Klein");
		c1.setId(1);
		c1.setHabilitado(true);
		
		Colaborador c2 = new Colaborador("Henrique");
		c2.setId(2);
		
		Colaborador c3 = new Colaborador("Funcionário Fantasma");
		c2.setId(3);
		
		ColaboradorController controller = new ColaboradorController();
		try {
			controller.salvar(c1);
			controller.salvar(c2);
			controller.salvar(c3);
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
		}
		
		try {
			List<Colaborador> colaboradores = controller.listar();
			colaboradores.forEach(c -> {
				System.out.println("Colaborador: " + c.getNome());
				System.out.println("Habilitado: " + c.isHabilitado());
				System.out.println("----");
			});
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		System.out.println("---- Alterando nome e habilitação do c2 e listando apenas c2 ----");
		
		c2.setNome("Henrique Sobrenome");
		c2.setHabilitado(true);
		
		try {
			controller.atualizar(c2);
			Colaborador c = controller.listById(c2.getId());
			System.out.println("Colaborador: " + c.getNome());
			System.out.println("Habilitado: " + c.isHabilitado());
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		System.out.println("---- Excluindo c2 e listando todos os colaboradores novamente ----");
		
		try {
			controller.excluir(c2.getId());
			List<Colaborador> colaboradores = controller.listar();
			colaboradores.forEach(c -> {
				System.out.println("Colaborador: " + c.getNome());
				System.out.println("Habilitado: " + c.isHabilitado());
				System.out.println("----");
			});
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
}
