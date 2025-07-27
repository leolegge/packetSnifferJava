package org.sniffer.backend;

import org.pcap4j.core.PcapDumper;
import org.pcap4j.core.PcapHandle;
import org.pcap4j.core.PcapNetworkInterface;
import org.pcap4j.packet.Packet;
import org.sniffer.GUI.SnifferDashboard;

import java.sql.Timestamp;

//TODO also use a dumper to save the current capture

public class PacketSniffer implements Runnable {

    private int packetNumber = 0;

    PcapNetworkInterface networkInterface;
    PcapNetworkInterface.PromiscuousMode promiscuousMode;
    PcapDumper dumper;
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
            dumper = handle.dumpOpen("temp.pcap");
            while (running) {

                Packet packet = handle.getNextPacket();
                Timestamp timestamp = handle.getTimestamp();
                if (packet != null) {
                    packetNumber++;
                    IdentifiedPacket identifiedPacket = new IdentifiedPacket(packet, timestamp, packetNumber);
                    dashboard.addSharedPacket(identifiedPacket);

                    dumper.dump(packet, timestamp);



                    //Writing to the general packet frame
                    dashboard.getDashboardPacketPanelsWrapper()
                            .getPacketsDisplayPanel()
                            .addRowToDisplayPanel(identifiedPacket);


                    //Writing onto the Detail frame
                    if(dashboard.getDashboardMenuBar().getViewMenu().getDetailFrameAuthenticator().isAuthenticated()){
                        dashboard.getDashboardMenuBar().
                                getViewMenu().
                                getDetailFrameAuthenticator().
                                getDetailFrame().
                                addRowToTable(identifiedPacket);
                    }
                }
            }
            dumper.close();
            handle.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //getters
    public SnifferDashboard getDashboard() {
        return dashboard;
    }
    public boolean isRunning() {
        return running;
    }

    //setters
    public void setRunning(boolean running) {
        this.running = running;
    }
}
