//Name: Valina Mendonca
//Register No: 230970084
//Section: B

import javax.swing.*;
import java.awt.*;

public class Start extends JFrame {
    public Start() {
        setTitle("Quiz Starter Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(465, 379);
        getContentPane().setLayout(new BorderLayout());

        // Details
        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(null);
        JLabel titleLabel = new JLabel("Quiz");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBounds(0, 0, 451, 48);
        titleLabel.setForeground(new Color(44, 62, 80));
        titleLabel.setBackground(new Color(255, 255, 255));
        titleLabel.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 24));
        detailsPanel.add(titleLabel);

        //Description
        JTextArea descriptionArea = new JTextArea("Description: Java Miniproject");
        descriptionArea.setBounds(0, 48, 451, 28);
        descriptionArea.setForeground(new Color(255, 255, 255));
        descriptionArea.setEditable(false);
        descriptionArea.setBackground(new Color(44, 62, 80));
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        detailsPanel.add(descriptionArea);

        //Author
        JLabel authorLabel = new JLabel("Author: Valina Mendonca");
        authorLabel.setBounds(0, 287, 441, 24);
        authorLabel.setFont(new Font("Arial", Font.ITALIC, 12));
        authorLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        detailsPanel.add(authorLabel);

        getContentPane().add(detailsPanel, BorderLayout.CENTER);
        
        //Image
        ImageIcon originalIcon = new ImageIcon("D:\\Miniproject\\Test1\\img\\quiz.jpg");
        Image image = originalIcon.getImage(); // Transform it into an Image
        Image newImage = image.getScaledInstance(500, 200,  java.awt.Image.SCALE_SMOOTH); // Scale the image to fit a 300x200 area
        ImageIcon resizedIcon = new ImageIcon(newImage);  // Transform it back to an ImageIcon
        JLabel label = new JLabel(resizedIcon);
        label.setBounds(0, 77, 451, 200);
        detailsPanel.add(label);
        detailsPanel.setOpaque(false);

        // Start Button
        JButton startButton = new JButton("Start Quiz");
        startButton.setFont(new Font("Tahoma", Font.BOLD, 13));
        startButton.setBounds(0, 313, 451, 29);
        detailsPanel.add(startButton);
        startButton.setBackground(new Color(44, 62, 80));
        startButton.setForeground(Color.WHITE);
        startButton.setBorderPainted(false);
        startButton.setFocusPainted(false);
        startButton.addActionListener(e -> {
            // Add action to start the quiz
        	dispose();
        	LoginForm lf=new LoginForm();
        	lf.setVisible(true);
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Start());
    }
}
