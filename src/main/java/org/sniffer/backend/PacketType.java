package org.sniffer.backend;

public enum PacketType {
    ETHERNET,
    ARP,
    IPV4,
    IPV6,
    TCP,
    UDP,
    ICMPV4,
    ICMPV6,
    UNKNOWN;

    @Override
    public String toString() {
        switch (this) {
            case ETHERNET:
                return "Ethernet";
            case ARP:
                return "ARP";
            case IPV4:
                return "IPV4";
            case IPV6:
                return "IPV6";
            case TCP:
                return "TCP";
            case UDP:
                return "UDP";
            case ICMPV4:
                return "ICMPV4";
            case ICMPV6:
                return "ICMPV6";
            case UNKNOWN:
                return "Unknown";
        }
        return null;
    }
}
