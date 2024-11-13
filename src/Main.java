import ConfigDB.ConfigDB;
import GUI.Login;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try {
            ConfigDB.getConnection();
            new Login().setVisible(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}