package group8;

import org.junit.Assert;
import org.junit.Test;

public class CurrentDateTest {
    
    
    /**
     * Asserts that all spaces are replaced and the right string is returned. Passes if 
     * all spaces are removed and replaced by Hyphens. Multiple consecutive spaces are 
     * replaced by a single hyphen. 
     */
    @Test
    public void replaceSpaceWithHyphenTest(){
        String str = " 123    a 12 asyt  ";
        String res = CurrentDate.replaceSpaceWithHyphen(str);
        Assert.assertEquals(res, "-123-a-12-asyt-");
    }

    /**
     * Asserts that all colons are replaced and the right string is returned. Passes if 
     * all colons are removed and replaced by hyphens. Multiple consecutive colons are 
     * replaced by a single hyphen. 
     */
    @Test
    public void replaceColonWithHyphenTest(){
        String str = ":123::::a:12:asyt::";
        String res = CurrentDate.replaceColonWithHyphen(str);
        Assert.assertEquals(res, "-123-a-12-asyt-");
    }
}
