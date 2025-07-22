package org.sniffer.GUI.snifferDashboardPanels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FilterButtonPanel extends JPanel {

    private final JButton applyFilterButton = new JButton("Apply filter");
    private final JButton clearFilterButton = new JButton("Clear filter");

    public FilterButtonPanel() {
        this.setLayout(new BorderLayout());

        this.add(applyFilterButton, BorderLayout.WEST);
        this.add(clearFilterButton, BorderLayout.EAST);

        applyFilterButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });

        clearFilterButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

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
