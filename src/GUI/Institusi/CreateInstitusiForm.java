package GUI.Institusi;

import ConfigDB.ConfigDB;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreateInstitusiForm extends JFrame {
    private JPanel panel;
    private JTextField namaInstitusiField;
    private JTextField alamatField;
    private JButton saveButton;
    private JButton cancelButton;

    public CreateInstitusiForm(InstitusiView parent) {
        setTitle("Tambah Institusi");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setContentPane(panel);
        setVisible(true);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String namaInstitusi = namaInstitusiField.getText();
                String alamat = alamatField.getText();

                try (Connection connection = ConfigDB.getConnection()) {
                    String query = "INSERT INTO institusi (nama_institusi, alamat_institusi) VALUES (?, ?)";
                    PreparedStatement statement = connection.prepareStatement(query);
                    statement.setString(1, namaInstitusi);
                    statement.setString(2, alamat);
                    statement.executeUpdate();
                    parent.loadInstitusiData();
                    dispose();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CreateInstitusiForm(null).setVisible(true);
            }
        });
    }
}