package GUI.BarangBukti;

import ConfigDB.ConfigDB;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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

        updateButton.setEnabled(false);
        deleteButton.setEnabled(false);

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CreateBarangBuktiForm(BarangBuktiView.this, null).setVisible(true);
            }
        });

        barangBuktiTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                updateButton.setEnabled(true);
                deleteButton.setEnabled(true);
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = barangBuktiTable.getSelectedRow();
                if (selectedRow != -1) {
                    int id = (int) barangBuktiTable.getValueAt(selectedRow, 0);
                    String asalPemohon = (String) barangBuktiTable.getValueAt(selectedRow, 1);
                    String tersangka = (String) barangBuktiTable.getValueAt(selectedRow, 2);
                    String tindakPidana = (String) barangBuktiTable.getValueAt(selectedRow, 3);
                    String dokumen = (String) barangBuktiTable.getValueAt(selectedRow, 4);
                    String tahap = (String) barangBuktiTable.getValueAt(selectedRow, 5);

                    BarangBukti barangBukti = new BarangBukti(id, asalPemohon, tersangka, tindakPidana, dokumen, tahap);
                    new CreateBarangBuktiForm(BarangBuktiView.this, barangBukti).setVisible(true);
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = barangBuktiTable.getSelectedRow();
                if (selectedRow != -1) {
                    int id = (int) barangBuktiTable.getValueAt(selectedRow, 0);
                    int response = JOptionPane.showConfirmDialog(null, "Apakah anda yakin untuk menghapus data Barang Bukti ini?", "Konfirmasi Hapus", JOptionPane.YES_NO_OPTION);
                    if (response == JOptionPane.YES_OPTION) {
                        ConfigDB configDB = new ConfigDB();
                        configDB.HapusDinamis("barang_bukti", "id_barang_bukti", String.valueOf(id));
                        loadBarangBuktiData();
                    }
                }
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