package GUI;

import javax.swing.*;

import Middleware.Semester;
import Middleware.Course;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class AddCourseWindow {

    private JFrame frame;
    private JTextField courseCodeField;
    private JTextField courseNameField;
    private JButton addButton;

    Semester semester;

    String filepath = "data.csv";
    public AddCourseWindow() {
        // Set up the frame
        frame = new JFrame("Course Input Window");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(new GridBagLayout());
        frame.setLocationRelativeTo(null); // Center the frame

        
        
        // Create the panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10); // Add padding between components

        // Course Code Label
        JLabel courseCodeLabel = new JLabel("Course Code:");
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.EAST;
        panel.add(courseCodeLabel, constraints);
        // styleLabel(courseCodeLabel);

        // Course Code Text Field
        courseCodeField = new JTextField(20);
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.WEST;
        panel.add(courseCodeField, constraints);

        // Course Name Label
        JLabel courseNameLabel = new JLabel("Course Name:");
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.anchor = GridBagConstraints.EAST;
        panel.add(courseNameLabel, constraints);
        // styleLabel(courseNameLabel);


        // Course Name Text Field
        courseNameField = new JTextField(20);
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.anchor = GridBagConstraints.WEST;
        panel.add(courseNameField, constraints);

        // Add Button
        addButton = new JButton("Add");
        styleButton(addButton);
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.anchor = GridBagConstraints.WEST;
        panel.add(addButton, constraints);

        // Add Action Listener to Add Button
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String courseCode = courseCodeField.getText();
                String courseName = courseNameField.getText();
                System.out.println("Selected Semester: "+semester.semester);
                if (!courseCode.isEmpty() && !courseName.isEmpty()) {
                    //checking if the course exists
                    Course course = new Course(semester,courseName, courseName);
                    if(!course.courseAlreadyExists()){
                        try{
                            // System.out.println(filepath);
                            BufferedWriter writer = new BufferedWriter(new FileWriter(filepath,true));
                            writer.append(semester.semester+","+courseName+","+courseCode+"\n");
                            writer.close();
                            JOptionPane.showMessageDialog(frame, "Course Added successfully.");
                            
                            //create course window
                            //get all course of the semester
                            ArrayList<String> courseNameList = semester.getAllCourseName();
                            ArrayList<String> courseCodeList = semester.getAllCourseCode();
                            System.out.println(courseNameList);
                            System.out.println(courseCodeList);
                            //create course window
                            CourseWindow coursewindow = new CourseWindow(courseNameList.size(),courseNameList, courseCodeList);
                            coursewindow.showWindow();
                            coursewindow.semester = semester;
                            frame.setVisible(false);
                        }catch(IOException ee){
                            System.out.println("Error Adding course");
                            JOptionPane.showMessageDialog(frame, "Error Adding Course!");
                        }
                        
                    } else {
                        JOptionPane.showMessageDialog(frame, "Course Already exists (Same Name/Course Code)");
                    }
                }
                else{
                    JOptionPane.showMessageDialog(frame, "Enter both course code and course name.");
                }
            }
        });

        //Add back button
        JButton cancelButton = new JButton("Cancel");
        styleButton(cancelButton);
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.anchor = GridBagConstraints.EAST;
        panel.add(cancelButton, constraints);
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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

        // Add panel to frame
        frame.add(panel, new GridBagConstraints());
    }

    private void styleButton(JButton button) {
        // Set button font
        button.setFont(new Font("Arial", Font.PLAIN, 16));

        // Set button background and foreground colors
        button.setBackground(new Color(63, 81, 181));
        button.setForeground(Color.WHITE);

        // Remove button border and set margin
        button.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15)); // Top, left, bottom, right padding
        button.setFont(new Font("Arial", Font.PLAIN, 16));

        // Set button background and foreground colors
        button.setBackground(new Color(63, 81, 181)); 
        button.setForeground(Color.WHITE);

        // Remove button border and set margin
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
    public void styleLabel(JLabel label){
        label.setFont(new Font("Arial", Font.PLAIN, 16));

        // Set label background and foreground colors
        label.setBackground(new Color(63, 81, 181)); // Indigo color
        label.setForeground(Color.WHITE);

        // Remove label border and set margin
        label.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15)); // Top, left, bottom, right padding
    }

    public void showWindow() {
        frame.setVisible(true);
    }

    public void hideWindow() {
        frame.setVisible(false);
    }
}
