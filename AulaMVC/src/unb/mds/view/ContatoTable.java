package unb.mds.view;


import javax.swing.JTable;

import unb.mds.model.Contato;


public class ContatoTable extends JTable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1408093677993168106L;
	private ContatoTableModel modelo;
	
	public ContatoTable(){
		modelo = new ContatoTableModel();
		setModel(modelo);
		
	}
	
	public Contato getContatoSelected() {
		int i = getSelectedRow();
		if (i < 0) {
			return null;
		}
		return modelo.getContatoAt(i);
	}
	
	public void reload() {
		modelo.reload();
	}
}
