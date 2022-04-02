
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import controller.ChamadoController;
import controller.ColaboradorController;
import controller.VeiculoController;
import model.Chamado;
import model.Colaborador;
import model.Veiculo;

public class TesteChamado {

	public static void testeListaSemChamados() {
		
		System.out.println("\n ---- Deve apresentar o erro: N�o foram encontrados chamados ----");
		ChamadoController controller = new ChamadoController();
		try {
			controller.listar();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void testeChamadoColaboradorNaoHabilitado() {
		
		System.out.println("\n---- Deve apresentar erro de colaborador n�o habilitado. ----");
		
		Veiculo v1 = new Veiculo("Civic", "KXQ8J91", 10.5);
		
		Colaborador c1 = new Colaborador("Carlos Klein");
		c1.setHabilitado(false);
		
		Chamado chamado = new Chamado(LocalDate.now(), "Rua 1001", 155.7, c1, v1);
		
		ChamadoController controller = new ChamadoController();
		try {
			controller.criarChamado(chamado);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		System.out.println("---------------------------------------------------");
	}
	
	public static void testeChamadoVeiculoIndisponivel() {
		
		System.out.println("\n---- Deve apresentar erro de ve�culo indispon�vel. ----");
		
		Veiculo v1 = new Veiculo("Civic", "KXQ8J91", 10.5);
		v1.setDisponivel(false);
		
		Colaborador c1 = new Colaborador("Carlos Klein");
		c1.setHabilitado(true);
		
		Chamado chamado = new Chamado(LocalDate.now(), "Rua 1001", 155.7, c1, v1);
		
		ChamadoController controller = new ChamadoController();
		try {
			controller.criarChamado(chamado);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		System.out.println("---------------------------------------------------");
	}

	public static void testeChamadoCriadoEFinalizado() {
		
		System.out.println("\n---- Deve criar chamado e setar o ve�culo utilizado como indispon�vel. "
				+ "Depois encerrar chamado e setar veiculo como dispon�vel novamente ----");
		
		ChamadoController controller = new ChamadoController();
		ColaboradorController colabController = new ColaboradorController();
		VeiculoController veiculoController = new VeiculoController();
		
		Veiculo v1 = new Veiculo("Civic", "KXQ8J91", 10.5);
		
		Colaborador c1 = new Colaborador("Carlos Klein");
		c1.setHabilitado(true);
				
		try {
			colabController.salvar(c1);
			veiculoController.salvar(v1);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		Colaborador colaborador = new Colaborador();
		Veiculo veiculo = new Veiculo();
		
		try {
			colaborador = colabController.listById(1);
			veiculo = veiculoController.listById(1);
		} catch (Exception e) {
			System.out.println(e.getMessage());;
		}
		
		Chamado c = new Chamado(LocalDate.now(), "Rua 1001", 155.7, colaborador, veiculo);
		
		try {
			controller.criarChamado(c);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		try {
			c = controller.listById(1);
			
			System.out.println("Id: " + c.getId() + " Inicio: " + c.getDataInicio());
			System.out.println("IdColaborador: " + c.getColaborador().getId() + " Colaborador: " + c.getColaborador().getNome());
			System.out.println("Endere�o: " + c.getEndereco() + " Dist: " + c.getDistancia());
			System.out.println("CO�: " + c.getPegadaCarbono());
			System.out.println("IdVe�culo: " + c.getVeiculo().getId() + " Veiculo: " + c.getVeiculo().getModelo() + " Dispon�vel: " + c.getVeiculo().isDisponivel());
			System.out.println("Concluido: " + c.isConcluido());
			System.out.println("----------------------\n");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		c.setConcluido(true);
		
		try {
			controller.encerrarChamado(c);
			
			c = controller.listById(c.getId());
			
			System.out.println("Id: " + c.getId() + " Inicio: " + c.getDataInicio());
			System.out.println("IdColaborador: " + c.getColaborador().getId() + " Colaborador: " + c.getColaborador().getNome());
			System.out.println("Endere�o: " + c.getEndereco() + " Dist: " + c.getDistancia());
			System.out.println("CO�: " + c.getPegadaCarbono());
			System.out.println("IdVe�culo: " + c.getVeiculo().getId() + " Veiculo: " + c.getVeiculo().getModelo() + " Dispon�vel: " + c.getVeiculo().isDisponivel());
			System.out.println("Concluido: " + c.isConcluido());
			System.out.println("----------------------\n");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		
		System.out.println("---------------------------------------------------\n");
	}
	
	public static void testeCriarListarTodosExcluirListarTodos() {
		
		System.out.println("---- Deve criar chamados e list�-los ----\n");
		
		ChamadoController controller = new ChamadoController();
		ColaboradorController colabController = new ColaboradorController();
		VeiculoController veiculoController = new VeiculoController();
		
		Veiculo v1 = new Veiculo("Civic", "KXQ8J91", 10.5);

		Veiculo v2 = new Veiculo("Sandero", "PXM2233", 14.00);
		
		Colaborador c1 = new Colaborador("Carlos Klein");
		c1.setHabilitado(true);
		
		Colaborador c2 = new Colaborador("Henrique");
		c2.setHabilitado(true);
		
		Chamado chamado1 = new Chamado(LocalDate.now(), "Rua dos Cambuatas", 155.7, c1, v1);
		Chamado chamado2 = new Chamado(LocalDate.now(), "Rua dos Polvos", 300.00, c2, v2);
		
		try {
			colabController.salvar(c1);
			colabController.salvar(c2);
			veiculoController.salvar(v1);
			veiculoController.salvar(v2);
			
			c1 = colabController.listById(1);
			c2 = colabController.listById(2);
			
			v1 = veiculoController.listById(1);
			v2 = veiculoController.listById(2);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		chamado1.setVeiculo(v1);
		chamado1.setColaborador(c1);
		chamado2.setVeiculo(v2);
		chamado2.setColaborador(c2);
		
		List<Chamado> chamados = new ArrayList<>();
		
		try {
			controller.criarChamado(chamado1);
			controller.criarChamado(chamado2);
			
			chamados = controller.listar();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		chamados.forEach(c -> {
			System.out.println("Id: " + c.getId() + " - Data: " + c.getDataInicio()  + " - CO�: " + c.getPegadaCarbono());
			System.out.println("Endere�o: " + c.getEndereco() + " - Dist�ncia: " + c.getDistancia());
			
			System.out.println("IdColaborador: " + c.getColaborador().getId() + " Colaborador: "+ c.getColaborador().getNome() +
					" - Habilitado: " + c.getColaborador().isHabilitado());
			
			System.out.println("IdVeiculo: " + c.getVeiculo().getId() + " - Veiculo: " + c.getVeiculo().getModelo() +
					" - Dispon�vel: " + c.getVeiculo().isDisponivel() + " - Placa: " + c.getVeiculo().getPlaca());
			
			System.out.println("---------------\n");
		});
		
		System.out.println("---- Deve excluir o segundo chamado e listar todos novamente. "
				+ "(O ve�culo utilizado no chamado exclu�do deve ficar dispon�vel novamente. ----\n");
		
		try {
			chamado2 = controller.listById(2);
			controller.excluir(chamado2);
			
			chamados = controller.listar();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		chamados.forEach(c -> {
			System.out.println("Id: " + c.getId() + " - Data: " + c.getDataInicio()  + " - CO�: " + c.getPegadaCarbono());
			System.out.println("Endere�o: " + c.getEndereco() + " - Dist�ncia: " + c.getDistancia());
			
			System.out.println("IdColaborador: " + c.getColaborador().getId() + " Colaborador: "+ c.getColaborador().getNome() +
					" - Habilitado: " + c.getColaborador().isHabilitado());
			
			System.out.println("IdVeiculo: " + c.getVeiculo().getId() + " - Veiculo: " + c.getVeiculo().getModelo() +
					" - Dispon�vel: " + c.getVeiculo().isDisponivel() + " - Placa: " + c.getVeiculo().getPlaca());
			
			System.out.println("---------------\n");
		});
	}
		
}


