// CreateBarangBuktiForm.java
package GUI.BarangBukti;

import ConfigDB.ConfigDB;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class CreateBarangBuktiForm extends JFrame {
    private JPanel panel;
    private JTextField asalPemohonField;
    private JTextField tersangkaField;
    private JComboBox<String> tindakPidanaComboBox;
    private JTextField dokumenField;
    private JTextField tahapField;
    private JButton saveButton;
    private JButton cancelButton;
    private Map<String, Integer> tindakPidanaMap;
    private Integer barangBuktiId;

    public CreateBarangBuktiForm(BarangBuktiView parent, BarangBukti barangBukti) {
        setTitle(barangBukti == null ? "Tambah Barang Bukti" : "Edit Barang Bukti");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setContentPane(panel);
        setVisible(true);

        // Load tindak pidana data
        loadTindakPidanaData();

        if (barangBukti != null) {
            barangBuktiId = barangBukti.getId();
            asalPemohonField.setText(barangBukti.getAsalPemohon());
            tersangkaField.setText(barangBukti.getTersangka());
            dokumenField.setText(barangBukti.getDokumen());
            tahapField.setText(barangBukti.getTahap());
            tindakPidanaComboBox.setSelectedItem(barangBukti.getTindakPidana());
        }

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String asalPemohon = asalPemohonField.getText();
                String tersangka = tersangkaField.getText();
                String dokumen = dokumenField.getText();
                String tahap = tahapField.getText();
                String selectedTindakPidana = (String) tindakPidanaComboBox.getSelectedItem();
                int tindakPidanaId = tindakPidanaMap.get(selectedTindakPidana);

                ConfigDB configDB = new ConfigDB();
                String[] fields = {"asal_permohonan", "tersangka", "id_tindak_pidana", "dokumen", "tahap"};
                String[] values = {asalPemohon, tersangka, String.valueOf(tindakPidanaId), dokumen, tahap};

                if (barangBuktiId == null) {
                    configDB.TambahDinamis("barang_bukti", fields, values);
                } else {
                    configDB.UbahDinamis("barang_bukti", "id_barang_bukti", String.valueOf(barangBuktiId), fields, values);
                }
                parent.loadBarangBuktiData();
                dispose();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    private void loadTindakPidanaData() {
        tindakPidanaMap = new HashMap<>();
        try (Connection connection = ConfigDB.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT id_tindak_pidana, nama_tindak_pidana FROM tindak_pidana")) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id_tindak_pidana");
                String name = resultSet.getString("nama_tindak_pidana");
                tindakPidanaMap.put(name, id);
                tindakPidanaComboBox.addItem(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}