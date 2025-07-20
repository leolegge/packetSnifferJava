package org.sniffer.GUI;

import org.pcap4j.core.PcapNativeException;

import javax.swing.*;
import java.awt.*;

public class SnifferDashboard extends JFrame {

    SnifferMenuBar menuBar = new SnifferMenuBar(this);

    public SnifferDashboard() throws PcapNativeException {
        this.setTitle("Sniffer");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Get the default screen device
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

        // Set fullscreen
        gd.setFullScreenWindow(this);

        //Adding menu bar
        this.setJMenuBar(menuBar);
        this.setVisible(true);
    }

}
