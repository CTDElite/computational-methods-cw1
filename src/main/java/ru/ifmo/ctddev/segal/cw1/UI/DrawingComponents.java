package ru.ifmo.ctddev.segal.cw1.ui;

import javax.swing.*;
import java.awt.*;

class DrawingComponents extends JPanel {

    int xg[];
    int yg[];
    int ng;

    DrawingComponents(int n, int[] x, int[] y) {
        ng = n;
        xg = x;
        yg = y;
    }

    @Override
    protected void paintComponent(Graphics gh) {
        Graphics2D drp = (Graphics2D)gh;
        drp.drawLine(20, 340, 20, 20);
        drp.drawLine(20, 340, 460, 340);
        drp.drawPolyline(xg, yg, ng);
    }
}
