package org.sniffer.GUI.packetPanels;

import org.sniffer.GUI.SnifferDashboard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class allows for data to be hidden and then expanded upon a button press
 *
 */
public class ExpandablePanel extends JPanel {

    SnifferDashboard dashboard;

    JButton expandButton = new JButton("Expand");
    JLabel label;
    JPanel expandableTitle = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JTextArea contentArea = new JTextArea();
    boolean expanded = false;


    /**
     * This is the constructor to build an expandable panel
     *
     * @param label is the name describing the content that is shown when the panel is expanded
     * @param content is the content shown upon expansion
     * @param dashboard is a reference to the SnifferDashboard in order to load packet data into the panel
     */
    public ExpandablePanel(String label, String content, SnifferDashboard dashboard) {
        this.dashboard = dashboard;
        this.setLayout(new BorderLayout());
        this.label = new JLabel(label);

        contentArea.setEditable(false);
        contentArea.setLineWrap(true);
        contentArea.setWrapStyleWord(true);
        contentArea.setText(content);
        contentArea.setVisible(false);



        expandableTitle.add(expandButton);
        expandableTitle.add(this.label);

        this.add(expandableTitle, BorderLayout.NORTH);
        this.add(contentArea, BorderLayout.CENTER);

        expandButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //switch status
                expanded = !expanded;
                expandButton.setText(expanded ? "Collapse" : "Expand");
                contentArea.setVisible(expanded);

                ExpandablePanel.this.setMaximumSize(new Dimension(Integer.MAX_VALUE, ExpandablePanel.this.getPreferredSize().height));


                ExpandablePanel.this.revalidate();
                ExpandablePanel.this.repaint();

            }
        });
    }
}
