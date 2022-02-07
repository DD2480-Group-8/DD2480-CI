package group8.server;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

import java.io.BufferedReader;
import java.io.IOException;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
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
        throws IOException, ServletException
    {
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        baseRequest.setHandled(true);

        String requestBody = getRequestBodyAsString(request);
        JsonObject requestJson = deserializeRequest(requestBody);

        System.out.println(requestJson);
        System.out.println(requestJson.get("ref"));
        System.out.println(target);

        // here you do all the continuous integration tasks
        // for example
        // 1st clone your repository
        // 2nd compile the code

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

    /**
     * Reads the entire body of an incoming request and returns the result as a string.
     * @param request - The incoming request
     * @return the entire request body as a string.
     * @throws IOException - if the reader fails to read the request body, an IOException will be thrown.
     */
    public static String getRequestBodyAsString(HttpServletRequest request) throws IOException {
        try {
            StringBuilder requestBuffer = new StringBuilder();
            BufferedReader requestReader = request.getReader();
            String nextLine;
            while ((nextLine = requestReader.readLine()) != null) {
                requestBuffer.append(nextLine);
                requestBuffer.append(System.lineSeparator());
            }
            return requestBuffer.toString();
        } catch (IOException err) {
            return "";
        }
    }

    /**
     * deserializes the incoming request body into a JSON object.
     * @param requestBody - the request body as a string
     * @return a JsonObject of the incoming request body
     * @throws JsonSyntaxException if string passed is invalid as a json object, a JsonSyntaxException is thrown.
     */
    public static JsonObject deserializeRequest(String requestBody) throws JsonSyntaxException {
        JsonObject j = new JsonParser().parse(requestBody).getAsJsonObject();
        return j;
    }

}
