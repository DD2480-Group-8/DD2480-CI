package group8;

import javax.xml.parsers.DocumentBuilderFactory;  
import javax.xml.parsers.DocumentBuilder;  
import org.w3c.dom.Document;  
import org.w3c.dom.Element;  
import java.io.File;  

public class LogIO {
    /**
     * Parses the build results from xml logs generated by maven. Returns an appropriate Status depending on the content of these logs. 
     * @return Status.FAILURE if there are test fails, Status.ERROR if there are runtime errors, and Status.SUCCESS otherwise. 
     */
    public static Status getBuildResult(){

        //Gets directory with logs and loops through the existing logs.
        File dir = new File("./target/surefire-reports/");
        File[] buildLogs = dir.listFiles();
        for(File log : buildLogs){
            
            //Extracts the filename of a test log to check file extension.
            String[] s = log.getName().split("\\.");
            if(s[s.length-1].equals("xml")){
                Document xmlLog;  
                try{

                    //Converts the xml file into a Document object.
                    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();  
                    DocumentBuilder db = dbf.newDocumentBuilder();  
                    xmlLog = db.parse(log);  
                    xmlLog.getDocumentElement().normalize();

                } catch(Exception e){
                    //TODO: Consider how exceptions should be handled here. 
                    return Status.ERROR;
                }

                //Parses the number of fails and errors in the test suite
                Element element = (Element) xmlLog.getElementsByTagName("testsuite").item(0);
                int errors = Integer.parseInt(element.getAttribute("errors"));
                int fails = Integer.parseInt(element.getAttribute("failures"));

                //Checks if there were any errors/fails and returns corresponding status.
                if(errors != 0){
                    return Status.ERROR;
                } else if(fails != 0){
                    return Status.FAILURE;
                }
            }
        }
        return Status.SUCCESS;
    } 
}
