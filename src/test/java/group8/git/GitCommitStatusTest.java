package group8.git;

import com.google.gson.JsonObject;
import group8.Status;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.io.IOException;


public class GitCommitStatusTest {

    /**
     * Test that the sendGitStatus is working by setting the first commit of this repository to success
     * and asserting that the HTTP status code is 201.
     *
     * This test only works locally as no Git Personal Access token is present when testing on Github.
     * Because of this, this test will be labeled ignored. To test this, add your own Personal Access Token
     * locally and remove the @Ignore annotation.
     */
    @Test
    @Ignore
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

    /**
     * Tests that the createHTTPBody method works as intended. Will test all four cases.
     */
    @Test
    public void createHTTPBodyTest() {
        // First commit of repo DD2480-Group-8/DD2480-CI (not used in this)
        GitCommitStatus GCS = new GitCommitStatus("DD2480-Group-8", "DD2480-CI", "3f2b0b0f9ebd8455c0c3485023055e3ab5fc5517");

        // Tests all four possible outcomes
        JsonObject body = GCS.createHTTPBody(Status.SUCCESS);
        Assert.assertEquals(body.get("state").getAsString(), "success");
        body = GCS.createHTTPBody(Status.PENDING);
        Assert.assertEquals(body.get("state").getAsString(), "pending");
        body = GCS.createHTTPBody(Status.ERROR);
        Assert.assertEquals(body.get("state").getAsString(), "error");
        body = GCS.createHTTPBody(Status.FAILURE);
        Assert.assertEquals(body.get("state").getAsString(), "failure");
    }

}