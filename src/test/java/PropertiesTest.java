import com.fastcampus.jcjboard.servlet.GetPropertyValue;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

//public class PropertiesTest {
//    @Test
//
//    public void test() throws Exception{
//        InputStream inputStream;
//        String DbUri;
//        String DbUser;
//        String DbPassword;
//        String DbDriver;
//
//        Properties properties = new Properties();
//        String proFileName = "db2";
//
//        inputStream = getClass().getClassLoader().getResourceAsStream(proFileName);
//
//        if(inputStream != null){
//            properties.load(inputStream);
//        }else{
//            throw new FileNotFoundException("property file" + proFileName + " not found in the classpath");
//        }
//        DbUri = properties.getProperty("dbUrl");
//        DbUser = properties.getProperty("dbId");
//        DbPassword = properties.getProperty("dbPassword");
//        DbDriver = properties.getProperty("dbDriver");
//
//        //System.out.println(DbUri+","+DbUser+","+DbPassword+","+DbDriver);
//    }
//
//    @Test
//    public void test2() throws IOException {
//        String dbUrl;
//        String dbId;
//        String dbPassword;
//
//        GetPropertyValue getPropertyValue = new GetPropertyValue();
//        // DBConfiguration dbConfiguration = DBConfiguration.getInstance();
//        getPropertyValue.getPropValues();
//        dbUrl = getPropertyValue.getDbUri();
//        dbId = getPropertyValue.getDbUser();
//        dbPassword = getPropertyValue.getDbPassword();
//        //System.out.println(dbUrl + "," + dbId + "," + dbPassword);
//    }
//}
