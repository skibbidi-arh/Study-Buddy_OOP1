package GUI;

import javax.swing.*;

import Middleware.Course;
import Middleware.Semester;
import Middleware.Material;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.channels.AcceptPendingException;
import java.util.ArrayList;

public class CourseWindow {

    private JFrame frame;
    private ArrayList<String> labels;
    Semester semester;

    public CourseWindow(int n, ArrayList<String> labels, ArrayList<String> codes) {
        this.labels = labels;
        frame = new JFrame("Custom Buttons Window");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(new GridBagLayout());
        frame.setLocationRelativeTo(null); // Center the frame

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout((n + 1) / 2, 2, 10, 10)); // Adjust rows based on n
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add padding around the panel

        //add course button
        JButton addCourseButton = new JButton("Add Course");
        styleCourseButton(addCourseButton);
        
        panel.add(addCourseButton);
        addCourseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                AddCourseWindow addcourse = new AddCourseWindow();
                addcourse.showWindow();
                addcourse.semester = semester;
                System.out.println("Selected Semester: "+semester.semester);
                frame.setVisible(false);
                
            }
        });
        
        //course button
        for (int i = 0; i < n; i++) {
            JButton button = new JButton(labels.get(i));
            styleButton(button);
            panel.add(button);
            String label = labels.get(i);
            String code = codes.get(i);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e){
                    Course course = new Course(semester, label, code);
                    ArrayList<Material> material = course.getAllMaterial();
                    MaterialWindow materialwindow = new MaterialWindow(label, material, semester);
                    materialwindow.showWindow();
                    frame.setVisible(false);
                }
            });
        }
        
        //add Back button - custom style
        JButton backButton = new JButton("Back");
        styleBackButton(backButton);
        panel.add(backButton);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                SemesterWindow semesterWindow = new SemesterWindow();
                frame.setVisible(false);
                semesterWindow.showWindow();
            }
        });
        // Add panel to frame
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

    private void styleCourseButton(JButton button) {
        // Set button font
        button.setFont(new Font("Arial", Font.PLAIN, 18));

        // Set button background and foreground colors
        button.setBackground(new Color(181, 0, 0));
        button.setForeground(Color.WHITE);

        // Remove button border and set margin
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Top, left, bottom, right padding

        // Make button flat
        button.setFocusPainted(false);

        // Add hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(159, 0, 0));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(181, 0, 0));
            }
        });
    }

    private void styleBackButton(JButton button) {
        // Set button font
        button.setFont(new Font("Arial", Font.PLAIN, 18));

        // Set button background and foreground colors
        button.setBackground(new Color(0, 181, 0)); 
        button.setForeground(Color.WHITE);

        // Remove button border and set margin
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Top, left, bottom, right padding

        // Make button flat
        button.setFocusPainted(false);

        // Add hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(0, 159, 0));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(0, 181, 0));
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
