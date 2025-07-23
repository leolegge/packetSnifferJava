package org.sniffer.GUI.packetPanels;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PacketInformationPanel extends JPanel {

        PacketBytesPanel packetBytesPanel;
        PayloadPanel payloadPanel;


    public PacketInformationPanel() {
        this.setBackground(Color.BLUE);
        this.setLayout(new GridLayout(1,2));
        this.packetBytesPanel = new PacketBytesPanel();
        this.payloadPanel = new PayloadPanel();

        this.add(packetBytesPanel);
        this.add(payloadPanel);

    }
}
