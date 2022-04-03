package controller;

import java.util.List;

import dao.ChamadoDao;
import dao.VeiculoDao;
import exception.RegraNegocioException;
import model.Chamado;

public class ChamadoController {

	public void criarChamado(Chamado chamado) throws RegraNegocioException {
		if (chamado.getDataInicio() == null) 
			throw new RegraNegocioException("Erro: Data de in�cio inv�lida.");
		
		if (chamado.getEndereco() == null || chamado.getEndereco().length() < 5) 
			throw new RegraNegocioException("Erro: Endere�o inv�lido.");
		
		if (chamado.getDistancia() < 1)
			throw new RegraNegocioException("Erro: Dist�ncia inv�lida.");
		
		if (!chamado.getColaborador().isHabilitado()) 
			throw new RegraNegocioException("Erro: Colaborador n�o habilitado.");
		
		if (!chamado.getVeiculo().isDisponivel()) 
			throw new RegraNegocioException("Erro: Ve�culo n�o dispon�vel.");
		
		ChamadoDao.getInstance().criarChamado(chamado);
		chamado.getVeiculo().setDisponivel(false);
		VeiculoDao.getInstance().atualizar(chamado.getVeiculo());
	}
	
	public void atualizar(Chamado chamado) throws RegraNegocioException {
		if (chamado.getEndereco() == null || chamado.getEndereco().length() < 5)
			throw new RegraNegocioException("Erro: Endere�o inv�lido."); 
		
		if (chamado.getDistancia() < 1)
			throw new RegraNegocioException("Erro: Dist�ncia inv�lida.");
		
		ChamadoDao.getInstance().atualizar(chamado);
	}
	
	public void encerrarChamado(Chamado chamado) throws RegraNegocioException {
		if (!chamado.isConcluido()) {
			throw new RegraNegocioException("Erro: Chamado n�o foi conclu�do.");
		}
		
		ChamadoDao.getInstance().atualizar(chamado);
		chamado.getVeiculo().setDisponivel(true);
		VeiculoDao.getInstance().atualizar(chamado.getVeiculo());
	}
	
	public void excluir(Chamado chamado) throws RegraNegocioException {
		if (chamado.getId() == 0) {
			throw new RegraNegocioException("Erro: IdChamado inv�lido");
		}
		
		ChamadoDao.getInstance().excluir(chamado.getId());
		chamado.getVeiculo().setDisponivel(true);
		VeiculoDao.getInstance().atualizar(chamado.getVeiculo());
	}
	
	public List<Chamado> listar() {
		List<Chamado> chamados = ChamadoDao.getInstance().listar();
		return chamados;
	}
	
	public Chamado listById(int id) {
		return ChamadoDao.getInstance().listById(id);
	}
	
}
