package model;

import java.time.LocalDate;

public class Principal {
	
	public static void main(String[] args) {
		
		Colaborador colab1 = new Colaborador("Carlos");
		Colaborador colab2 = new Colaborador("Henrique");
		colab1.setHabilitado(false);
		colab2.setHabilitado(true);
		
		Veiculo carro1 = new Veiculo("Celta", "MCH1010", 14.0);
		Veiculo carro2 = new Veiculo("Mobi", "MCH2020", 16.5);
		carro1.setDisponivel(false);
				
		//Chamado deve ser criado e impresso
		try {
			Chamado ch1 = new Chamado(LocalDate.now(), "Rua 150", 100.00, colab2, carro2);
			
			System.out.println("Data chamado: " + ch1.getDataInicio());
			System.out.println("Nome colaborador: " + ch1.getColaborador().getNome());
			System.out.println("Modelo: " + ch1.getVeiculo().getModelo() + " Placa: " + ch1.getVeiculo().getPlaca());
			System.out.println("Distância: " + ch1.getDistancia());
			System.out.println("Emissão CO²: " + ch1.getPegadaCarbono());
			System.out.println("--------------------------------");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	
		//Deve apresentar erro Colaborador não habilitado
		try {
			Chamado ch2 = new Chamado(LocalDate.now(), "Rua 250", 200.00, colab1, carro2);
			System.out.println(ch2.getDataInicio());
			System.out.println("--------------------------------");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		//Deve apresentar erro Veículo não disponível
		try {
			Chamado ch3 = new Chamado(LocalDate.now(), "Rua 350", 150.00, colab2, carro1);
			System.out.println(ch3.getDataInicio());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

}
