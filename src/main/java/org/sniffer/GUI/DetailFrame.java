package org.sniffer.GUI;

import org.pcap4j.packet.*;
import org.sniffer.GUI.detailFramePanels.DetailedPacketsDisplayScrollPanel;
import org.sniffer.backend.IndentifiedPacket;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class DetailFrame extends JFrame {

    DetailedPacketsDisplayScrollPanel packetsDisplayScrollPanel;
    private final String[] PACKET_COLUMNS_NAMES = {"Packet Number", "Packet source", "packet destination", "Protocol", "Inner Protocol","Length (Bytes)"};


    DefaultTableModel mainDetailedPacketTable;
    JTable table;


    public DetailFrame(SnifferDashboard dashboard) {
        this.setTitle("Detail Viewer");
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

    //TODO this needs a refactor


    public void addRowToTable(Packet packet, int packetNumber) {



        //Finding what packet type it is
        IndentifiedPacket indentifiedPacket = new IndentifiedPacket(packet);


        switch (indentifiedPacket.getPacketType()) {
            case IPV4:
                IpV4Packet ipv4 = (IpV4Packet) indentifiedPacket.getPacket();
                mainDetailedPacketTable.addRow(new Object[]{packetNumber, ipv4.getHeader().getSrcAddr(), ipv4.getHeader().getDstAddr(), indentifiedPacket.getPacketType().toString(), ipv4.getHeader().getProtocol().toString()});
                break;
            case TCP:
                TcpPacket tcp = (TcpPacket) indentifiedPacket.getPacket();
                mainDetailedPacketTable.addRow(new Object[]{packetNumber, tcp.getHeader().getSrcPort().valueAsInt(),tcp.getHeader().getDstPort().valueAsInt(), indentifiedPacket.getPacketType().toString()});
                break;
            case UDP:
                UdpPacket udp = (UdpPacket) indentifiedPacket.getPacket();
                mainDetailedPacketTable.addRow(new Object[]{packetNumber, udp.getHeader().getSrcPort(), udp.getHeader().getDstPort(), indentifiedPacket.getPacketType().toString(), udp.getHeader().getLengthAsInt() });
                break;
            case IPV6:
                IpV6Packet ipv6 = (IpV6Packet) indentifiedPacket.getPacket();
                mainDetailedPacketTable.addRow(new Object[]{packetNumber, ipv6.getHeader().getSrcAddr(), ipv6.getHeader().getDstAddr(), indentifiedPacket.getPacketType().toString(), ipv6.getHeader().getProtocol().toString()});
                break;
            case ETHERNET:
                EthernetPacket eth = (EthernetPacket) indentifiedPacket.getPacket();
                mainDetailedPacketTable.addRow(new Object[]{packetNumber, eth.getHeader().getSrcAddr(), eth.getHeader().getDstAddr(), indentifiedPacket.getPacketType().toString()});
                break;
            case ARP:
                ArpPacket arp = (ArpPacket) indentifiedPacket.getPacket();
                mainDetailedPacketTable.addRow(new Object[]{packetNumber, arp.getHeader().getSrcHardwareAddr(), arp.getHeader().getDstHardwareAddr(), indentifiedPacket.getPacketType().toString(), arp.getHeader().getProtocolType().toString()});
                break;
            case ICMPV4:
                IcmpV4CommonPacket icmpv4 = (IcmpV4CommonPacket) indentifiedPacket.getPacket();
                mainDetailedPacketTable.addRow(new Object[]{packetNumber, "N/A", "N/A", indentifiedPacket.getPacketType().toString()});
                break;
            case ICMPV6:
                IcmpV6CommonPacket icmpv6 = (IcmpV6CommonPacket) indentifiedPacket.getPacket();
                mainDetailedPacketTable.addRow(new Object[]{packetNumber, "N/A", "N/A", indentifiedPacket.getPacketType().toString()});
                break;
            case UNKNOWN:
                //Do nothing probably could add stuff
                break;
            default:
                System.out.println("Unknown packet type");
        }


        //TODO make this so it only does this if the user isn't looking at a packet not sure if possible
        //setting screen to the bottom
        JScrollBar verticalBar = packetsDisplayScrollPanel.getVerticalScrollBar();
        verticalBar.setValue(verticalBar.getMaximum());






    }
}
