import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import GUI.SemesterWindow;
import Middleware.Stopwatch;
import Middleware.*;

public class Main {
    // static String filepath = "data.csv";
    public static void main(String[] args) {
        //landing window
        JFrame frame = new JFrame();
        frame.setSize(400, 400);
        frame.setLayout(new GridBagLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        frame.add(panel);
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
        frame.setVisible(true);
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
