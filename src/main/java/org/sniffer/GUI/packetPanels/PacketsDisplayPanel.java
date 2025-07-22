package org.sniffer.GUI.packetPanels;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PacketsDisplayPanel extends JPanel {

    PacketsDisplayScrollPanel packetsDisplayScrollPanel;

    private final String[] PACKET_COLUMNS_NAMES = {"Packet Number", "Packet source", "packet destination", "Protocol", "Length"};
    private String[][] packetTableData = {};

    //TODO this allows us to add rows to the table hopefully the program can deal with the amount of data
    //TODO add a listener handler to choose what data to send to the information panel when the table is pressed on

    DefaultTableModel mainPacketTable;
    JTable table;

    public PacketsDisplayPanel() {
        this.setBackground(Color.GREEN);

        this.setLayout(new BorderLayout());
        mainPacketTable = new DefaultTableModel(PACKET_COLUMNS_NAMES, 0);
        table = new JTable(mainPacketTable);

        mainPacketTable.addRow(new String[] {"test","test2", "test3", "test4", "test5"});

        ListSelectionModel mainPacketTableSelectionModel = table.getSelectionModel();


        /////////////////////////////////////////////THIS IS AN EXAMPLE OF HOW TO GET DATA FROM TABLE

        mainPacketTableSelectionModel.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    System.out.println("Selected Row: " + selectedRow);
                    //TODO can make a object from this or push to the PacketInfomationPanel
                    for (int i = 0; i < PACKET_COLUMNS_NAMES.length; i++) {
                        System.out.println(table.getValueAt(selectedRow, i));
                    }
                }
            }
        });

        ///////////////////////////////////////////////////////////////////////////////////////////////



        packetsDisplayScrollPanel = new PacketsDisplayScrollPanel(table);

        this.add(packetsDisplayScrollPanel, BorderLayout.NORTH);

    }
}
