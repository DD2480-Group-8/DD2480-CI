package group8;

import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import group8.server.ContinuousIntegrationServer;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;

import java.io.IOException;

/**
 * Unit tests for utility functions
 */
public class UtilitiesTest {

    /**
     * Tests if string is correctly deserialized to JsonObject.
     * Should create a JsonObject of type {string: string} when given a serialized object.
     */
    @Test
    public void deserializeRequestValid() {
        JsonObject j = new JsonObject();
        j.addProperty("param", "param");
        Assert.assertEquals(Utilities.deserializeRequest(
                "{\n'param':'param'\n}"), j
        );
    }

    /**
     * Tests if invalid json throws JsonSyntaxException
     * Should throw, since no value is given to the key 'param'.
     */
    @Test(expected = JsonSyntaxException.class)
    public void deserializeRequestInvalid() {
        Utilities.deserializeRequest("{'param'}");
    }

    /**
     * Creates a mock request with a bytestream body, which is handled by the
     * getRequestBodyAsString method used in the CI server.
     * @throws IOException if the BufferedReader in getRequestBodyAsString fails to read.
     * Is passed a serialized json object, should return the same object.
     */
    @Test
    public void requestBodyAsStringValidTest() throws IOException {
        MockHttpServletRequest mockRequest = new MockHttpServletRequest("POST", "/");
        byte[] bytes = "{\n'param':'param'\n}".getBytes();
        mockRequest.setContent(bytes);
        Assert.assertEquals(
                Utilities.getRequestBodyAsString(mockRequest),
                "{\n'param':'param'\n}\n"
        );
    }


    /**
     * Asserts that when something goes wrong, such as there being no content in the request,
     * the returned value is simply nothing.
     * @throws IOException if BufferedReader in getRequestBodyAsString fails to read.
     */
    @Test
    public void requestBodyAsStringInvalidTest() throws IOException {
        MockHttpServletRequest mockRequest = new MockHttpServletRequest("POST", "/");
        Assert.assertEquals(
                Utilities.getRequestBodyAsString(mockRequest),
                ""
        );
    }

    @Test
    public void getRefSuffixValidTest() {
        Assert.assertEquals("issue1", Utilities.getRefSuffix("refs/heads/issue/1"));
    }

    @Test
    public void getRefSuffixInvalidTest() {
        Assert.assertEquals("containsNoSlashes", Utilities.getRefSuffix("containsNoSlashes"));
    }

}
