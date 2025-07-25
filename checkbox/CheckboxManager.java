package br.com.grupolider.app_impressao_etiqueta_pallet.shared.components.checkbox;

import br.com.grupolider.app_impressao_etiqueta_pallet.shared.util.CheckboxStatus;
import javax.swing.*;
import java.awt.event.ItemEvent;
import java.util.List;
import java.util.stream.Collectors;

public class CheckboxManager {

    private final List<CheckboxStatus> checkboxesStatus;

    public CheckboxManager(List<CheckboxStatus> checkboxesStatus) {
        this.checkboxesStatus = checkboxesStatus;
        setupCheckboxListeners();
    }

    private void setupCheckboxListeners() {
        for (CheckboxStatus checkboxStatus : checkboxesStatus) {
            JCheckBox checkbox = checkboxStatus.getCheckBox();

            checkbox.addItemListener((ItemEvent e) -> {
                if (e.getStateChange() == ItemEvent.DESELECTED) {
                    if (contarCheckboxesMarcados() == 0) {
                        SwingUtilities.invokeLater(() -> {
                            checkbox.setSelected(true);
                        });
                        return;
                    }
                }
                onCheckboxChanged();
            });
        }
    }

    private int contarCheckboxesMarcados() {
        int count = 0;
        
        count = checkboxesStatus.stream()
                .filter(checkboxStatus -> (checkboxStatus.getCheckBox().isSelected()))
                .map(_item -> 1)
                .reduce(count, Integer::sum);
        
        return count;
    }

    public java.util.List<String> getStatusSelecionados() {
        return checkboxesStatus.stream()
                .filter(cs -> cs.getCheckBox().isSelected())
                .map(CheckboxStatus::getValor)
                .collect(Collectors.toList());
    }

    protected void onCheckboxChanged() {
        System.out.println("Status selecionados: " + getStatusSelecionados());
    }

    public void garantirPeloMenosUmSelecionado() {
        if (contarCheckboxesMarcados() == 0) {
            if (!checkboxesStatus.isEmpty()) {
                checkboxesStatus.get(0).getCheckBox().setSelected(true);
            }
        }
    }

    public void selecionarTodos(boolean selecionar) {
        checkboxesStatus.forEach(checkboxStatus -> {
            checkboxStatus.getCheckBox().setSelected(selecionar);
        });
    }
    
}
