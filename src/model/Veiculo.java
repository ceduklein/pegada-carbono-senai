package model;

public class Veiculo {
	
	private int id;
	private String modelo;
	private String placa;
	private boolean disponivel;
	private Double kmLitro;
	
	public Veiculo(String modelo, String placa, Double kmLitro) {
		this.modelo = modelo;
		this.placa = placa;
		this.kmLitro = kmLitro;
		this.disponivel = true;
	}
	
	public Veiculo() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public boolean isDisponivel() {
		return disponivel;
	}

	public void setDisponivel(boolean disponivel) {
		this.disponivel = disponivel;
	}

	public Double getKmLitro() {
		return kmLitro;
	}

	public void setKmLitro(Double kmLitro) {
		this.kmLitro = kmLitro;
	}
	
}
