package br.com.grupolider.app_impressao_etiqueta_pallet.shared.components.tablemodel.renderer;

import br.com.grupolider.app_impressao_etiqueta_pallet.shared.components.tablemodel.model.Tablemodel;
import java.awt.*;
import javax.swing.*;

import javax.swing.table.TableCellRenderer;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CheckBoxHeader extends JPanel implements TableCellRenderer, MouseListener {

    private final JTable table;
    private final int column;
    private boolean selected = false;

    private final Color headerBackground = new Color(51, 102, 255);
    private final Color checkBackgroundSelected = new Color(235, 235, 235);
    private final Color borderColor = Color.WHITE;

    public CheckBoxHeader(JTable table, int column) {
        this.table = table;
        this.column = column;

        setOpaque(true);
        setBackground(headerBackground);
        setPreferredSize(new Dimension(80, 30));

        table.getTableHeader().addMouseListener(this);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        return this;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(headerBackground);
        g2.fillRect(0, 0, getWidth(), getHeight());

        int checkboxSize = 16;
        int x = (getWidth() - checkboxSize) / 2;
        int y = (getHeight() - checkboxSize) / 2;

        boolean isEmpty = table.getRowCount() == 0;

        if (selected && !isEmpty) {
            g2.setColor(checkBackgroundSelected);
        } else {
            g2.setColor(headerBackground);
        }
        g2.fillRoundRect(x, y, checkboxSize, checkboxSize, 4, 4);

        g2.setColor(isEmpty ? borderColor.darker() : borderColor);
        g2.setStroke(new BasicStroke(1.5f));
        g2.drawRoundRect(x, y, checkboxSize, checkboxSize, 4, 4);

        if (selected && !isEmpty) {
            g2.setColor(headerBackground);
            g2.setFont(new Font("Dialog", Font.BOLD, 12));

            FontMetrics fm = g2.getFontMetrics();
            String checkSymbol = "✓";
            int symbolWidth = fm.stringWidth(checkSymbol);
            int symbolHeight = fm.getAscent();

            int textX = x + (checkboxSize - symbolWidth) / 2;
            int textY = y + (checkboxSize + symbolHeight) / 2 - 1;

            g2.drawString(checkSymbol, textX, textY);
        }

        g2.dispose();
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
        repaint();
    }

    public void resetWhenEmpty() {
        if (table.getRowCount() == 0) {
            selected = false;
            repaint();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int colIndex = table.getTableHeader().columnAtPoint(e.getPoint());
        if (colIndex == column) {
            if (table.getRowCount() == 0) {
                return;
            }

            selected = !selected;
            setSelected(selected);

            Tablemodel model = (Tablemodel) table.getModel();
            model.setAllSelecionados(selected);

            table.repaint();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
