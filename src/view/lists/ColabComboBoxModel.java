package view.lists;

import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

import model.Colaborador;

public class ColabComboBoxModel extends AbstractListModel implements ComboBoxModel {

	private List<Colaborador> listaColaboradores;
	private Colaborador colaboradorSelecionado;
	
	public ColabComboBoxModel() {
		this.listaColaboradores = new ArrayList<>();
	}
	
	@Override
	public int getSize() {
		return listaColaboradores.size();
	}

	@Override
	public Object getElementAt(int index) {
		return this.listaColaboradores.get(index);
	}

	@Override
	public void setSelectedItem(Object anItem) {
		if(anItem instanceof Colaborador) {
			this.colaboradorSelecionado = (Colaborador) anItem;
			fireContentsChanged(this.listaColaboradores, 0, this.listaColaboradores.size());
		}
	}

	@Override
	public Object getSelectedItem() {
		return this.colaboradorSelecionado;
	}
	
	public void addColaboradores(Colaborador colab) {
		listaColaboradores.add(colab);
	}
	
}
