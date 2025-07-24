package org.sniffer.GUI.packetPanels;

import org.pcap4j.packet.Packet;
import org.sniffer.GUI.SnifferDashboard;
import org.sniffer.backend.IdentifiedPacket;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Timestamp;

public class PacketsDisplayPanel extends JPanel {

    PacketsDisplayScrollPanel packetsDisplayScrollPanel;

    SnifferDashboard dashboard;

    private final String[] PACKET_COLUMNS_NAMES = {"Packet number",
            "Protocol",
            "Info",
            "length (bytes)",
            "timestamp",};

    //TODO this allows us to add rows to the table hopefully the program can deal with the amount of data
    //TODO add a listener handler to choose what data to send to the information panel when the table is pressed on

    DefaultTableModel mainPacketTable;
    JTable table;

    public PacketsDisplayPanel(SnifferDashboard dashboard) {
        this.dashboard = dashboard;
        this.setLayout(new BorderLayout());
        mainPacketTable = new DefaultTableModel(PACKET_COLUMNS_NAMES, 0);
        table = new JTable(mainPacketTable);



        //Table listener
        ListSelectionModel mainPacketTableSelectionModel = table.getSelectionModel();

        mainPacketTableSelectionModel.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    //TODO can make a object from this or push to the PacketInfomationPanel
                    dashboard.printAllLayers(dashboard.findPacket(
                            Integer.parseInt(
                                    table.getValueAt(selectedRow, 0).toString())));


                }
            }
        });





        packetsDisplayScrollPanel = new PacketsDisplayScrollPanel(table);

        this.add(packetsDisplayScrollPanel, BorderLayout.NORTH);

    }

    public void addRowToDisplayPanel(IdentifiedPacket identifiedPacket) {
        mainPacketTable.addRow(new Object[] {identifiedPacket.getPacketNumber(),
                identifiedPacket.getProtocol(), "Filler", identifiedPacket.getPacket().length(), identifiedPacket.getTimestamp()});


    }

}
