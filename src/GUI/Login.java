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
                    new BarangBuktiView(); // Open BarangBukti UI
                    dispose(); // Close the login window
                } else {
                    JOptionPane.showMessageDialog(Login.this, "Invalid username or password.");
                }
            }
        });
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