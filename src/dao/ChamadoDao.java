package dao;

import java.util.ArrayList;
import java.util.List;

import model.Chamado;

public class ChamadoDao {

	private static ChamadoDao instance;
	private List<Chamado> chamados = new ArrayList<>();
	
	public static ChamadoDao getInstance() {
		if (instance == null) {
			instance = new ChamadoDao();
		}
		return instance;
	}
	
	public void criarChamado(Chamado chamado) {
		chamados.add(chamado);
	}
	
	public void atualizar(Chamado chamado) {
		chamados.set(chamado.getId() - 1 , chamado);
	}
	
	public void excluir(int id) {
		chamados.remove(id - 1);
	}
	
	public List<Chamado> listar() {
		return chamados;
	}
	
	public Chamado listById(int id) {
		return chamados.get(id - 1);
	}
	
}
