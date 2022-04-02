package controller;

import java.util.List;

import dao.VeiculoDao;
import exception.RegraNegocioException;
import model.Veiculo;

public class VeiculoController {

	public void salvar(Veiculo veiculo) throws RegraNegocioException {
		if (!validarModelo(veiculo.getModelo())) {
			throw new RegraNegocioException("Erro: Modelo inválido.");
		}
		
		if (!validarPlaca(veiculo.getPlaca())) {
			throw new RegraNegocioException("Erro: Placa inválida.");
		}
		
		if (!validarConsumo(veiculo.getKmLitro())) {
			throw new RegraNegocioException("Erro: Consumo inválido");
		}
		
		VeiculoDao.getInstance().salvar(veiculo);
	}
	
	public void atualizar(Veiculo veiculo) throws RegraNegocioException {
		if (!validarModelo(veiculo.getModelo())) {
			throw new RegraNegocioException("Erro: Modelo inválido.");
		}
		
		if (!validarPlaca(veiculo.getPlaca())) {
			throw new RegraNegocioException("Erro: Placa inválida.");
		}
		
		if (!validarConsumo(veiculo.getKmLitro())) {
			throw new RegraNegocioException("Erro: Consumo inválido");
		}
		
		VeiculoDao.getInstance().atualizar(veiculo);
	}
	
	public void excluir(int id) throws RegraNegocioException {
		if (id == 0) {
			throw new RegraNegocioException("Erro: Id inválido.");
		}
		
		VeiculoDao.getInstance().excluir(id);
	}
	
	public List<Veiculo> listar() {
		List<Veiculo> veiculos = VeiculoDao.getInstance().listar();
		return veiculos;
	}
	
	public Veiculo listById(int id) {
		return VeiculoDao.getInstance().listById(id);
	}
	
	private boolean validarModelo(String modelo) {
		if (modelo == null || modelo.length() < 2) {
			return false;
		}else {
			return true;
		}
	}
	
	private boolean validarPlaca(String placa) {
		if (placa == null || placa.length() < 7) {
			return false;
		}else {
			return true;
		}
	}
	
	private boolean validarConsumo(Double consumo) {
		if (consumo < 1.00 || consumo > 50) {
			return false;
		}else {
			return true;
		}
	}
}
