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

    PacketsDisplayPanel packetsDisplayPanel = new PacketsDisplayPanel();
    PacketInformationPanel packetInformationPanel = new PacketInformationPanel();

    public PacketPanelsWrapper() {

        this.setLayout(new GridLayout(2, 1));
        this.add(packetsDisplayPanel);
        this.add(packetInformationPanel);
    }

}
