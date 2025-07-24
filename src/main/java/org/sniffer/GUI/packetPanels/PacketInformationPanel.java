package org.sniffer.GUI.packetPanels;

import org.sniffer.GUI.SnifferDashboard;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PacketInformationPanel extends JPanel {

        PacketBytesPanel packetBytesPanel;
        PayloadPanel payloadPanel;

        SnifferDashboard dashboard;


    public PacketInformationPanel(SnifferDashboard dashboard) {
        this.dashboard = dashboard;
        this.setBackground(Color.BLUE);
        this.setLayout(new GridLayout(1,2));
        this.packetBytesPanel = new PacketBytesPanel();
        this.payloadPanel = new PayloadPanel();

        this.add(packetBytesPanel);
        this.add(payloadPanel);

    }
}
