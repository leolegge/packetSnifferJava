package org.sniffer.GUI.packetPanels;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PacketsDisplayPanel extends JPanel {

    PacketsDisplayScrollPanel packetsDisplayScrollPanel;



    private final String[] PACKET_COLUMNS_NAMES = {"Packet Number", "Packet source", "packet destination", "Protocol", "Length"};
    private String[][] packetTableData = {};

    //TODO this allows us to add rows to the table hopefully the program can deal with the amount of data
    DefaultTableModel mainPacketTable;
    JTable table;

    public PacketsDisplayPanel() {
        this.setBackground(Color.GREEN);

        this.setLayout(new BorderLayout());
        mainPacketTable = new DefaultTableModel(PACKET_COLUMNS_NAMES, 0);
        table = new JTable(mainPacketTable);

        packetsDisplayScrollPanel = new PacketsDisplayScrollPanel(table);



        this.add(packetsDisplayScrollPanel, BorderLayout.NORTH);

    }
}
