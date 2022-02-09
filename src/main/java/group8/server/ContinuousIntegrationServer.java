package group8.server;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;


import java.io.File;
import java.io.IOException;

import com.google.gson.JsonObject;

import group8.CurrentDate;
import group8.MavenTester;
import group8.TestLogger;
import group8.Utilities;
import group8.git.GitRepoFetcher;
import group8.Status;
import group8.LogIO;
import group8.git.GitCommitStatus;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

/** 
 Skeleton of a server.ContinuousIntegrationServer which acts as webhook
 See the Jetty documentation for API documentation of those classes.
*/
public class ContinuousIntegrationServer extends AbstractHandler {

    public void handle(String target,
                       Request baseRequest,
                       HttpServletRequest request,
                       HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        baseRequest.setHandled(true);

        //Sends the PENDING status to github. 
        GitCommitStatus.sendGitStatus(Status.PENDING);

        String requestBody = Utilities.getRequestBodyAsString(request);
        JsonObject requestJson = Utilities.deserializeRequest(requestBody);

//        System.out.println(requestJson);
        System.out.println(requestJson.get("ref"));
        System.out.println(String.format("request received on %s", target));

        String currentDate = CurrentDate.getCurrentDate();

        // here you do all the continuous integration tasks
        // for example
        // 1st clone your repository
        // 2nd compile the code
        System.out.println("Cloning repo...");
        String destinationPath = String.format("./builds/DD2480-CI-%s", currentDate);
        File destinationDirectory = new File(destinationPath);
        GitRepoFetcher repo = new GitRepoFetcher("https://github.com/DD2480-Group-8/DD2480-CI.git");
        repo.fetchToDestination(destinationDirectory);
        System.out.println("Repo cloned.");


        Process process = MavenTester.mvnVerifyInDirectory(destinationDirectory);
        TestLogger log = new TestLogger();
        log.logProcess(process);
        log.writeLogToDestination(destinationDirectory);

        System.out.println(currentDate);

        //Sends the result of the build to Github.
        Status buildres = LogIO.getBuildResult(destinationPath + "/target/surefire-reports");
        GitCommitStatus.sendGitStatus(buildres);

        response.getWriter().println("CI job done");
    }
 
    // used to start the CI server in command line
    public static void main(String[] args) throws Exception
    {
        Server server = new Server(8080);
        server.setHandler(new ContinuousIntegrationServer()); 
        server.start();
        server.join();
    }
}
