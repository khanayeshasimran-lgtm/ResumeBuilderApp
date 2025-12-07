package com.resume.ui;

import com.resume.data.AppState;
import com.resume.data.ResumeData;

import javax.swing.*;
import java.awt.*;

public class EducationPage extends JFrame {

    JTextField degreeField, branchField, collegeField, startYearField, endYearField, cgpaField;

    public EducationPage() {

        setTitle("Education Details");
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

        JLabel heading = new JLabel("Education Details");
        heading.setFont(new Font("SansSerif", Font.BOLD, 30));
        heading.setBounds(30, 20, 400, 40);

        // Degree
        JLabel degreeLbl = new JLabel("Degree:");
        degreeLbl.setBounds(30, 90, 150, 25);
        degreeField = new JTextField();
        degreeField.setBounds(160, 90, 250, 30);

        // Branch
        JLabel branchLbl = new JLabel("Branch:");
        branchLbl.setBounds(30, 140, 150, 25);
        branchField = new JTextField();
        branchField.setBounds(160, 140, 250, 30);

        // College
        JLabel collegeLbl = new JLabel("College:");
        collegeLbl.setBounds(30, 190, 150, 25);
        collegeField = new JTextField();
        collegeField.setBounds(160, 190, 400, 30);

        // Start Year
        JLabel startLbl = new JLabel("Start Year:");
        startLbl.setBounds(30, 240, 150, 25);
        startYearField = new JTextField();
        startYearField.setBounds(160, 240, 120, 30);

        // End Year
        JLabel endLbl = new JLabel("End Year:");
        endLbl.setBounds(300, 240, 150, 25);
        endYearField = new JTextField();
        endYearField.setBounds(380, 240, 120, 30);

        // CGPA
        JLabel cgpaLbl = new JLabel("CGPA:");
        cgpaLbl.setBounds(30, 290, 150, 25);
        cgpaField = new JTextField();
        cgpaField.setBounds(160, 290, 100, 30);

        // Buttons
        JButton saveBtn = new JButton("Save");
        saveBtn.setBounds(250, 400, 120, 40);
        saveBtn.setBackground(new Color(46, 62, 92));
        saveBtn.setForeground(Color.BLACK);

        JButton clearBtn = new JButton("Clear");
        clearBtn.setBounds(380, 400, 120, 40);
        clearBtn.setBackground(new Color(140, 140, 140));
        clearBtn.setForeground(Color.BLACK);

        JButton backBtn = new JButton("Back");
        backBtn.setBounds(510, 400, 120, 40);
        backBtn.setBackground(new Color(70, 70, 70));
        backBtn.setForeground(Color.BLACK);

        // Add to card
        card.add(heading);

        card.add(degreeLbl); card.add(degreeField);
        card.add(branchLbl); card.add(branchField);
        card.add(collegeLbl); card.add(collegeField);
        card.add(startLbl); card.add(startYearField);
        card.add(endLbl); card.add(endYearField);
        card.add(cgpaLbl); card.add(cgpaField);

        card.add(saveBtn); 
        card.add(clearBtn); 
        card.add(backBtn);

        bg.add(card);
        add(bg);

        // Actions
        saveBtn.addActionListener(e -> saveData());
        clearBtn.addActionListener(e -> clearForm());
        backBtn.addActionListener(e -> {
            dispose();
            new DashboardPage();
        });

        setVisible(true);
    }


    // ---------------- SAVE DATA ----------------

    private void saveData() {
        ResumeData rd = AppState.getResumeData();

        rd.setDegree(degreeField.getText().trim());
        rd.setBranch(branchField.getText().trim());
        rd.setCollege(collegeField.getText().trim());
        rd.setStartYear(startYearField.getText().trim());
        rd.setEndYear(endYearField.getText().trim());
        rd.setCgpa(cgpaField.getText().trim());

        JOptionPane.showMessageDialog(this, "Education Details Saved!");
    }

    // ---------------- CLEAR FORM ----------------

    private void clearForm() {
        degreeField.setText("");
        branchField.setText("");
        collegeField.setText("");
        startYearField.setText("");
        endYearField.setText("");
        cgpaField.setText("");
    }

    // ---------------- BACKGROUND PANEL ----------------

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
