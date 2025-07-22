package org.sniffer.GUI.packetQueryPanels;

import org.sniffer.GUI.SnifferDashboard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartStopSnifferPanel extends JPanel {

    private SnifferDashboard dashboard;

    private final JButton startSnifferButton = new JButton("Start sniffer");
    private final JButton stopSnifferButton = new JButton("Stop sniffer");


    public StartStopSnifferPanel(SnifferDashboard dashboard) {
        this.setLayout(new BorderLayout());
        this.dashboard = dashboard;

        //setting up buttons
        this.add(startSnifferButton, BorderLayout.WEST);
        this.add(stopSnifferButton, BorderLayout.EAST);
        startSnifferButton.setEnabled(false);
        stopSnifferButton.setEnabled(false);


        startSnifferButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                startSnifferButton.setEnabled(false);
                stopSnifferButton.setEnabled(true);

                dashboard.startSniffer();
            }
        });

        stopSnifferButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                startSnifferButton.setEnabled(true);
                stopSnifferButton.setEnabled(false);

                dashboard.stopSniffer();
            }
        });

    }

    //getters
    public JButton getStartSnifferButton() {
        return startSnifferButton;
    }
    public JButton getStopSnifferButton() {
        return stopSnifferButton;
    }
    public SnifferDashboard getSnifferDashboard() {
        return dashboard;
    }
}
