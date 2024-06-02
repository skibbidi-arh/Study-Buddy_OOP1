package Middleware;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;


public class Sync {

    public String uploadFile(String filepath){
        File file = new File(filepath);
        if (file.exists() && file.isFile()) {
            try {
                String fileUrl = uploadFile(file);
                return fileUrl;
            } catch (IOException ex) {
                return "";
            }
        } else {
            return "";
        }
    }

    //Fix this
    public String retriveFile(String url){
        if (url != null && !url.trim().isEmpty()) {
            try {
                String content = retrieveFile(url.trim());
                return content;
            } catch (IOException ex) {
                return "";
            }
        }
        return "";
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
}
