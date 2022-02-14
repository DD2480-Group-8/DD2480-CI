package group8;

import java.io.File;
import java.io.IOException;

/**
 * Runs 'mvn verify' in the specified directory
 */
public class MavenTester {
    static Runtime runtime = Runtime.getRuntime();

    public static Process mvnVerifyInDirectory(File destinationDirectory) throws IOException {
        return runtime.exec("mvn verify", null, destinationDirectory);
    }
}
