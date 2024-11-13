package ConfigDB;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ConfigDB {
    private static final String URL = "jdbc:mysql://localhost:8889/pbo2_penyitaan";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    private static final Logger LOGGER = Logger.getLogger(ConfigDB.class.getName());

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static boolean authenticate(String username, String hashedPassword) {
        String query = "SELECT * FROM login WHERE username = ? AND password = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            statement.setString(2, hashedPassword);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error in authenticate", e);
            return false;
        }
    }

    public String getFieldValueEdit(String[] Field, String[] value) {
        StringBuilder hasil = new StringBuilder();
        int deteksi = Field.length - 1;
        try {
            for (int i = 0; i < Field.length; i++) {
                if (i == deteksi) {
                    hasil.append(Field[i]).append(" = ?");
                } else {
                    hasil.append(Field[i]).append(" = ?, ");
                }
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error in getFieldValueEdit", e);
        }
        return hasil.toString();
    }

    public void UbahDinamis(String NamaTabel, String PrimaryKey, String IsiPrimary, String[] Field, String[] Value) {
        String SQLUbah = "UPDATE " + NamaTabel + " SET " + getFieldValueEdit(Field, Value) + " WHERE " + PrimaryKey + " = ?";
        try (Connection connection = ConfigDB.getConnection();
             PreparedStatement perintah = connection.prepareStatement(SQLUbah)) {
            for (int i = 0; i < Value.length; i++) {
                perintah.setString(i + 1, Value[i]);
            }
            perintah.setString(Value.length + 1, IsiPrimary);
            perintah.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Berhasil Diubah");
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error in UbahDinamis", e);
        }
    }

    public void HapusDinamis(String NamaTabel, String PK, String isi) {
        String SQL = "DELETE FROM " + NamaTabel + " WHERE " + PK + " = ?";
        try (Connection connection = ConfigDB.getConnection();
             PreparedStatement perintah = connection.prepareStatement(SQL)) {
            perintah.setString(1, isi);
            perintah.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error in HapusDinamis", e);
        }
    }

    public void TambahDinamis(String NamaTabel, String[] Field, String[] Value) {
        StringBuilder fields = new StringBuilder();
        StringBuilder values = new StringBuilder();
        for (int i = 0; i < Field.length; i++) {
            fields.append(Field[i]);
            values.append("?");
            if (i < Field.length - 1) {
                fields.append(", ");
                values.append(", ");
            }
        }
        String SQLTambah = "INSERT INTO " + NamaTabel + " (" + fields + ") VALUES (" + values + ")";
        try (Connection connection = ConfigDB.getConnection();
             PreparedStatement perintah = connection.prepareStatement(SQLTambah)) {
            for (int i = 0; i < Value.length; i++) {
                perintah.setString(i + 1, Value[i]);
            }
            perintah.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Berhasil Ditambahkan");
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error in TambahDinamis", e);
        }
    }
}