package dao;

import java.util.ArrayList;
import java.util.List;

import model.Colaborador;

public class ColaboradorDao {

	private static ColaboradorDao instance;
	private List<Colaborador> colaboradores = new ArrayList<>();
	
	public static ColaboradorDao getInstance() {
		if (instance == null) {
			instance = new ColaboradorDao();
		}
		return instance;
	}
	
	public void salvar(Colaborador colaborador) {
		colaboradores.add(colaborador);
	}
	
	public void atualizar(Colaborador colaborador) {
		colaboradores.set(colaborador.getId() - 1 , colaborador);
	}
	
	public void excluir(int id) {
		colaboradores.remove(id -1);
	}
	
	public List<Colaborador> listar() {
		return colaboradores;
	}
	
	public Colaborador listById(int id) {
		return colaboradores.get(id - 1);
	}
}
