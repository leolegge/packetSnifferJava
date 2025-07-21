package org.sniffer.backend;

import org.pcap4j.core.PcapHandle;
import org.pcap4j.core.PcapNetworkInterface;
import org.pcap4j.packet.Packet;

public class PacketSniffer implements Runnable {

    PcapNetworkInterface networkInterface;
    PcapNetworkInterface.PromiscuousMode promiscuousMode;
    private boolean running = true;


    public PacketSniffer(PcapNetworkInterface networkInterface, PcapNetworkInterface.PromiscuousMode promiscuousMode) {
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

            while (running) {
                Packet packet = handle.getNextPacket();
                if (packet != null) {
                    System.out.println(packet);  // you can forward this to your GUI panel
                }
            }

            handle.close();
            System.out.println("Stopped sniffing.");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
