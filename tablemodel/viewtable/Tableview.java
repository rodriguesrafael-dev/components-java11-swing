package br.com.grupolider.app_impressao_etiqueta_pallet.shared.components.tablemodel.viewtable;

import br.com.grupolider.app_impressao_etiqueta_pallet.domain.dto.SelectPalete_dto;
import br.com.grupolider.app_impressao_etiqueta_pallet.shared.components.tablemodel.model.Tablemodel;
import br.com.grupolider.app_impressao_etiqueta_pallet.shared.components.tablemodel.renderer.CheckBoxHeader;
import br.com.grupolider.app_impressao_etiqueta_pallet.shared.components.tablemodel.renderer.TableCellRenderer;
import java.awt.Dimension;
import java.util.List;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

public class Tableview {

    private final JTable jtable;
    private final Tablemodel tablemodel;
    private final TableCellRenderer tableCellRenderer;
    private CheckBoxHeader checkBoxHeader; 

    public Tableview(JTable jtable) {
        this.jtable = jtable;
        tablemodel = new Tablemodel();
        tableCellRenderer = new TableCellRenderer();
        formatTable();
    }

    public void formatTable() {
        jtable.setModel(tablemodel);

        jtable.setDefaultRenderer(Boolean.class, tableCellRenderer);
        jtable.setDefaultRenderer(Object.class, tableCellRenderer);
        jtable.setDefaultRenderer(String.class, tableCellRenderer);

        tableCellRenderer.getFormat(jtable);

        jtable.getColumnModel().getColumn(0).setCellEditor(new DefaultCellEditor(new JCheckBox()));

        checkBoxHeader = new CheckBoxHeader(jtable, 0);
        jtable.getColumnModel().getColumn(0).setHeaderRenderer(checkBoxHeader);

        jtable.setShowGrid(false);
        jtable.setIntercellSpacing(new Dimension(0, 1));
        jtable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    public void updateData(List<SelectPalete_dto> dados) {
        tablemodel.setDados(dados);

        if (checkBoxHeader != null) {
            checkBoxHeader.resetWhenEmpty();
        }

        jtable.repaint();
        jtable.revalidate();
    }
    
}
