package br.com.grupolider.app_impressao_etiqueta_pallet.shared.components.tablemodel.renderer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import java.awt.*;

public class TableCellRenderer extends DefaultTableCellRenderer {

    private static final int CHECKBOX = 0;
    private static final int DATA = 1;
    private static final int HORA = 2;
    private static final int CARGA = 3;
    private static final int PALETE = 4;

    private final JLabel c;

    public TableCellRenderer() {
        c = new JLabel();
        c.setOpaque(false);
        c.setBorder(BorderFactory.createEmptyBorder(3, 9, 3, 9));
    }

    @Override
    public Component getTableCellRendererComponent(
            JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

        getFormat(table);

        boolean isMarcado = false;
        Object checkboxValue = table.getValueAt(row, CHECKBOX);
        if (checkboxValue instanceof Boolean && (Boolean) checkboxValue) {
            isMarcado = true;
        }

        Color background = isMarcado
                ? new Color(255, 204, 204)
                : isSelected ? new Color(255, 204, 204) : getRowColor(row);

        Color foreground = isSelected
                ? new Color(33, 90, 255)
                : new Color(33, 90, 255);

        Font fonte = new Font("Roboto", isMarcado || isSelected ? Font.BOLD : Font.PLAIN, 12);

        if (column == CHECKBOX && value instanceof Boolean) {
            return new CustomCheckboxCell((Boolean) value, background);
        }

        c.setText(String.valueOf(value).toUpperCase());
        c.setHorizontalAlignment(getColumnTextAlignment(column));
        c.setForeground(foreground);
        c.setFont(fonte);

        return new RoundedCellPanel(background, c);
    }

    public void getFormat(JTable table) {
        getHeaderDefault(table);
        getColumnModel(table);
        table.setRowHeight(32);
    }

    private void getHeaderDefault(JTable table) {
        JTableHeader header = table.getTableHeader();
        header.setDefaultRenderer(new DefaultTableCellRenderer() {
            {
                setOpaque(true);
                setBackground(new Color(51, 102, 255));
                setForeground(Color.WHITE);
                setFont(new Font("Roboto", Font.BOLD, 13));
                setHorizontalAlignment(SwingConstants.CENTER);
                setBorder(BorderFactory.createEmptyBorder(3, 9, 3, 9));
            }

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus,
                    int row, int column) {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                setText(value != null ? value.toString().toUpperCase() : "");
                return this;
            }
        });

        header.setReorderingAllowed(false);
        header.setResizingAllowed(false);
    }

    private void getColumnModel(JTable table) {
        TableColumnModel model = table.getColumnModel();
        model.getColumn(CHECKBOX).setPreferredWidth(10);
        model.getColumn(DATA).setPreferredWidth(100);
        model.getColumn(HORA).setPreferredWidth(100);
        model.getColumn(CARGA).setPreferredWidth(100);
        model.getColumn(PALETE).setPreferredWidth(100);
    }

    private int getColumnTextAlignment(int column) {
        switch (column) {
            case CHECKBOX:
                return SwingConstants.CENTER;
            case DATA:
                return SwingConstants.RIGHT;
            case HORA:
                return SwingConstants.LEFT;
            case CARGA:
                return SwingConstants.RIGHT;
            case PALETE:
                return SwingConstants.RIGHT;
            default:
                return SwingConstants.CENTER;
        }
    }

    private Color getRowColor(int row) {
        return (row % 2 == 0)
                ? new Color(250, 250, 250)
                : new Color(240, 240, 240);
    }

    private static class CustomCheckboxCell extends JPanel {
        private final boolean selected;
        private final Color backgroundColor;
        private final Color checkColor = new Color(51, 153, 255);
        private final Color bgColor = new Color(235, 235, 235);

        public CustomCheckboxCell(boolean selected, Color backgroundColor) {
            this.selected = selected;
            this.backgroundColor = backgroundColor;
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            g2.setColor(backgroundColor);
            g2.fillRoundRect(2, 2, getWidth() - 4, getHeight() - 4, 12, 12);

            int checkboxSize = 18;
            int x = (getWidth() - checkboxSize) / 2;
            int y = (getHeight() - checkboxSize) / 2;

            if (selected) {
                g2.setColor(checkColor);
            } else {
                g2.setColor(bgColor);
            }
            g2.fillRoundRect(x, y, checkboxSize, checkboxSize, 6, 6);

            g2.setColor(checkColor);
            g2.setStroke(new BasicStroke(1.8f));
            g2.drawRoundRect(x, y, checkboxSize, checkboxSize, 6, 6);

            if (selected) {
                g2.setColor(Color.WHITE);
                g2.setFont(new Font("Dialog", Font.BOLD, 14));
                
                FontMetrics fm = g2.getFontMetrics();
                String checkSymbol = "✓";
                int symbolWidth = fm.stringWidth(checkSymbol);
                int symbolHeight = fm.getAscent();
                
                int textX = x + (checkboxSize - symbolWidth) / 2;
                int textY = y + (checkboxSize + symbolHeight) / 2 - 2;
                
                g2.drawString(checkSymbol, textX, textY);
            }

            g2.dispose();
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(80, 32);
        }
    }

    private static class RoundedCellPanel extends JPanel {
        private final Color backgroundColor;
        private final JComponent content;

        public RoundedCellPanel(Color backgroundColor, JComponent content) {
            this.backgroundColor = backgroundColor;
            this.content = content;
            setLayout(new BorderLayout());
            setOpaque(false);
            add(content, BorderLayout.CENTER);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(backgroundColor);
            g2.fillRoundRect(2, 2, getWidth() - 4, getHeight() - 4, 12, 12);
            g2.dispose();
            super.paintComponent(g);
        }
    }
}
