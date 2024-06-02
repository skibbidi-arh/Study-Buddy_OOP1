import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;

public class FileUploadRetrieval extends JFrame {
    private JTextField filePathField;
    private JTextArea resultArea;
    private JButton uploadButton, retrieveButton;

    public FileUploadRetrieval() {
        setTitle("File Upload and Retrieval System");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        filePathField = new JTextField(20);
        filePathField.setEditable(false);
        JButton browseButton = new JButton("Browse...");
        browseButton.addActionListener(new BrowseButtonListener());

        uploadButton = new JButton("Upload");
        uploadButton.addActionListener(new UploadButtonListener());

        resultArea = new JTextArea(10, 30);
        resultArea.setLineWrap(true);
        resultArea.setWrapStyleWord(true);
        resultArea.setEditable(false);

        retrieveButton = new JButton("Retrieve");
        retrieveButton.addActionListener(new RetrieveButtonListener());

        add(filePathField);
        add(browseButton);
        add(uploadButton);
        add(new JScrollPane(resultArea));
        add(retrieveButton);
    }

    private class BrowseButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            int returnValue = fileChooser.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                filePathField.setText(selectedFile.getAbsolutePath());
            }
        }
    }

    private class UploadButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            File file = new File(filePathField.getText());
            if (file.exists() && file.isFile()) {
                try {
                    String fileUrl = uploadFile(file);
                    resultArea.setText("File uploaded successfully: " + fileUrl);
                } catch (IOException ex) {
                    resultArea.setText("File upload failed: " + ex.getMessage());
                }
            } else {
                resultArea.setText("Please select a valid file.");
            }
        }
    }

    private class RetrieveButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String url = JOptionPane.showInputDialog("Enter the file URL:");
            if (url != null && !url.trim().isEmpty()) {
                try {
                    String content = retrieveFile(url.trim());
                    resultArea.setText("File content:\n" + content);
                } catch (IOException ex) {
                    resultArea.setText("File retrieval failed: " + ex.getMessage());
                }
            }
        }
    }

    private String uploadFile(File file) throws IOException {
        String boundary = Long.toHexString(System.currentTimeMillis()); // Just a random string
        String CRLF = "\r\n"; // Line separator required by multipart/form-data.

        URL url = new URL("https://file.io/");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);

        try (
            OutputStream output = connection.getOutputStream();
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(output, "UTF-8"), true)
        ) {
            // Send file.
            writer.append("--" + boundary).append(CRLF);
            writer.append("Content-Disposition: form-data; name=\"file\"; filename=\"" + file.getName() + "\"").append(CRLF);
            writer.append("Content-Type: " + Files.probeContentType(file.toPath())).append(CRLF);
            writer.append(CRLF).flush();
            Files.copy(file.toPath(), output);
            output.flush(); // Important before continuing with writer!
            writer.append(CRLF).flush();

            // End of multipart/form-data.
            writer.append("--" + boundary + "--").append(CRLF).flush();
        }

        // Response
        InputStream response = connection.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(response));
        StringBuilder responseBuilder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            responseBuilder.append(line);
        }

        String responseStr = responseBuilder.toString();
        int urlStart = responseStr.indexOf("https://file.io/");
        int urlEnd = responseStr.indexOf("\"", urlStart);
        return responseStr.substring(urlStart, urlEnd);
    }

    private String retrieveFile(String fileUrl) throws IOException {
        URL url = new URL(fileUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        InputStream response = connection.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(response));
        StringBuilder responseBuilder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            responseBuilder.append(line);
            responseBuilder.append("\n");
        }

        return responseBuilder.toString();
    }

    // public static void main(String[] args) {
    //     SwingUtilities.invokeLater(() -> {
    //         FileUploadRetrieval frame = new FileUploadRetrieval();
    //         frame.setVisible(true);
    //     });
    // }
}
