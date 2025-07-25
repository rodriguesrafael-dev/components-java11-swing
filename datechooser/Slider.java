package br.com.grupolider.app_impressao_etiqueta_pallet.shared.components.datechooser;

import java.awt.Component;

public class Slider extends javax.swing.JPanel {

    private boolean sliding = false;

    public Slider() {
        initComponents();
    }

    public boolean slideToRight(Component com) {
        boolean act = false;
        if (!sliding) {
            act = true;
            new Thread(() -> {
                sliding = true;
                Component old = null;
                if (getComponentCount() > 0) {
                    old = getComponent(0);
                }
                add(com);
                com.setLocation(-getWidth(), 0);
                int x1 = 0;
                int width1 = (int) getSize().getWidth() / 70;
                for (int i = -getWidth(); i <= 0; i += width1) {
                    com.setLocation(i, 0);
                    if (old != null) {
                        old.setLocation(x1, 0);
                        x1 += width1;
                    }
                    sleep();
                }
                com.setLocation(0, 0);
                while (getComponentCount() != 1) {
                    remove(getComponentCount() - 2);
                }
                repaint();
                revalidate();
                sliding = false;
            }).start();
        }
        return act;
    }

    public boolean slideToRightBack(Component com) {
        boolean act = false;
        if (!sliding) {
            act = true;
            new Thread(() -> {
                sliding = true;
                Component old = null;
                if (getComponentCount() > 0) {
                    old = getComponent(0);
                }
                add(com);
                com.setLocation(-getWidth(), 0);
                int x1 = 0;
                int width1 = (int) getSize().getWidth() / 70;
                for (int i = -getWidth(); i <= 0; i += width1) {
                    com.setLocation(i, 0);
                    if (old != null) {
                        old.setLocation(x1, 0);
                        x1 -= width1;
                    }
                    sleep();
                }
                com.setLocation(0, 0);
                while (getComponentCount() != 1) {
                    remove(getComponentCount() - 2);
                }
                repaint();
                revalidate();
                sliding = false;
            }).start();
        }
        return act;
    }

    public boolean slideToLeft(Component com) {
        boolean act = false;
        if (!sliding) {
            act = true;
            new Thread(() -> {
                sliding = true;
                Component old = null;
                if (getComponentCount() > 0) {
                    old = getComponent(0);
                }
                add(com);
                com.setLocation(getWidth(), 0);
                int x1 = 0;
                int width1 = (int) getSize().getWidth() / 70;
                for (int i = getWidth(); i >= 0; i -= width1) {
                    com.setLocation(i, 0);
                    if (old != null) {
                        old.setLocation(x1, 0);
                        x1 -= width1;
                    }
                    sleep();
                }
                com.setLocation(0, 0);
                while (getComponentCount() != 1) {
                    remove(getComponentCount() - 2);
                }
                repaint();
                revalidate();
                sliding = false;
            }).start();
        }
        return act;
    }

    public boolean slideToLeftBack(Component com) {
        boolean act = false;
        if (!sliding) {
            act = true;
            new Thread(() -> {
                sliding = true;
                Component old = null;
                if (getComponentCount() > 0) {
                    old = getComponent(0);
                }
                add(com);
                com.setLocation(getWidth(), 0);
                int x1 = 0;
                int width1 = (int) getSize().getWidth() / 70;
                for (int i = getWidth(); i >= 0; i -= width1) {
                    com.setLocation(i, 0);
                    if (old != null) {
                        old.setLocation(x1, 0);
                        x1 += width1;
                    }
                    sleep();
                }
                com.setLocation(0, 0);
                while (getComponentCount() != 1) {
                    remove(getComponentCount() - 2);
                }
                repaint();
                revalidate();
                sliding = false;
            }).start();
        }
        return act;
    }

    public boolean slideToDown(Component com) {
        boolean act = false;
        if (!sliding) {
            act = true;
            new Thread(() -> {
                sliding = true;
                Component old = null;
                if (getComponentCount() > 0) {
                    old = getComponent(0);
                }
                add(com);
                com.setLocation(0, -getHeight());
                int x1 = 0;
                int right = (int) getSize().getHeight() / 70;
                for (int i = getHeight(); i >= 0; i -= right) {
                    com.setLocation(0, i);
                    if (old != null) {
                        old.setLocation(0, x1);
                        x1 -= right;
                    }
                    sleep();
                }
                com.setLocation(0, 0);
                while (getComponentCount() != 1) {
                    remove(getComponentCount() - 2);
                }
                repaint();
                revalidate();
                sliding = false;
            }).start();
        }
        return act;
    }

    public boolean slideToDownBack(Component com) {
        boolean act = false;
        if (!sliding) {
            act = true;
            new Thread(() -> {
                sliding = true;
                Component old = null;
                if (getComponentCount() > 0) {
                    old = getComponent(0);
                }
                add(com);
                com.setLocation(0, -getHeight());
                int x1 = 0;
                int right = (int) getSize().getHeight() / 70;
                for (int i = getHeight(); i >= 0; i -= right) {
                    com.setLocation(0, i);
                    if (old != null) {
                        old.setLocation(0, x1);
                        x1 += right;
                    }
                    sleep();
                }
                com.setLocation(0, 0);
                while (getComponentCount() != 1) {
                    remove(getComponentCount() - 2);
                }
                repaint();
                revalidate();
                sliding = false;
            }).start();
        }
        return act;
    }

    public void slideNon(Component com) {
        this.removeAll();
        this.add(com);
        repaint();
        revalidate();
    }

    private void sleep() {
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new java.awt.BorderLayout());
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
