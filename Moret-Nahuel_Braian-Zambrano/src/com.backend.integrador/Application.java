package src.com.backend.integrador;

import java.sql.Connection;
import java.sql.DriverManager;
public class Application {
    public static void main(String[] args) {
        Connection connection = null;
        try{
            Class.forName("org.h2.driver");
            connection = DriverManager.getConnection("jdbc:h2:~/db_clinica;INIT=RUNSCRIPT FROM 'create.sql'", "sa", "");


        } catch (Exception e){
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception ex){
                ex.printStackTrace();
            }
        }

    }
}
