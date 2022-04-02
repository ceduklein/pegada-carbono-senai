package view.tables;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Veiculo;

public class VeiculoTableModel extends AbstractTableModel{

	private static final long serialVersionUID = 1L;
	private static final int COL_ID = 0;
	private static final int COL_MODELO = 1;
	private static final int COL_PLACA = 2;
	private static final int COL_KMLITRO = 3;
	private static final int COL_HABILITADO = 4;
	
	private List<Veiculo> veiculos;
	
	// Construtor que recebe a lista de clientes
	public VeiculoTableModel(List<Veiculo> veiculos) {
		this.veiculos = new ArrayList<Veiculo>(veiculos);
	}

	@Override
	public int getRowCount() {
		return veiculos.size();
	}

	@Override
	public int getColumnCount() {
		return 5;
	}
	
	public String getColumnName(int column) {
		if (column == COL_ID) return "Id";
		if (column == COL_MODELO) return "Modelo";
		if (column == COL_PLACA) return "Placa";
		if (column == COL_KMLITRO) return "Consumo (km/litro)";
		if (column == COL_HABILITADO) return "Situação";
		return "";
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Veiculo v = veiculos.get(rowIndex);
		if (columnIndex == COL_ID)
			return v.getId();
		else if (columnIndex == COL_MODELO)
			return v.getModelo();
		else if (columnIndex == COL_PLACA)
			return v.getPlaca();
		else if (columnIndex == COL_KMLITRO)
			return v.getKmLitro();
		else if (columnIndex == COL_HABILITADO)
			return verificarDisponibildade(v.isDisponivel());
		return "";
	}
	
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		Veiculo v = veiculos.get(rowIndex);
		if (columnIndex == COL_ID)
			v.setId(Integer.parseInt(aValue.toString()));
		else if (columnIndex == COL_MODELO)
			v.setModelo(aValue.toString());
		else if (columnIndex == COL_PLACA)
			v.setPlaca(aValue.toString());
		else if (columnIndex == COL_KMLITRO)
			v.setKmLitro(Double.parseDouble(aValue.toString()));
		else if (columnIndex == COL_HABILITADO)
			v.setDisponivel(Boolean.parseBoolean(aValue.toString()));
	}
	
	public Class<?> getColumnClass(int columnIndex) {
		// Pegar classe da coluna
		return String.class;
	}
	
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		// Coluna é editável?
		return true;
	}
	
	public Veiculo get(int row) {
		// Retorna o objeto veiculo da linha selecionada
		return veiculos.get(row);
	}
	
	public String verificarDisponibildade(boolean habilitado) {
		if (habilitado) 
			return "Disponível";
		else
			return "Indisponível";
	}
}
