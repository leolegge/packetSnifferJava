package org.sniffer.GUI;

import org.sniffer.GUI.packetPanels.PacketInformationPanel;
import org.sniffer.GUI.packetPanels.PacketsDisplayPanel;

import javax.swing.*;

public class PacketPanelsWrapper extends JPanel {
    PacketsDisplayPanel packetsDisplayPanel = new PacketsDisplayPanel();
    PacketInformationPanel packetInformationPanel = new PacketInformationPanel();

    public PacketPanelsWrapper() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(packetsDisplayPanel);
        this.add(packetInformationPanel);
    }

}
