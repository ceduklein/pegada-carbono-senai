import java.util.List;

import controller.ColaboradorController;
import model.Colaborador;

public class TesteColaborador {
		
	public static void testeListaSemColaboradores() {
		ColaboradorController controller = new ColaboradorController();
		
		try {
			controller.listar();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void testeCriarListarTodosAlterarListarUmExcluir() {
		System.out.println("\n---- Deve criar três colaboradores e listar todos ----");
		
		Colaborador c1 = new Colaborador("Carlos Klein");
		c1.setHabilitado(true);
		
		Colaborador c2 = new Colaborador("Henrique");
		c2.setHabilitado(true);
		
		Colaborador c3 = new Colaborador("Funcionário Fantasma");
		
		ColaboradorController controller = new ColaboradorController();
		try {
			controller.salvar(c1);
			controller.salvar(c2);
			controller.salvar(c3);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		try {
			List<Colaborador> colaboradores = controller.listar();
			colaboradores.forEach(c -> {
				System.out.println("Colaborador: " + c.getNome() + " ID: " + c.getId());
				System.out.println("Habilitado: " + c.isHabilitado());
				System.out.println("----");
			});
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		System.out.println("\n---- Deve alterar colaborador e listar apenas colaborador alterado ----");
		
		c2.setNome("Teste Alteração Nome");
		c2.setHabilitado(false);
		c2.setId(2);
		
		try {
			controller.atualizar(c2);
			Colaborador c = controller.listById(c2.getId());
			System.out.println("Colaborador: " + c.getNome() + " ID: " + c.getId());
			System.out.println("Habilitado: " + c.isHabilitado());
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		System.out.println("\n---- Deve excluir um colaborador e listar todos novamente ----");
		
		try {
			controller.excluir(c2.getId());
			List<Colaborador> colaboradores = controller.listar();
			colaboradores.forEach(c -> {
				System.out.println("Colaborador: " + c.getNome() + " ID: " + c.getId());
				System.out.println("Habilitado: " + c.isHabilitado());
				System.out.println("----");
			});
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
