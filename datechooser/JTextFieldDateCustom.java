package br.com.grupolider.app_impressao_etiqueta_pallet.shared.components.datechooser;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.AbstractBorder;
import java.awt.geom.RoundRectangle2D;

public class JTextFieldDateCustom {
   
    public static void aplicarEstiloFlatArredondado(JTextField textField) {
        textField.setFont(new Font("Roboto", Font.BOLD, 13));
        textField.setForeground(new Color(0, 51, 153));
        textField.setBackground(new Color(245, 245, 245));
        textField.setCaretColor(new Color(0, 51, 153));
        textField.setBorder(new RoundedBorderComSeta(12, new Color(51, 153, 255)));
        textField.setOpaque(false);
        textField.setMargin(new Insets(8, 12, 8, 32)); 
        textField.setPreferredSize(new Dimension(200, 40));
    }

    private static class RoundedBorderComSeta extends AbstractBorder {

        private final int radius;
        private final Color borderColor;

        public RoundedBorderComSeta(int radius, Color borderColor) {
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

            int arrowSize = 6;
            int centerX = width - 16;
            int centerY = height / 2;
            int[] xPoints = {centerX - arrowSize, centerX + arrowSize, centerX};
            int[] yPoints = {centerY - arrowSize / 2, centerY - arrowSize / 2, centerY + arrowSize / 2};

            g2.setColor(borderColor);
            g2.fillPolygon(xPoints, yPoints, 3);

            g2.dispose();
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(8, 12, 8, 32); 
        }

        @Override
        public Insets getBorderInsets(Component c, Insets insets) {
            return getBorderInsets(c);
        }
    }
    
}
