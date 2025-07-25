package br.com.grupolider.app_impressao_etiqueta_pallet.shared.components.datechooser;

public final class Months extends javax.swing.JPanel {

    private Event event;

    public Months() {
        initComponents();
    }

    private void addEvent() {
        for (int i = 0; i < getComponentCount(); i++) {
            ((Button) getComponent(i)).setEvent(event);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cmd1 = new br.com.grupolider.app_impressao_etiqueta_pallet.shared.components.datechooser.Button();
        cmd2 = new br.com.grupolider.app_impressao_etiqueta_pallet.shared.components.datechooser.Button();
        cmd3 = new br.com.grupolider.app_impressao_etiqueta_pallet.shared.components.datechooser.Button();
        cmd4 = new br.com.grupolider.app_impressao_etiqueta_pallet.shared.components.datechooser.Button();
        cmd5 = new br.com.grupolider.app_impressao_etiqueta_pallet.shared.components.datechooser.Button();
        cmd6 = new br.com.grupolider.app_impressao_etiqueta_pallet.shared.components.datechooser.Button();
        cmd7 = new br.com.grupolider.app_impressao_etiqueta_pallet.shared.components.datechooser.Button();
        cmd8 = new br.com.grupolider.app_impressao_etiqueta_pallet.shared.components.datechooser.Button();
        cmd9 = new br.com.grupolider.app_impressao_etiqueta_pallet.shared.components.datechooser.Button();
        cmd10 = new br.com.grupolider.app_impressao_etiqueta_pallet.shared.components.datechooser.Button();
        cmd11 = new br.com.grupolider.app_impressao_etiqueta_pallet.shared.components.datechooser.Button();
        cmd12 = new br.com.grupolider.app_impressao_etiqueta_pallet.shared.components.datechooser.Button();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new java.awt.GridLayout(4, 4));

        cmd1.setBackground(new java.awt.Color(255, 255, 255));
        cmd1.setForeground(new java.awt.Color(75, 75, 75));
        cmd1.setText("Janeiro");
        cmd1.setName("1"); 
        cmd1.setOpaque(true);
        add(cmd1);

        cmd2.setBackground(new java.awt.Color(255, 255, 255));
        cmd2.setForeground(new java.awt.Color(75, 75, 75));
        cmd2.setText("Fevereiro");
        cmd2.setName("2"); 
        cmd2.setOpaque(true);
        add(cmd2);

        cmd3.setBackground(new java.awt.Color(255, 255, 255));
        cmd3.setForeground(new java.awt.Color(75, 75, 75));
        cmd3.setText("Marco");
        cmd3.setName("3"); 
        cmd3.setOpaque(true);
        add(cmd3);

        cmd4.setBackground(new java.awt.Color(255, 255, 255));
        cmd4.setForeground(new java.awt.Color(75, 75, 75));
        cmd4.setText("Abril");
        cmd4.setName("4"); 
        cmd4.setOpaque(true);
        add(cmd4);

        cmd5.setBackground(new java.awt.Color(255, 255, 255));
        cmd5.setForeground(new java.awt.Color(75, 75, 75));
        cmd5.setText("Maio");
        cmd5.setName("5"); 
        cmd5.setOpaque(true);
        add(cmd5);

        cmd6.setBackground(new java.awt.Color(255, 255, 255));
        cmd6.setForeground(new java.awt.Color(75, 75, 75));
        cmd6.setText("Junho");
        cmd6.setName("6"); 
        cmd6.setOpaque(true);
        add(cmd6);

        cmd7.setBackground(new java.awt.Color(255, 255, 255));
        cmd7.setForeground(new java.awt.Color(75, 75, 75));
        cmd7.setText("Julho");
        cmd7.setName("7");
        cmd7.setOpaque(true);
        add(cmd7);

        cmd8.setBackground(new java.awt.Color(255, 255, 255));
        cmd8.setForeground(new java.awt.Color(75, 75, 75));
        cmd8.setText("Agosto");
        cmd8.setName("8");
        cmd8.setOpaque(true);
        add(cmd8);

        cmd9.setBackground(new java.awt.Color(255, 255, 255));
        cmd9.setForeground(new java.awt.Color(75, 75, 75));
        cmd9.setText("Setembro");
        cmd9.setName("9"); 
        cmd9.setOpaque(true);
        add(cmd9);

        cmd10.setBackground(new java.awt.Color(255, 255, 255));
        cmd10.setForeground(new java.awt.Color(75, 75, 75));
        cmd10.setText("Outubro");
        cmd10.setName("10"); 
        cmd10.setOpaque(true);
        add(cmd10);

        cmd11.setBackground(new java.awt.Color(255, 255, 255));
        cmd11.setForeground(new java.awt.Color(75, 75, 75));
        cmd11.setText("Novembro");
        cmd11.setName("11"); 
        cmd11.setOpaque(true);
        add(cmd11);

        cmd12.setBackground(new java.awt.Color(255, 255, 255));
        cmd12.setForeground(new java.awt.Color(75, 75, 75));
        cmd12.setText("Dezembro");
        cmd12.setName("12"); 
        cmd12.setOpaque(true);
        add(cmd12);
    }// </editor-fold>//GEN-END:initComponents

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
        addEvent();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private br.com.grupolider.app_impressao_etiqueta_pallet.shared.components.datechooser.Button cmd1;
    private br.com.grupolider.app_impressao_etiqueta_pallet.shared.components.datechooser.Button cmd10;
    private br.com.grupolider.app_impressao_etiqueta_pallet.shared.components.datechooser.Button cmd11;
    private br.com.grupolider.app_impressao_etiqueta_pallet.shared.components.datechooser.Button cmd12;
    private br.com.grupolider.app_impressao_etiqueta_pallet.shared.components.datechooser.Button cmd2;
    private br.com.grupolider.app_impressao_etiqueta_pallet.shared.components.datechooser.Button cmd3;
    private br.com.grupolider.app_impressao_etiqueta_pallet.shared.components.datechooser.Button cmd4;
    private br.com.grupolider.app_impressao_etiqueta_pallet.shared.components.datechooser.Button cmd5;
    private br.com.grupolider.app_impressao_etiqueta_pallet.shared.components.datechooser.Button cmd6;
    private br.com.grupolider.app_impressao_etiqueta_pallet.shared.components.datechooser.Button cmd7;
    private br.com.grupolider.app_impressao_etiqueta_pallet.shared.components.datechooser.Button cmd8;
    private br.com.grupolider.app_impressao_etiqueta_pallet.shared.components.datechooser.Button cmd9;
    // End of variables declaration//GEN-END:variables

}
