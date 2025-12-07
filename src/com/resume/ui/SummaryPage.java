package com.resume.ui;

import com.resume.data.AppState;
import com.resume.data.ResumeData;

import javax.swing.*;
import java.awt.*;

public class SummaryPage extends JFrame {

    JTextArea summaryArea;

    public SummaryPage() {

        setTitle("Professional Summary");
        setSize(900, 620);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // ⭐ APP ICON
        setIconImage(Toolkit.getDefaultToolkit().getImage("assets/app_icon.png"));


        JPanel bg = new GradientPanel();
        bg.setLayout(null);

        JPanel card = new JPanel(null);
        card.setBackground(Color.WHITE);
        card.setBounds(70, 40, 760, 520);
        card.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));

        JLabel heading = new JLabel("Professional Summary");
        heading.setFont(new Font("SansSerif", Font.BOLD, 28));
        heading.setBounds(30, 20, 400, 40);

        JLabel label = new JLabel("Write a short professional summary:");
        label.setBounds(30, 80, 400, 25);

        summaryArea = new JTextArea();
        summaryArea.setLineWrap(true);
        summaryArea.setWrapStyleWord(true);

        JScrollPane scroll = new JScrollPane(summaryArea);
        scroll.setBounds(30, 110, 700, 300);

        JButton saveBtn = new JButton("Save");
        saveBtn.setBounds(220, 440, 120, 40);
        saveBtn.setBackground(new Color(46, 62, 92));
        saveBtn.setForeground(Color.BLACK);

        JButton clearBtn = new JButton("Clear");
        clearBtn.setBounds(350, 440, 120, 40);
        clearBtn.setBackground(new Color(140, 140, 140));
        clearBtn.setForeground(Color.BLACK);

        JButton backBtn = new JButton("Back");
        backBtn.setBounds(480, 440, 120, 40);
        backBtn.setBackground(new Color(70, 70, 70));
        backBtn.setForeground(Color.BLACK);

        card.add(heading);
        card.add(label);
        card.add(scroll);
        card.add(saveBtn);
        card.add(clearBtn);
        card.add(backBtn);

        bg.add(card);
        add(bg);

        // Actions
        saveBtn.addActionListener(e -> saveData());
        clearBtn.addActionListener(e -> summaryArea.setText(""));
        backBtn.addActionListener(e -> { dispose(); new DashboardPage(); });

        setVisible(true);
    }

    // ---------------- SAVE ----------------

    private void saveData() {
        ResumeData data = AppState.getResumeData();
        data.setSummary(summaryArea.getText().trim());
        JOptionPane.showMessageDialog(this, "Summary saved!");
    }

    // ---------------- BACKGROUND PANEL ----------------

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
