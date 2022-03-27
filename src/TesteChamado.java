import java.time.LocalDateTime;
import java.util.List;

import controller.ChamadoController;
import controller.ColaboradorController;
import controller.VeiculoController;
import model.Chamado;
import model.Colaborador;
import model.Veiculo;

public class TesteChamado {

	public static void testeListaSemChamados() {
		
		System.out.println("---- Deve apresentar o erro: Não foram encontrados chamados ----");
		ChamadoController controller = new ChamadoController();
		try {
			controller.listar();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void testeChamadoColaboradorNaoHabilitado() {
		
		System.out.println("---- Deve apresentar erro de colaborador não habilitado. ----");
		
		Veiculo v1 = new Veiculo("Civic", "KXQ8J91", 10.5);
		v1.setId(1);
		
		Colaborador c1 = new Colaborador("Carlos Klein");
		c1.setId(1);
		c1.setHabilitado(false);
		
		Chamado chamado = new Chamado(LocalDateTime.now(), "Rua 1001", 155.7, c1, v1);
		
		ChamadoController controller = new ChamadoController();
		try {
			controller.criarChamado(chamado);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		System.out.println("---------------------------------------------------");
	}
	
	public static void testeChamadoVeiculoIndisponivel() {
		
		System.out.println("---- Deve apresentar erro de veículo indisponível. ----");
		
		Veiculo v1 = new Veiculo("Civic", "KXQ8J91", 10.5);
		v1.setId(1);
		v1.setDisponivel(false);
		
		Colaborador c1 = new Colaborador("Carlos Klein");
		c1.setId(1);
		c1.setHabilitado(true);
		
		Chamado chamado = new Chamado(LocalDateTime.now(), "Rua 1001", 155.7, c1, v1);
		
		ChamadoController controller = new ChamadoController();
		try {
			controller.criarChamado(chamado);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		System.out.println("---------------------------------------------------");
	}

	public static void testeChamadoCriadoEFinalizado() {
		
		System.out.println("---- Deve criar chamado e tornar o veículo utilizado indisponível. "
				+ "Depois encerrar chamado e tornar veiculo disponível novamente ----");
		
		Veiculo v1 = new Veiculo("Civic", "KXQ8J91", 10.5);
		v1.setId(1);
		
		Colaborador c1 = new Colaborador("Carlos Klein");
		c1.setId(1);
		c1.setHabilitado(true);
		
		Chamado chamado = new Chamado(LocalDateTime.now(), "Rua 1001", 155.7, c1, v1);
		chamado.setId(1);
		
		ChamadoController controller = new ChamadoController();
		ColaboradorController colabController = new ColaboradorController();
		VeiculoController veiculoController = new VeiculoController();
		
		try {
			colabController.salvar(c1);
			veiculoController.salvar(v1);
			controller.criarChamado(chamado);
			Chamado c = controller.listById(chamado.getId());
			System.out.println("Id: " + c.getId() + " Inicio: " + c.getDataInicio() + " Colaborador: " + c.getColaborador().getNome());
			System.out.println("Endereço: " + c.getEndereco() + " Dist: " + c.getDistancia());
			System.out.println("CO²: " + c.getPegadaCarbono());
			System.out.println("Veiculo: " + c.getVeiculo().getModelo() + " Disponível: " + c.getVeiculo().isDisponivel());
			System.out.println("----------------------");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		chamado.setDataFim(LocalDateTime.now());
		
		try {
			controller.encerrarChamado(chamado);
			Chamado c = controller.listById(chamado.getId());
			System.out.println("Id: " + c.getId() + " Inicio: " + c.getDataInicio() + " Colaborador: " + c.getColaborador().getNome());
			System.out.println("Endereço: " + c.getEndereco() + " Dist: " + c.getDistancia());
			System.out.println("CO²: " + c.getPegadaCarbono());
			System.out.println("Veiculo: " + c.getVeiculo().getModelo() + " Disponível: " + c.getVeiculo().isDisponivel());
			System.out.println("Fim: " + c.getDataFim());
			System.out.println("----------------------");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		
		System.out.println("---------------------------------------------------");
	}
	
	public static void testeListaChamadosEExclusao() {
		
		System.out.println("---- Deve criar dois chamados e listá-los ----");
		
		Veiculo v1 = new Veiculo("Civic", "KXQ8J91", 10.5);
		v1.setId(1);
		Veiculo v2 = new Veiculo("Sandero", "PXM2233", 14.00);
		v2.setId(2);
		
		Colaborador c1 = new Colaborador("Carlos Klein");
		c1.setId(1);
		c1.setHabilitado(true);
		Colaborador c2 = new Colaborador("Fulano da Silva");
		c2.setId(2);
		c2.setHabilitado(true);
		
		Chamado chamado1 = new Chamado(LocalDateTime.now(), "Rua 1001", 155.7, c1, v1);
		chamado1.setId(1);
		Chamado chamado2 = new Chamado(LocalDateTime.now(), "Rua dos Polvos", 300.00, c2, v2);
		chamado2.setId(2);
		
		ChamadoController controller = new ChamadoController();
		ColaboradorController colabController = new ColaboradorController();
		VeiculoController veiculoController = new VeiculoController();
		
		try {
			colabController.salvar(c1);
			colabController.salvar(c2);
			veiculoController.salvar(v1);
			veiculoController.salvar(v2);
			controller.criarChamado(chamado1);
			controller.criarChamado(chamado2);
			
			List<Chamado> chamados = controller.listar();
			chamados.forEach(c -> {
				System.out.println("Id: " + c.getId() + " - Início: " + c.getDataInicio()  + " - CO²: " + c.getPegadaCarbono());
				System.out.println("Endereço: " + c.getEndereco() + " - Distância: " + c.getDistancia());
				System.out.println("Colaborador: "+ c.getColaborador().getNome() + " - Veiculo: " + c.getVeiculo().getModelo());
				System.out.println("---------------");
			});
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		System.out.println("---- Deve excluir o segundo chamado e listar todos novamente ----");
		
		try {
			controller.excluir(chamado2.getId());
			
			List<Chamado> chamados = controller.listar();
			chamados.forEach(c -> {
				System.out.println("Id: " + c.getId() + " - Início: " + c.getDataInicio()  + " - CO²: " + c.getPegadaCarbono());
				System.out.println("Endereço: " + c.getEndereco() + " - Distância: " + c.getDistancia());
				System.out.println("Colaborador: "+ c.getColaborador().getNome() + " - Veiculo: " + c.getVeiculo().getModelo());
				System.out.println("---------------");
			});
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
		
}


