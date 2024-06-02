package GUI;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Middleware.Material;
import Middleware.Semester;
import Middleware.Sync;

public class SyncWindow {
    private JFrame frame;
    
    public SyncWindow(){
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
        new JLabel("Local files are not synced.");
        JButton shareButton = new JButton("Share notes");
        styleButton(shareButton,new Color(63, 81, 181),new Color(48, 63, 159) );
        panel.add(shareButton);

        shareButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Sync sync = new Sync();
                String uploadLink = sync.uploadFile("data.csv");
                System.out.println(uploadLink)
                ;
                if(!uploadLink.equals("")){
                    StringSelection selection = new StringSelection(uploadLink); 
                    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                    clipboard.setContents(selection, selection);
                    System.out.println(uploadLink);
                    JOptionPane.showMessageDialog(frame, "Link copied to clipboard. Share the link with someone.");
                }
                else{
                    JOptionPane.showMessageDialog(frame, "File upload failed!");
                }
            }
        });

        JButton retriveButton = new JButton("Sync notes");
        styleButton(retriveButton,new Color(181, 0, 0),new Color(159, 0, 0) );
        panel.add(retriveButton);

        retriveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showPopupDialog();
            }
        });

        JButton backButton = new JButton("Back");
        styleButton(backButton,new Color(0, 181, 0),new Color(0, 159, 0) );
        panel.add(backButton);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hideWindow();
                LandingWindow landingwindow = new LandingWindow();
                landingwindow.showWindow();
            }
        });
        frame.add(panel);
    }
    
     private void showPopupDialog() {
        // Create the dialog
        JDialog dialog = new JDialog(frame, "Enter sync link", true);
        dialog.setSize(300, 150);
        dialog.setLayout(new FlowLayout());
        dialog.setLocationRelativeTo(frame);

        // Dialog components
        JTextField popupTextField = new JTextField(20);
        JButton submitButton = new JButton("Sync");

        dialog.add(popupTextField);
        dialog.add(submitButton);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //sync logic
                Sync sync = new Sync();
                boolean retrivalFailed = true;
                String retrivalData = sync.retriveFile(popupTextField.getText());
                if(retrivalData.equals("")){
                    JOptionPane.showMessageDialog(frame, "File sync failed!");
                    retrivalFailed = false;
                    // dialog.setVisible(false);
                }
                String []retrivalDataSplit = retrivalData.split("\n");
                //compare and add files to local file
                for(int i=0;i<retrivalDataSplit.length;i++){
                    System.out.println(retrivalDataSplit[i]);
                    boolean isFound = false; 
                    try{
                        BufferedReader reader = new BufferedReader(new FileReader("data.csv"));
                        String line;
                        while ((line = reader.readLine()) != null) {
                            if(retrivalDataSplit[i].equals(line)){
                                isFound = true;
                                break;
                            }
                        }
                        System.out.println(isFound);
                        if(!isFound){
                            //add line to file
                            BufferedWriter writer = new BufferedWriter(new FileWriter("data.csv",true));
                            writer.append(retrivalDataSplit[i]+"\n");
                            writer.close();
                            // dialog.setVisible(false);
                        }
                        reader.close();
                    } catch(IOException ee){
                        System.out.println("Error reading file");
                    }
                }
                if(!retrivalFailed){
                    JOptionPane.showMessageDialog(frame, "Data synced succefully.");
                    // dialog.setVisible(false);
                }
                //hide popup dialog
                dialog.setVisible(false);
            }
        });

        dialog.setVisible(true);
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
