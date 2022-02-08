package group8.server;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import group8.Utilities;
import org.apache.maven.shared.invoker.*;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;

/** 
 Skeleton of a server.ContinuousIntegrationServer which acts as webhook
 See the Jetty documentation for API documentation of those classes.
*/
public class ContinuousIntegrationServer extends AbstractHandler {

    private Invoker invoker;

    public void invokerInit(File repoLocation) {
        Invoker newInvoker = new DefaultInvoker();
        newInvoker.setLocalRepositoryDirectory(repoLocation);

        this.invoker = newInvoker;
    }

    public void handle(String target,
                       Request baseRequest,
                       HttpServletRequest request,
                       HttpServletResponse response) 
        throws IOException, ServletException
    {
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        baseRequest.setHandled(true);

        String requestBody = Utilities.getRequestBodyAsString(request);
        JsonObject requestJson = Utilities.deserializeRequest(requestBody);

        System.out.println(requestJson);
        System.out.println(requestJson.get("ref"));
        System.out.println(target);

        // here you do all the continuous integration tasks
        // for example
        // 1st clone your repository
        // 2nd compile the code
        System.out.println("Cloned repo");
        File dirLoc = new File("/test/");
        try {
            Git git = Git.cloneRepository()
                            .setURI("https://github.com/DD2480-Group-8/DD2480-DECIDE.git")
                            .setDirectory(dirLoc)
                            .call();
        } catch (GitAPIException e) {
            e.printStackTrace();
        }

        List<String> goals = Arrays.asList("clean", "test");
        InvocationRequest req = new DefaultInvocationRequest();
        req.setBaseDirectory(dirLoc);
        req.setGoals(goals);
        invokerInit(dirLoc);

        InvocationResult res = null;
        try {
            res = invoker.execute(req);
        } catch (MavenInvocationException e) {
            System.out.println("Maven test failed, error code: " + res.getExitCode());
        }

        response.getWriter().println(res.getExitCode());
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
