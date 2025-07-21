package org.sniffer.GUI;

import org.pcap4j.core.PcapNativeException;
import org.pcap4j.core.PcapNetworkInterface;


import javax.swing.*;
import java.awt.*;

/**
 * This is the main GUI class which contains all the necessary panels for the program
 *
 */
public class SnifferDashboard extends JFrame {

    SnifferMenuBar menuBar = new SnifferMenuBar(this);
    PacketQueryPanel packetQueryPanel = new PacketQueryPanel();
    PacketPanelsWrapper packetPanelsWrapper = new PacketPanelsWrapper();

    private PcapNetworkInterface networkInterface = null;



    public SnifferDashboard() throws PcapNativeException {
        this.setTitle("Sniffer");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        // Set fullscreen
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        //Adding menu bar
        this.setJMenuBar(menuBar);

        //set layout of frame
        this.setLayout(new BorderLayout());

        //Adding panels
        this.add(packetQueryPanel, BorderLayout.NORTH);
        this.add(packetPanelsWrapper, BorderLayout.CENTER);



        this.setVisible(true);
    }

    //getter
    public PcapNetworkInterface getNetworkInterface() {
        return networkInterface;
    }


    //setter
    public void setNetworkInterface(PcapNetworkInterface networkInterface) {
        this.networkInterface = networkInterface;
    }




}
