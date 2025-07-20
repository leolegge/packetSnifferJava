package org.sniffer;
import org.sniffer.GUI.*;

import org.pcap4j.core.*;
import org.pcap4j.packet.Packet;

import java.util.List;

public class Main {
    public static void main(String[] args) throws PcapNativeException, NotOpenException {


        //this snippet finds all the possible network devices on the network

        List<PcapNetworkInterface> nif = Pcaps.findAllDevs();

        if (nif == null) {
            System.out.println("No network interfaces found.");

        }else{
            for (PcapNetworkInterface ni : nif) {
                System.out.println(ni);
            }
        }

        /////////////////////////////////////////////////////

        SnifferDashboard dahsboard = new SnifferDashboard();



    }
}