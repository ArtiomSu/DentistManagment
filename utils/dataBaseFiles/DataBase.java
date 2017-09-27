package utils.dataBaseFiles;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import utils.Hashes;

public class DataBase {

	
	public static final String JDBC_URL = "jdbc:mysql://localhost/databaseCollegeJava?";
	public final static String JDBC_createURLs = "jdbc:mysql://localhost/?";
	static String password = "a";
	static String user = "root";
	private Connection connections = null;
	private ResultSet resultSet = null;
	private Statement statement = null; 
	
	public ResultSet getResultSet() {
		return resultSet;
	}

	public void setResultSet(ResultSet resultSet) {
		this.resultSet = resultSet;
	}

	public Statement getStatement() {
		return statement;
	}

	public void setStatement(Statement statement) {
		this.statement = statement;
	}

	public DataBase(){
		
	}
	
	public DataBase(Connection con){
		connections = con;
	}
	
	public boolean checkifDataBaseExists(){
		boolean exists = true;
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
            // Setup the connection with the DB
            connections = DriverManager.getConnection(JDBC_URL + "user=" + user + "&password=" + password);
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("database not found");
			exists = false;
			e.printStackTrace();
		}
		
		
		return exists;
	}
	
	public boolean checkiftablesExists(){
		boolean exists = true;
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
            // Setup the connection with the DB
            connections = DriverManager.getConnection(JDBC_URL + "user=" + user + "&password=" + password);
            connections.createStatement().executeQuery("select * from Dentist, Invoice, Patient, Payment, Pprocedure;");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("some tables are missing");
			exists = false;
			
			
			
			
		}
		
		
		return exists;
	}
	
	public boolean createNewDB(){
		boolean successfull = true;
		try {
			Class.forName("com.mysql.jdbc.Driver");
            connections = DriverManager.getConnection(JDBC_createURLs + "user=" + user + "&password=" + password);
            connections.createStatement().executeUpdate("create database databaseCollegeJava");
            connections = DriverManager.getConnection(JDBC_URL + "user=" + user + "&password=" + password);

		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			successfull = false;
			e1.printStackTrace();
		}
		return successfull;
	}
	
	public boolean getConnection(){
		boolean successfull = true;
		if(connections == null){
		
			try {
				
				Class.forName("com.mysql.jdbc.Driver");
	            // Setup the connection with the DB
	            connections = DriverManager.getConnection(JDBC_URL + "user=" + user + "&password=" + password);
			} catch (SQLException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				System.out.println("Problem with database");
				e.printStackTrace();
			}
		}
		
		
		
		return successfull;
	}
	
	public boolean createTables(){
	
		boolean successfull = true;
		boolean connected = getConnection();
		
		if(connected){
			try {
				//tables need to be created individually since you can't create multiple tables in the same query 
				connections.createStatement().executeUpdate("CREATE TABLE Dentist("+
						"dentistID INT PRIMARY KEY AUTO_INCREMENT,"+
						"salt VARCHAR(64),"+
						"username VARCHAR(20),"+
						"password VARCHAR(64),"+
						"firstName VARCHAR(20),"+
						"lastName VARCHAR(20),"+
						"email VARCHAR(20),"+
						"address1 VARCHAR(20),"+
						"address2 VARCHAR(20),"+
						"address3 VARCHAR(20),"+
						"address4 VARCHAR(20),"+
						"telephone VARCHAR(20)"+
						");");
			
				connections.createStatement().executeUpdate("CREATE TABLE Patient("+
						"patientID INT PRIMARY KEY AUTO_INCREMENT,"+
						"firstName VARCHAR(20),"+
						"lastName VARCHAR(20),"+
						"email VARCHAR(20),"+
						"address1 VARCHAR(20),"+
						"address2 VARCHAR(20),"+
						"address3 VARCHAR(20),"+
						"address4 VARCHAR(20),"+
						"telephone VARCHAR(20),"+
						"balance VARCHAR(20),"+
						"dentistID INT"+
						");");
				
				connections.createStatement().executeUpdate("CREATE TABLE Invoice("+
						"invoiceID INT PRIMARY KEY AUTO_INCREMENT,"+
						"invoiceAmnt VARCHAR(20),"+
						"invoiceLeftToPay VARCHAR(20),"+
						"date VARCHAR(64),"+
						"isPaid VARCHAR(5),"+
						"patientID INT"+
						");");
				
				connections.createStatement().executeUpdate("CREATE TABLE Payment("+
						"paymentID INT PRIMARY KEY AUTO_INCREMENT,"+
						"paymentAmnt VARCHAR(20),"+
						"paymentType VARCHAR(20),"+
						"date VARCHAR(64),"+
						"InvoiceID INT"+
						");");
				
				connections.createStatement().executeUpdate("CREATE TABLE Pprocedure("+
						"procedureID INT PRIMARY KEY AUTO_INCREMENT,"+
						"procName VARCHAR(20),"+
						"proc VARCHAR(20),"+
						"date VARCHAR(64),"+
						"InvoiceID INT"+
						");");
			
				
			
				System.out.println("Created successfully");
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("failed table or already exists");
				successfull = false;
				e.printStackTrace();
			}
			
			
			
			
		}
		else{
			successfull = false;
		}
		
		
		
		return successfull;
	}

	public Connection getConnections() {
		return connections;
	}

	public void setConnections(Connection connections) {
		this.connections = connections;
	}


	public int checkLogin(String username, String Password){
		int dentistID = -1;
		
		try {
			statement = connections.createStatement();
			resultSet = statement.executeQuery(            
				      "SELECT * FROM Dentist where username = '" + username + "';");
			  // process query results
	         //ResultSetMetaData metaData = resultSet.getMetaData();
	         //int numberOfColumns = metaData.getColumnCount();     
	        /* for ( int i = 1; i <= numberOfColumns; i++ )
		            System.out.printf( "%-8s\t", metaData.getColumnName( i ) );
		         System.out.println();
		         
		         while ( resultSet.next() ) 
		         {
		            for ( int i = 1; i <= numberOfColumns; i++ )
		               System.out.printf( "%-8s\t", resultSet.getObject( i ) );
		            System.out.println();
		         } // end while
		         */
		         //checks if there is data returned
		        if(!resultSet.isBeforeFirst()){
		        	return -1;
		        }
		        else{
		        	while(resultSet.next()){
		        	Hashes hash = new Hashes();
		        	String salt = resultSet.getString("salt");
		        	System.out.println("inside checklogin"+salt);
		        	int id = resultSet.getInt("dentistID");
		        	System.out.println("inside checklogin"+id);
		        	String storedPass = resultSet.getString("password");
		        	System.out.println("inside checklogin"+storedPass);

		        	boolean found = false;
		        	found = hash.compareHash(storedPass, Password, salt);
		        	if(found){
		        		dentistID = id;
		        	}
		        	}

		        }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
       
		
		
		return dentistID;
	}
	
	
	
	public boolean checkIfUserNameExists(String username){
		boolean exists = false;
		
		try {
			statement = connections.createStatement();
			resultSet = statement.executeQuery(            
				      "SELECT * FROM Dentist where username = '" + username + "';");
			  
	        
		         //checks if there is data returned
		        if(resultSet.next()){
		        	return true;
		        }
		        else{
		        	return false;
		        	}
		    		

		        
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
       
		
		
		return exists;
	}
	
	public int insertDentist(String a,String b,String c,String d,String e,String f,String g,String h,String i,String j,String k){
		int id = -1;
		try {
			String query =  "insert into Dentist (salt, username, password, firstName, lastName, email, address1, address2, address3, address4, telephone)" + " values (?,?,?,?,?,?,?,?,?,?,?);";
			
			
			PreparedStatement preparedStmt = connections.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			preparedStmt.setString(1, a);
			preparedStmt.setString(2, b);
			preparedStmt.setString(3, c);
			preparedStmt.setString(4, d);
			preparedStmt.setString(5, e);
			preparedStmt.setString(6, f);
			preparedStmt.setString(7, g);
			preparedStmt.setString(8, h);
			preparedStmt.setString(9, i);
			preparedStmt.setString(10, j);
			preparedStmt.setString(11, k);
			
		    preparedStmt.execute();
			
		    
		    ResultSet rs = preparedStmt.getGeneratedKeys();
            if(rs.next())
            {
                id = rs.getInt(1);
            }
        	
        	return id;
		        
		} catch (SQLException ew) {
			// TODO Auto-generated catch block
			ew.printStackTrace();
		}
		return id;
		
	}
	

	
	
	
}
