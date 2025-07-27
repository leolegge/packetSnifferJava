package org.sniffer.GUI;

import org.pcap4j.core.PcapNativeException;
import org.pcap4j.core.PcapNetworkInterface;
import org.pcap4j.packet.Packet;
import org.sniffer.backend.PacketSniffer;
import org.sniffer.backend.Query;


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


    private List<Packet> sharedPacketList = Collections.synchronizedList(new ArrayList<>());
    private Packet selectedPacket = null;


    private PacketSniffer packetSniffer;
    private Thread snifferThread;

    private Query currentQuery = null;

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
    public Packet getSelectedPacket(){
        return selectedPacket;
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
    public void setSelectedPacket(Packet selectedPacket) {
        this.selectedPacket = selectedPacket;
    }
    public void setQuery(Query query) {
        this.currentQuery = query;
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
}
