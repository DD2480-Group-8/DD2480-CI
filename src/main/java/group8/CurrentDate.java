package group8;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class to retrieve the current date to be used in build files.
 */
public class CurrentDate {

    /**
     * static method to enable retrieval of correctly formatted current date without an instance of CurrentDate.
     * @return correctly formatted current date, hyphens instead of spaces and colons.
     */
    public static String getCurrentDate() {
        String date = replaceSpaceWithHyphen(new java.util.Date().toString());
        return replaceColonWithHypn(date);
    }

    /**
     * replaces spaces in a string with hyphens.
     * @param str a string containing spaces
     * @return a string with hyphens instead of spaces: "hello world" -> "hello-world"
     */
    private static String replaceSpaceWithHyphen(String str) {
        if (str != null && str.trim().length() > 0) {
            str = str.toLowerCase();
            String patternStr = "\\s+";
            String replaceStr = "-";
            Pattern pattern = Pattern.compile(patternStr);
            Matcher matcher = pattern.matcher(str);
            str = matcher.replaceAll(replaceStr);
            patternStr = "\\s";
            replaceStr = "-";
            pattern = Pattern.compile(patternStr);
            matcher = pattern.matcher(str);
            str = matcher.replaceAll(replaceStr);
        }
        return str;
    }

    /**
     * replaces colons in a string with hyphens.
     * @param str a string containing colons
     * @return a string with hyphens instead of colons: "hello:world" -> "hello-world"
     */
    private static String replaceColonWithHypn(String str) {
        if (str != null && str.trim().length() > 0) {
            str = str.toLowerCase();
            String patternStr = ":+";
            String replaceStr = "-";
            Pattern pattern = Pattern.compile(patternStr);
            Matcher matcher = pattern.matcher(str);
            str = matcher.replaceAll(replaceStr);
            patternStr = ":";
            replaceStr = "-";
            pattern = Pattern.compile(patternStr);
            matcher = pattern.matcher(str);
            str = matcher.replaceAll(replaceStr);
        }
        return str;
    }
}
