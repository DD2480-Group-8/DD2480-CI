package group8;

import org.junit.Assert;
import org.junit.Test;

public class LogIOTest {
    
    
    /**
     * Tests that Status.SUCESS is returned when logs with no failures or errors
     * are searched. 
     */
    @Test
    public void getBuildResultPositiveTest(){
        Status res = LogIO.getBuildResult("./src/test/java/group8/testlogsSuccess");
        Assert.assertEquals(Status.SUCCESS, res);
    }

    /**
     * Tests that Status.FAILURE is returned when logs with failures
     * are searched
     */
    @Test
    public void getBuildResultNegativeTest(){
        Status res = LogIO.getBuildResult("./src/test/java/group8/testlogsFail");
        Assert.assertEquals(Status.FAILURE, res);
    }
}
