package Appliers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Middleware.Services;
import Model.RegModel;
import conn.JdbcHandler;


public class Methods implements Services {

	RegModel obj=new RegModel();
	@Override
	public void create(RegModel a) {
		try (Connection conn = JdbcHandler.getConnection();
	             PreparedStatement ps = conn.prepareStatement(
	                     "INSERT INTO Userdetails(name,mobile,password) VALUES (?, ?, ?)")) {
	            ps.setString(1, a.getName());
	            ps.setInt(2, a.getMobile());
	            ps.setString(3, a.getPassword());
	            

	            int rows = ps.executeUpdate();
	            System.out.println(rows + " row(s) inserted.");
	        } catch (SQLException e) {
	            e.printStackTrace();
	     }
	 }
	
	@Override
	public boolean check(String logname, int lognum, String logpass) {
		
		try (Connection conn = JdbcHandler.getConnection();
	             PreparedStatement ps = conn.prepareStatement(
	                     "SELECT * FROM Userdetails WHERE name = ? AND mobile = ? AND password = ?")) {
	            ps.setString(1, logname);
	            ps.setInt(2, lognum);
	            ps.setString(3, logpass);

	            try (ResultSet resultSet = ps.executeQuery()) {
	                return resultSet.next(); 
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false; 
	        }
		
	}

		
}


