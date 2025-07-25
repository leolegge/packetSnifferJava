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

    private boolean supressListener = false;

    private final String[] PACKET_COLUMNS_NAMES = {"Packet number",
            "Protocol",
            "Info",
            "length (bytes)",
            "timestamp",};


    DefaultTableModel mainPacketTable;
    JTable table;

    public PacketsDisplayPanel(SnifferDashboard dashboard) {
        this.dashboard = dashboard;
        this.setLayout(new BorderLayout());
        mainPacketTable = new DefaultTableModel(PACKET_COLUMNS_NAMES, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;  // prevents editing
            }
        };

        table = new JTable(mainPacketTable);
        table.setRowSelectionAllowed(true);

        //Table listener
        ListSelectionModel mainPacketTableSelectionModel = table.getSelectionModel();

        mainPacketTableSelectionModel.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && !supressListener) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    int packetNumberSelected = Integer.parseInt(table.getValueAt(selectedRow, 0).toString());

                    if(dashboard.getDashboardMenuBar().getViewMenu().getDetailFrameAuthenticator().isAuthenticated()) {
                        int startingPacketNumber = Integer.parseInt(dashboard.
                                getDashboardMenuBar().
                                getViewMenu().
                                getDetailFrameAuthenticator().
                                getDetailFrame().
                                getTable().
                                getValueAt(0,0).toString());
                        if (startingPacketNumber <= packetNumberSelected) {
                            //temporarily turn off other tables listener to avoid infinite loop
                            dashboard.getDashboardMenuBar().
                                    getViewMenu().getDetailFrameAuthenticator().
                                    getDetailFrame().setSuppressListener(true);
                            //setting selection
                            dashboard.getDashboardMenuBar().
                                    getViewMenu().
                                    getDetailFrameAuthenticator().
                                    getDetailFrame().
                                    getTable().setRowSelectionInterval(packetNumberSelected - startingPacketNumber,
                                            packetNumberSelected - startingPacketNumber);

                            dashboard.getDashboardMenuBar().
                                    getViewMenu().getDetailFrameAuthenticator().
                                    getDetailFrame().setSuppressListener(false);
                        }
                    }




                    dashboard.setSelectedPacket(dashboard.findPacket(packetNumberSelected));
                    System.out.println(dashboard.getSelectedPacket());

                }
            }
        });





        packetsDisplayScrollPanel = new PacketsDisplayScrollPanel(table);

        this.add(packetsDisplayScrollPanel, BorderLayout.NORTH);

    }


    public JTable getTable() {
        return table;
    }
    public boolean getSuppressListener() {
        return supressListener;
    }



    public void setSuppressListener(boolean suppressListener) {
        this.supressListener = suppressListener;
    }

    public void addRowToDisplayPanel(IdentifiedPacket identifiedPacket) {
        mainPacketTable.addRow(new Object[] {identifiedPacket.getPacketNumber(),
                identifiedPacket.getProtocol(), "Filler", identifiedPacket.getPacket().length(), identifiedPacket.getTimestamp()});


    }

    public void resetTable(){
        this.mainPacketTable.setRowCount(0);
    }

}
