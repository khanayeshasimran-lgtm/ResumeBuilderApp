package com.resume.ui;

import com.resume.data.AppState;
import com.resume.data.ResumeData;

import javax.swing.*;
import java.awt.*;

public class ExperiencePage extends JFrame {

    JTextField titleField, companyField, durationField;
    JTextArea descArea;

    public ExperiencePage() {

        setTitle("Experience Details");
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
        card.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));

        JLabel heading = new JLabel("Experience");
        heading.setFont(new Font("SansSerif", Font.BOLD, 30));
        heading.setBounds(30, 20, 400, 40);

        // Job Title
        JLabel titleLbl = new JLabel("Job Title:");
        titleLbl.setBounds(30, 90, 150, 25);
        titleField = new JTextField();
        titleField.setBounds(160, 90, 300, 30);

        // Company
        JLabel companyLbl = new JLabel("Company:");
        companyLbl.setBounds(30, 140, 150, 25);
        companyField = new JTextField();
        companyField.setBounds(160, 140, 300, 30);

        // Duration
        JLabel durationLbl = new JLabel("Duration:");
        durationLbl.setBounds(30, 190, 150, 25);
        durationField = new JTextField();
        durationField.setBounds(160, 190, 200, 30);

        // Description
        JLabel descLbl = new JLabel("Description:");
        descLbl.setBounds(30, 240, 150, 25);

        descArea = new JTextArea();
        descArea.setLineWrap(true);
        descArea.setWrapStyleWord(true);

        JScrollPane descScroll = new JScrollPane(descArea);
        descScroll.setBounds(160, 240, 500, 150);

        // Buttons
        JButton saveBtn = new JButton("Save");
        saveBtn.setBounds(280, 430, 120, 40);
        saveBtn.setBackground(new Color(46, 62, 92));
        saveBtn.setForeground(Color.BLACK);

        JButton clearBtn = new JButton("Clear");
        clearBtn.setBounds(410, 430, 120, 40);
        clearBtn.setBackground(new Color(140, 140, 140));
        clearBtn.setForeground(Color.BLACK);

        JButton backBtn = new JButton("Back");
        backBtn.setBounds(540, 430, 120, 40);
        backBtn.setBackground(new Color(70, 70, 70));
        backBtn.setForeground(Color.BLACK);

        // Add to card
        card.add(heading);

        card.add(titleLbl); card.add(titleField);
        card.add(companyLbl); card.add(companyField);
        card.add(durationLbl); card.add(durationField);
        card.add(descLbl); card.add(descScroll);

        card.add(saveBtn);
        card.add(clearBtn);
        card.add(backBtn);

        bg.add(card);
        add(bg);

        // Actions
        saveBtn.addActionListener(e -> saveData());
        clearBtn.addActionListener(e -> clearForm());
        backBtn.addActionListener(e -> { dispose(); new DashboardPage(); });

        setVisible(true);
    }

    // ---------------- SAVE DATA ----------------

    private void saveData() {
        ResumeData rd = AppState.getResumeData();

        rd.setExpTitle(titleField.getText().trim());
        rd.setExpCompany(companyField.getText().trim());
        rd.setExpDuration(durationField.getText().trim());
        rd.setExpDescription(descArea.getText().trim());

        JOptionPane.showMessageDialog(this, "Experience Saved!");
    }

    // ---------------- CLEAR FORM ----------------

    private void clearForm() {
        titleField.setText("");
        companyField.setText("");
        durationField.setText("");
        descArea.setText("");
    }

    // ---------------- BACKGROUND GRADIENT ----------------

    private static class GradientPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2d = (Graphics2D) g;
            g2d.setPaint(new GradientPaint(
                0, 0, new Color(24,40,72),
                0, getHeight(), new Color(44,62,92)
            ));
            g2d.fillRect(0, 0, getWidth(), getHeight());
        }
    }
}

