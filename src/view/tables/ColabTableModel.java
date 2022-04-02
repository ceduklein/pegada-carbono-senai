package view.tables;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Colaborador;

public class ColabTableModel extends AbstractTableModel {
	
	private static final long serialVersionUID = 1L;
	private static final int COL_ID = 0;
	private static final int COL_NOME = 1;
	private static final int COL_HABILITADO = 2;
	
	private List<Colaborador> colaboradores;
	
	// Construtor que recebe a lista de clientes
	public ColabTableModel(List<Colaborador> colaboradores) {
		this.colaboradores = new ArrayList<Colaborador>(colaboradores);
	}

	@Override
	public int getRowCount() {
		return colaboradores.size();
	}

	@Override
	public int getColumnCount() {
		return 3;
	}
	
	public String getColumnName(int column) {
		if (column == COL_ID) return "Id";
		if (column == COL_NOME) return "Nome";
		if (column == COL_HABILITADO) return "Habilitado";
		return "";
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Colaborador colab = colaboradores.get(rowIndex);
		if (columnIndex == COL_ID)
			return colab.getId();
		else if (columnIndex == COL_NOME)
			return colab.getNome();
		else if (columnIndex == COL_HABILITADO)
			return verificarHabilitacao(colab.isHabilitado());
		return "";
	}
	
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		Colaborador colab = colaboradores.get(rowIndex);
		if (columnIndex == COL_ID)
			colab.setId(Integer.parseInt(aValue.toString()));
		else if (columnIndex == COL_NOME)
			colab.setNome(aValue.toString());
		else if (columnIndex == COL_HABILITADO)
			colab.setHabilitado(Boolean.parseBoolean(aValue.toString()));
	}
	
	public Class<?> getColumnClass(int columnIndex) {
		// Pegar classe da coluna
		return String.class;
	}
	
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		// Coluna é editável?
		return true;
	}
	
	public Colaborador get(int row) {
		// Retorna o objeto colaborador da linha selecionada
		return colaboradores.get(row);
	}
	
	public String verificarHabilitacao(boolean habilitado) {
		if (habilitado) 
			return "Sim";
		else
			return "Não";
	}

}
