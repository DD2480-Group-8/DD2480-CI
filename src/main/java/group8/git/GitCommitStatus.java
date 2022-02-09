package group8.git;

import com.google.gson.JsonObject;
import group8.Config;
import group8.Status;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;

public class GitCommitStatus {
    private final String url;
    private final String owner;
    private final String repo;
    private final String sha;
    private final String baseUrl;

    /**
     * GitCommitStatus constructor. Needs three Strings as parameters to create the url that the HTTPPost will use
     *
     * @param owner String of who owns the repository
     * @param repo String of the name of the repository
     * @param sha String of SHA of the commit that is checked
     */
    public GitCommitStatus(String owner, String repo, String sha) {
        this.owner = owner;
        this.repo = repo;
        this.sha = sha;
        this.baseUrl = "https://api.github.com/repos/";
        url = baseUrl + owner + "/" + repo + "/" + "statuses/" + sha;
    }

    /**
     * Given a Status, this function will create a JsonObject with a correct state and short
     * description of the situation. An HTTP Client is created and will send an HTTP Post request
     * to GitHub, changing the status of a commit.
     *
     * @param status Enum Status, will depend on the status of the build and tests
     * @throws IOException If something goes wrong with the Http client this exception is thrown
     */
    public static void sendGitStatus(Status status) throws IOException {
        JsonObject jsonObject = new JsonObject();

        // Possible outcomes: success, error, failure, pending
        if (status.equals(Status.SUCCESS)) {
            jsonObject.addProperty("state", "success");
            jsonObject.addProperty("description", "Build and tests succeeded!");
        } else if (status.equals(Status.ERROR)) {
            jsonObject.addProperty("state", "error");
            jsonObject.addProperty("description", "Build and tests reported an error!");
        } else if (status.equals(Status.FAILURE)) {
            jsonObject.addProperty("state", "failure");
            jsonObject.addProperty("description", "Build and tests failed!");
        } else if (status.equals(Status.PENDING)) {
            jsonObject.addProperty("state", "pending");
            jsonObject.addProperty("description", "Build and tests are ongoing!");
        }

        try(final CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost(url);

            httpPost.addHeader("Content-Type", "application/vnd.github.v3+json");
            httpPost.addHeader("Authorization", "token " + Config.GitToken);

            StringEntity se = new StringEntity(jsonObject.toString());

            httpPost.setEntity(se);
            CloseableHttpResponse response = httpClient.execute(httpPost);

            // TODO Remove
            System.out.println(response.getStatusLine());
        }

    }

}
