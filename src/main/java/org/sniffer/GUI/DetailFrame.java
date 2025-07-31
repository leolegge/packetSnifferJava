package org.sniffer.GUI;

import org.pcap4j.packet.*;
import org.sniffer.GUI.detailFramePanels.DetailedPacketsDisplayScrollPanel;
import org.sniffer.backend.IdentifiedPacket;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * This class is used to contain everything to do with the detail frame which can be activated in the view menu
 *
 */
public class DetailFrame extends JFrame {

    DetailedPacketsDisplayScrollPanel packetsDisplayScrollPanel;
    private final String[] PACKET_COLUMNS_NAMES = {"Packet Number", "Source", "Destination","Port source", "Port destination" ,"Protocol","Exact Protocol","Length"};


    private boolean suppressListener = false;
    DefaultTableModel mainDetailedPacketTable;
    JTable table;

    /**
     * This is the constructor for this class and sets up everything to do with its configuration
     *
     * @param dashboard is a reference to a SnifferDashboard object to be able to receive packets and display them
     *                  in the frame
     */
    public DetailFrame(SnifferDashboard dashboard) {
        this.setTitle("Detail Viewer");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(800, 400);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(dashboard);
        this.setVisible(true);

        mainDetailedPacketTable = new DefaultTableModel(PACKET_COLUMNS_NAMES, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;  // prevents editing
            }
        };
        table = new JTable(mainDetailedPacketTable);
        table.setRowSelectionAllowed(true);
        packetsDisplayScrollPanel = new DetailedPacketsDisplayScrollPanel(table);

        this.add(packetsDisplayScrollPanel, BorderLayout.CENTER);

        ListSelectionModel mainDetailedPacketTableSelectionModel = table.getSelectionModel();

        mainDetailedPacketTableSelectionModel.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && !suppressListener) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    int packetNumberSelected = Integer.parseInt(table.getValueAt(selectedRow, 0).toString());
                    //temporarily turn off other tables listener to avoid infinite loop
                    dashboard.getDashboardPacketPanelsWrapper().
                            getPacketsDisplayPanel().setSuppressListener(true);
                    dashboard.getDashboardPacketPanelsWrapper().
                            getPacketsDisplayPanel().
                            getTable().
                            setRowSelectionInterval(packetNumberSelected-1, packetNumberSelected-1);
                    dashboard.getDashboardPacketPanelsWrapper().
                            getPacketsDisplayPanel().setSuppressListener(false);

                    dashboard.setSelectedPacket(dashboard.findPacket(packetNumberSelected));
                    dashboard.getDashboardPacketPanelsWrapper().
                            getPacketInformationPanel().
                            getPayloadPanel().
                            setUpPayloadPanel(dashboard.getSelectedPacket().getPacket(), dashboard);
                    dashboard.getDashboardPacketPanelsWrapper().
                            getPacketInformationPanel().
                            getPacketBytesPanel().
                            setBytesPage(dashboard.getSelectedPacket().getPacket());

                }
            }
        });


    }

    //general methods
    public void addRowToTable(IdentifiedPacket identifiedPacket) {

        mainDetailedPacketTable.addRow(new Object[]{identifiedPacket.getPacketNumber(),
                identifiedPacket.getSrcIp(),
                identifiedPacket.getDstIp(),
                identifiedPacket.getSrcPort(),
                identifiedPacket.getDstPort(),
                identifiedPacket.getProtocol(),
                identifiedPacket.getLowestPacket().getClass().getSimpleName().replace("Packet", ""),
                identifiedPacket.getPacket().length()});

        //TODO make this so it only does this if the user isn't looking at a packet not sure if possible
        //setting screen to the bottom
        JScrollBar verticalBar = packetsDisplayScrollPanel.getVerticalScrollBar();
        verticalBar.setValue(verticalBar.getMaximum());
    }

    public void resetMainDetailedPacketTable() {
        mainDetailedPacketTable.setRowCount(0);
    }

    //getters
    public JTable getTable() {
        return table;
    }
    public boolean getSuppressListener() {
        return suppressListener;
    }

    //setters
    public void setSuppressListener(boolean suppressListener) {
        this.suppressListener = suppressListener;
    }
}
