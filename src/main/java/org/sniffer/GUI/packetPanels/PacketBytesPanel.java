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
        bytesArea = new JTextArea();
        bytesArea.setEditable(false);
        bytesArea.setLineWrap(true);
        bytesArea.setWrapStyleWord(true);
        bytesArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        this.packetBytesScrollablePanel = new PacketBytesScrollablePanel(bytesArea);
        this.add(packetBytesScrollablePanel, BorderLayout.CENTER);


    }

    //TODO make this correctly display byte data onto the screen
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
