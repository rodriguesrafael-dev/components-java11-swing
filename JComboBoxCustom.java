package br.com.grupolider.app_impressao_etiqueta_pallet.shared.components;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.plaf.basic.ComboPopup;

public class JComboBoxCustom {

    public static void aplicarEstiloFlatArredondado(JComboBox<?> combo) {
        Color seta = new Color(51, 153, 255);
        combo.setUI(new MaterialComboBoxUI(seta));
        combo.setRenderer(new MaterialComboBoxRenderer());

        combo.setFont(new Font("Roboto", Font.BOLD, 13));
        combo.setBackground(new Color(245, 245, 245));
        combo.setForeground(new Color(0, 51, 153));
        combo.setBorder(new RoundedBorder(12, new Color(51, 153, 255)));

        combo.setOpaque(false);
        combo.setFocusable(true);
        combo.setPreferredSize(new Dimension(combo.getPreferredSize().width, 40));
        combo.repaint();
    }

    static class MaterialComboBoxUI extends BasicComboBoxUI {

        private final Color arrowColor;
        private final int cornerRadius = 12;

        public MaterialComboBoxUI(Color arrowColor) {
            this.arrowColor = arrowColor;
        }

        @Override
        protected JButton createArrowButton() {
            return new MaterialArrowButton(arrowColor);
        }

        @Override
        public void paintCurrentValueBackground(Graphics g, Rectangle bounds, boolean hasFocus) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(comboBox.isEnabled() ? comboBox.getBackground() : Color.LIGHT_GRAY);
            g2.fillRoundRect(bounds.x, bounds.y, bounds.width, bounds.height, cornerRadius, cornerRadius);
            g2.dispose();
        }

        @Override
        protected ComboPopup createPopup() {
            return new RoundedComboPopup(comboBox);
        }

        protected class RoundedComboPopup extends BasicComboPopup {

            public RoundedComboPopup(JComboBox combo) {
                super(combo);
                this.setBorder(BorderFactory.createEmptyBorder());
                for (Component comp : getComponents()) {
                    if (comp instanceof JScrollPane) {
                        JScrollPane scroll = (JScrollPane) comp;
                        scroll.setBorder(BorderFactory.createEmptyBorder());
                        scroll.getViewport().setOpaque(false);
                        break;
                    }
                }
            }
            
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                g2.setColor(getBackground()); 
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);

                super.paintComponent(g);
                g2.dispose();
            }

            @Override
            public void paintBorder(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.dispose();
            }
        }

    }

    static class MaterialArrowButton extends JButton {

        private final Color arrowColor;

        public MaterialArrowButton(Color arrowColor) {
            this.arrowColor = arrowColor;
            setBorder(BorderFactory.createEmptyBorder());
            setFocusPainted(false);
            setContentAreaFilled(false);
            setOpaque(false);
        }

        @Override
        public void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            int w = getWidth();
            int h = getHeight();
            g2d.setColor(arrowColor);
            int size = 6;
            int[] x = {w / 2 - size, w / 2 + size, w / 2};
            int[] y = {h / 2 - size / 2, h / 2 - size / 2, h / 2 + size / 2};
            g2d.fillPolygon(x, y, 3);
            g2d.dispose();
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(24, 24);
        }
    }

    static class MaterialComboBoxRenderer extends DefaultListCellRenderer {

        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            setFont(new Font("Roboto", Font.PLAIN, 13));
            setBorder(BorderFactory.createEmptyBorder(4, 8, 4, 8));
            if (isSelected) {
                setBackground(new Color(51, 153, 255));
                setForeground(new Color(255, 255, 255));
            } else {
                setBackground(new Color(245, 245, 245));
                setForeground(new Color(0, 51, 153));
            }
            return this;
        }
    }

    private static class RoundedBorder extends AbstractBorder {

        private final int radius;
        private final Color borderColor;

        public RoundedBorder(int radius, Color borderColor) {
            this.radius = radius;
            this.borderColor = borderColor;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(borderColor);
            g2.setStroke(new BasicStroke(1.5f));
            g2.draw(new RoundRectangle2D.Double(x + 1, y + 1, width - 3, height - 3, radius, radius));
            g2.dispose();
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(8, 12, 8, 12);
        }

        @Override
        public Insets getBorderInsets(Component c, Insets insets) {
            return getBorderInsets(c);
        }
    }

}
