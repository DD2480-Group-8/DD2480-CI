package group8;

import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import group8.server.ContinuousIntegrationServer;
import org.junit.Assert;
import org.junit.Test;

public class CITests {

    /**
     * Tests if string is correctly deserialized to JsonObject.
     * Should create a JsonObject of type {string: string} when given a serialized object.
     */
    @Test
    public void deserializeRequestValid() {
        JsonObject j = new JsonObject();
        j.addProperty("param", "param");
        Assert.assertEquals(ContinuousIntegrationServer.deserializeRequest(
                "{\n'param':'param'\n}"), j
        );
    }

    /**
     * Tests if invalid json throws JsonSyntaxException
     * Should throw, since no value is given to the key 'param'.
     */
    @Test(expected = JsonSyntaxException.class)
    public void deserializeRequestInvalid() {
        ContinuousIntegrationServer.deserializeRequest("{'param'}");
    }

}
