package br.com.grupolider.app_impressao_etiqueta_pallet.shared.components.datechooser;

public interface SelectedAction {
    
    public final int DAY_SELECTED = 1;
    public final int MONTH_SELECTED = 2;
    public final int YEAR_SELECTED = 3;

    public int getAction();
    
}
