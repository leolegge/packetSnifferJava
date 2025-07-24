package org.sniffer.backend;

import org.pcap4j.packet.Packet;
import org.pcap4j.packet.*;

//TODO make a function to get the exact protocol of the received packet out of the raw hex bytes only do this for a few
//TODO as there thousands of different protocols

public class IdentifiedPacket {

    private final Packet rawPacket;

    private String srcIp = "N/A";
    private String dstIp = "N/A";
    private String protocol = "Unknown";
    private String srcPort = "N/A";
    private String dstPort = "N/A";


    public IdentifiedPacket(Packet rawPacket) {
        this.rawPacket = rawPacket;
        parsePacket();
    }


    //general methods
    public void parsePacket() {
        ArpPacket arp = rawPacket.get(ArpPacket.class);
        if (arp != null) {
            protocol = "ARP";
            ArpPacket.ArpHeader h = arp.getHeader();
            srcIp = h.getSrcProtocolAddr().getHostAddress();
            dstIp = h.getDstProtocolAddr().getHostAddress();
            return;
        }

        //checking for ipv4 or ipv6
        IpPacket ip = rawPacket.get(IpV4Packet.class);
        if (ip == null) {
            ip = rawPacket.get(IpV6Packet.class);
        }

        if (ip != null) {
            IpPacket.IpHeader ipHeader = ip.getHeader();
            srcIp = ipHeader.getSrcAddr().getHostAddress();
            dstIp = ipHeader.getDstAddr().getHostAddress();

            TcpPacket tcp = rawPacket.get(TcpPacket.class);
            UdpPacket udp = rawPacket.get(UdpPacket.class);

            if (tcp != null) {
                protocol = "TCP";
                srcPort = tcp.getHeader().getSrcPort().valueAsString();
                dstPort = tcp.getHeader().getDstPort().valueAsString();

                byte[] payload = tcp.getPayload() != null ? tcp.getPayload().getRawData() : null;
                if (payload != null && isLikelyHttp(payload)) {
                    protocol = "HTTP";
                }

            } else if (udp != null) {
                protocol = "UDP";
                srcPort = udp.getHeader().getSrcPort().valueAsString();
                dstPort = udp.getHeader().getDstPort().valueAsString();

                DnsPacket dns = rawPacket.get(DnsPacket.class);
                if (dns != null) {
                    protocol = "DNS";
                }
            }
        }
    }

    private boolean isLikelyHttp(byte[] data) {
        String str = new String(data);
        return str.startsWith("GET") || str.startsWith("POST") || str.startsWith("HTTP");
    }



    //getters
    public Packet getPacket() {
        return rawPacket;
    }
    public String getSrcIp() {
        return srcIp;
    }
    public String getDstIp() {
        return dstIp;
    }
    public String getProtocol() {
        return protocol;
    }
    public String getSrcPort() {
        return srcPort;
    }
    public String getDstPort() {
        return dstPort;
    }


}
