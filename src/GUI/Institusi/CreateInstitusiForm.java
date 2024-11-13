package GUI.Institusi;

import ConfigDB.ConfigDB;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateInstitusiForm extends JFrame {
    private JPanel panel;
    private JTextField namaInstitusiField;
    private JTextField alamatField;
    private JButton saveButton;
    private JButton cancelButton;
    private Integer institusiId;

    public CreateInstitusiForm(InstitusiView parent, Institusi institusi) {
        setTitle(institusi == null ? "Tambah Institusi" : "Edit Institusi");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setContentPane(panel);
        setVisible(true);

        if (institusi != null) {
            institusiId = institusi.getId();
            namaInstitusiField.setText(institusi.getNamaInstitusi());
            alamatField.setText(institusi.getAlamat());
        }

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String namaInstitusi = namaInstitusiField.getText();
                String alamat = alamatField.getText();

                ConfigDB configDB = new ConfigDB();
                if (institusiId == null) {
                    String[] fields = {"nama_institusi", "alamat_institusi"};
                    String[] values = {namaInstitusi, alamat};
                    configDB.UbahDinamis("institusi", "id_institusi", String.valueOf(institusiId), fields, values);
                } else {
                    String[] fields = {"nama_institusi", "alamat_institusi"};
                    String[] values = {namaInstitusi, alamat};
                    configDB.UbahDinamis("institusi", "id_institusi", String.valueOf(institusiId), fields, values);
                }
                parent.loadInstitusiData();
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CreateInstitusiForm(null, null).setVisible(true);
            }
        });
    }
}