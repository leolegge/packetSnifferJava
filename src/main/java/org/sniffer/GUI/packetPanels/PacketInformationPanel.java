package org.sniffer.GUI.packetPanels;

import org.sniffer.GUI.SnifferDashboard;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * This class holds the bottom holds everything to do with the bottom half of the screen and all packet infomation
 *
 */
public class PacketInformationPanel extends JPanel {

        PacketBytesPanel packetBytesPanel;
        PayloadPanel payloadPanel;

        SnifferDashboard dashboard;

    /**
     * This is the constructor for the panel in which all relevant components are added
     *
     * @param dashboard is a reference to the SnifferDashboard frame which allows access to packet information
     */
    public PacketInformationPanel(SnifferDashboard dashboard) {
        this.dashboard = dashboard;
        this.setBackground(Color.BLUE);
        this.setLayout(new GridLayout(1,2));
        this.packetBytesPanel = new PacketBytesPanel();
        this.payloadPanel = new PayloadPanel();

        this.add(packetBytesPanel);
        this.add(payloadPanel);
    }

    //getters
    public PacketBytesPanel getPacketBytesPanel() {
        return packetBytesPanel;
    }
    public PayloadPanel getPayloadPanel() {
        return payloadPanel;
    }
}
