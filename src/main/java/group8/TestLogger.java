package group8;

import java.io.*;

public class TestLogger {
    StringBuilder stringBuilder = new StringBuilder();

    public void logProcess(Process process) throws IOException {
        final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            this.stringBuilder.append(line);
            this.stringBuilder.append("\n");
        }
    }

    public void writeLogToDestination(File destinationDirectory) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(String.format("%s/output.txt", destinationDirectory)));
        writer.write(stringBuilder.toString());
        writer.close();
    }
}
