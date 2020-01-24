package sdacademy.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class JDBCConnection {

    public static void main(String[] args) {
        //strefa czasowa oraz wyłączenie SSL
        String jdbcUrl = "jdbc:mysql://localhost:3306/javawro25?useSSL=false&serverTimezone=UTC";
        String user = "root";
        String password = "root";

        try {
            System.out.println("łaczenie z bazą danych ");

            Connection connection = DriverManager.getConnection(jdbcUrl, user, password);

            System.out.println("połączyłeś się z bazą danych");

        } catch (Exception ex) {
            ex.printStackTrace();

        }
    }
}
