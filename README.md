# components-java11-swing
Componentes customizados, inspirados no material (Angular), em Java 11 Swing

# Exemplo de uso

```java
package br.com.example;

import br.com.shared.components.DialogCustom;
import br.com.shared.components.JButtonCustom;
import br.com.shared.components.checkbox.JCheckBoxCustom;
import br.com.shared.components.JComboBoxCustom;
import br.com.shared.components.JPanelCustom;
import br.com.shared.components.JTextFieldCustom;
import br.com.shared.components.checkbox.CheckboxManager;
import br.com.shared.components.datechooser.JTextFieldDateCustom;
import br.com.shared.components.tablemodel.viewtable.Tableview;

public class Example {

    private final List<CheckboxStatus> checkboxesStatus = new ArrayList<>();
    private CheckboxManager checkboxManager;

    public static void main(String[] args) {
        
        new Tableview(jTable).formatTable();

        JPanelCustom.aplicarEstiloFlatArredondado(jPanelExemplo);
        
        JComboBoxCustom.aplicarEstiloFlatArredondado(jComboBoxExemplo);

        JTextFieldDateCustom.aplicarEstiloFlatArredondado(jTextFieldDataExemplo);

        JTextFieldCustom.aplicarEstiloFlatArredondado(jTextFieldExemplo, true);

        JButtonCustom.aplicarEstiloFlatArredondado(jButtonExemplo, null, "/br/com/caminho/do/icone", new Dimension(36, 36), true);

        JCheckBoxCustom.aplicarEstiloFlatArredondado(jCheckBoxExemploStatus1, true);
        checkboxesStatus.add(new CheckboxStatus(jCheckBoxExemploStatus1, 1));

        JCheckBoxCustom.aplicarEstiloFlatArredondado(jCheckBoxExemploStatus2, true);
        checkboxesStatus.add(new CheckboxStatus(jCheckBoxExemploStatus2, 2));

        checkboxManager = new CheckboxManager(checkboxesStatus) {
            @Override
            protected void onCheckboxChanged() {
                filtrarDadosTabela();
            }
        };

        checkboxManager.garantirPeloMenosUmSelecionado();
    }

    private void filtrarDadosTabela() {
        List<String> statusSelecionados = checkboxManager.getStatusSelecionados();
        // List<SelectStatusSelecionado> dadosFiltrados = service.buscarPorStatus(statusSelecionados);
        // tableview.updateData(dadosFiltrados);
        System.out.println("Filtrando por status: " + statusSelecionados);
    }

    private void onClickPesquisarStatusSelecionado() {
        List<String> statusSelecionados = checkboxesStatus.stream()
                .filter(cs -> cs.getCheckBox().isSelected())
                .map(CheckboxStatus::getValor)
                .collect(Collectors.toList());

        String data = jTextFieldData.getText();

        DialogSpinnerProgress dialog = new DialogSpinnerProgress(this, true);
        dialog.setModal(false);
        dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);

        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                new ConsultaStatusSelecionadoService(jTable).get(statusSelecionados, new FormatDate.set(data));
                return null;
            }

            @Override
            protected void done() {
                dialog.dispose(); 
            }
        }.execute();
    }
    
}
```
