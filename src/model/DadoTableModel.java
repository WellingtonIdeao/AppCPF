package model;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class DadoTableModel extends AbstractTableModel {
	private List<Dado> dados;
	private String[] colunas ={"Nome","CPF"};
	
	public DadoTableModel(Dado dado){
        this.dados = new ArrayList<Dado>();
        this.dados.add(dado);
        this.fireTableDataChanged();
    }
	public DadoTableModel(List<Dado> list){
		this.dados = list;
		this.fireTableDataChanged();
	}
     
    public void addRow(Dado dado){
        this.dados.add(dado);
        this.fireTableDataChanged();
    }
    public void clearTable(){
    	this.dados = new ArrayList<Dado>();
    	this.fireTableDataChanged();
    }
    
    public void setDados(List<Dado> dados) {
		this.dados = dados;
		this.fireTableDataChanged();
	}
	public Dado getDado(int index){
    	return this.dados.get(index);
    }
 
    public String getColumnName(int num){
        return this.colunas[num];
    }
	
	@Override
	public int getColumnCount() {
		return this.colunas.length;
	}

	@Override
	public int getRowCount() {
		 return this.dados.size();
	}

	@Override
	public Object getValueAt(int linha, int coluna) {
		switch(coluna){
		case 0: return this.dados.get(linha).getNome();
		case 1: return this.dados.get(linha).getCpf();
		}
		return null;
	}

}
