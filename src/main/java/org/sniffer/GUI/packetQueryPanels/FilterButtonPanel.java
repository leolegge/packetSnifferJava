package org.sniffer.GUI.packetQueryPanels;

import org.pcap4j.packet.Packet;
import org.sniffer.GUI.SnifferDashboard;
import org.sniffer.backend.InvalidQueryOption;
import org.sniffer.backend.Query;
import org.sniffer.backend.QueryOptions;
import org.sniffer.backend.SubQuery;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class FilterButtonPanel extends JPanel {

    private SnifferDashboard dashboard;

    private final JButton applyFilterButton = new JButton("Apply filter");
    private final JButton clearFilterButton = new JButton("Clear filter");

    public FilterButtonPanel(SnifferDashboard dashboard) {
        this.dashboard = dashboard;
        this.setLayout(new BorderLayout());

        this.add(applyFilterButton, BorderLayout.WEST);
        this.add(clearFilterButton, BorderLayout.EAST);

        applyFilterButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String filterText = dashboard.getDashboardPacketQueryPanel().getQueryField().getText();
                String[] tokens = filterText.split("\\s+");
                Query newQuery = new Query();
                SubQuery currentSubQuery;
                if(tokens.length > 0) {
                    try {
                        currentSubQuery = new SubQuery(QueryOptions.translateToQueryOption(tokens[0]));
                        newQuery.addSubQuery(currentSubQuery);
                    } catch (InvalidQueryOption ex) {
                        JOptionPane.showMessageDialog(
                                null,
                                ex.getMessage(),
                                "Error",
                                JOptionPane.ERROR_MESSAGE
                        );
                    }
                    int i = 1;
                    while(tokens.length > 1 && i<tokens.length && Objects.equals(tokens[i], "&&")) {
                        i = i + 2;
                        try {
                            currentSubQuery = new SubQuery(QueryOptions.translateToQueryOption(tokens[i-1]));
                            newQuery.addSubQuery(currentSubQuery);
                        } catch (InvalidQueryOption ex) {
                            JOptionPane.showMessageDialog(
                                    null,
                                    ex.getMessage(),
                                    "Error",
                                    JOptionPane.ERROR_MESSAGE
                            );
                        }
                    }
                }

                dashboard.setQuery(newQuery);
                //resetting all packets on display
                dashboard.getDashboardPacketPanelsWrapper().getPacketsDisplayPanel().resetTable();

                //Going through shared packets list to write all packets that fit the filter onto screen
                for(Packet currentPacket : dashboard.getSharedPacketList()){

                }


                //TODO parse this to delete all things on screen currently then go through the
                //TODO shared list which need to be changed to accomodate IdentifiedPackets
                //TODO then write the filtered packets to the dashboard



            }
        });

        clearFilterButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dashboard.getDashboardPacketQueryPanel().resetQueryField();
                //TODO also reset some other methods here for how packets are filtered

            }
        });
    }

    public JButton getApplyFilterButton() {
        return applyFilterButton;

    }
    public JButton getClearFilterButton() {
        return clearFilterButton;
    }
}
