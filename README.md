This is my packet sniffer project designed to resemble a simple packet sniffer with the ability to view packet infomation and raw hex bytes with a GUI built in swing

# Requirements
Java 17+
Pcap4j 1.8.2+
WinPcap / Npcap (for Windows)
libpcap (for Linux/macOS)
gradle

# To run the current build run
<code>
git clone https://github.com/leolegge/packetSnifferJava.git
cd packetSnifferJava
gradle run
</code>

# Features
- Live packet capture
- Source & destination IPs
- Protocol and port display
- Hex bytes display
- Saving and loading of previous captures
