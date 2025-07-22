package org.sniffer.backend;

import org.pcap4j.core.PcapHandle;
import org.pcap4j.core.PcapNetworkInterface;
import org.pcap4j.packet.Packet;
import org.sniffer.GUI.SnifferDashboard;

//TODO update this class to deal with more stuff

public class PacketSniffer implements Runnable {



    PcapNetworkInterface networkInterface;
    PcapNetworkInterface.PromiscuousMode promiscuousMode;
    private boolean running = true;

    private SnifferDashboard dashboard;


    public PacketSniffer(SnifferDashboard dashboard,PcapNetworkInterface networkInterface, PcapNetworkInterface.PromiscuousMode promiscuousMode) {
        this.dashboard = dashboard;
        this.networkInterface = networkInterface;
        this.promiscuousMode = promiscuousMode;
    }
    @Override
    public void run() {

        try {
            PcapHandle handle = networkInterface.openLive(
                    65536,                             // snaplen
                    promiscuousMode,
                    10                                 // timeout in ms
            );

            System.out.println("Started sniffing on " + networkInterface.getName());

            int i = 0;
            while (running) {

                Packet packet = handle.getNextPacket();
                if (packet != null) {
                    i++;
                    System.out.println("Packet " + i + " " + packet.getHeader()); //Can add to GUI here
                }
            }

            handle.close();
            System.out.println("Stopped sniffing.");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public SnifferDashboard getDashboard() {
        return dashboard;
    }
}
