package view.tables;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Chamado;
import model.Colaborador;
import model.Veiculo;

public class ChamadoTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private static final int COL_ID = 0;
	private static final int COL_DATA = 1;
	private static final int COL_DISTANCIA = 2;
	private static final int COL_ENDERECO = 3;	
	private static final int COL_CO2 = 4;
	private static final int COL_COLAB = 5;
	private static final int COL_VEICULO = 6;
	private static final int COL_SITUACAO = 7;
	
	private List<Chamado> chamados;
	
	// Construtor que recebe a lista de chamados
	public ChamadoTableModel(List<Chamado> chamados) {
		this.chamados = new ArrayList<Chamado>(chamados);
	}
	
	@Override
	public int getRowCount() {
		return chamados.size();
	}

	@Override
	public int getColumnCount() {
		return 8;
	}
	
	public String getColumnName(int column) {
		if (column == COL_ID) return "Id";
		if (column == COL_DATA) return "Data";
		if (column == COL_DISTANCIA) return "Distância (Km)";
		if (column == COL_ENDERECO) return "Endereço";
		if (column == COL_CO2) return "CO²";
		if (column == COL_COLAB) return "Colaborador";
		if (column == COL_VEICULO) return "Veículo";
		if (column == COL_SITUACAO) return "Situação";
		return "";
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Chamado c = chamados.get(rowIndex);
		LocalDate data = c.getDataInicio();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String dataFormatada = data.format(formatter);
		
		DecimalFormat df = new DecimalFormat("#.##");
		
		if (columnIndex == COL_ID)
			return c.getId();
		else if (columnIndex == COL_DATA)
			return dataFormatada;
		else if (columnIndex == COL_DISTANCIA)
			return c.getDistancia();
		else if (columnIndex == COL_ENDERECO)
			return c.getEndereco();
		else if (columnIndex == COL_CO2)
			return df.format(c.getPegadaCarbono());
		else if (columnIndex == COL_COLAB)
			return c.getColaborador().getNome();
		else if (columnIndex == COL_VEICULO)
			return c.getVeiculo().getModelo();
		else if (columnIndex == COL_SITUACAO)
			return verificarSituacaoChamado(c.isConcluido());
		return "";
	}
	
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		Chamado c = chamados.get(rowIndex);
		Colaborador colab = new Colaborador();
		colab.setId(c.getColaborador().getId());
		colab.setNome(c.getColaborador().getNome());
		colab.setHabilitado(c.getColaborador().isHabilitado());
		
		Veiculo v = new Veiculo();
		v.setId(c.getVeiculo().getId());
		v.setModelo(c.getVeiculo().getModelo());
		v.setPlaca(c.getVeiculo().getPlaca());
		v.setDisponivel(c.getVeiculo().isDisponivel());
		v.setKmLitro(c.getVeiculo().getKmLitro());
		
		if (columnIndex == COL_ID)
			c.setId(Integer.parseInt(aValue.toString()));
		else if (columnIndex == COL_DATA)
			c.setDataInicio(LocalDate.parse(aValue.toString()));
		else if (columnIndex == COL_DISTANCIA)
			c.setDistancia(Double.parseDouble(aValue.toString()));
		else if (columnIndex == COL_ENDERECO)
			c.setEndereco(aValue.toString());
		else if (columnIndex == COL_CO2)
			c.setPegadaCarbono(Double.parseDouble(aValue.toString()));
		else if (columnIndex == COL_COLAB)
			c.setColaborador(colab);
		else if (columnIndex == COL_VEICULO)
			c.setVeiculo(v);
		else if (columnIndex == COL_SITUACAO)
			c.setConcluido(Boolean.parseBoolean(aValue.toString()));
	}
	
	public Class<?> getColumnClass(int columnIndex) {
		// Pegar classe da coluna
		return String.class;
	}
	
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		// Coluna é editável?
		return true;
	}
	
	public Chamado get(int row) {
		// Retorna o objeto colaborador da linha selecionada
		return chamados.get(row);
	}
	
	public String verificarSituacaoChamado(boolean concluido) {
		if (concluido)
			return "Encerrado";
		else
			return "Em progresso";
	}
}
