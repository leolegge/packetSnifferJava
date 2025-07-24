package org.sniffer.GUI;

import org.pcap4j.packet.*;
import org.sniffer.GUI.detailFramePanels.DetailedPacketsDisplayScrollPanel;
import org.sniffer.backend.IdentifiedPacket;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

//TODO when a new souce to sniff is selected if this is open then clear the table

public class DetailFrame extends JFrame {

    DetailedPacketsDisplayScrollPanel packetsDisplayScrollPanel;
    private final String[] PACKET_COLUMNS_NAMES = {"Packet Number", "Source", "Destination","Port source", "Port destination" ,"Protocol","Exact Protocol","Length"};


    DefaultTableModel mainDetailedPacketTable;
    JTable table;


    public DetailFrame(SnifferDashboard dashboard) {
        this.setTitle("Detail Viewer");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(800, 400);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(dashboard);
        this.setVisible(true);

        mainDetailedPacketTable = new DefaultTableModel(PACKET_COLUMNS_NAMES, 0);
        table = new JTable(mainDetailedPacketTable);
        packetsDisplayScrollPanel = new DetailedPacketsDisplayScrollPanel(table);

        this.add(packetsDisplayScrollPanel, BorderLayout.CENTER);

    }



    public void addRowToTable(IdentifiedPacket identifiedPacket) {

        mainDetailedPacketTable.addRow(new Object[]{identifiedPacket.getPacketNumber(),
                identifiedPacket.getSrcIp(),
                identifiedPacket.getDstIp(),
                identifiedPacket.getSrcPort(),
                identifiedPacket.getDstPort(),
                identifiedPacket.getProtocol()});




        //TODO make this so it only does this if the user isn't looking at a packet not sure if possible
        //setting screen to the bottom
        JScrollBar verticalBar = packetsDisplayScrollPanel.getVerticalScrollBar();
        verticalBar.setValue(verticalBar.getMaximum());






    }
}
