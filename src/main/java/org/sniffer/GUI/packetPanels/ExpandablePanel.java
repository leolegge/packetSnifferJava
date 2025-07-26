package org.sniffer.GUI.packetPanels;

import org.sniffer.GUI.SnifferDashboard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExpandablePanel extends JPanel {

    SnifferDashboard dashboard;

    JButton expandButton = new JButton("Expand");
    JLabel label;
    JPanel expandableTitle = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JTextArea contentArea = new JTextArea();
    boolean expanded = false;

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

    @Override
    public Dimension getPreferredSize() {
        if (expanded) {
            // When expanded, use normal preferred size (header + content)
            return super.getPreferredSize();
        } else {
            // When collapsed, only show header (expand button + label)
            Dimension headerSize = expandableTitle.getPreferredSize();
            return new Dimension(headerSize.width, headerSize.height);
        }
    }
}
