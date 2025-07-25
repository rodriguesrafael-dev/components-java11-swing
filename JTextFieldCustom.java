package br.com.grupolider.app_impressao_etiqueta_pallet.shared.components;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class JTextFieldCustom {

    public static void aplicarEstiloFlatArredondado(JTextField textField, boolean enabled) {
        Color bordaColor = new Color(51, 153, 255);
        Color textoColor = new Color(0, 51, 153);
        Color fundoColor = new Color(235, 235, 235);

        textField.setFont(new Font("Roboto", Font.BOLD, 13));
        textField.setBackground(fundoColor);
        textField.setForeground(textoColor);
        textField.setBorder(new RoundedBorder(12, bordaColor));
        textField.setOpaque(false);
        textField.setPreferredSize(new Dimension(textField.getPreferredSize().width, 40));
        textField.setMargin(new Insets(8, 12, 8, 12));
        textField.setEnabled(enabled);

        textField.addPropertyChangeListener("enabled", evt -> {
            textField.setDisabledTextColor(textoColor);
            textField.repaint();
        });
        textField.setDisabledTextColor(textoColor);
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
