//Name: Valina Mendonca
//Register No: 230970084
//Section: B
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Quiz extends JFrame {
    private JLabel titleLabel;
    private JRadioButton[][] options;
    private JButton submitButton;
    private String username;

    Connection conn=null;
    
    public Quiz(String username) {
    	//establish database connection
    	conn=MySQLConnection.dbConnector();
    	
    	this.username=username;
    	
        setTitle("Quiz Form");
        setSize(500, 800);
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);        
        JPanel formPanel = new JPanel();
        formPanel.setLayout(null);
        formPanel.setPreferredSize(new Dimension(500, 1100));
        formPanel.setBackground(new Color(52, 73, 94));

        titleLabel = new JLabel("Quiz Form");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBounds(167, 30, 150, 30);
        formPanel.add(titleLabel);

        String[] questions = new String[5]; 
        
        try {
        	//getting all questions and options
	    	String query="select * from quiz;";
	    	PreparedStatement stmt = conn.prepareStatement(query);		    
		    ResultSet resultSet = stmt.executeQuery();
	    	ButtonGroup[] buttonGroups = new ButtonGroup[5];
            options = new JRadioButton[5][4];
            int questionIndex = 0;
            while (resultSet.next() && questionIndex < 5) {
                String question = resultSet.getString("question");
                String op1 = resultSet.getString("op1");
                String op2 = resultSet.getString("op2");
                String op3 = resultSet.getString("op3");
                String op4 = resultSet.getString("op4");

                questions[questionIndex] = question;
                
                JLabel questionLabel = new JLabel(question);
                questionLabel.setForeground(Color.WHITE);
                questionLabel.setFont(new Font("Arial", Font.PLAIN, 14));
                questionLabel.setBounds(50, 110 + questionIndex * 180, 400, 20);
                formPanel.add(questionLabel);

                buttonGroups[questionIndex] = new ButtonGroup();
                for (int j = 0; j < 4; j++) {
                    options[questionIndex][j] = new JRadioButton(j == 0 ? op1 : j == 1 ? op2 : j == 2 ? op3 : op4);
                    options[questionIndex][j].setForeground(Color.WHITE);
                    options[questionIndex][j].setFont(new Font("Arial", Font.PLAIN, 14));
                    options[questionIndex][j].setBounds(70, 150 + questionIndex * 180 + j * 30, 200, 20);
                    options[questionIndex][j].setOpaque(false);
                    buttonGroups[questionIndex].add(options[questionIndex][j]);
                    formPanel.add(options[questionIndex][j]);
                }

                questionIndex++;
            }
        	resultSet.close();
	    	stmt.close();
	    }
	    catch(Exception e1) {
			System.out.println(e1);
		}
        
        submitButton = new JButton("Submit");
        submitButton.setBounds(201, 1025, 100, 40);
        submitButton.setBackground(new Color(34, 167, 240));
        submitButton.setForeground(Color.WHITE);
        submitButton.setBorderPainted(false);
        submitButton.setFocusPainted(false);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
            	//validating the answers to calculate the score
            	int score = 0;
                for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < 4; j++) {
                        if (options[i][j].isSelected()) {
                            String selectedOption = options[i][j].getText();
                            try {
                                String query = "SELECT ans FROM quiz WHERE question = ?";
                                PreparedStatement stmt = conn.prepareStatement(query);
                                stmt.setString(1, questions[i]);
                                ResultSet resultSet = stmt.executeQuery();
                                if (resultSet.next()) {
                                    String correctAnswer = resultSet.getString("ans");
                                    if (selectedOption.equals(correctAnswer)) {
                                        score++;
                                    }
                                }
                                resultSet.close();
                                stmt.close();
                            } catch (Exception ex) {
                                System.out.println(ex);
                            }
                        }
                    }
                }
                
                System.out.println("Score: " + score);
                
                //updating score to the database
                try {                	
                	String insertQuery="UPDATE `student` SET `score`= ? WHERE `username` = ?;";                	
                    PreparedStatement insertStmt = conn.prepareStatement(insertQuery);                    
                    insertStmt.setString(2, username);
                    insertStmt.setInt(1, score); 
                    insertStmt.executeUpdate();
                    insertStmt.close();
                    
                    dispose();
                    
                    //displaying the score in the new frame
                    Score s=new Score(username, score);
		    		s.setVisible(true);
                }
                catch(Exception e2) {
                	System.out.println(e2);
                }
            }
        });
        formPanel.add(submitButton);

        setVisible(true);
       
        JScrollPane scrollPane = new JScrollPane(formPanel);
       
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        
        getContentPane().add(scrollPane, BorderLayout.CENTER);
    }
}
