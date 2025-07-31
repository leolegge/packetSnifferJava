package org.sniffer.GUI;



import org.sniffer.GUI.packetQueryPanels.FilterButtonPanel;
import org.sniffer.GUI.packetQueryPanels.StartStopSnifferPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This is the panel to hold the query line in which users can enter a query as well as start and stop the program
 *
 */
public class PacketQueryPanel extends JPanel {

    private SnifferDashboard dashboard;

    private final JTextField queryField = new JTextField();
    private final FilterButtonPanel filterButtonPanel;
    private final StartStopSnifferPanel startStopSnifferPanel;

    /**
     * This is the constructor for the Query panel at the top of the program
     *
     * @param dashboard is a reference to the SnifferDashboard to allow access to information as well as control
     *                  over the packet sniffer
     */
    public PacketQueryPanel(SnifferDashboard dashboard) {
        this.dashboard = dashboard;
        //setting up filter buttons
        filterButtonPanel = new FilterButtonPanel(dashboard);
        //setting up start stop buttons
        startStopSnifferPanel = new StartStopSnifferPanel(dashboard);

        this.setPreferredSize(new Dimension(0, 20));
        this.setLayout(new BorderLayout());
        this.add(queryField, BorderLayout.CENTER);


        queryField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });

        //setting up filters buttons
        this.add(filterButtonPanel, BorderLayout.EAST);


        //setting up start and stop buttons
        this.add(startStopSnifferPanel, BorderLayout.WEST);
    }

    //getters
    public JTextField getQueryField() {
        return queryField;
    }

    public FilterButtonPanel getFilterButtonPanel() {
        return filterButtonPanel;
    }

    public StartStopSnifferPanel getStartStopSnifferPanel() {
        return startStopSnifferPanel;
    }
    public SnifferDashboard getSnifferDashboard() {
        return dashboard;
    }

    //general methods
    public void resetQueryField(){
        queryField.setText("");
    }





}
