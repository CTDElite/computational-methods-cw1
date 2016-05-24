package ru.ifmo.ctddev.segal.cw1.ui;

import javax.swing.*;
import java.awt.*;

public class Up7 extends JFrame {
    public int x[];
    public int y[];
    public int n = 5;

    public Up7 (int n, int x[], int y[]) {
        super("График по точкам");
        this.n = n;
        this.x = x;
        this.y = y;
        JPanel jcp = new JPanel(new BorderLayout());
        setContentPane(jcp);
        jcp.add(new DrawingComponents(n, x, y), BorderLayout.CENTER);
        jcp.setBackground(Color.gray);
        setSize(1000, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

}
