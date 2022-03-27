package dao;

import java.util.ArrayList;
import java.util.List;

import model.Veiculo;

public class VeiculoDao {

	private static VeiculoDao instance;
	private List<Veiculo> veiculos = new ArrayList<>();
	
	public static VeiculoDao getInstance() {
		if (instance == null) {
			instance = new VeiculoDao();
		}
		return instance;
	}
	
	public void salvar(Veiculo veiculo) {
		veiculos.add(veiculo);
	}
	
	public void atualizar(Veiculo veiculo) {
		veiculos.set(veiculo.getId() - 1, veiculo);
	}
	
	public void tornarDisponivel(Veiculo veiculo) {
		veiculo.setDisponivel(true);
		veiculos.set(veiculo.getId() - 1, veiculo);
	}
	
	public void tornarIndisponivel(Veiculo veiculo) {
		veiculo.setDisponivel(false);
		veiculos.set(veiculo.getId() - 1, veiculo);
	}
	
	public void excluir(int id) {
		veiculos.remove(id - 1);
	}
	
	public List<Veiculo> listar() {
		return veiculos;
	}
	
	public Veiculo listById(int id) {
		return veiculos.get(id - 1);
	}
	
}
