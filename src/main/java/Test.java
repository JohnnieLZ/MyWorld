import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Test {

    public static void main(String[] args) {
        System.out.println(Test.getConnection());
    }

    public static Connection getConnection() {
        Connection conn = null;
        try {
            FileInputStream in = new FileInputStream("src/main/resources/jdbc.properties");
            Properties properties = new Properties();
            properties.load(in);
            Class.forName(properties.get("driverClassName").toString());
            conn = DriverManager.getConnection(properties.get("url").toString(),properties.get("user").toString(),properties.get("password").toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
