import java.util.List;

import controller.VeiculoController;
import model.Veiculo;

public class TesteVeiculo {
	
	public static void testeListaSemVeiculos() {
		
		VeiculoController controller = new VeiculoController();
		try {
			controller.listar();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void testeEspecificoVeiculo() {
		
		System.out.println("---- Criando 3 veiculos e listando ----");
		
		Veiculo v1 = new Veiculo("Civic", "KXQ8J91", 10.5);
		v1.setId(1);
		
		Veiculo v2 = new Veiculo("Mobi", "MLQ2750", 14.5);
		v2.setId(2);
		v2.setDisponivel(false);
		
		Veiculo v3 = new Veiculo("Sandero", "PXQ3344", 13.0);
		v3.setId(3);
		
		VeiculoController controller = new VeiculoController();
		
		try {
			controller.salvar(v1);
			controller.salvar(v2);
			controller.salvar(v3);
			
			List<Veiculo> veiculos = controller.listar();
			veiculos.forEach(v -> {
				System.out.println("Modelo: " + v.getModelo() + " Id: " + v.getId() + " Consumo: " + v.getKmLitro() + "/litro");
				System.out.println("Placa: " + v.getPlaca() + " Disponível: " + v.isDisponivel());
				System.out.println("----");
			});
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		System.out.println("---- Alterando modelo e e disponibilidade v2 e listando apenas v2 ----");
		
		v2.setDisponivel(true);
		v2.setModelo("KWID");
		
		try {
			controller.atualizar(v2);
			
			Veiculo v = controller.listById(v2.getId());
			System.out.println("Modelo: " + v.getModelo() + " Id: " + v.getId() + " Consumo: " + v.getKmLitro() + "/litro");
			System.out.println("Placa: " + v.getPlaca() + " Disponível: " + v.isDisponivel());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		System.out.println("---- Excluindo v2 e listando todos os veiculos novamente ----");
		
		try {
			controller.excluir(v2.getId());
			
			List<Veiculo> veiculos = controller.listar();
			veiculos.forEach(v -> {
				System.out.println("Modelo: " + v.getModelo() + " Id: " + v.getId() + " Consumo: " + v.getKmLitro() + "/litro");
				System.out.println("Placa: " + v.getPlaca() + " Disponível: " + v.isDisponivel());
				System.out.println("----");
			});
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
}
