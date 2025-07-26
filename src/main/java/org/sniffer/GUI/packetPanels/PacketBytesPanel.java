package org.sniffer.GUI.packetPanels;

import org.pcap4j.packet.Packet;

import javax.swing.*;
import javax.swing.text.StyledDocument;
import java.awt.*;

public class PacketBytesPanel extends JPanel {

    PacketBytesScrollablePanel packetBytesScrollablePanel;

    JTextArea bytesArea;

    public PacketBytesPanel() {
        this.setLayout(new BorderLayout());
        this.setBackground(Color.GREEN);

    }

    //TODO make this correctly display byte data onto the screen
    public void setBytesPage(Packet packet){

        bytesArea = new JTextArea();
        bytesArea.setEditable(false);
        bytesArea.setLineWrap(true);
        bytesArea.setWrapStyleWord(true);




        this.packetBytesScrollablePanel = new PacketBytesScrollablePanel(bytesArea);
    }
}
