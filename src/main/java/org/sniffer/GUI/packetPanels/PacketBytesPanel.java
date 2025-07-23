package org.sniffer.GUI.packetPanels;

import org.pcap4j.packet.Packet;

import javax.swing.*;
import javax.swing.text.StyledDocument;
import java.awt.*;

public class PacketBytesPanel extends JPanel {

    PacketBytesScrollablePanel packetBytesScrollablePanel;

    JTextPane bytesPane;

    public PacketBytesPanel() {
        this.setLayout(new BorderLayout());
        this.setBackground(Color.GREEN);

    }

    //TODO make this correctly display byte data onto the screen
    public void setBytesPage(Packet packet){

        bytesPane = new JTextPane();
        bytesPane.setFont(new Font("Monospaced", Font.PLAIN, 12));
        bytesPane.setEditable(false);
        StyledDocument doc = bytesPane.getStyledDocument();



        this.packetBytesScrollablePanel = new PacketBytesScrollablePanel(bytesPane);
    }
}
