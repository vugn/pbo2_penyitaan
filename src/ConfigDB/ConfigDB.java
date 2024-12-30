package ConfigDB;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.File;
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
        String hasil = "";
        int deteksi = Field.length - 1;
        try {
            for (int i = 0; i < Field.length; i++) {
                if (i == deteksi) {
                    hasil += Field[i] + " = ?";
                } else {
                    hasil += Field[i] + " = ?, ";
                }
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error in getFieldValueEdit", e);
        }
        return hasil;
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
        String fields = "";
        String values = "";
        for (int i = 0; i < Field.length; i++) {
            fields += Field[i];
            values += "?";
            if (i < Field.length - 1) {
                fields += ", ";
                values += ", ";
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

    public ResultSet CariDinamis(String NamaTabel, String[] Field, String[] Value) {
        String fields = "";
        for (int i = 0; i < Field.length; i++) {
            fields += Field[i] + " LIKE ?";
            if (i < Field.length - 1) {
                fields += " OR ";
            }
        }
        String SQLCari = "SELECT * FROM " + NamaTabel + " WHERE " + fields;
        try {
            Connection connection = ConfigDB.getConnection();
            PreparedStatement perintah = connection.prepareStatement(SQLCari);
            for (int i = 0; i < Value.length; i++) {
                perintah.setString(i + 1, "%" + Value[i] + "%");
            }

            System.out.println(perintah);
            return perintah.executeQuery();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error in CariDinamis", e);
            return null;
        }
    }


    public void TampilLaporan(String laporanFile, String SQL) throws SQLException{
        try {
            File file = new File(laporanFile);
            JasperDesign jasDes = JRXmlLoader.load(file);

            JRDesignQuery sqlQuery = new JRDesignQuery();
            sqlQuery.setText(SQL);
            jasDes.setQuery(sqlQuery);

            JasperReport JR = JasperCompileManager.compileReport(jasDes);
            JasperPrint JP = JasperFillManager.fillReport(JR,null,getConnection());
            JasperViewer.viewReport(JP,false);
        } catch (JRException e) {
            JOptionPane.showMessageDialog(null,e.toString());

        }
    }
}