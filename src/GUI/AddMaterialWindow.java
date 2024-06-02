package GUI;

import javax.swing.*;

import Middleware.Course;
import Middleware.Material;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class AddMaterialWindow {

    private JFrame frame;
    private JTextField materialTitleField;
    private JTextField materialLinkField;

    String filepath = "data.csv";

    public AddMaterialWindow(Course course) {
        // Set up the frame
        frame = new JFrame("Add Material for " + course.name);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(new GridBagLayout());
        frame.setLocationRelativeTo(null); // Center the frame

        // Set up GridBagConstraints
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Course Name label
        JLabel courseNameLabel = new JLabel("Course Name: " + course.name);
        courseNameLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        frame.add(courseNameLabel, gbc);

        // Course Code label
        JLabel courseCodeLabel = new JLabel("Course Code: " + course.code);
        courseCodeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridy = 1;
        frame.add(courseCodeLabel, gbc);

        // Material Title input
        JLabel materialTitleLabel = new JLabel("Material Title:");
        materialTitleLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        frame.add(materialTitleLabel, gbc);

        materialTitleField = new JTextField();
        gbc.gridx = 1;
        frame.add(materialTitleField, gbc);

        // Material Link input
        JLabel materialLinkLabel = new JLabel("Material Link:");
        materialLinkLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 3;
        frame.add(materialLinkLabel, gbc);

        materialLinkField = new JTextField();
        gbc.gridx = 1;
        frame.add(materialLinkField, gbc);

        // Browse button for file chooser
        JButton browseButton = new JButton("Browse");
        styleButton(browseButton,new Color(63, 81, 181),new Color(48, 63, 159));
        browseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(frame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    materialLinkField.setText(fileChooser.getSelectedFile().getAbsolutePath());
                }
            }
        });
        gbc.gridx = 2;
        frame.add(browseButton, gbc);

        // Add button
        JButton addButton = new JButton("Add");
        styleButton(addButton, new Color(181,0,0), new Color(159,0,0));
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String materialTitle = materialTitleField.getText();
                String materialLink = materialLinkField.getText();
                // Add material logic here
                System.out.println("Material Title: " + materialTitle);
                System.out.println("Material Link: " + materialLink);
                if (!materialTitle.isEmpty() && !materialLink.isEmpty()) {
                    //checking if the course exists
                    // Course course = new Course(semester,courseName, courseName);
                    Material material = new Material(course.semester, course, materialTitle, materialLink);
                    if(!material.materialAlreadyExists()){
                        try{
                            // System.out.println(filepath);
                            BufferedWriter writer = new BufferedWriter(new FileWriter(filepath,true));
                            writer.append(course.semester.semester+","+course.name+","+course.code+","+materialTitle+","+materialLink+"\n");
                            writer.close();
                            JOptionPane.showMessageDialog(frame, "Material Added successfully.");
                            
                            //create material window
                            ArrayList<Material> materials = course.getAllMaterial();
                            MaterialWindow materialwindow = new MaterialWindow(course, materials);
                            materialwindow.showWindow();
                            frame.setVisible(false);
                        }catch(IOException ee){
                            System.out.println("Error Adding course");
                            JOptionPane.showMessageDialog(frame, "Error Adding Material!");
                        }
                        
                    } else {
                        JOptionPane.showMessageDialog(frame, "Material Already exists (Same Title/File link)");
                    }
                }
                else{
                    JOptionPane.showMessageDialog(frame, "Enter both Material title and path.");
                }
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 4;
        frame.add(addButton, gbc);
        // Cancel button
        JButton cancelButton = new JButton("Cancel");
        styleButton(cancelButton, new Color(0,181,0), new Color(0,159,0));
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Course course = new Course(semester, label, code);
                ArrayList<Material> material = course.getAllMaterial();
                MaterialWindow materialwindow = new MaterialWindow(course, material);
                materialwindow.showWindow();
                frame.setVisible(false);
            }
        });
        gbc.gridx = 1;
        frame.add(cancelButton, gbc);
    }

    private void styleButton(JButton button, Color defaultColor, Color hoverColor) {
        // Set button font
        button.setFont(new Font("Arial", Font.PLAIN, 14));

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