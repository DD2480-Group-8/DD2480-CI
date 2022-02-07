package group8;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;

public class Utilities {

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
}
