package br.com.grupolider.app_impressao_etiqueta_pallet.shared.components.spinnerprogress;

import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public interface SpinnerRender {

    public boolean isDisplayStringAble();

    public boolean isPaintComplete();

    public void paintCompleteIndeterminate(Graphics2D g2, Component component, Rectangle rec, float last, float f, float p);

    public void paintIndeterminate(Graphics2D g2, Component component, Rectangle rec, float f);

    public void paintDeterminate(Graphics2D g2, Component component, Rectangle rec, float p);

    public int getInsets();
}
