package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import Middleware.Course;
import Middleware.Material;
import Middleware.Semester;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class MaterialWindow {

    private JFrame frame;
    private JTable table;
    private DefaultTableModel tableModel;

    public MaterialWindow(String courseName, ArrayList<Material> materialList, Semester semester) {
        // Set up the frame
        frame = new JFrame(courseName + " Materials");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null); // Center the frame

        // Create the course name label
        JLabel courseNameLabel = new JLabel(courseName, SwingConstants.CENTER);
        courseNameLabel.setFont(new Font("Arial", Font.BOLD, 24));
        courseNameLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        frame.add(courseNameLabel, BorderLayout.NORTH);

        //add Add material button
        JButton addMaterialButton = new JButton("Add course material");
        styleButton(addMaterialButton,new Color(181, 0, 0), new Color(159, 0, 0));
        frame.add(addMaterialButton, BorderLayout.NORTH);
        //handle the add button trigger
        addMaterialButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                
                
            }
        });

        //add back button
        JButton backButton = new JButton("Back");
        styleButton(backButton,new Color(0, 181, 0), new Color(0, 159, 0));
        frame.add(backButton, BorderLayout.SOUTH);
        //handle the add button trigger
        System.out.println(semester.semester);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
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

        // Create the table model and set columns
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Material Title");
        tableModel.addColumn("Course Code");
        tableModel.addColumn("Material Link");

        // Populate the table model with data from the materialList

        for (Material material : materialList) {
            tableModel.addRow(new Object[]{material.getMaterialTitle(), material.getCourseCode(), material.getMaterialLink()});
        }

        // Create the table and set the model
        table = new JTable(tableModel);
        styleTable();
        // table.setEnabled(false);

        // Add the table to a scroll pane
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Add mouse listener to table for row click events
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    String materialLink = table.getValueAt(selectedRow, 2).toString();

                    // Copy row data (for demonstration, just print it out)
                    StringSelection selection = new StringSelection(materialLink);
                    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                    clipboard.setContents(selection, selection);
                    System.out.println("Material Link: " + materialLink);
                }
            }
        });
    }

    private void styleTable() {
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.setRowHeight(24);
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));
        table.getTableHeader().setBackground(new Color(63, 81, 181));
        table.getTableHeader().setForeground(Color.WHITE);
        table.setShowGrid(false);
        table.setIntercellSpacing(new Dimension(0, 0));
    
        // Set header renderer for hover effect
        JTableHeader header = table.getTableHeader();
        header.setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                label.setOpaque(true);
                label.setBackground(new Color(63, 81, 181));
                label.setForeground(Color.WHITE);
                label.setFont(new Font("Arial", Font.BOLD, 16));
                return label;
            }
        });
    }

    private void styleButton(JButton button, Color regularColor, Color hoverColor) {
        // Set button font
        button.setFont(new Font("Arial", Font.PLAIN, 18));

        // Set button background and foreground colors
        button.setBackground(regularColor);
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
                button.setBackground(regularColor);
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