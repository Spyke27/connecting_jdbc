package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import db.DB;

public class FindById {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.print("Enter product's ID: ");
		int id = scan.nextInt();
		
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		
		try {
			conn = DB.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery("SELECT * FROM product WHERE ID = " + id);
			
			if(rs.next()) {
				System.out.println(rs.getString("Name"));
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Deu ruim!");
		}
		finally {
			DB.closeStatement(st);
			DB.closeResult(rs);
			DB.closeConnection();
		}
		scan.close();
	}
}
