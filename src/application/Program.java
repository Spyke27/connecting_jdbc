package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;
import db.DbException;

public class Program {

	public static void main(String[] args) {
		
		Connection conn = null;
		Statement statement = null;
		ResultSet result = null;
		
		try {
			conn = DB.getConnection();
			statement = conn.createStatement();
			result = statement.executeQuery("SELECT * FROM product");
			
			while(result.next()) {
				System.out.println(result.getString("Name"));
			}
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		
		

	}

}
