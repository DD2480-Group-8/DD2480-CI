package group8.git;

import group8.Status;
import org.junit.Assert;
import org.junit.Test;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.io.IOException;


public class GitCommitStatusTest {

    /**
     * Test that the sendGitStatus is working by setting the first commit of this repository to success
     * and asserting that the HTTP status code is 201.
     */
    @Test
    public void SendGitStatusTest() {
        // First commit of repo DD2480-Group-8/DD2480-CI
        GitCommitStatus GCS = new GitCommitStatus("DD2480-Group-8", "DD2480-CI", "3f2b0b0f9ebd8455c0c3485023055e3ab5fc5517");

        try {
            CloseableHttpResponse response = GCS.sendGitStatus(Status.SUCCESS);

            Assert.assertEquals(response.getStatusLine().getStatusCode(), 201);
        } catch (IOException e) {
            e.printStackTrace();
            // If we get here, we should fail
            Assert.assertTrue(false);
        }
    }

}