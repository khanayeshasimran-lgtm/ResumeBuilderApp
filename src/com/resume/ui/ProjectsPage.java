package com.resume.ui;

import com.resume.data.AppState;
import com.resume.data.ResumeData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ProjectsPage extends JFrame {

    JTextField titleField, techField, durationField;
    JTextArea descArea;

    DefaultListModel<String> projectListModel;
    JList<String> projectList;

    int selectedIndex = -1;

    public ProjectsPage() {

        setTitle("Projects");
        setSize(950, 750);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setIconImage(new ImageIcon("assets/app_icon.png").getImage());

        JPanel bg = new GradientPanel();
        bg.setLayout(null);

        JPanel card = new JPanel(null);
        card.setBackground(Color.WHITE);
        card.setBounds(40, 40, 860, 640);
        card.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));

        JLabel heading = new JLabel("Projects");
        heading.setFont(new Font("SansSerif", Font.BOLD, 28));
        heading.setBounds(30, 20, 400, 40);

        JLabel titleLbl = new JLabel("Title:");
        titleLbl.setBounds(30, 80, 150, 25);
        titleField = new JTextField();
        titleField.setBounds(160, 80, 280, 30);

        JLabel techLbl = new JLabel("Tech Stack:");
        techLbl.setBounds(30, 130, 150, 25);
        techField = new JTextField();
        techField.setBounds(160, 130, 280, 30);

        JLabel durationLbl = new JLabel("Duration:");
        durationLbl.setBounds(30, 180, 150, 25);
        durationField = new JTextField();
        durationField.setBounds(160, 180, 180, 30);

        JLabel descLbl = new JLabel("Description:");
        descLbl.setBounds(30, 230, 150, 25);
        descArea = new JTextArea();
        descArea.setLineWrap(true);
        descArea.setWrapStyleWord(true);
        JScrollPane descScroll = new JScrollPane(descArea);
        descScroll.setBounds(160, 230, 600, 130);

        JLabel listLbl = new JLabel("Your Projects:");
        listLbl.setBounds(30, 380, 200, 25);
        projectListModel = new DefaultListModel<>();
        projectList = new JList<>(projectListModel);
        JScrollPane listScroll = new JScrollPane(projectList);
        listScroll.setBounds(30, 410, 800, 110);

        JButton addBtn = new JButton("Add");
        addBtn.setBounds(160, 540, 110, 35);
        addBtn.setBackground(new Color(46, 62, 92));
        addBtn.setForeground(Color.BLACK);

        JButton updateBtn = new JButton("Update");
        updateBtn.setBounds(280, 540, 110, 35);
        updateBtn.setBackground(new Color(52, 152, 219));
        updateBtn.setForeground(Color.BLACK);

        JButton deleteBtn = new JButton("Delete");
        deleteBtn.setBounds(400, 540, 110, 35);
        deleteBtn.setBackground(new Color(255, 77, 77));
        deleteBtn.setForeground(Color.BLACK);

        JButton backBtn = new JButton("Back");
        backBtn.setBounds(520, 540, 110, 35);
        backBtn.setBackground(new Color(70, 70, 70));
        backBtn.setForeground(Color.BLACK);

        card.add(heading);
        card.add(titleLbl); card.add(titleField);
        card.add(techLbl); card.add(techField);
        card.add(durationLbl); card.add(durationField);
        card.add(descLbl); card.add(descScroll);
        card.add(listLbl); card.add(listScroll);
        card.add(addBtn); card.add(updateBtn); card.add(deleteBtn); card.add(backBtn);

        bg.add(card);
        add(bg);

        loadExistingProjects();

        addBtn.addActionListener(e -> addProject());
        updateBtn.addActionListener(e -> updateProject());
        deleteBtn.addActionListener(e -> deleteProject());
        backBtn.addActionListener(e -> { dispose(); new DashboardPage(); });

        projectList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                loadProjectIntoForm();
            }
        });

        setVisible(true);
    }

    private void loadExistingProjects() {
        for (ResumeData.Project p : AppState.getResumeData().getProjects()) {
            projectListModel.addElement(p.title);
        }
    }

    private void addProject() {
        String title = titleField.getText().trim();
        String tech = techField.getText().trim();
        String duration = durationField.getText().trim();
        String desc = descArea.getText().trim();

        if (title.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Title cannot be empty!");
            return;
        }

        ResumeData.Project p = new ResumeData.Project(title, desc, tech, duration);
        AppState.getResumeData().addProject(p);
        projectListModel.addElement(title);
        clearForm();
        JOptionPane.showMessageDialog(this, "Project Added!");
    }

    private void updateProject() {
        if (selectedIndex == -1) {
            JOptionPane.showMessageDialog(this, "Select a project first!");
            return;
        }

        projectListModel.set(selectedIndex, titleField.getText().trim());
        ResumeData.Project p = AppState.getResumeData().getProjects().get(selectedIndex);
        p.title = titleField.getText().trim();
        p.tech = techField.getText().trim();
        p.duration = durationField.getText().trim();
        p.description = descArea.getText().trim();
        JOptionPane.showMessageDialog(this, "Project Updated!");
    }

    private void deleteProject() {
        if (selectedIndex == -1) {
            JOptionPane.showMessageDialog(this, "Select a project to delete!");
            return;
        }

        AppState.getResumeData().getProjects().remove(selectedIndex);
        projectListModel.remove(selectedIndex);
        clearForm();
        selectedIndex = -1;
        JOptionPane.showMessageDialog(this, "Deleted!");
    }

    private void loadProjectIntoForm() {
        selectedIndex = projectList.getSelectedIndex();
        if (selectedIndex == -1) return;
        ResumeData.Project p = AppState.getResumeData().getProjects().get(selectedIndex);
        titleField.setText(p.title);
        techField.setText(p.tech);
        durationField.setText(p.duration);
        descArea.setText(p.description);
    }

    private void clearForm() {
        titleField.setText("");
        techField.setText("");
        durationField.setText("");
        descArea.setText("");
    }

    private static class GradientPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.setPaint(new GradientPaint(0, 0, new Color(24, 40, 72), 0, getHeight(), new Color(44, 62, 92)));
            g2.fillRect(0, 0, getWidth(), getHeight());
        }
    }
}
