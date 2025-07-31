package org.sniffer.GUI.packetQueryPanels;

import org.pcap4j.packet.Packet;
import org.sniffer.GUI.SnifferDashboard;
import org.sniffer.backend.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;


/**
 * This class is used to contain the filter buttons and their functionalities
 *
 */
public class FilterButtonPanel extends JPanel {

    private SnifferDashboard dashboard;

    private final JButton applyFilterButton = new JButton("Apply filter");
    private final JButton clearFilterButton = new JButton("Clear filter");

    /**
     * This is the constructor to set up the listeners for the buttons as well as construct the panel
     *
     * @param dashboard is a reference to the SnifferDashboard which data is filtered from
     */
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
                for(IdentifiedPacket currentPacket : dashboard.getSharedPacketList()){
                    //Now for this packet check if that packet is valid
                    for(SubQuery subQuery : dashboard.getCurrentQuery().getSubQueries()){
                        if(subQuery.getQueryOption().equals(QueryOptions.translateToQueryOption(currentPacket.getProtocol()))){
                            dashboard.getDashboardPacketPanelsWrapper().
                                    getPacketsDisplayPanel().
                                    addRowToDisplayPanel(currentPacket);
                        }
                    }
                }
            }
        });

        clearFilterButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dashboard.getDashboardPacketQueryPanel().resetQueryField();
                if (dashboard.getDashboardMenuBar().getViewMenu().getDetailFrameAuthenticator().isAuthenticated()){
                    dashboard.getDashboardMenuBar().
                            getViewMenu().
                            getDetailFrameAuthenticator().
                            getDetailFrame().
                            resetMainDetailedPacketTable();
                }

                dashboard.setQuery(null);

                dashboard.getDashboardPacketPanelsWrapper().getPacketsDisplayPanel().resetTable();
                for(IdentifiedPacket currentPacket : dashboard.getSharedPacketList()){
                    //Writing to the general packet frame
                    dashboard.getDashboardPacketPanelsWrapper()
                            .getPacketsDisplayPanel()
                            .addRowToDisplayPanel(currentPacket);


                    //Writing onto the Detail frame
                    if (dashboard.getDashboardMenuBar().getViewMenu().getDetailFrameAuthenticator().isAuthenticated()) {
                        dashboard.getDashboardMenuBar().
                                getViewMenu().
                                getDetailFrameAuthenticator().
                                getDetailFrame().
                                addRowToTable(currentPacket);
                    }
                }
            }
        });
    }

    //getters
    public JButton getApplyFilterButton() {
        return applyFilterButton;

    }
    public JButton getClearFilterButton() {
        return clearFilterButton;
    }
}
