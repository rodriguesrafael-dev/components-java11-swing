package br.com.grupolider.app_impressao_etiqueta_pallet.shared.components.tablemodel.model;

import br.com.grupolider.app_impressao_etiqueta_pallet.domain.dto.SelectPalete_dto;
import br.com.grupolider.app_impressao_etiqueta_pallet.shared.util.DateConverterRMSOracle;
import br.com.grupolider.app_impressao_etiqueta_pallet.shared.util.HourConverterRMSOracle;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class Tablemodel extends AbstractTableModel {

    private static final int CHECKBOX = 0;
    private static final int DATA = 1;
    private static final int HORA = 2;
    private static final int CARGA = 3;
    private static final int PALETE = 4;
    
    private List<SelectPalete_dto> row;
    private List<Boolean> selecionados;

    private static final String[] columns = {
        "CHECKBOX", 
        "DATA", 
        "HORA", 
        "CARGA", 
        "PALETE"
    };

    public Tablemodel() {
        this.row = new ArrayList<>();
        this.selecionados = new ArrayList<>();
    }

    public void setDados(List<SelectPalete_dto> dados) {
        this.row = new ArrayList<>(dados);
        this.selecionados = new ArrayList<>();
        
        dados.forEach(_item -> {
            selecionados.add(false);
        });
        
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return row.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex == CHECKBOX) {
            return Boolean.class;
        }
        return String.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == CHECKBOX;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        SelectPalete_dto dto = row.get(rowIndex);
        switch (columnIndex) {
            case CHECKBOX:
                return selecionados.get(rowIndex);
            case DATA:
                return DateConverterRMSOracle.set(dto.getData());
            case HORA:
                return HourConverterRMSOracle.set(dto.getHora());
            case CARGA:
                return dto.getCarga();
            case PALETE:
                return dto.getPalete();
            default:
                throw new IndexOutOfBoundsException("Coluna inválida");
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        if (columnIndex == CHECKBOX && aValue instanceof Boolean) {
            selecionados.set(rowIndex, (Boolean) aValue);
            fireTableCellUpdated(rowIndex, columnIndex);
        }
    }

    public void setAllSelecionados(boolean selected) {
        for (int i = 0; i < selecionados.size(); i++) {
            selecionados.set(i, selected);
        }
        fireTableDataChanged();
    }

}
