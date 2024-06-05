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

public class LoginForm extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    Connection conn=null;
    
    public LoginForm() {
    	//establish database connection
    	conn=MySQLConnection.dbConnector();
    	
        setTitle("Login Form");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel formPanel = new JPanel();
        formPanel.setLayout(null);
        formPanel.setBackground(new Color(44, 62, 80));

        JLabel titleLabel = new JLabel("Login Form");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBounds(140, 20, 200, 30);
        formPanel.add(titleLabel);

        //Username Field
        usernameField = new JTextField();
        usernameField.setBounds(50, 80, 300, 30);
        usernameField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.WHITE));
        usernameField.setOpaque(false);
        usernameField.setForeground(Color.WHITE);
        usernameField.setFont(new Font("Arial", Font.PLAIN, 16));
        usernameField.setCaretColor(Color.WHITE);
        usernameField.setText("Username");
        formPanel.add(usernameField);

        //Password Field
        passwordField = new JPasswordField();
        passwordField.setBounds(50, 130, 300, 30);
        passwordField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.WHITE));
        passwordField.setOpaque(false);
        passwordField.setForeground(Color.WHITE);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 14));
        passwordField.setCaretColor(Color.WHITE);
        passwordField.setText("Password");
        formPanel.add(passwordField);

        //Login Button
        loginButton = new JButton("Login");
        loginButton.setBounds(150, 180, 100, 40);
        loginButton.setBackground(new Color(34, 167, 240));
        loginButton.setForeground(Color.WHITE);
        loginButton.setBorderPainted(false);
        loginButton.setFocusPainted(false);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                
                try {
                	//checking if username is valid
			    	String query="select * from student where username=?;";
			    	PreparedStatement stmt = conn.prepareStatement(query);
				    stmt.setString(1, username);
				    ResultSet rs = stmt.executeQuery();
				    
				    if(rs.next()) {
				    	//checking if password is correct
				    	query="select * from student where username=? and password=?;";
				    	stmt = conn.prepareStatement(query);
					    stmt.setString(1, username);
					    stmt.setString(2, password);
					    rs = stmt.executeQuery();
					    
				    	if(rs.next()) {
				    		//redirecting to quiz page			    		
				    		dispose();
				    		Quiz q=new Quiz(username);
				    		q.setVisible(true);
				    	}
				    	else {
				    		JOptionPane.showMessageDialog(null, "Incorrect Password!");
				    	}
				    }
				    else {
				    	JOptionPane.showMessageDialog(null, "Invalid Username!");
				    }
				    rs.close();
			    	stmt.close();			    	
			    }
			    catch(Exception e1) {
					System.out.println(e1);
				}
            }
        });
        formPanel.add(loginButton);

        add(formPanel, BorderLayout.CENTER);

        setVisible(true);
    }
}
