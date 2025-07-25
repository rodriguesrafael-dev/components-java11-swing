package br.com.grupolider.app_impressao_etiqueta_pallet.shared.components;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.RenderingHints;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.border.AbstractBorder;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.geom.RoundRectangle2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import javax.swing.ImageIcon;

public class JButtonCustom {
    
    private static final Color DEFAULT_NORMAL_BG = new Color(51, 102, 255);
    private static final Color DEFAULT_HOVER_BG = new Color(0, 0, 102);
    private static final Color DEFAULT_PRESSED_BG = new Color(0, 0, 102);
    private static final Color DEFAULT_NORMAL_FG = Color.WHITE;
    private static final Color DEFAULT_HOVER_FG = Color.WHITE;
    private static final int DEFAULT_BORDER_RADIUS = 12;
    private static final int PRESS_EFFECT_OFFSET = 2;

    public static void aplicarEstiloFlatArredondado(JButton button) {
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);

        button.setBackground(DEFAULT_NORMAL_BG);
        button.setForeground(DEFAULT_NORMAL_FG);
        button.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
        button.setBorder(new RoundedButtonBorder(DEFAULT_BORDER_RADIUS, DEFAULT_NORMAL_BG));

        button.setUI(new CustomMaterialButtonUI(
                DEFAULT_NORMAL_BG,
                DEFAULT_HOVER_BG,
                DEFAULT_PRESSED_BG,
                DEFAULT_NORMAL_FG,
                DEFAULT_HOVER_FG,
                DEFAULT_BORDER_RADIUS,
                PRESS_EFFECT_OFFSET
        ));

        button.setPreferredSize(new Dimension(button.getPreferredSize().width, 40));
    }

    public static void aplicarEstiloFlatArredondado(JButton button, String buttonText, String iconPath, Dimension preferredSize, boolean enabled) {
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));

        if (preferredSize != null) {
            button.setPreferredSize(preferredSize);
        } else {
            button.setPreferredSize(new Dimension(button.getPreferredSize().width, 40));
        }

        if (buttonText != null) {
            button.setText(buttonText);
        } else {
            button.setText("");
        }

        if (iconPath != null && !iconPath.isEmpty()) {
            try {
                URL iconURL = JButtonCustom.class.getResource(iconPath);
                if (iconURL != null) {
                    ImageIcon icon = new ImageIcon(iconURL);
                    int iconSize = Math.min(button.getPreferredSize().width, button.getPreferredSize().height) - 8;
                    if (iconSize > 0) {
                        Image img = icon.getImage();
                        Image resizedImg = img.getScaledInstance(iconSize, iconSize, Image.SCALE_SMOOTH);
                        icon = new ImageIcon(resizedImg);
                    }
                    button.setIcon(icon);
                    button.setIconTextGap(8);
                } else {
                    System.err.println("Erro: Ícone não encontrado no caminho: " + iconPath);
                }
            } catch (Exception e) {
                System.err.println("Erro ao carregar ícone: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            button.setIcon(null);
        }

        if (enabled) {
            button.setEnabled(true);
            button.setBackground(DEFAULT_NORMAL_BG);
            button.setForeground(DEFAULT_NORMAL_FG);
            button.setBorder(new RoundedButtonBorder(DEFAULT_BORDER_RADIUS, DEFAULT_NORMAL_BG));
            button.setUI(new CustomMaterialButtonUI(
                    DEFAULT_NORMAL_BG,
                    DEFAULT_HOVER_BG,
                    DEFAULT_PRESSED_BG,
                    DEFAULT_NORMAL_FG,
                    DEFAULT_HOVER_FG,
                    DEFAULT_BORDER_RADIUS,
                    PRESS_EFFECT_OFFSET
            ));
            button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        } else {
            button.setEnabled(false);
            button.setForeground(Color.GRAY);
            button.setBackground(new Color(51, 51, 51));
            button.setBorder(new RoundedButtonBorder(DEFAULT_BORDER_RADIUS, new Color(220, 220, 220)));
            button.setUI(new DisabledButtonUI());
            button.setCursor(Cursor.getDefaultCursor());
        }
    }

    private static class CustomMaterialButtonUI extends BasicButtonUI {

        private final Color normalBg;
        private final Color hoverBg;
        private final Color pressedBg;
        private final Color normalFg;
        private final Color hoverFg;
        private final int borderRadius;
        private final int pressEffectOffset;

        private boolean hovered = false;
        private boolean pressed = false;

        public CustomMaterialButtonUI(Color normalBg, Color hoverBg, Color pressedBg,
                Color normalFg, Color hoverFg, int borderRadius, int pressEffectOffset) {
            this.normalBg = normalBg;
            this.hoverBg = hoverBg;
            this.pressedBg = pressedBg;
            this.normalFg = normalFg;
            this.hoverFg = hoverFg;
            this.borderRadius = borderRadius;
            this.pressEffectOffset = pressEffectOffset;
        }

        @Override
        public void installUI(JComponent c) {
            super.installUI(c);
            AbstractButton button = (AbstractButton) c;
            button.setOpaque(false);
            button.setForeground(normalFg);

            button.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    hovered = true;
                    button.setForeground(hoverFg);
                    button.repaint();
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    hovered = false;
                    pressed = false;
                    button.setForeground(normalFg);
                    button.repaint();
                }

                @Override
                public void mousePressed(MouseEvent e) {
                    pressed = true;
                    button.repaint();
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    pressed = false;
                    if (button.getBounds().contains(e.getPoint())) {
                        hovered = true;
                        button.setForeground(hoverFg);
                    } else {
                        hovered = false;
                        button.setForeground(normalFg);
                    }
                    button.repaint();
                }
            });
        }

        @Override
        public void uninstallUI(JComponent c) {
            super.uninstallUI(c);
        }

        @Override
        public void paint(Graphics g, JComponent c) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            AbstractButton button = (AbstractButton) c;
            int width = button.getWidth();
            int height = button.getHeight();

            int xOffset = 0;
            int yOffset = 0;
            int currentWidth = width;
            int currentHeight = height;

            if (pressed) {
                xOffset = pressEffectOffset / 2;
                yOffset = pressEffectOffset / 2;
                currentWidth = width - pressEffectOffset;
                currentHeight = height - pressEffectOffset;
            }

            Color currentBgColor = normalBg;
            if (pressed) {
                currentBgColor = pressedBg;
            } else if (hovered) {
                currentBgColor = hoverBg;
            }

            g2.setColor(currentBgColor);

            g2.fill(new RoundRectangle2D.Double(xOffset, yOffset, currentWidth - 1, currentHeight - 1, borderRadius, borderRadius));

            g2.translate(xOffset, yOffset);
            super.paint(g2, c);
            g2.dispose();
        }
    }

    private static class RoundedButtonBorder extends AbstractBorder {

        private final int radius;
        private final Color borderColor;

        public RoundedButtonBorder(int radius, Color borderColor) {
            this.radius = radius;
            this.borderColor = borderColor;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(c.hasFocus() ? Color.BLUE : borderColor);
            g2.setStroke(new BasicStroke(2.0f));
            g2.draw(new RoundRectangle2D.Double(x + 1, y + 1, width - 3, height - 3, radius, radius));
            g2.dispose();
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(DEFAULT_BORDER_RADIUS / 2, DEFAULT_BORDER_RADIUS, DEFAULT_BORDER_RADIUS / 2, DEFAULT_BORDER_RADIUS);
        }

        @Override
        public Insets getBorderInsets(Component c, Insets insets) {
            return getBorderInsets(c);
        }

    }

    private static class DisabledButtonUI extends BasicButtonUI {

        @Override
        public void installUI(JComponent c) {
            super.installUI(c);
            c.setOpaque(false);
        }

        @Override
        public void paint(Graphics g, JComponent c) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            AbstractButton button = (AbstractButton) c;
            int width = button.getWidth();
            int height = button.getHeight();

            Color disabledBg = new Color(220, 220, 220);
            g2.setColor(disabledBg);
            g2.fill(new RoundRectangle2D.Double(0, 0, width - 1, height - 1, DEFAULT_BORDER_RADIUS, DEFAULT_BORDER_RADIUS));

            g2.dispose();

            super.paint(g, c);
        }
    }
    
}
