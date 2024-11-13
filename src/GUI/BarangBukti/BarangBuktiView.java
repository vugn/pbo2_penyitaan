package GUI.BarangBukti;

import ConfigDB.ConfigDB;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BarangBuktiView extends JFrame {
    private JTable barangBuktiTable;
    private JPanel panel;
    private JButton createButton;
    private JButton updateButton;
    private JButton deleteButton;

    public BarangBuktiView() {
        setTitle("Daftar Barang Bukti");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setContentPane(panel);
        setVisible(true);
        loadBarangBuktiData();

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CreateBarangBuktiForm(BarangBuktiView.this).setVisible(true);
            }
        });
    }

    public void loadBarangBuktiData() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Asal Pemohon");
        model.addColumn("Tersangka");
        model.addColumn("Tindak Pidana");
        model.addColumn("Dokumen");
        model.addColumn("Tahap");

        String query = "SELECT bb.id_barang_bukti, bb.asal_permohonan, bb.tersangka, tp.nama_tindak_pidana, bb.dokumen, bb.tahap " +
                       "FROM barang_bukti bb " +
                       "JOIN tindak_pidana tp ON bb.id_tindak_pidana = tp.id_tindak_pidana";

        try (Connection connection = ConfigDB.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                model.addRow(new Object[]{
                        resultSet.getInt("id_barang_bukti"),
                        resultSet.getString("asal_permohonan"),
                        resultSet.getString("tersangka"),
                        resultSet.getString("nama_tindak_pidana"),
                        resultSet.getString("dokumen"),
                        resultSet.getString("tahap")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        barangBuktiTable.setModel(model);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new BarangBuktiView();
            }
        });
    }
}