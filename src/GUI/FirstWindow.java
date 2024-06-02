package GUI;
import javax.swing.*;

import Middleware.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FirstWindow extends JFrame{

public FirstWindow()
{
    this.setSize(400, 400);
    this.setLayout(new GridBagLayout());
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    JPanel panel = new JPanel();
    this.add(panel);
    JButton button1 = new JButton("View Semester List");
    JButton button2 = new JButton("Start Study Timer");


    button1.addActionListener(new ActionListener() {

        public void actionPerformed(ActionEvent e) {

            SemesterWindow semesterwindow = new SemesterWindow();
            semesterwindow.showWindow();
        }

    });

    button2.addActionListener(new ActionListener() {

        public void actionPerformed(ActionEvent e) {

            Stopwatch st = new Stopwatch();
        }

    });


    styleButton(button1);
    styleButton(button2);
    panel.add(button1);
    panel.add(button2);
    this.setVisible(true);
}



    static void styleButton(JButton button) {
        // Set button font
        button.setFont(new Font("Arial", Font.PLAIN, 18));

        // Set button background and foreground colors
        button.setBackground(new Color(63, 81, 181)); // Indigo color
        button.setForeground(Color.WHITE);

        // Remove button border and set margin
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Top, left, bottom, right padding

        // Make button flat
        button.setFocusPainted(false);

        // Add hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(48, 63, 159)); // Darker indigo on hover
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(63, 81, 181)); // Original indigo
            }
        });
    }

}
