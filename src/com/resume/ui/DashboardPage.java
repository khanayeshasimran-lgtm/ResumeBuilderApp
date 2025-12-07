package com.resume.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DashboardPage extends JFrame {

    public DashboardPage() {
        setTitle("Resume Builder - Dashboard");
        setSize(900, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 🔹 APP ICON
        setIconImage(Toolkit.getDefaultToolkit().getImage("assets/app_icon.png"));

        // Gradient background panel
        JPanel background = new GradientPanel();
        background.setLayout(new BorderLayout());
        background.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Top bar
        JLabel title = new JLabel("Resume Builder", JLabel.LEFT);
        title.setFont(new Font("SansSerif", Font.BOLD, 26));
        title.setForeground(Color.WHITE);

        JLabel subtitle = new JLabel("Dashboard");
        subtitle.setFont(new Font("SansSerif", Font.PLAIN, 16));
        subtitle.setForeground(new Color(220, 220, 220));

        JPanel topBar = new JPanel(new BorderLayout());
        topBar.setOpaque(false);

        JPanel textWrap = new JPanel();
        textWrap.setOpaque(false);
        textWrap.setLayout(new BoxLayout(textWrap, BoxLayout.Y_AXIS));
        textWrap.add(title);
        textWrap.add(subtitle);

        topBar.add(textWrap, BorderLayout.WEST);
        background.add(topBar, BorderLayout.NORTH);

        // Tiles area
        JPanel tilesPanel = new JPanel();
        tilesPanel.setOpaque(false);
        tilesPanel.setLayout(new GridLayout(2, 4, 20, 20));

        // Add 8 tiles
        tilesPanel.add(createTile("Personal Info", "👤", new Color(255, 99, 132)));
        tilesPanel.add(createTile("Education", "🎓", new Color(54, 162, 235)));
        tilesPanel.add(createTile("Skills", "⚙️", new Color(255, 206, 86)));
        tilesPanel.add(createTile("Experience", "💼", new Color(75, 192, 192)));

        tilesPanel.add(createTile("Projects", "📁", new Color(153, 102, 255)));
        tilesPanel.add(createTile("Certifications", "📜", new Color(255, 159, 64)));
        tilesPanel.add(createTile("Summary", "📝", new Color(46, 204, 113)));
        tilesPanel.add(createTile("Generate Resume", "📄", new Color(52, 152, 219)));

        background.add(tilesPanel, BorderLayout.CENTER);
        add(background);

        setVisible(true);
    }

    // Create tile with hover and click listener
    private JPanel createTile(String text, String icon, Color baseColor) {

        JPanel tile = new JPanel(new BorderLayout());
        tile.setPreferredSize(new Dimension(160, 120));
        tile.setBackground(baseColor);
        tile.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        tile.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel iconLabel = new JLabel(icon);
        iconLabel.setFont(new Font("SansSerif", Font.PLAIN, 30));
        iconLabel.setHorizontalAlignment(JLabel.LEFT);
        iconLabel.setForeground(Color.WHITE);

        JLabel textLabel = new JLabel("<html><b>" + text + "</b></html>");
        textLabel.setFont(new Font("SansSerif", Font.PLAIN, 15));
        textLabel.setForeground(Color.WHITE);

        JPanel inner = new JPanel(new BorderLayout());
        inner.setOpaque(false);
        inner.add(iconLabel, BorderLayout.NORTH);
        inner.add(textLabel, BorderLayout.SOUTH);

        tile.add(inner);

        Color hoverColor = baseColor.brighter();

        tile.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                tile.setBackground(hoverColor);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                tile.setBackground(baseColor);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                handleTileClick(text);
            }
        });

        return tile;
    }

    // Navigation handler
    private void handleTileClick(String tileName) {

        switch (tileName) {

            case "Personal Info":
                dispose();
                new PersonalInfoPage();
                break;

            case "Education":
                dispose();
                new EducationPage();
                break;

            case "Skills":
                dispose();
                new SkillsPage();
                break;

            case "Experience":
                dispose();
                new ExperiencePage();
                break;

            case "Projects":
                dispose();
                new ProjectsPage();
                break;

            case "Certifications":
                dispose();
                new CertificationsPage();
                break;

            case "Summary":
                dispose();
                new SummaryPage();
                break;

            case "Generate Resume":
                dispose();
                new ResumeGeneratorPage();
                break;

            default:
                JOptionPane.showMessageDialog(this, tileName + " clicked");
        }
    }

    // Gradient background panel
    private static class GradientPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2d = (Graphics2D) g;
            g2d.setPaint(new GradientPaint(
                    0, 0, new Color(24, 40, 72),
                    0, getHeight(), new Color(44, 62, 92)
            ));
            g2d.fillRect(0, 0, getWidth(), getHeight());
        }
    }public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> new DashboardPage());
}

}
