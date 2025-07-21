package org.sniffer.GUI;

import javax.swing.*;

public class WorldViewFrame extends JFrame {

    public WorldViewFrame(SnifferDashboard dashboard) {
        this.setTitle("World View");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        this.setSize(600, 400);
        this.setVisible(true);

        this.setLocationRelativeTo(dashboard);
    }
}