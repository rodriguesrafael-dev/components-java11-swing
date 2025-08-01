package br.com.grupolider.app_impressao_etiqueta_pallet.shared.components.datechooser;

import javax.swing.JButton;

public final class Years extends javax.swing.JPanel {

    private Event event;
    private int startYear;

    public Years() {
        initComponents();
    }

    public int showYear(int year) {
        year = calculateYear(year);
        for (int i = 0; i < getComponentCount(); i++) {
            JButton cmd = (JButton) getComponent(i);
            cmd.setText(year + "");
            year++;
        }
        return startYear;
    }

    private int calculateYear(int year) {
        year -= year % 10;
        startYear = year;
        return year;
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
        cmd13 = new br.com.grupolider.app_impressao_etiqueta_pallet.shared.components.datechooser.Button();
        cmd14 = new br.com.grupolider.app_impressao_etiqueta_pallet.shared.components.datechooser.Button();
        cmd15 = new br.com.grupolider.app_impressao_etiqueta_pallet.shared.components.datechooser.Button();
        cmd16 = new br.com.grupolider.app_impressao_etiqueta_pallet.shared.components.datechooser.Button();
        cmd17 = new br.com.grupolider.app_impressao_etiqueta_pallet.shared.components.datechooser.Button();
        cmd18 = new br.com.grupolider.app_impressao_etiqueta_pallet.shared.components.datechooser.Button();
        cmd19 = new br.com.grupolider.app_impressao_etiqueta_pallet.shared.components.datechooser.Button();
        cmd20 = new br.com.grupolider.app_impressao_etiqueta_pallet.shared.components.datechooser.Button();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new java.awt.GridLayout(5, 4));

        cmd1.setBackground(new java.awt.Color(255, 255, 255));
        cmd1.setForeground(new java.awt.Color(75, 75, 75));
        cmd1.setText("2010");
        cmd1.setName("year"); 
        cmd1.setOpaque(true);
        add(cmd1);

        cmd2.setBackground(new java.awt.Color(255, 255, 255));
        cmd2.setForeground(new java.awt.Color(75, 75, 75));
        cmd2.setText("2011");
        cmd2.setName("year"); 
        cmd2.setOpaque(true);
        add(cmd2);

        cmd3.setBackground(new java.awt.Color(255, 255, 255));
        cmd3.setForeground(new java.awt.Color(75, 75, 75));
        cmd3.setText("2012");
        cmd3.setName("year"); 
        cmd3.setOpaque(true);
        add(cmd3);

        cmd4.setBackground(new java.awt.Color(255, 255, 255));
        cmd4.setForeground(new java.awt.Color(75, 75, 75));
        cmd4.setText("2013");
        cmd4.setName("year"); 
        cmd4.setOpaque(true);
        add(cmd4);

        cmd5.setBackground(new java.awt.Color(255, 255, 255));
        cmd5.setForeground(new java.awt.Color(75, 75, 75));
        cmd5.setText("2014");
        cmd5.setName("year"); 
        cmd5.setOpaque(true);
        add(cmd5);

        cmd6.setBackground(new java.awt.Color(255, 255, 255));
        cmd6.setForeground(new java.awt.Color(75, 75, 75));
        cmd6.setText("2015");
        cmd6.setName("year"); 
        cmd6.setOpaque(true);
        add(cmd6);

        cmd7.setBackground(new java.awt.Color(255, 255, 255));
        cmd7.setForeground(new java.awt.Color(75, 75, 75));
        cmd7.setText("2016");
        cmd7.setName("year"); 
        cmd7.setOpaque(true);
        add(cmd7);

        cmd8.setBackground(new java.awt.Color(255, 255, 255));
        cmd8.setForeground(new java.awt.Color(75, 75, 75));
        cmd8.setText("2017");
        cmd8.setName("year"); 
        cmd8.setOpaque(true);
        add(cmd8);

        cmd9.setBackground(new java.awt.Color(255, 255, 255));
        cmd9.setForeground(new java.awt.Color(75, 75, 75));
        cmd9.setText("2018");
        cmd9.setName("year"); 
        cmd9.setOpaque(true);
        add(cmd9);

        cmd10.setBackground(new java.awt.Color(255, 255, 255));
        cmd10.setForeground(new java.awt.Color(75, 75, 75));
        cmd10.setText("2019");
        cmd10.setName("year"); 
        cmd10.setOpaque(true);
        add(cmd10);

        cmd11.setBackground(new java.awt.Color(255, 255, 255));
        cmd11.setForeground(new java.awt.Color(75, 75, 75));
        cmd11.setText("2020");
        cmd11.setName("year");
        cmd11.setOpaque(true);
        add(cmd11);

        cmd12.setBackground(new java.awt.Color(255, 255, 255));
        cmd12.setForeground(new java.awt.Color(75, 75, 75));
        cmd12.setText("2021");
        cmd12.setName("year"); 
        cmd12.setOpaque(true);
        add(cmd12);

        cmd13.setBackground(new java.awt.Color(255, 255, 255));
        cmd13.setForeground(new java.awt.Color(75, 75, 75));
        cmd13.setText("2022");
        cmd13.setName("year");
        cmd13.setOpaque(true);
        add(cmd13);

        cmd14.setBackground(new java.awt.Color(255, 255, 255));
        cmd14.setForeground(new java.awt.Color(75, 75, 75));
        cmd14.setText("2023");
        cmd14.setName("year"); 
        cmd14.setOpaque(true);
        add(cmd14);

        cmd15.setBackground(new java.awt.Color(255, 255, 255));
        cmd15.setForeground(new java.awt.Color(75, 75, 75));
        cmd15.setText("2024");
        cmd15.setName("year"); 
        cmd15.setOpaque(true);
        add(cmd15);

        cmd16.setBackground(new java.awt.Color(255, 255, 255));
        cmd16.setForeground(new java.awt.Color(75, 75, 75));
        cmd16.setText("2025");
        cmd16.setName("year"); 
        cmd16.setOpaque(true);
        add(cmd16);

        cmd17.setBackground(new java.awt.Color(255, 255, 255));
        cmd17.setForeground(new java.awt.Color(75, 75, 75));
        cmd17.setText("2026");
        cmd17.setName("year"); 
        cmd17.setOpaque(true);
        add(cmd17);

        cmd18.setBackground(new java.awt.Color(255, 255, 255));
        cmd18.setForeground(new java.awt.Color(75, 75, 75));
        cmd18.setText("2027");
        cmd18.setName("year"); 
        cmd18.setOpaque(true);
        add(cmd18);

        cmd19.setBackground(new java.awt.Color(255, 255, 255));
        cmd19.setForeground(new java.awt.Color(75, 75, 75));
        cmd19.setText("2028");
        cmd19.setName("year"); 
        cmd19.setOpaque(true);
        add(cmd19);

        cmd20.setBackground(new java.awt.Color(255, 255, 255));
        cmd20.setForeground(new java.awt.Color(75, 75, 75));
        cmd20.setText("2029");
        cmd20.setName("year"); 
        cmd20.setOpaque(true);
        add(cmd20);
    }// </editor-fold>//GEN-END:initComponents

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
        addEvent();
    }

    public int next(int year) {
        showYear(year + 20);
        return startYear;
    }

    public int back(int year) {
        showYear(year - 20);
        return startYear;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private br.com.grupolider.app_impressao_etiqueta_pallet.shared.components.datechooser.Button cmd1;
    private br.com.grupolider.app_impressao_etiqueta_pallet.shared.components.datechooser.Button cmd10;
    private br.com.grupolider.app_impressao_etiqueta_pallet.shared.components.datechooser.Button cmd11;
    private br.com.grupolider.app_impressao_etiqueta_pallet.shared.components.datechooser.Button cmd12;
    private br.com.grupolider.app_impressao_etiqueta_pallet.shared.components.datechooser.Button cmd13;
    private br.com.grupolider.app_impressao_etiqueta_pallet.shared.components.datechooser.Button cmd14;
    private br.com.grupolider.app_impressao_etiqueta_pallet.shared.components.datechooser.Button cmd15;
    private br.com.grupolider.app_impressao_etiqueta_pallet.shared.components.datechooser.Button cmd16;
    private br.com.grupolider.app_impressao_etiqueta_pallet.shared.components.datechooser.Button cmd17;
    private br.com.grupolider.app_impressao_etiqueta_pallet.shared.components.datechooser.Button cmd18;
    private br.com.grupolider.app_impressao_etiqueta_pallet.shared.components.datechooser.Button cmd19;
    private br.com.grupolider.app_impressao_etiqueta_pallet.shared.components.datechooser.Button cmd2;
    private br.com.grupolider.app_impressao_etiqueta_pallet.shared.components.datechooser.Button cmd20;
    private br.com.grupolider.app_impressao_etiqueta_pallet.shared.components.datechooser.Button cmd3;
    private br.com.grupolider.app_impressao_etiqueta_pallet.shared.components.datechooser.Button cmd4;
    private br.com.grupolider.app_impressao_etiqueta_pallet.shared.components.datechooser.Button cmd5;
    private br.com.grupolider.app_impressao_etiqueta_pallet.shared.components.datechooser.Button cmd6;
    private br.com.grupolider.app_impressao_etiqueta_pallet.shared.components.datechooser.Button cmd7;
    private br.com.grupolider.app_impressao_etiqueta_pallet.shared.components.datechooser.Button cmd8;
    private br.com.grupolider.app_impressao_etiqueta_pallet.shared.components.datechooser.Button cmd9;
    // End of variables declaration//GEN-END:variables

}
