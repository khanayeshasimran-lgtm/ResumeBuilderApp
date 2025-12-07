package com.resume.ui;

import com.resume.data.AppState;
import com.resume.data.ResumeData;

import javax.swing.*;
import java.awt.*;

public class CertificationsPage extends JFrame {

    JTextField certField;
    DefaultListModel<String> certModel;
    JList<String> certList;

    public CertificationsPage() {

        setTitle("Certifications");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // ⭐ APP ICON
        setIconImage(Toolkit.getDefaultToolkit().getImage("assets/app_icon.png"));

        JPanel bg = new GradientPanel();
        bg.setLayout(null);

        JPanel card = new JPanel(null);
        card.setBackground(Color.WHITE);
        card.setBounds(70, 40, 760, 500);
        card.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        JLabel heading = new JLabel("Certifications");
        heading.setFont(new Font("SansSerif", Font.BOLD, 30));
        heading.setBounds(30, 20, 400, 40);

        // Input Field
        JLabel certLbl = new JLabel("Certification:");
        certLbl.setBounds(30, 90, 150, 25);

        certField = new JTextField();
        certField.setBounds(150, 90, 320, 30);

        JButton addBtn = new JButton("Add");
        addBtn.setBounds(490, 90, 100, 30);
        addBtn.setBackground(new Color(46, 62, 92));
        addBtn.setForeground(Color.BLACK);

        // Certifications list
        certModel = new DefaultListModel<>();
        certList = new JList<>(certModel);

        JScrollPane certScroll = new JScrollPane(certList);
        certScroll.setBounds(30, 150, 560, 260);

        JButton deleteBtn = new JButton("Delete Selected");
        deleteBtn.setBounds(610, 150, 130, 30);
        deleteBtn.setBackground(new Color(255, 99, 71));
        deleteBtn.setForeground(Color.BLACK);

        // Buttons
        JButton saveBtn = new JButton("Save");
        saveBtn.setBounds(260, 430, 120, 40);
        saveBtn.setBackground(new Color(46, 62, 92));
        saveBtn.setForeground(Color.BLACK);

        JButton clearBtn = new JButton("Clear");
        clearBtn.setBounds(390, 430, 120, 40);
        clearBtn.setBackground(new Color(140, 140, 140));
        clearBtn.setForeground(Color.BLACK);

        JButton backBtn = new JButton("Back");
        backBtn.setBounds(520, 430, 120, 40);
        backBtn.setBackground(new Color(70, 70, 70));
        backBtn.setForeground(Color.BLACK);

        // Add to card
        card.add(heading);
        card.add(certLbl);
        card.add(certField);
        card.add(addBtn);
        card.add(certScroll);
        card.add(deleteBtn);
        card.add(saveBtn);
        card.add(clearBtn);
        card.add(backBtn);

        bg.add(card);
        add(bg);

        // Actions
        addBtn.addActionListener(e -> addCertification());
        deleteBtn.addActionListener(e -> deleteCertification());
        saveBtn.addActionListener(e -> saveCertifications());
        clearBtn.addActionListener(e -> clearCertifications());
        backBtn.addActionListener(e -> { dispose(); new DashboardPage(); });

        loadExistingCerts();

        setVisible(true);
    }

    // ---------------- LOAD EXISTING ----------------

    private void loadExistingCerts() {
        for (String cert : AppState.getResumeData().getCertifications()) {
            certModel.addElement(cert);
        }
    }

    // ---------------- ADD ----------------

    private void addCertification() {
        String cert = certField.getText().trim();
        if (cert.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Enter a certification!");
            return;
        }

        certModel.addElement(cert);
        certField.setText("");
    }

    // ---------------- DELETE ----------------

    private void deleteCertification() {
        int index = certList.getSelectedIndex();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Select a certification to delete.");
            return;
        }

        certModel.remove(index);
    }

    // ---------------- SAVE ----------------

    private void saveCertifications() {
        ResumeData rd = AppState.getResumeData();
        rd.clearCertifications();

        for (int i = 0; i < certModel.size(); i++) {
            rd.addCertification(certModel.get(i));
        }

        JOptionPane.showMessageDialog(this, "Certifications Saved!");
    }

    // ---------------- CLEAR ----------------

    private void clearCertifications() {
        certModel.clear();
    }

    // ---------------- BACKGROUND ----------------

    private static class GradientPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2 = (Graphics2D) g;
            g2.setPaint(new GradientPaint(
                0, 0, new Color(24,40,72),
                0, getHeight(), new Color(44,62,92)
            ));
            g2.fillRect(0, 0, getWidth(), getHeight());
        }
    }
}
