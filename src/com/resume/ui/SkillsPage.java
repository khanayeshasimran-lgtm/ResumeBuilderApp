package com.resume.ui;

import com.resume.data.AppState;
import com.resume.data.ResumeData;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SkillsPage extends JFrame {

    JTextField skillField;
    DefaultListModel<String> skillsModel;
    JList<String> skillsList;

    public SkillsPage() {

        setTitle("Skills");
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

        JLabel heading = new JLabel("Skills");
        heading.setFont(new Font("SansSerif", Font.BOLD, 30));
        heading.setBounds(30, 20, 200, 40);

        // Skill input
        JLabel skillLbl = new JLabel("Enter Skill:");
        skillLbl.setBounds(30, 90, 150, 25);

        skillField = new JTextField();
        skillField.setBounds(150, 90, 250, 30);

        JButton addSkillBtn = new JButton("Add Skill");
        addSkillBtn.setBounds(420, 90, 120, 30);
        addSkillBtn.setBackground(new Color(46, 62, 92));
        addSkillBtn.setForeground(Color.BLACK);

        // List of skills
        skillsModel = new DefaultListModel<>();
        skillsList = new JList<>(skillsModel);

        JScrollPane skillScroll = new JScrollPane(skillsList);
        skillScroll.setBounds(30, 150, 350, 250);

        // Delete skill button
        JButton deleteBtn = new JButton("Delete Selected");
        deleteBtn.setBounds(400, 150, 150, 30);
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
        card.add(skillLbl);
        card.add(skillField);
        card.add(addSkillBtn);
        card.add(skillScroll);
        card.add(deleteBtn);
        card.add(saveBtn);
        card.add(clearBtn);
        card.add(backBtn);

        bg.add(card);
        add(bg);

        // Actions
        addSkillBtn.addActionListener(e -> addSkill());
        deleteBtn.addActionListener(e -> deleteSkill());
        saveBtn.addActionListener(e -> saveSkills());
        clearBtn.addActionListener(e -> clearSkills());
        backBtn.addActionListener(e -> { dispose(); new DashboardPage(); });

        setVisible(true);
    }

    // ---------------- ADD SKILL ----------------

    private void addSkill() {
        String skill = skillField.getText().trim();
        if (skill.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Enter a skill!");
            return;
        }
        skillsModel.addElement(skill);
        skillField.setText("");
    }

    // ---------------- DELETE SKILL ----------------

    private void deleteSkill() {
        int index = skillsList.getSelectedIndex();
        if (index != -1) {
            skillsModel.remove(index);
        } else {
            JOptionPane.showMessageDialog(this, "Select a skill to delete.");
        }
    }

    // ---------------- SAVE SKILLS ----------------

    private void saveSkills() {
        ArrayList<String> skillList = new ArrayList<>();

        for (int i = 0; i < skillsModel.size(); i++) {
            skillList.add(skillsModel.get(i));
        }

        // Convert list -> comma separated string
        String skillsStr = String.join(", ", skillList);

        AppState.getResumeData().setSkills(skillsStr);

        JOptionPane.showMessageDialog(this, "Skills Saved!");
    }

    // ---------------- CLEAR ALL ----------------

    private void clearSkills() {
        skillsModel.clear();
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
