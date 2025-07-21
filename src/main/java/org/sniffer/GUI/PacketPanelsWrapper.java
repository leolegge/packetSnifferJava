package org.sniffer.GUI;

import org.sniffer.GUI.packetPanels.PacketInformationPanel;
import org.sniffer.GUI.packetPanels.PacketsDisplayPanel;

import javax.swing.*;

/**
 * This is the wrapper for the main display area of packets and their information
 *
 */
public class PacketPanelsWrapper extends JPanel {
    PacketsDisplayPanel packetsDisplayPanel = new PacketsDisplayPanel();
    PacketInformationPanel packetInformationPanel = new PacketInformationPanel();

    public PacketPanelsWrapper() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(packetsDisplayPanel);
        this.add(packetInformationPanel);
    }

}
