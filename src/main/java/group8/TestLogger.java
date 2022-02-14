package group8;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.FileWriter;

/**
 * Used for logging the console and persisting the logs through saving to a specified directory
 */
public class TestLogger {
    StringBuilder stringBuilder = new StringBuilder();

    /**
     * Reads logs from terminal
     * @param process that reads the terminal text
     * @throws IOException on reading errors
     */
    public void logProcess(Process process) throws IOException {
        final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            this.stringBuilder.append(line);
            this.stringBuilder.append("\n");
        }
    }

    /**
     * Writes logs to specified directory
     * @param logDestination directory to save the logs
     * @param branchName the name of the branch which was pulled
     * @param sha commit identifier
     * @param date the time of compilation which produced the output.
     * @throws IOException on writing errors
     */
    public void writeLogToDestination(File logDestination, String branchName, String sha, String date) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(String.format("%s/%s-%s-%s.txt", logDestination, branchName, sha, date)));
        writer.write(stringBuilder.toString());
        writer.close();
    }
}
