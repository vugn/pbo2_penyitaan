package GUI;

import ConfigDB.ConfigDB;
import GUI.BarangBukti.BarangBuktiView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame {
    private JPanel panel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel passwordLabel;
    private JLabel usernameLabel;

    public Login() {
        setTitle("Masuk ke Aplikasi Penyitaan");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setContentPane(panel);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                if (ConfigDB.authenticate(username, password)) {
                    JOptionPane.showMessageDialog(Login.this, "Login successful!");
                    showActionButtons();
                } else {
                    JOptionPane.showMessageDialog(Login.this, "Invalid username or password.");
                }
            }
        });
    }

    private void showActionButtons() {
        JFrame actionFrame = new JFrame("Select Action");
        actionFrame.setSize(300, 150);
        actionFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        actionFrame.setLocationRelativeTo(null);

        JPanel actionPanel = new JPanel();
        JButton barangBuktiButton = new JButton("Barang Bukti");
        JButton anotherButton = new JButton("Institusin");

        barangBuktiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new BarangBuktiView();
                actionFrame.dispose();
            }
        });

        anotherButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add action for another button here
                JOptionPane.showMessageDialog(actionFrame, "Another action selected!");
            }
        });

        actionPanel.add(barangBuktiButton);
        actionPanel.add(anotherButton);
        actionFrame.add(actionPanel);
        actionFrame.setVisible(true);
        dispose(); // Close the login window
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Login().setVisible(true);
            }
        });
    }
}