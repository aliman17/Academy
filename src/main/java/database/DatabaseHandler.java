package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public class DatabaseHandler {
	
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
	static final String DB_URL =	"jdbc:mysql://localhost/AcademyDB?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	
	// Database credentials
	static final String USER = "ales";
	static final String PASS = "1234";
	
	
	public static void initDatabase() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		String createStudents = "CREATE TABLE IF NOT EXISTS Students ("
				+ "StudentID VARCHAR(60), "
				+ "FirstName VARCHAR(60), "
				+ "LastName VARCHAR(60),"
				+ "PRIMARY KEY (StudentID));";
		
		String createCourses = "CREATE TABLE IF NOT EXISTS Courses ("
				+ "CourseID VARCHAR(60), "
				+ "CourseName VARCHAR(60), "
				+ "PRIMARY KEY (CourseID));";
		
		String createEnrollments = "CREATE TABLE IF NOT EXISTS Enrollments ("
				+ "StudentID VARCHAR(60), "
				+ "CourseID VARCHAR(60), "
				+ "PRIMARY KEY (StudentID, CourseID),"
				+ "FOREIGN KEY (StudentID) REFERENCES Students(StudentID) ON DELETE CASCADE ON UPDATE NO ACTION,"
				+ "FOREIGN KEY (CourseID) REFERENCES Courses(CourseID) ON DELETE CASCADE ON UPDATE NO ACTION);";
		
		Connection conn = getConnection();
		update(conn, createStudents);
		update(conn, createCourses);
		update(conn, createEnrollments);
		closeConnection(conn);
	}
	
	
	public static Connection getConnection() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		Class.forName(JDBC_DRIVER).newInstance();
		Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
		return conn;
	}
	
	
	public static void closeConnection(Connection conn) throws SQLException {
		conn.close();
	}
	
	
	public static ArrayList<HashMap<String, String>> query(Connection conn, String query) throws SQLException {
		
		ArrayList<HashMap<String, String>> results = new ArrayList<>();
        
		PreparedStatement stmt = conn.prepareStatement(query);
        ResultSet resultSet = stmt.executeQuery();
        
        ResultSetMetaData rsmd = resultSet.getMetaData();
        int columnsNumber = rsmd.getColumnCount();
        
        while (resultSet.next()) {
            HashMap<String,String> eachResult = new HashMap<String,String>();
            for (int i = 1; i <= columnsNumber; i++) { 
            		String label = rsmd.getColumnLabel(i);
            		String val = resultSet.getString(i);
                eachResult.put(label, val);  
            }
            results.add(eachResult);
        }
        resultSet.close();
        stmt.close();
        
        return results;
	}
	
	
	public static void update(Connection conn, String update) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement(update);
        stmt.executeUpdate(update);
        stmt.close();
	}
}
