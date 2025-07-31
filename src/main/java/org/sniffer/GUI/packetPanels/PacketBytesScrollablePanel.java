package org.sniffer.GUI.packetPanels;

import javax.swing.*;

/**
 * This class allows the bytes panel to have scrollable functionality
 *
 */
public class PacketBytesScrollablePanel extends JScrollPane {


    public PacketBytesScrollablePanel(JTextArea textArea) {
        super(textArea);
    }
}
