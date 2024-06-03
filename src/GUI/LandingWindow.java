package GUI;
import javax.swing.*;

import Middleware.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class LandingWindow{
        private JFrame frame;

    public LandingWindow(){
        frame = new JFrame("Landing Window");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(new GridBagLayout());
        //center the frame
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        // 4 rows , 2 columns
        panel.setLayout(new GridLayout(4, 2, 10, 10));
        //padding
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton materialButton = new JButton("Study Materials");
        styleButton(materialButton,new Color(63, 81, 181),new Color(48, 63, 159));
        JButton timerButton = new JButton("Study Timer");
        styleButton(timerButton,new Color(181, 0, 0),new Color(159, 0, 0));
        JButton syncButton = new JButton("Share/Sync Notes");
        styleButton(syncButton,new Color(0, 181, 0),new Color(0, 159, 0));
        panel.add(materialButton);
        panel.add(timerButton);
        panel.add(syncButton);

        materialButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                SemesterWindow semesterwindow = new SemesterWindow();
                semesterwindow.showWindow();
                frame.setVisible(false);
            }

        });

        timerButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                Stopwatch st = new Stopwatch();
            }

        });

        syncButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SyncWindow syncwindow = new SyncWindow();
                syncwindow.showWindow();
                hideWindow();
            }
        });

        frame.add(panel);
    }

    static void styleButton(JButton button, Color defaultColor, Color hoverColor) {
        // Set button font
        button.setFont(new Font("Arial", Font.PLAIN, 18));

        // Set button background and foreground colors
        button.setBackground(defaultColor);
        button.setForeground(Color.WHITE);

        // Remove button border and set margin
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Top, left, bottom, right padding

        // Make button flat
        button.setFocusPainted(false);

        // Add hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(hoverColor);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(defaultColor);
            }
        });
    }
    public void showWindow(){
        frame.setVisible(true);
    }
    public void hideWindow(){
        frame.setVisible(false);
    }
}
