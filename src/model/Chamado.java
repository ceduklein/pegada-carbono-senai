package model;

//import java.time.LocalDate;

public class Chamado {

	private int id;
	private String dataInicio;
	private String dataFim;
	private String endereco;
	private Double distancia;
	private double pegadaCarbono;
	private Colaborador colaborador;
	private Veiculo veiculo;
		
	public Chamado(String dataInicio, String endereco, Double distancia, Colaborador colaborador, Veiculo veiculo) {
		if (colaborador.isHabilitado()) {
			if (veiculo.isDisponivel()) {
				this.dataInicio = dataInicio;
				this.endereco = endereco;
				this.distancia = distancia;
				this.colaborador = colaborador;
				this.veiculo = veiculo;
				calcularPegadaCarbono();
			} else {
				System.out.println("Veículo está em uso. Favor selecionar veículo disponível.");
			}
		} else {
			System.out.println("Colaborador não é habilitado. Favor indicar colaborador habilitado");
		}
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(String dataInicio) {
		this.dataInicio = dataInicio;
	}
	public String getDataFim() {
		return dataFim;
	}
	public void setDataFim(String dataFim) {
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
