package utils.dataBaseFiles;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class createDBTest {

	
	public final static String JDBC_URLs = "jdbc:mysql://localhost/test?";
	public final static String JDBC_createURLs = "jdbc:mysql://localhost/?";
	static String password = "monkey!ISKingK1$ong";
	static String user = "root";
	public static void main(String[] args) {
		Connection connections = null;
		boolean dbexists = false;
		ResultSet resultSet = null;
		Statement statement = null; 
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
            // Setup the connection with the DB
            connections = DriverManager.getConnection(JDBC_URLs + "user=" + user + "&password=" + password);
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Problem with database will create new one");
			//e.printStackTrace();
			try {
				Class.forName("com.mysql.jdbc.Driver");
	            connections = DriverManager.getConnection(JDBC_createURLs + "user=" + user + "&password=" + password);
	            connections.createStatement().executeUpdate("create database test");
	            connections.close();
	            connections = DriverManager.getConnection(JDBC_URLs + "user=" + user + "&password=" + password);


			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            // Setup the connection with the DB
		}
		
		try {
			connections.createStatement().execute("CREATE TABLE Authors(authorID INT PRIMARY KEY, firstName VARCHAR(20),lastName varchar(20))");
		
		
			
		
			System.out.println("Created successfully");
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("failed table or already exists");
			dbexists = true;
			//e.printStackTrace();
		}
		
		if(dbexists == false){
		try {
			connections.createStatement().execute("INSERT INTO Authors VALUES(1,'deitel','Harvey'),(2,'deitel','paul'),(3,'savitch','walter')");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Added crap");
			e.printStackTrace();
		}
		}
		
		
		
		
		
		
		
		
		
		try {
	        //connections.createStatement().executeUpdate("DELETE FROM Authors where 1 = 1;");

			 statement = connections.createStatement();
			resultSet = statement.executeQuery(            
			      "SELECT * FROM Authors" );
		
	         // process query results
	         ResultSetMetaData metaData = resultSet.getMetaData();
	         int numberOfColumns = metaData.getColumnCount();     
	         System.out.println( "Authors Table of Books Database:\n" );
	         
	         for ( int i = 1; i <= numberOfColumns; i++ )
	            System.out.printf( "%-8s\t", metaData.getColumnName( i ) );
	         System.out.println();
	         
	         while ( resultSet.next() ) 
	         {
	            for ( int i = 1; i <= numberOfColumns; i++ )
	               System.out.printf( "%-8s\t", resultSet.getObject( i ) );
	            System.out.println();
	         } // end while
	         
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	         
		
		try {
			resultSet.close();
		
		statement.close();
		connections.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
