package org.sniffer.GUI.menus;

import org.pcap4j.core.PcapDumper;
import org.pcap4j.core.PcapHandle;
import org.pcap4j.core.Pcaps;
import org.pcap4j.packet.Packet;
import org.sniffer.GUI.SnifferDashboard;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


public class FileMenu extends JMenu {
   private SnifferDashboard dashboard;


   private JButton saveButton = new JButton("Save");
   private JButton openButton = new JButton("Open");


    public FileMenu(SnifferDashboard dashboard) {
        super("File");
        this.dashboard = dashboard;

        //adding buttons
        this.add(saveButton);
        this.add(openButton);

        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //TODO add save functionality

                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Save capture as...");
                fileChooser.setFileFilter(new FileNameExtensionFilter("PCAP files", "pcap"));

                int option = fileChooser.showSaveDialog(null);

                if (option == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();

                    // Ensure .pcap extension
                    if (!file.getName().toLowerCase().endsWith(".pcap")) {
                        file = new File(file.getAbsolutePath() + ".pcap");
                    }

                    try {
                        Files.copy(Paths.get("temp.pcap"), file.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }

                    new File("temp.pcap").delete();


                }
            }
        });

        openButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Open a .pcap file");
                fileChooser.setFileFilter(new FileNameExtensionFilter("PCAP files", "pcap"));

                int result = fileChooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();

                    dashboard.getDashboardMenuBar().getNetworkMenu().unselectRadioButtons();

                    //Loading file here
                    try (PcapHandle handle = Pcaps.openOffline(file.getAbsolutePath())) {
                        System.out.println("Opened: " + file.getAbsolutePath());

                        Packet packet;
                        while ((packet = handle.getNextPacket()) != null) {
                            System.out.println("Timestamp: " + handle.getTimestamp());
                            System.out.println("Packet: " + packet);
                        }

                        System.out.println("Finished reading.");
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Failed to open pcap: " + ex.getMessage());
                    }


                }
            }
        });

        saveButton.setEnabled(false);
        openButton.setEnabled(true);

    }

    public JButton getSaveButton() {
        return saveButton;
    }

    public JButton getOpenButton() {
        return openButton;
    }


}
