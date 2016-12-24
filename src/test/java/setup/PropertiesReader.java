package setup;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by User on 11.12.2016.
 */
public class PropertiesReader {
    String fileLocation = "rozetkaTestProject.properties";
    InputStream inputStream;

    public String getProperty(String arg) throws IOException{
        String argument = "";
        try{
            Properties properties = new Properties();
            inputStream = getClass().getClassLoader().getResourceAsStream(fileLocation);
            if(inputStream != null){
                properties.load(inputStream);
            }else {
                throw new FileNotFoundException(fileLocation + " not found");
            }
            argument = properties.getProperty(arg);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            inputStream.close();
        }
        return argument;
    }
}
