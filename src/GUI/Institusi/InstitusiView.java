package GUI.Institusi;

import ConfigDB.ConfigDB;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CreateInstitusiForm(InstitusiView.this).setVisible(true);
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