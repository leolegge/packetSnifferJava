package org.sniffer.GUI.snifferDashboardPanels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartStopSnifferPanel extends JPanel {

    private final JButton startSnifferButton = new JButton("Start sniffer");
    private final JButton stopSnifferButton = new JButton("Stop sniffer");


    public StartStopSnifferPanel() {
        this.setLayout(new BorderLayout());

        //setting up buttons
        this.add(startSnifferButton, BorderLayout.WEST);
        this.add(stopSnifferButton, BorderLayout.EAST);
        startSnifferButton.setEnabled(false);
        stopSnifferButton.setEnabled(false);


        startSnifferButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                startSnifferButton.setEnabled(false);
                stopSnifferButton.setEnabled(true);
            }
        });

        stopSnifferButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                startSnifferButton.setEnabled(true);
                stopSnifferButton.setEnabled(false);
            }
        });

    }

    public JButton getStartSnifferButton() {
        return startSnifferButton;
    }
    public JButton getStopSnifferButton() {
        return stopSnifferButton;
    }
}
