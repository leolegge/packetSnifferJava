package org.sniffer.GUI.packetQueryPanels;

import org.sniffer.GUI.SnifferDashboard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

/**
 * This class is used to contain the stop and start buttons for the packet sniffer
 *
 */
public class StartStopSnifferPanel extends JPanel {

    private SnifferDashboard dashboard;

    private final JButton startSnifferButton = new JButton("Start sniffer");
    private final JButton stopSnifferButton = new JButton("Stop sniffer");

    /**
     * This is the constructor to set up the listeners for the buttons as well as their layout
     *
     * @param dashboard is used as a reference to allow the buttons to access the PacketSniffer class and start it
     */
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

                dashboard.getDashboardMenuBar().getNetworkMenu().disableRadioButtons();
                dashboard.setSnifferThread(new Thread(dashboard.getPacketSniffer()));
                dashboard.setSharedPacketList(Collections.synchronizedList(new ArrayList<>()));
                dashboard.startSniffer();

                dashboard.getDashboardMenuBar().getFileMenu().getSaveButton().setEnabled(false);
                dashboard.getDashboardMenuBar().getFileMenu().getOpenButton().setEnabled(false);
                dashboard.getDashboardMenuBar().getFileMenu().getSaveButton().setEnabled(false);
            }
        });

        stopSnifferButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                startSnifferButton.setEnabled(true);
                stopSnifferButton.setEnabled(false);

                dashboard.getDashboardMenuBar().getNetworkMenu().enableRadioButtons();
                dashboard.stopSniffer();

                dashboard.getDashboardMenuBar().getFileMenu().getSaveButton().setEnabled(true);
                dashboard.getDashboardMenuBar().getFileMenu().getOpenButton().setEnabled(true);
                dashboard.getDashboardMenuBar().getFileMenu().getSaveButton().setEnabled(true);

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
