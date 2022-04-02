package controller;

import java.util.List;

import dao.ColaboradorDao;
import exception.RegraNegocioException;
import model.Colaborador;

public class ColaboradorController {

	public void salvar(Colaborador colaborador) throws RegraNegocioException {
		if (!validarNome(colaborador.getNome())) {
			throw new RegraNegocioException("Nome inválido.");
		}
		
		ColaboradorDao.getInstance().salvar(colaborador);
	}
	
	public void atualizar(Colaborador colaborador) throws RegraNegocioException  {
		if (!validarNome(colaborador.getNome())) {
			throw new RegraNegocioException("Erro: Nome inválido.");
		}
		
		ColaboradorDao.getInstance().atualizar(colaborador);
	}
	
	public void excluir(int id) throws RegraNegocioException {
		if (id == 0) {
			throw new RegraNegocioException("Erro: Id inválido.");
		}
		
		ColaboradorDao.getInstance().excluir(id);
	}
	
	public List<Colaborador> listar() {
		List<Colaborador> colaboradores = ColaboradorDao.getInstance().listar();
		return colaboradores;
	}
	
	public Colaborador listById(int id) {
		return ColaboradorDao.getInstance().listById(id);
	}
	
	private boolean validarNome(String nome) {
		if (nome == null || nome.length() < 3) {
			return false;
		} else {
			return true;
		}
	}
}
