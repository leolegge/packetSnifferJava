package org.sniffer.GUI;

import org.sniffer.GUI.packetPanels.PacketInformationPanel;
import org.sniffer.GUI.packetPanels.PacketsDisplayPanel;
import org.sniffer.GUI.packetPanels.PacketsDisplayScrollPanel;

import javax.swing.*;
import java.awt.*;

/**
 * This is the wrapper for the main display area of packets and their information
 *
 */
public class PacketPanelsWrapper extends JPanel {

    private PacketsDisplayPanel packetsDisplayPanel;
    private PacketInformationPanel packetInformationPanel;

    SnifferDashboard dashboard;

    /**
     * This is the constructor for the wrapper and sets up all panels in the main area of the GUI
     *
     * @param dashboard is a reference to the SnifferDashboard to allow passing of information between panels
     */
    public PacketPanelsWrapper(SnifferDashboard dashboard) {
        this.dashboard = dashboard;
        packetsDisplayPanel = new PacketsDisplayPanel(dashboard);
        packetInformationPanel = new PacketInformationPanel(dashboard);

        this.setLayout(new GridLayout(2, 1));
        this.add(packetsDisplayPanel);
        this.add(packetInformationPanel);
    }

    //getters
    public PacketsDisplayPanel getPacketsDisplayPanel() {
        return packetsDisplayPanel;
    }
    public PacketInformationPanel getPacketInformationPanel() {
        return packetInformationPanel;
    }
}
