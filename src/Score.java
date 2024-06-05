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

public class Score extends JFrame {
	String username;
	int score;
    
    public Score(String user, int score) {
    	username=user;
    	this.score=score;
    	
        setTitle("Score");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());

        JPanel formPanel = new JPanel();
        formPanel.setForeground(new Color(255, 255, 255));
        formPanel.setLayout(null);
        formPanel.setBackground(new Color(44, 62, 80));

        //Score
        JLabel titleLabel = new JLabel("Your Score: "+score+"/5");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setBounds(63, 80, 200, 30);
        formPanel.add(titleLabel);

        getContentPane().add(formPanel, BorderLayout.CENTER);
        
        //Register
        JLabel lblRegisterNo = new JLabel("Register No.: "+username);
        lblRegisterNo.setForeground(Color.WHITE);
        lblRegisterNo.setFont(new Font("Arial", Font.BOLD, 20));
        lblRegisterNo.setBounds(63, 42, 284, 30);
        formPanel.add(lblRegisterNo);
        
        //Logout Message
        JLabel lblNewLabel = new JLabel("You have been successfully logged out!");
        lblNewLabel.setForeground(new Color(255, 255, 255));
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblNewLabel.setBounds(75, 199, 272, 38);
        formPanel.add(lblNewLabel);
        
        //Feedback according to score
        String feedback="";
        if(score==5)
        	feedback="Excellent work!";
        else if(score>=3)
        	feedback="Good effort, but there's room for improvement!";
        else
        	feedback="Try harder next time!";
        
        JLabel lblNewLabel_1 = new JLabel(feedback);
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
        lblNewLabel_1.setForeground(new Color(255, 255, 255));
        lblNewLabel_1.setBounds(63, 145, 300, 30);
        formPanel.add(lblNewLabel_1);

        setVisible(true);
    }
}
