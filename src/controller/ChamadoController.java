package controller;

import java.util.List;

import dao.ChamadoDao;
import dao.VeiculoDao;
import exception.RegraNegocioException;
import model.Chamado;

public class ChamadoController {

	public void criarChamado(Chamado chamado) throws RegraNegocioException {
		if (chamado.getDataInicio() == null) {
			throw new RegraNegocioException("Erro: Data de início inválida.");
		}
		
		if (chamado.getEndereco() == null || chamado.getEndereco().length() < 5) {
			throw new RegraNegocioException("Erro: Endereço inválido.");
		}
		
		if (!chamado.getColaborador().isHabilitado()) {
			throw new RegraNegocioException("Erro: Colaborador não habilitado.");
		}
		
		if (!chamado.getVeiculo().isDisponivel()) {
			throw new RegraNegocioException("Erro: Veículo não disponível.");
		}
		
		ChamadoDao.getInstance().criarChamado(chamado);
		chamado.getVeiculo().setDisponivel(false);
		VeiculoDao.getInstance().atualizar(chamado.getVeiculo());
	}
	
	public void encerrarChamado(Chamado chamado) throws RegraNegocioException {
		if (!chamado.isConcluido()) {
			throw new RegraNegocioException("Erro: Chamado não foi concluído.");
		}
		
		ChamadoDao.getInstance().atualizar(chamado);
		chamado.getVeiculo().setDisponivel(true);
		VeiculoDao.getInstance().atualizar(chamado.getVeiculo());
	}
	
	public void excluir(Chamado chamado) throws RegraNegocioException {
		if (chamado.getId() == 0) {
			throw new RegraNegocioException("Erro: IdChamado inválido");
		}
		
		ChamadoDao.getInstance().excluir(chamado.getId());
		chamado.getVeiculo().setDisponivel(true);
		VeiculoDao.getInstance().atualizar(chamado.getVeiculo());
	}
	
	public List<Chamado> listar() throws RegraNegocioException {
		List<Chamado> chamados = ChamadoDao.getInstance().listar();
		
		if(chamados.isEmpty()) {
			throw new RegraNegocioException("Erro: Não existem chamados cadastrados.");
		}
		
		return chamados;
	}
	
	public Chamado listById(int id) {
		return ChamadoDao.getInstance().listById(id);
	}
	
}
