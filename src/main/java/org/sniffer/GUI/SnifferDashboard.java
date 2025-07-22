package org.sniffer.GUI;

import org.pcap4j.core.PcapNativeException;
import org.pcap4j.core.PcapNetworkInterface;
import org.sniffer.backend.PacketSniffer;


import javax.swing.*;
import java.awt.*;

/**
 * This is the main GUI class which contains all the necessary panels for the program
 *
 */
public class SnifferDashboard extends JFrame {

    private SnifferMenuBar menuBar;
    private PacketQueryPanel packetQueryPanel;
    private PacketPanelsWrapper packetPanelsWrapper = new PacketPanelsWrapper();

    private PacketSniffer packetSniffer;
    private Thread snifferThread;

    private PcapNetworkInterface networkInterface = null;



    public SnifferDashboard() throws PcapNativeException {
        this.setTitle("Sniffer");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        // Set fullscreen
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        //setting up menu bar
        menuBar = new SnifferMenuBar(this);

        //Adding menu bar
        this.setJMenuBar(menuBar);

        //setting up query panel
        packetQueryPanel = new PacketQueryPanel(this);

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

    public SnifferMenuBar getDashboardMenuBar(){
        return menuBar;
    }

    public PacketQueryPanel getDashboardPacketQueryPanel() {
        return packetQueryPanel;
    }
    public PacketPanelsWrapper getDashboardPacketPanelsWrapper() {
        return packetPanelsWrapper;
    }

    public PacketSniffer getPacketSniffer() {
        return packetSniffer;
    }
    public Thread getSnifferThread(){
        return snifferThread;
    }


    //setter
    public void setNetworkInterface(PcapNetworkInterface networkInterface) {
        this.networkInterface = networkInterface;
    }

    public void setPacketSniffer(PacketSniffer packetSniffer) {
        this.packetSniffer = packetSniffer;
    }
    public void setSnifferThread(Thread snifferThread) {
        this.snifferThread = snifferThread;
    }

    //general methods
    public void startSniffer() {
        snifferThread.start();
    }

    public void stopSniffer() {
        packetSniffer.setRunning(false);
    }



}
