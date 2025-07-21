package org.sniffer.GUI;

import org.pcap4j.core.PcapNativeException;
import org.sniffer.GUI.menus.PacketInformationPanel;
import org.sniffer.GUI.menus.PacketQueryPanel;

import javax.swing.*;
import java.awt.*;

public class SnifferDashboard extends JFrame {

    SnifferMenuBar menuBar = new SnifferMenuBar(this);
    PacketsDisplayPanel packetsDisplayPanel = new PacketsDisplayPanel();
    PacketInformationPanel packetInformationPanel = new PacketInformationPanel();
    PacketQueryPanel packetQueryPanel = new PacketQueryPanel();

    public SnifferDashboard() throws PcapNativeException {
        this.setTitle("Sniffer");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Get the default screen device
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

        // Set fullscreen
        gd.setFullScreenWindow(this);

        //Adding menu bar
        this.setJMenuBar(menuBar);

        //set layout of frame
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

        //Adding panels to frame
        this.add(packetQueryPanel);
        this.add(packetsDisplayPanel);
        this.add(packetInformationPanel);


        this.setVisible(true);
    }

}
