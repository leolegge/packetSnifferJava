package org.sniffer.backend;

import org.pcap4j.core.PcapHandle;
import org.pcap4j.core.PcapNetworkInterface;
import org.pcap4j.packet.Packet;

public class PacketSniffer implements Runnable {

    PcapNetworkInterface networkInterface;
    private boolean running = true;


    public PacketSniffer(PcapNetworkInterface networkInterface) {
        this.networkInterface = networkInterface;
    }
    @Override
    public void run() {

        try {
            PcapHandle handle = networkInterface.openLive(
                    65536,                             // snaplen
                    PcapNetworkInterface.PromiscuousMode.PROMISCUOUS,
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
