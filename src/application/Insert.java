package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Locale;
import java.util.Scanner;

import db.DB;

public class Insert {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Enter product's data: ");
		System.out.print("Name: ");
		String name = scan.nextLine();
		System.out.print("Price: ");
		Double price = (double) scan.nextDouble();
		System.out.print("Quantity: ");
		Integer quantity = scan.nextInt();
		
		Connection conn = null;
		PreparedStatement st = null;
		
		try {
			conn = DB.getConnection();
			st = conn.prepareStatement("INSERT INTO product (Name, Price, Quantity) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			st.setString(1, name);
			st.setDouble(2, price);
			st.setInt(3, quantity);
			
			int rowEffect = st.executeUpdate();
			
			if(rowEffect > 0) {
				System.out.println("Product added! New product's ID: " + st.getGeneratedKeys());
			} else {
				System.out.println("No row affected!");
			}
			
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}
		
		scan.close();
	}

}
