package com.resume.ui;

import com.resume.data.AppState;
import com.resume.data.ResumeData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;

public class PersonalInfoPage extends JFrame {

    JTextField nameField, emailField, phoneField, dobField, linkedinField, githubField;
    JTextArea addressArea, summaryArea;
    JRadioButton maleBtn, femaleBtn, otherBtn;
    JLabel photoPreview;
    String photoPath = null;

    public PersonalInfoPage() {

        setTitle("Personal Information");
        setSize(950, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setIconImage(new ImageIcon("assets/app_icon.png").getImage());

        JPanel bg = new GradientPanel();
        bg.setLayout(null);

        JPanel card = new JPanel(null);
        card.setBackground(Color.WHITE);
        card.setBounds(80, 40, 780, 630); // ✅ Increased height
        card.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));

        JLabel heading = new JLabel("Personal Details");
        heading.setFont(new Font("SansSerif", Font.BOLD, 26));
        heading.setBounds(30, 20, 400, 30);

        JButton uploadBtn = new JButton("Upload Photo");
        uploadBtn.setBounds(550, 20, 150, 30);
        uploadBtn.setBackground(new Color(46, 62, 92));
        uploadBtn.setForeground(Color.BLACK);

        photoPreview = new JLabel();
        photoPreview.setBounds(550, 60, 150, 150);
        photoPreview.setOpaque(true);
        photoPreview.setBackground(new Color(230, 230, 230));
        photoPreview.setHorizontalAlignment(JLabel.CENTER);
        photoPreview.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        JLabel nameLabel = new JLabel("Full Name:");
        nameLabel.setBounds(30, 70, 150, 25);
        nameField = new JTextField();
        nameField.setBounds(160, 70, 300, 30);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(30, 120, 150, 25);
        emailField = new JTextField();
        emailField.setBounds(160, 120, 300, 30);

        JLabel phoneLabel = new JLabel("Phone:");
        phoneLabel.setBounds(30, 170, 150, 25);
        phoneField = new JTextField();
        phoneField.setBounds(160, 170, 300, 30);

        JLabel dobLabel = new JLabel("Date of Birth:");
        dobLabel.setBounds(30, 220, 150, 25);
        dobField = new JTextField();
        dobField.setBounds(160, 220, 300, 30);

        JLabel genderLabel = new JLabel("Gender:");
        genderLabel.setBounds(30, 270, 150, 25);

        maleBtn = new JRadioButton("Male");
        femaleBtn = new JRadioButton("Female");
        otherBtn = new JRadioButton("Other");

        maleBtn.setBounds(160, 270, 80, 25);
        femaleBtn.setBounds(250, 270, 100, 25);
        otherBtn.setBounds(360, 270, 100, 25);

        maleBtn.setOpaque(false);
        femaleBtn.setOpaque(false);
        otherBtn.setOpaque(false);

        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleBtn);
        genderGroup.add(femaleBtn);
        genderGroup.add(otherBtn);

        JLabel addressLabel = new JLabel("Address:");
        addressLabel.setBounds(30, 320, 150, 25);

        addressArea = new JTextArea();
        addressArea.setLineWrap(true);
        addressArea.setWrapStyleWord(true);

        JScrollPane addressScroll = new JScrollPane(addressArea);
        addressScroll.setBounds(160, 320, 300, 60);

        JLabel linkedinLabel = new JLabel("LinkedIn:");
        linkedinLabel.setBounds(30, 400, 150, 25);
        linkedinField = new JTextField();
        linkedinField.setBounds(160, 400, 300, 30);

        JLabel githubLabel = new JLabel("GitHub:");
        githubLabel.setBounds(30, 450, 150, 25);
        githubField = new JTextField();
        githubField.setBounds(160, 450, 300, 30);

        JLabel summaryLabel = new JLabel("Summary:");
        summaryLabel.setBounds(30, 500, 150, 25);

        summaryArea = new JTextArea();
        summaryArea.setLineWrap(true);
        summaryArea.setWrapStyleWord(true);

        JScrollPane summaryScroll = new JScrollPane(summaryArea);
        summaryScroll.setBounds(160, 500, 540, 60);

        JButton saveBtn = new JButton("Save");
        saveBtn.setBounds(300, 580, 120, 35);
        saveBtn.setBackground(new Color(46, 62, 92));
        saveBtn.setForeground(Color.BLACK);

        JButton backBtn = new JButton("Back");
        backBtn.setBounds(440, 580, 120, 35);
        backBtn.setBackground(new Color(70, 70, 70));
        backBtn.setForeground(Color.BLACK);

        card.add(heading);
        card.add(uploadBtn);
        card.add(photoPreview);

        card.add(nameLabel); card.add(nameField);
        card.add(emailLabel); card.add(emailField);
        card.add(phoneLabel); card.add(phoneField);
        card.add(dobLabel); card.add(dobField);

        card.add(genderLabel);
        card.add(maleBtn); card.add(femaleBtn); card.add(otherBtn);

        card.add(addressLabel); card.add(addressScroll);
        card.add(linkedinLabel); card.add(linkedinField);
        card.add(githubLabel); card.add(githubField);

        card.add(summaryLabel); card.add(summaryScroll);

        card.add(saveBtn); card.add(backBtn);

        bg.add(card);
        add(bg);

        uploadBtn.addActionListener(this::uploadPhoto);
        saveBtn.addActionListener(e -> saveData());
        backBtn.addActionListener(e -> { dispose(); new DashboardPage(); });

        setVisible(true);
    }

    private void uploadPhoto(ActionEvent e) {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Select Profile Photo");

        int result = chooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File imgFile = chooser.getSelectedFile();
            photoPath = imgFile.getAbsolutePath();
            AppState.getResumeData().setPhotoPath(photoPath);

            ImageIcon icon = new ImageIcon(photoPath);
            Image img = icon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
            photoPreview.setIcon(new ImageIcon(img));
            photoPreview.setText("");
        }
    }

    private void saveData() {
        ResumeData data = AppState.getResumeData();

        data.setFullName(nameField.getText().trim());
        data.setEmail(emailField.getText().trim());
        data.setPhone(phoneField.getText().trim());
        data.setDob(dobField.getText().trim());
        data.setAddress(addressArea.getText().trim());
        data.setLinkedin(linkedinField.getText().trim());
        data.setGithub(githubField.getText().trim());
        data.setSummary(summaryArea.getText().trim());

        if (maleBtn.isSelected()) data.setGender("Male");
        else if (femaleBtn.isSelected()) data.setGender("Female");
        else if (otherBtn.isSelected()) data.setGender("Other");

        JOptionPane.showMessageDialog(this, "Personal Info Saved!");
    }

    private static class GradientPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.setPaint(new GradientPaint(
                    0, 0, new Color(24, 40, 72),
                    0, getHeight(), new Color(44, 62, 92)
            ));
            g2.fillRect(0, 0, getWidth(), getHeight());
        }
    }
}
