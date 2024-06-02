import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class FileUploadApp extends JFrame {
    private JButton selectButton;
    private JButton uploadButton;
    private JLabel statusLabel;
    private File selectedFile;

    public FileUploadApp() {
        createUI();
    }

    private void createUI() {
        setTitle("File.io Uploader");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());

        selectButton = new JButton("Select File");
        uploadButton = new JButton("Upload File");
        statusLabel = new JLabel("No file selected.");

        selectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    selectedFile = fileChooser.getSelectedFile();
                    statusLabel.setText("Selected: " + selectedFile.getName());
                }
            }
        });

        uploadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedFile != null) {
                    try {
                        uploadFile(selectedFile);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                        statusLabel.setText("Upload failed.");
                    }
                } else {
                    statusLabel.setText("No file selected.");
                }
            }
        });

        add(selectButton);
        add(uploadButton);
        add(statusLabel);
    }

    private void uploadFile(File file) throws IOException {
        String uploadUrl = "https://file.io";
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost uploadFile = new HttpPost(uploadUrl);
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.addBinaryBody("file", file);
            HttpEntity multipart = builder.build();

            uploadFile.setEntity(multipart);
            HttpResponse response = httpClient.execute(uploadFile);
            HttpEntity responseEntity = response.getEntity();

            if (responseEntity != null) {
                String responseString = EntityUtils.toString(responseEntity);
                statusLabel.setText("Upload successful: " + responseString);
            } else {
                statusLabel.setText("Upload failed.");
            }
        }
    }

    // public static void main(String[] args) {
    //     SwingUtilities.invokeLater(new Runnable() {
    //         @Override
    //         public void run() {
    //             FileUploadApp app = new FileUploadApp();
    //             app.setVisible(true);
    //         }
    //     });
    // }
}
