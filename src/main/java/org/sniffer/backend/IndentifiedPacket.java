package org.sniffer.backend;

import org.pcap4j.packet.Packet;
import org.pcap4j.packet.*;

public class IndentifiedPacket {

    private Packet packet;
    private PacketType packetType;

    //TODO nest this properly so high level packets are identified first


    public IndentifiedPacket(Packet packet) {
        if (packet.contains(TcpPacket.class)) {
            this.packet = packet.get(TcpPacket.class);
            this.packetType = PacketType.TCP;
        } else if (packet.contains(UdpPacket.class)) {
            this.packet = packet.get(UdpPacket.class);
            this.packetType = PacketType.UDP;
        } else if (packet.contains(IcmpV4CommonPacket.class)) {
            this.packet = packet.get(IcmpV4CommonPacket.class);
            this.packetType = PacketType.ICMPV4;
        } else if (packet.contains(IcmpV6CommonPacket.class)) {
            this.packet = packet.get(IcmpV6CommonPacket.class);
            this.packetType = PacketType.ICMPV6;
        } else if (packet.contains(IpV4Packet.class)) {
            this.packet = packet.get(IpV4Packet.class);
            this.packetType = PacketType.IPV4;
        } else if (packet.contains(IpV6Packet.class)) {
            this.packet = packet.get(IpV6Packet.class);
            this.packetType = PacketType.IPV6;
        } else if (packet.contains(ArpPacket.class)) {
            this.packet = packet.get(ArpPacket.class);
            this.packetType = PacketType.ARP;
        } else if (packet.contains(EthernetPacket.class)) {
            this.packet = packet.get(EthernetPacket.class);
            this.packetType = PacketType.ETHERNET;
        } else {
            this.packet = packet;
            this.packetType = PacketType.UNKNOWN;
        }

    }

    //getters
    public Packet getPacket() {
        return packet;
    }
    public PacketType getPacketType() {
        return packetType;
    }

}
