package GUI.Institusi;

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

public class InstitusiView extends JFrame {
    private JTable institusiTable;
    private JPanel panel;
    private JButton createButton;
    private JButton updateButton;
    private JButton deleteButton;

    public InstitusiView() {
        setTitle("Daftar Institusi");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setContentPane(panel);
        setVisible(true);
        loadInstitusiData();

        updateButton.setEnabled(false);
        deleteButton.setEnabled(false);

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CreateInstitusiForm(InstitusiView.this, null).setVisible(true);
            }
        });

        institusiTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                updateButton.setEnabled(true);
                deleteButton.setEnabled(true);
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = institusiTable.getSelectedRow();
                if (selectedRow != -1) {
                    int id = (int) institusiTable.getValueAt(selectedRow, 0);
                    String namaInstitusi = (String) institusiTable.getValueAt(selectedRow, 1);
                    String alamat = (String) institusiTable.getValueAt(selectedRow, 2);

                    Institusi institusi = new Institusi(id, namaInstitusi, alamat);
                    new CreateInstitusiForm(InstitusiView.this, institusi).setVisible(true);
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = institusiTable.getSelectedRow();
                if (selectedRow != -1) {
                    int id = (int) institusiTable.getValueAt(selectedRow, 0);
                    int response = JOptionPane.showConfirmDialog(null, "Apakah anda yakin untuk menghapus data Institusi ini?", "Konfirmasi Hapus", JOptionPane.YES_NO_OPTION);
                    if (response == JOptionPane.YES_OPTION) {
                        ConfigDB configDB = new ConfigDB();
                        configDB.HapusDinamis("institusi", "id_institusi", String.valueOf(id));
                        loadInstitusiData();
                    }
                }
            }
        });
    }

    public void loadInstitusiData() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Nama Institusi");
        model.addColumn("Alamat");

        String query = "SELECT id_institusi, nama_institusi, alamat_institusi FROM institusi";

        try (Connection connection = ConfigDB.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                model.addRow(new Object[]{
                        resultSet.getInt("id_institusi"),
                        resultSet.getString("nama_institusi"),
                        resultSet.getString("alamat_institusi")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        institusiTable.setModel(model);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new InstitusiView();
            }
        });
    }
}