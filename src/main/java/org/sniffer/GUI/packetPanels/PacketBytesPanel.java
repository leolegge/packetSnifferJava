package org.sniffer.GUI.packetPanels;

import org.pcap4j.packet.Packet;

import javax.swing.*;
import javax.swing.text.StyledDocument;
import java.awt.*;

/**
 * This class is used to contain and manage how packet bytes are displayed onto the GUI
 *
 */
public class PacketBytesPanel extends JPanel {

    PacketBytesScrollablePanel packetBytesScrollablePanel;

    JTextArea bytesArea;

    /**
     * Constructor for the panel initially it is empty as in the programs initial state no packet is selected
     *
     */
    public PacketBytesPanel() {
        this.setLayout(new BorderLayout());
        bytesArea = new JTextArea();
        bytesArea.setEditable(false);
        bytesArea.setLineWrap(true);
        bytesArea.setWrapStyleWord(true);
        bytesArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        this.packetBytesScrollablePanel = new PacketBytesScrollablePanel(bytesArea);
        this.add(packetBytesScrollablePanel, BorderLayout.CENTER);
    }

    /**
     * This sets the data on the panel which is then added and displayed
     *
     * @param packet is the packet whose bytes is to be displayed
     */
    public void setBytesPage(Packet packet){
        bytesArea.removeAll();

        byte[] rawData = packet.getRawData();

        StringBuilder hexBuilder = new StringBuilder();

        for (int i = 0; i < rawData.length; i++) {
            if (i % 16 == 0) {
                hexBuilder.append(String.format("%04X: ", i)); // offset
            }

            hexBuilder.append(String.format("%02X ", rawData[i]));

            if ((i + 1) % 16 == 0 || i == rawData.length - 1) {
                hexBuilder.append("\n");
            }
        }

        bytesArea.setText(hexBuilder.toString());
    }
}
