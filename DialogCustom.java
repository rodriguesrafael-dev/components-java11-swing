package br.com.grupolider.app_impressao_etiqueta_pallet.shared.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import keeptoo.KButton;
import keeptoo.KGradientPanel;

public class DialogCustom {
    
    public static void showMessageDialog(JFrame jFrame, String message) {
        JDialog dialog = new JDialog(jFrame, null, true);
        dialog.setUndecorated(true);
        dialog.setModal(true);       
        dialog.setLayout(new BorderLayout());

        KGradientPanel contentGradientPanel = new KGradientPanel();
        contentGradientPanel.setkBorderRadius(12);
        contentGradientPanel.setkStartColor(new Color(215, 215, 215));
        contentGradientPanel.setkEndColor(new Color(225, 225, 225));

        int borderThickness = 12;
        Border leftBorder = BorderFactory.createMatteBorder(0, borderThickness, 0, 0, new Color(35, 200, 79));
        contentGradientPanel.setBorder(leftBorder);

        JLabel label = new JLabel("<html><h2><font color='#1c8707' face='arial'><b>" + message + "</b></font></h2></html>", SwingConstants.CENTER);
        label.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        contentGradientPanel.add(label, BorderLayout.CENTER);

        KButton okButton = new KButton();
        okButton.setText("OK");
        okButton.setPreferredSize(new Dimension(50, 30));
        okButton.setMinimumSize(new Dimension(50, 30));
        okButton.setMaximumSize(new Dimension(50, 30));
        okButton.setBackground(new Color(35, 200, 79));
        okButton.setkStartColor(new Color(35, 200, 79));
        okButton.setkEndColor(new Color(35, 200, 79));
        okButton.setkHoverStartColor(new Color(30, 95, 74));
        okButton.setkHoverEndColor(new Color(30, 95, 74));
        okButton.setkHoverForeGround(Color.WHITE);
        okButton.setBorder(null);
        okButton.setkBorderRadius(24);
        okButton.setHorizontalAlignment(SwingConstants.CENTER);

        okButton.addActionListener(e -> {
            dialog.dispose();
        });

        contentGradientPanel.add(okButton, BorderLayout.SOUTH);

        dialog.setContentPane(contentGradientPanel);
        dialog.pack();
        dialog.setLocationRelativeTo(jFrame);
        dialog.setVisible(true);
    }

}
