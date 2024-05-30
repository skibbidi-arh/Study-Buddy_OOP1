package GUI;
import Middleware.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SemesterWindow {
    private JFrame frame;

    public SemesterWindow() {
        frame = new JFrame("Semester Buttons");
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

        //creating 8 buttons
        for (int i = 1; i <= 8; i++) {
            JButton button = new JButton("Semester " + i);
            styleButton(button);
            panel.add(button);

            int semesterNumber = i;
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //fetch semster - course data
                    Semester semester = new Semester(Integer.toString(semesterNumber));
                    System.out.println("Selected Semester: "+semester.semester);
                    //create course window
                    ArrayList<String> x = new ArrayList<>();
                    CourseWindow coursewindow = new CourseWindow(0,x);
                    coursewindow.showWindow();
                    coursewindow.semester = semester;
                }
            });
        }

        //adding panel to frame
        frame.add(panel, new GridBagConstraints());
    }

    private void styleButton(JButton button) {
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

    public void showWindow() {
        frame.setVisible(true);
    }

    public void hideWindow() {
        frame.setVisible(false);
    }
}
