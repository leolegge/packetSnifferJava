package org.sniffer.GUI.packetQueryPanels;

import org.sniffer.GUI.SnifferDashboard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
                String filtertext = dashboard.getDashboardPacketQueryPanel().getQueryField().getText();
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
