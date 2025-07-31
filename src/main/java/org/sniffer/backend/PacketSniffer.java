package org.sniffer.backend;

import org.pcap4j.core.PcapDumper;
import org.pcap4j.core.PcapHandle;
import org.pcap4j.core.PcapNetworkInterface;
import org.pcap4j.packet.Packet;
import org.sniffer.GUI.SnifferDashboard;

import java.sql.Timestamp;


/**
 * This is the packet sniffer that collect all the packets and running from a separate thread
 *
 */
public class PacketSniffer implements Runnable {
    private SnifferDashboard dashboard;

    private int packetNumber = 0;

    private PcapNetworkInterface networkInterface;
    private PcapNetworkInterface.PromiscuousMode promiscuousMode;
    private PcapDumper dumper;
    private boolean running = true;



    /**
     * This is the constructor to create a PacketSniffer object
     *
     * @param dashboard is a reference to the SnifferDashboard which contains everything relating to the GUI
     * @param networkInterface is the network interface being sniffed on
     * @param promiscuousMode is the mode the sniffer is in
     */
    public PacketSniffer(SnifferDashboard dashboard,PcapNetworkInterface networkInterface, PcapNetworkInterface.PromiscuousMode promiscuousMode) {
        this.dashboard = dashboard;
        this.networkInterface = networkInterface;
        this.promiscuousMode = promiscuousMode;
    }

    /**
     * This is the run method for the thread that writes packets onto the GUI and populates the shared packets list
     * It also dumps packets to be saved if required
     *
     */
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


                    if(dashboard.getCurrentQuery() == null) {
                        //Writing to the general packet frame
                        dashboard.getDashboardPacketPanelsWrapper()
                                .getPacketsDisplayPanel()
                                .addRowToDisplayPanel(identifiedPacket);


                        //Writing onto the Detail frame
                        if (dashboard.getDashboardMenuBar().getViewMenu().getDetailFrameAuthenticator().isAuthenticated()) {
                            dashboard.getDashboardMenuBar().
                                    getViewMenu().
                                    getDetailFrameAuthenticator().
                                    getDetailFrame().
                                    addRowToTable(identifiedPacket);
                        }
                    }else{
                        for(SubQuery subQuery : dashboard.getCurrentQuery().getSubQueries()){
                            if(subQuery.getQueryOption().equals(QueryOptions.translateToQueryOption(identifiedPacket.getProtocol()))){
                                //Writing to the general packet frame
                                dashboard.getDashboardPacketPanelsWrapper()
                                        .getPacketsDisplayPanel()
                                        .addRowToDisplayPanel(identifiedPacket);


                                //Writing onto the Detail frame
                                if (dashboard.getDashboardMenuBar().getViewMenu().getDetailFrameAuthenticator().isAuthenticated()) {
                                    dashboard.getDashboardMenuBar().
                                            getViewMenu().
                                            getDetailFrameAuthenticator().
                                            getDetailFrame().
                                            addRowToTable(identifiedPacket);
                                }
                            }
                        }



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
