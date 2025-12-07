package com.resume.ui;

import javax.swing.*;
import java.awt.*;

public class LoginPage extends JFrame {

    JTextField usernameField;
    JPasswordField passwordField;
    JButton loginBtn, clearBtn;

    public LoginPage() {
        setTitle("Resume Builder - Login");
        setSize(420, 540);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // ✅ Set app icon
        ImageIcon icon = new ImageIcon("assets/app_icon.png");
        if (icon.getImageLoadStatus() != MediaTracker.COMPLETE) {
            System.out.println("⚠️ Failed to load app icon.");
        } else {
            System.out.println("✅ App icon loaded successfully.");
        }
        setIconImage(icon.getImage());

        // ✅ Set Dock icon (for macOS)
        try {
            java.awt.Taskbar.getTaskbar().setIconImage(icon.getImage());
        } catch (UnsupportedOperationException | SecurityException e) {
            System.out.println("⚠️ Unable to set dock icon: " + e.getMessage());
        }

        // Main background panel
        JPanel background = new JPanel(null);
        background.setBackground(new Color(46, 62, 92)); // navy blue

        // Card panel
        JPanel card = new JPanel(null);
        card.setBackground(Color.WHITE);
        card.setBounds(50, 70, 320, 420); // taller to fit logo
        card.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220), 1));

        // Logo image
        JLabel logoLabel = new JLabel(icon);
        logoLabel.setBounds(110, 10, 100, 100);
        card.add(logoLabel);

        // Title
        JLabel title = new JLabel("Login", JLabel.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 24));
        title.setBounds(0, 110, 320, 30);
        card.add(title);

        JLabel subtitle = new JLabel("Welcome back!", JLabel.CENTER);
        subtitle.setFont(new Font("SansSerif", Font.PLAIN, 14));
        subtitle.setForeground(Color.GRAY);
        subtitle.setBounds(0, 145, 320, 20);
        card.add(subtitle);

        // Username
        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(40, 175, 100, 20);
        card.add(userLabel);

        usernameField = new JTextField();
        usernameField.setBounds(40, 200, 240, 32);
        card.add(usernameField);

        // Password
        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(40, 245, 100, 20);
        card.add(passLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(40, 270, 240, 32);
        card.add(passwordField);

        // Login button
        loginBtn = new JButton("Login");
        loginBtn.setBounds(40, 325, 110, 38);
        loginBtn.setBackground(new Color(46, 62, 92));
        loginBtn.setForeground(Color.BLACK);
        loginBtn.setFont(new Font("SansSerif", Font.BOLD, 14));
        card.add(loginBtn);

        // Hover effect
        loginBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                loginBtn.setBackground(new Color(30, 45, 70));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                loginBtn.setBackground(new Color(46, 62, 92));
            }
        });

        // Clear button
        clearBtn = new JButton("Clear");
        clearBtn.setBounds(170, 325, 110, 38);
        clearBtn.setBackground(new Color(190, 190, 190));
        clearBtn.setForeground(Color.BLACK);
        clearBtn.setFont(new Font("SansSerif", Font.BOLD, 14));
        card.add(clearBtn);

        // Add everything to background
        background.add(card);
        add(background);

        addActionListeners();
        setVisible(true);
    }

    private void addActionListeners() {
        loginBtn.addActionListener(e -> {
            String user = usernameField.getText();
            String pass = new String(passwordField.getPassword());

            if (user.equals("ayesha") && pass.equals("1234")) {
                JOptionPane.showMessageDialog(this, "Login successful!");
                dispose();
                new DashboardPage();
            } else {
                JOptionPane.showMessageDialog(this,
                        "Invalid Username or Password",
                        "Login Failed",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        clearBtn.addActionListener(e -> {
            usernameField.setText("");
            passwordField.setText("");
        });
    }
}
