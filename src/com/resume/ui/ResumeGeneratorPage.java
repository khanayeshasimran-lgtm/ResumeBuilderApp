package com.resume.ui;

import com.resume.data.AppState;
import com.resume.data.ResumeData;

import javax.swing.*;
import java.awt.*;
import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class ResumeGeneratorPage extends JFrame {

    JComboBox<String> templateBox;
    JTextArea previewArea;
    JLabel photoPreview;

    JButton generateBtn, pdfBtn, backBtn;

    public ResumeGeneratorPage() {

        setTitle("Resume Generator");
        setSize(950, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setIconImage(new ImageIcon("assets/app_icon.png").getImage());

        JPanel bg = new GradientPanel();
        bg.setLayout(null);

        JPanel card = new JPanel(null);
        card.setBackground(Color.WHITE);
        card.setBounds(40, 40, 860, 600);
        card.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        JLabel heading = new JLabel("Resume Generator");
        heading.setFont(new Font("SansSerif", Font.BOLD, 28));
        heading.setBounds(30, 20, 400, 40);

        photoPreview = new JLabel();
        photoPreview.setBounds(30, 80, 120, 120);
        photoPreview.setOpaque(true);
        photoPreview.setBackground(new Color(230, 230, 230));
        photoPreview.setHorizontalAlignment(JLabel.CENTER);
        photoPreview.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        loadPhotoPreview();

        JLabel templateLabel = new JLabel("Template:");
        templateLabel.setBounds(170, 120, 150, 25);

        templateBox = new JComboBox<>(new String[]{"Minimal", "Clean", "Compact"});
        templateBox.setBounds(240, 115, 200, 35);

        JLabel previewLabel = new JLabel("Preview:");
        previewLabel.setBounds(30, 220, 150, 25);

        previewArea = new JTextArea();
        previewArea.setEditable(false);
        previewArea.setFont(new Font("Monospaced", Font.PLAIN, 14));

        JScrollPane scroll = new JScrollPane(previewArea);
        scroll.setBounds(30, 250, 800, 280);

        generateBtn = new JButton("Generate Preview");
        generateBtn.setBounds(350, 540, 160, 35);
        generateBtn.setBackground(new Color(46, 62, 92));
        generateBtn.setForeground(Color.BLACK); // updated

        pdfBtn = new JButton("Download PDF");
        pdfBtn.setBounds(520, 540, 160, 35);
        pdfBtn.setBackground(new Color(30, 144, 255));
        pdfBtn.setForeground(Color.BLACK); // updated

        backBtn = new JButton("Back");
        backBtn.setBounds(690, 540, 120, 35);
        backBtn.setBackground(new Color(70, 70, 70));
        backBtn.setForeground(Color.BLACK); // updated

        card.add(heading);
        card.add(photoPreview);
        card.add(templateLabel);
        card.add(templateBox);
        card.add(previewLabel);
        card.add(scroll);
        card.add(generateBtn);
        card.add(pdfBtn);
        card.add(backBtn);

        bg.add(card);
        add(bg);

        addListeners();
        setVisible(true);
    }

    private void loadPhotoPreview() {
        String path = AppState.getResumeData().getPhotoPath();
        if (path == null || path.trim().isEmpty()) {
            photoPreview.setText("No Photo");
            return;
        }
        ImageIcon icon = new ImageIcon(path);
        Image img = icon.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
        photoPreview.setIcon(new ImageIcon(img));
        photoPreview.setText("");
    }

    private void addListeners() {
        generateBtn.addActionListener(e ->
                previewArea.setText(generateResume()));

        pdfBtn.addActionListener(e -> {
            try {
                generatePDF();
                JOptionPane.showMessageDialog(this,
                        "PDF generated successfully!\nSaved as resume.pdf");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        backBtn.addActionListener(e -> {
            dispose();
            new DashboardPage();
        });
    }

    private String orDefault(String x, String def) {
        return (x != null && !x.trim().isEmpty()) ? x : def;
    }

    private String generateResume() {
        ResumeData rd = AppState.getResumeData();
        StringBuilder projects = new StringBuilder();
        for (ResumeData.Project p : rd.getProjects()) {
            projects.append(p.title)
                    .append(" (").append(orDefault(p.duration, "N/A")).append(")\n")
                    .append("Tech: ").append(orDefault(p.tech, "N/A")).append("\n")
                    .append(orDefault(p.description, "No description")).append("\n\n");
        }

        StringBuilder certs = new StringBuilder();
        for (String c : rd.getCertifications()) {
            certs.append("\u2022 ").append(c).append("\n");
        }

        return "----------------------------\n" +
                "         RESUME PREVIEW\n" +
                "----------------------------\n\n" +

                rd.getFullName() + "\n" +
                rd.getEmail() + " | " + rd.getPhone() + "\n" +
                rd.getLinkedin() + " | " + rd.getGithub() + "\n\n" +

                "SUMMARY\n" +
                orDefault(rd.getSummary(), "No summary provided.") + "\n\n" +

                "EDUCATION\n" +
                rd.getDegree() + " - " + rd.getBranch() + "\n" +
                rd.getCollege() + "\n" +
                rd.getStartYear() + " - " + rd.getEndYear() +
                " | CGPA: " + rd.getCgpa() + "\n\n" +

                "SKILLS\n" +
                orDefault(rd.getSkills(), "No skills added.") + "\n\n" +

                "EXPERIENCE\n" +
                rd.getExpTitle() + "\n" +
                rd.getExpCompany() + " | " + rd.getExpDuration() + "\n" +
                rd.getExpDescription() + "\n\n" +

                "PROJECTS\n" +
                (projects.length() == 0 ? "No projects added.\n" : projects.toString()) + "\n" +

                "CERTIFICATIONS\n" +
                (certs.length() == 0 ? "No certifications added.\n" : certs.toString());
    }

    private void generatePDF() throws Exception {
        ResumeData rd = AppState.getResumeData();

        Document doc = new Document();
        PdfWriter.getInstance(doc, new FileOutputStream("resume.pdf"));
        doc.open();

        com.itextpdf.text.Image img = null;
        if (rd.getPhotoPath() != null && !rd.getPhotoPath().isEmpty()) {
            img = com.itextpdf.text.Image.getInstance(rd.getPhotoPath());
            img.scaleToFit(120, 120);
            img.setAlignment(com.itextpdf.text.Image.ALIGN_LEFT);
            doc.add(img);
        }

        com.itextpdf.text.Font titleFont = new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, 20, com.itextpdf.text.Font.BOLD);
        com.itextpdf.text.Font headerFont = new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, 14, com.itextpdf.text.Font.BOLD);
        com.itextpdf.text.Font normalFont = new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, 12);

        doc.add(new Paragraph(rd.getFullName(), titleFont));
        doc.add(new Paragraph(rd.getEmail() + " | " + rd.getPhone(), normalFont));
        doc.add(new Paragraph(rd.getLinkedin() + " | " + rd.getGithub(), normalFont));
        doc.add(new Paragraph("\n"));

        doc.add(new Paragraph("SUMMARY", headerFont));
        doc.add(new Paragraph(orDefault(rd.getSummary(), ""), normalFont));
        doc.add(new Paragraph("\n"));

        doc.add(new Paragraph("EDUCATION", headerFont));
        doc.add(new Paragraph(rd.getDegree() + " - " + rd.getBranch() + "\n" +
                rd.getCollege() + "\n" +
                rd.getStartYear() + " - " + rd.getEndYear() +
                " | CGPA: " + rd.getCgpa(), normalFont));
        doc.add(new Paragraph("\n"));

        doc.add(new Paragraph("SKILLS", headerFont));
        doc.add(new Paragraph(orDefault(rd.getSkills(), ""), normalFont));
        doc.add(new Paragraph("\n"));

        doc.add(new Paragraph("EXPERIENCE", headerFont));
        doc.add(new Paragraph(rd.getExpTitle() + "\n" +
                rd.getExpCompany() + " | " + rd.getExpDuration() + "\n" +
                rd.getExpDescription(), normalFont));
        doc.add(new Paragraph("\n"));

        doc.add(new Paragraph("PROJECTS", headerFont));
        if (rd.getProjects().isEmpty()) {
            doc.add(new Paragraph("No projects added.", normalFont));
        } else {
            for (ResumeData.Project p : rd.getProjects()) {
                doc.add(new Paragraph(p.title + " (" + p.duration + ")", normalFont));
                doc.add(new Paragraph("Tech: " + p.tech, normalFont));
                doc.add(new Paragraph(p.description, normalFont));
                doc.add(new Paragraph("\n"));
            }
        }

        doc.add(new Paragraph("CERTIFICATIONS", headerFont));
        if (rd.getCertifications().isEmpty()) {
            doc.add(new Paragraph("No certifications added.", normalFont));
        } else {
            for (String c : rd.getCertifications()) {
                doc.add(new Paragraph("\u2022 " + c, normalFont));
            }
        }

        doc.close();
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
