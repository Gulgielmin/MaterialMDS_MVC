package unb.mds.view;


import javax.swing.table.AbstractTableModel;


import unb.mds.controller.ContatoController;
import unb.mds.model.Contato;

public class ContatoTableModel extends AbstractTableModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2893624404573071124L;

	private String[] colNomes = { "Nome", "Telefone", "Email", "Endereco" };
	private Class<?>[] colTipos = { String.class, String.class, String.class, String.class };
	
	public ContatoTableModel(){
		
	}
	
	public void reload() {
		fireTableDataChanged();
	}

	@Override
	public Class<?> getColumnClass(int coluna) {
		return colTipos[coluna];
	}

	@Override
	public int getColumnCount() {
		return 4;
	}

	@Override
	public String getColumnName(int coluna) {
		return colNomes[coluna];
	}

	@Override
	public int getRowCount() {
		if (ContatoController.getInstance().listarContatos() == null){
			return 0;
		}
		return ContatoController.getInstance().listarContatos().size();
	}

	@Override
	public Object getValueAt(int linha, int coluna) {
		Contato c = ContatoController.getInstance().listarContatos().get(linha);
		switch (coluna) {
		case 0:
			return c.getNome();
		case 1:
			return c.getTelefone();
		case 2:
			return c.getEmail();
		case 3:
			return c.getEndereco();
		default:
			return null;
		}
	}
	
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}
	
	public Contato getContatoAt(int i) {
		return ContatoController.getInstance().listarContatos().get(i);
	}
}


