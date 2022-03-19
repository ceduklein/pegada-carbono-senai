package model;

import java.time.LocalDate;

public class Chamado {

	private int id;
	private LocalDate dataInicio;
	private LocalDate dataFim;
	private String endereco;
	private Double distancia;
	private double pegadaCarbono;
	private Colaborador colaborador;
	private Veiculo veiculo;
		
	public Chamado(LocalDate dataInicio, String endereco, Double distancia, Colaborador colaborador, Veiculo veiculo) throws Exception {
		if (colaborador.isHabilitado()) {
			if(veiculo.isDisponivel()) {
				this.dataInicio = dataInicio;
				this.endereco = endereco;
				this.distancia = distancia;
				this.colaborador = colaborador;
				this.veiculo = veiculo;
				calcularPegadaCarbono();
			} else {
				throw new Exception("Veículo não disponível.");
			}
		} else {
			throw new Exception("Colaborador não habilitado.");
		}
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public LocalDate getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio;
	}
	public LocalDate getDataFim() {
		return dataFim;
	}
	public void setDataFim(LocalDate dataFim) {
		this.dataFim = dataFim;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public Double getDistancia() {
		return distancia;
	}
	public void setDistancia(Double distancia) {
		this.distancia = distancia;
	}
	public double getPegadaCarbono() {
		return pegadaCarbono;
	}
	public void setPegadaCarbono(double pegadaCarbono) {
		this.pegadaCarbono = pegadaCarbono;
	}
	public Colaborador getColaborador() {
		return colaborador;
	}
	public void setColaborador(Colaborador colaborador) {
		this.colaborador = colaborador;
	}
	public Veiculo getVeiculo() {
		return veiculo;
	}
	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}
	
	public void calcularPegadaCarbono() {
		Double consumo = distancia / veiculo.getKmLitro();
		this.pegadaCarbono = consumo * 0.82 * 0.75 * 3.7;
	}
	
}
