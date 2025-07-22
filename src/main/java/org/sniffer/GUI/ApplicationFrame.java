package org.sniffer.GUI;

import javax.swing.*;

public class ApplicationFrame extends JFrame {

    public ApplicationFrame(SnifferDashboard dashboard) {
        this.setTitle("Application Viewer");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        this.setSize(600, 400);
        this.setVisible(true);

        this.setLocationRelativeTo(dashboard);

    }
}
