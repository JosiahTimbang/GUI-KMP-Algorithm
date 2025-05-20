import javax.swing.*;
import java.awt.*;
import java.util.*;

public class App {
    private static JTextArea resultArea;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Redundant Word Checker");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);
            frame.setLayout(new BorderLayout());

            // Header Panel
            JPanel headerPanel = new JPanel();
            headerPanel.setBackground(new Color(126, 192, 238));
            headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
            JLabel titleLabel = new JLabel("Redundant Word Checker");
            titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
            titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            JLabel subtitleLabel = new JLabel("A web based application used to detect redundant words in texts using the KMP algorithm");
            subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            headerPanel.add(Box.createVerticalStrut(20));
            headerPanel.add(titleLabel);
            headerPanel.add(Box.createVerticalStrut(10));
            headerPanel.add(subtitleLabel);
            headerPanel.add(Box.createVerticalStrut(20));

            // Main Panel
            JPanel mainPanel = new JPanel(new GridLayout(2, 1, 0, 10));
            mainPanel.setBackground(Color.WHITE);
            mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

            // Left Panel
            JPanel leftPanel = new JPanel(new BorderLayout());
            leftPanel.setBackground(Color.WHITE);
            JLabel inputLabel = new JLabel("To detect redundant words, type or paste your text here then click \"Detect.\"");
            inputLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            JTextArea inputArea = new JTextArea(10, 40);
            inputArea.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            inputArea.setLineWrap(true);
            inputArea.setWrapStyleWord(true);
            JScrollPane inputScrollPane = new JScrollPane(inputArea);
            inputScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
            inputScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            JButton detectButton = new JButton("✪ Detect");
            detectButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
            detectButton.setBackground(new Color(33, 150, 243));
            detectButton.setForeground(Color.WHITE);
            detectButton.setFocusPainted(false);
            detectButton.setBorderPainted(false);
            detectButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            detectButton.addActionListener(e -> {
                String input = inputArea.getText();
                Map<String, Integer> redundant = RedundantWordChecker.findRedundantWords(input);
                StringBuilder result = new StringBuilder();
                if (redundant.isEmpty()) {
                    result.append("No redundant words found.");
                } else {
                    result.append("Redundant Words:\n");
                    for (Map.Entry<String, Integer> entry : redundant.entrySet()) {
                        result.append(entry.getKey()).append(" - ").append(entry.getValue()).append(" times\n");
                    }
                }
                resultArea.setText(result.toString());
            });
            leftPanel.add(inputLabel, BorderLayout.NORTH);
            leftPanel.add(inputScrollPane, BorderLayout.CENTER);
            leftPanel.add(detectButton, BorderLayout.SOUTH);

            // Right Panel
            JPanel rightPanel = new JPanel(new BorderLayout());
            rightPanel.setBackground(Color.WHITE);
            JLabel resultLabel = new JLabel("Results:");
            resultLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
            resultArea = new JTextArea(10, 40);
            resultArea.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            resultArea.setEditable(false);
            resultArea.setLineWrap(true);
            resultArea.setWrapStyleWord(true);
            JScrollPane resultScrollPane = new JScrollPane(resultArea);
            resultScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
            resultScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            rightPanel.add(resultLabel, BorderLayout.NORTH);
            rightPanel.add(resultScrollPane, BorderLayout.CENTER);

            mainPanel.add(leftPanel);
            mainPanel.add(rightPanel);

            frame.add(headerPanel, BorderLayout.NORTH);
            frame.add(mainPanel, BorderLayout.CENTER);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
