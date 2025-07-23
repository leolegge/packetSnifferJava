package org.sniffer.backend;

import org.pcap4j.core.PcapHandle;
import org.pcap4j.core.PcapNetworkInterface;
import org.pcap4j.packet.Packet;
import org.sniffer.GUI.SnifferDashboard;

//TODO write straight to JTables from this class when running
//TODO also use a dumper to save the current capture


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

            int packetNumber = 0;
            while (running) {

                Packet packet = handle.getNextPacket();
                if (packet != null) {
                    packetNumber++;
                    System.out.println("Packet " + packetNumber + " " + packet.getHeader());
                    dashboard.addSharedPacket(packet);


                    //Writing onto the Detail frame
                    if(dashboard.getDashboardMenuBar().getViewMenu().getDetailFrameAuthenticator().isAuthenticated()){
                        dashboard.getDashboardMenuBar().
                                getViewMenu().
                                getDetailFrameAuthenticator().
                                getDetailFrame().
                                addRowToTable(packet, packetNumber);
                    }
                }
            }

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
