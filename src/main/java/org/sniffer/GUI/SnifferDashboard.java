package org.sniffer.GUI;

import org.pcap4j.core.PcapNativeException;
import org.pcap4j.core.PcapNetworkInterface;
import org.pcap4j.packet.Packet;
import org.sniffer.backend.PacketSniffer;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//TODO add saving and loading using Pcap dump this is to be implemented after packets can be pushed to both Detail frame
//TODO and the main frame


/**
 * This is the main GUI class which contains all the necessary panels for the program
 *
 */
public class SnifferDashboard extends JFrame {

    private SnifferMenuBar menuBar;
    private PacketQueryPanel packetQueryPanel;
    private PacketPanelsWrapper packetPanelsWrapper;


    private List<Packet> sharedPacketList;


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

        //setting up packetWrapperPanel
        packetPanelsWrapper = new PacketPanelsWrapper(this);

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
    public List<Packet> getSharedPacketList() {
        synchronized (sharedPacketList) {
            return sharedPacketList;
        }
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
    public void setSharedPacketList(List<Packet> sharedPacketList) {
        this.sharedPacketList = sharedPacketList;
    }

    //general methods
    public void startSniffer() {
        packetSniffer.setRunning(true);
        snifferThread.start();
    }

    public void stopSniffer() {
        packetSniffer.setRunning(false);
    }

    public void addSharedPacket(Packet packet) {
        sharedPacketList.add(packet);
    }

    public Packet findPacket(int packetNumber){
        synchronized (sharedPacketList) {
            return sharedPacketList.get(packetNumber-1);
        }
    }

    public void printAllLayers(Packet packet) {
        Packet current = packet;
        int layer = 1;

        while (current != null) {
            System.out.println("Layer " + layer + ": " + current.getClass().getSimpleName());
            System.out.println(current); // or current.getHeader() for just header info
            current = current.getPayload(); // go to next inner layer
            layer++;
        }
    }





}
