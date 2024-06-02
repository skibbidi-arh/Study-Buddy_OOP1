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
        panel.setLayout(new GridLayout(5, 2, 10, 10));
        //padding
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        //creating 8 buttons
        for (int i = 1; i <= 8; i++) {
            JButton button = new JButton("Semester " + i);
            styleButton(button,new Color(63, 81, 181),new Color(48, 63, 159) );
            panel.add(button);

            int semesterNumber = i;
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //fetch semster - course data
                    Semester semester = new Semester(Integer.toString(semesterNumber));
                    System.out.println("Selected Semester: "+semester.semester);
                    //get all course of the semester
                    ArrayList<String> courseNameList = semester.getAllCourseName();
                    ArrayList<String> courseCodeList = semester.getAllCourseCode();
                    System.out.println(courseNameList);
                    //create course window
                    CourseWindow coursewindow = new CourseWindow(courseNameList.size(),courseNameList,courseCodeList);
                    coursewindow.showWindow();
                    coursewindow.semester = semester;
                    frame.setVisible(false);
                }
            });
        }
        //back button
        JButton backButton = new JButton("Back");
        styleButton(backButton,new Color(0, 181, 0),new Color(0, 159, 0));
        panel.add(backButton);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                LandingWindow landingwindow = new LandingWindow();
                frame.setVisible(false);
                landingwindow.showWindow();
            }
        });
        //adding panel to frame
        frame.add(panel, new GridBagConstraints());
    }

    private void styleButton(JButton button, Color defaultColor, Color hoverColor) {
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

    public void showWindow() {
        frame.setVisible(true);
    }

    public void hideWindow() {
        frame.setVisible(false);
    }
}
