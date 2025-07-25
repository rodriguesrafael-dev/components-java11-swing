package br.com.grupolider.app_impressao_etiqueta_pallet.shared.components.checkbox;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import javax.swing.Icon;
import javax.swing.JCheckBox;
import javax.swing.border.AbstractBorder;

public class JCheckBoxCustom {

    public static void aplicarEstiloFlatArredondado(JCheckBox checkBox, boolean enabled) {
        Color bordaColor = new Color(51, 153, 255);
        Color textoAzul = new Color(0, 51, 153);
        Color textoCinza = Color.GRAY;
        Color fundoColor = new Color(235, 235, 235);
        Color checkMarkColor = new Color(51, 153, 255);

        checkBox.setFont(new Font("Roboto", Font.BOLD, 13));
        checkBox.setOpaque(false);
        checkBox.setBorder(new RoundedBorder(12, bordaColor));
        checkBox.setIcon(new CheckBoxIcon(checkMarkColor, fundoColor));
        checkBox.setBackground(fundoColor);
        checkBox.setEnabled(enabled);
        checkBox.setPreferredSize(new Dimension(180, 40));

        checkBox.setForeground(checkBox.isSelected() ? textoAzul : textoCinza);

        checkBox.addItemListener(e -> {
            if (checkBox.isSelected()) {
                checkBox.setForeground(textoAzul);
            } else {
                checkBox.setForeground(textoCinza);
            }
            checkBox.repaint();
        });

        checkBox.addPropertyChangeListener("enabled", evt -> {
            if (!checkBox.isEnabled()) {
                checkBox.setForeground(textoCinza.darker().darker());
            } else {
                checkBox.setForeground(checkBox.isSelected() ? textoAzul : textoCinza);
            }
            checkBox.repaint();
        });
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

    private static class CheckBoxIcon implements Icon {

        private final int size = 18;
        private final int arc = 6; 
        private final Color checkMarkColor;     
        private final Color backgroundColor;    

        public CheckBoxIcon(Color checkMarkColor, Color backgroundColor) {
            this.checkMarkColor = checkMarkColor;
            this.backgroundColor = backgroundColor;
        }

        @Override
        public void paintIcon(Component c, Graphics g, int x, int y) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            boolean isSelected = false;
            if (c instanceof JCheckBox) {
                isSelected = ((JCheckBox) c).isSelected();
            }

            if (isSelected) {
                g2.setColor(checkMarkColor); 
            } else {
                g2.setColor(backgroundColor); 
            }
            g2.fill(new RoundRectangle2D.Double(x, y, size, size, arc, arc));

            g2.setColor(checkMarkColor);
            g2.setStroke(new BasicStroke(1.8f));
            g2.draw(new RoundRectangle2D.Double(x, y, size, size, arc, arc));

            if (isSelected) {
                g2.setColor(Color.WHITE);
                g2.setFont(new Font("Dialog", Font.BOLD, 14));

                FontMetrics fm = g2.getFontMetrics();
                String checkSymbol = "✓"; 
                int symbolWidth = fm.stringWidth(checkSymbol);
                int symbolHeight = fm.getAscent();

                int textX = x + (size - symbolWidth) / 2;
                int textY = y + (size + symbolHeight) / 2 - 2;

                g2.drawString(checkSymbol, textX, textY);
            }

            g2.dispose();
        }

        @Override
        public int getIconWidth() {
            return size;
        }

        @Override
        public int getIconHeight() {
            return size;
        }
    }

}
