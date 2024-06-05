//Name: Valina Mendonca
//Register No: 230970084
//Section: B
import java.sql.*;

public class MySQLConnection {	
	public static Connection dbConnector() {
		try {
			Connection conn;
			Statement stmt;
			
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/", "root", "");
            
            // Create the database if it doesn't exist
            stmt = conn.createStatement();
            String createDatabaseQuery = "CREATE DATABASE IF NOT EXISTS studentdb";
            stmt.executeUpdate(createDatabaseQuery);
            
			//connecting to database
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3307/studentdb", "root", "");
			System.out.println("Connected!");
			
			// Check if the table exists, if not, create it
            stmt = conn.createStatement();
            String createTableQuery = "CREATE TABLE IF NOT EXISTS student ("
                    + "username VARCHAR(50) NOT NULL PRIMARY KEY,"
                    + "password VARCHAR(50) NOT NULL,"
                    + "score INT(10) NULL"                 
                    + ")";
            stmt.executeUpdate(createTableQuery);
            String query = "CREATE TABLE IF NOT EXISTS quiz ("
                    + "id INT(10) AUTO_INCREMENT PRIMARY KEY,"
                    + "question VARCHAR(255) NOT NULL,"
                    + "op1 VARCHAR(255) NOT NULL,"
                    + "op2 VARCHAR(255) NOT NULL,"
                    + "op3 VARCHAR(255) NOT NULL,"
                    + "op4 VARCHAR(255) NOT NULL,"
                    + "ans VARCHAR(255) NOT NULL"
                    + ")";
            stmt.executeUpdate(query);
            stmt.close();
			return conn;
		}
		catch(Exception e) {
			System.out.print("Couldn't connect! "+e);
			return null;
		}
	}
}
