package org.sniffer.GUI;

import org.sniffer.GUI.applicationFramePanels.DetailedPacketsDisplayScrollPanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ApplicationFrame extends JFrame {

    DetailedPacketsDisplayScrollPanel packetsDisplayScrollPanel;
    private final String[] PACKET_COLUMNS_NAMES = {"Packet Number", "Packet source", "packet destination", "Protocol", "Length"};


    DefaultTableModel mainDetailedPacketTable;
    JTable table;


    public ApplicationFrame(SnifferDashboard dashboard) {
        this.setTitle("Application Viewer");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(600, 400);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(dashboard);
        this.setVisible(true);

        mainDetailedPacketTable = new DefaultTableModel(PACKET_COLUMNS_NAMES, 0);
        table = new JTable(mainDetailedPacketTable);
        packetsDisplayScrollPanel = new DetailedPacketsDisplayScrollPanel(table);

        this.add(packetsDisplayScrollPanel, BorderLayout.NORTH);



    }
}
