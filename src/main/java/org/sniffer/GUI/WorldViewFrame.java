package org.sniffer.GUI;

import javax.swing.*;

/**
 * This class is still to be implemented but if implemented will show a map of all connected networks
 *
 */
public class WorldViewFrame extends JFrame {

    public WorldViewFrame(SnifferDashboard dashboard) {
        this.setTitle("World View");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        this.setSize(600, 400);
        this.setVisible(true);

        this.setLocationRelativeTo(dashboard);
    }
}