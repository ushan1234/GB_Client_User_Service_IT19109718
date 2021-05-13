package com; 
import java.sql.*;

public class User 
{ 	// Database Connection
	private Connection connect() 
	{ 
		Connection con = null; 
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/pafuserservice","root", "root");
			//For testing purposes  
			System.out.print("Successfully connected to the Database when inserting");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return con; 
	} 
	
	
    // Add user Method 
	public String AddUser(String fname, String lname, String age,
			 String pnumber, String address, String email, String password, String category ) 
			 { 
			 String output = ""; 
			 try
			 { 
			 Connection con = connect(); 
			 if (con == null) 
			 { 
			 return " There is an Error while connecting to the database"; 
			 } 
			 // create a prepared statement
			 String query = " insert into users (`idusers`,`fname`,`lname`,`age`,`pnumber`,`address`,`email`,`password`,`category`)"
					+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?)"; 
 
			 PreparedStatement preparedStmt = con.prepareStatement(query); 
			 // binding values
			 preparedStmt.setInt(1, 0); 
			 preparedStmt.setString(2, fname); 
			 preparedStmt.setString(3, lname); 
			 preparedStmt.setString(4, age); 
			 preparedStmt.setString(5, pnumber); 
			 preparedStmt.setString(6, address); 
			 preparedStmt.setString(7, email); 
			 preparedStmt.setString(8, password); 
			 preparedStmt.setString(9, category);
			 
			 // execute the statement
			 preparedStmt.execute(); 
			 con.close(); 
			 String newUser = readUser(); 
			 output = "{\"status\":\"success\", \"data\": \"" + 
			 newUser + "\"}"; 
			 } 
			 catch (Exception e) 
			 { 
			 output = "{\"status\":\"error\", \"data\": \"Error while inserting the item.\"}"; 
			 System.err.println(e.getMessage()); 
			 } 
			 return output; 
			 }  
			
	public String readUser()
	{ 
	 String output = ""; 
	try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 { 
	 return "There is an Error while connecting to the database for reading."; 
	 } 
	 // Prepare the html table to be displayed
	 output = "<table border='1'><tr><th>First Name</th>" 
	 + "<th>Last Name</th><th>Age</th>"
	 + "<th>Phone Number</th>" + "<th>Address</th>" + "<th>E-mail</th>" + "<th>Password</th>" + "<th>Category</th>" 
	 + "<th>Update</th><th>Remove</th></tr>"; 
	 String query = "select * from users"; 
	 Statement stmt = con.createStatement(); 
	 ResultSet rs = stmt.executeQuery(query); 
	 // iterate through the rows in the result set
	 while (rs.next()) 
	 { 
	 String idusers = Integer.toString(rs.getInt("idusers")); 
	 String fname = rs.getString("fname"); 
	 String lname = rs.getString("lname"); 
	 String age = rs.getString("age"); 
	 String pnumber = rs.getString("pnumber"); 
	 String address = rs.getString("address"); 
	 String email = rs.getString("email"); 
	 String password = rs.getString("password"); 
	 String category = rs.getString("category"); 


	 
	// Add into the html table
	 output += "<tr><td>" + fname + "</td>"; 
	 output += "<td>" + lname + "</td>"; 
	 output += "<td>" + age + "</td>"; 
	 output += "<td>" + pnumber + "</td>";
	 output += "<td>" + address + "</td>";
	 output += "<td>" + email + "</td>";
	 output += "<td>" + password + "</td>";
	 output += "<td>" + category + "</td>";
	// buttons
	output += "<td><input name='btnUpdate' type='button' value='Update' "
	+ "class='btnUpdate btn btn-secondary' data-idusers='" + idusers + "'></td>"
	+ "<td><input name='btnRemove' type='button' value='Remove' "
	+ "class='btnRemove btn btn-danger' data-idusers='" + idusers + "'></td></tr>"; 
	 } 
	 con.close(); 
	 // Complete the html table
	 output += "</table>"; 
	 } 
	catch (Exception e) 
	 { 
	 output = "Error while reading the items."; 
	 System.err.println(e.getMessage()); 
	 } 
	return output; 
	}
		
	public String updateUser(String idusers, String fname, String lname, String age,
			 String pnumber, String address, String email, String password, String category) 
			 { 
			 String output = ""; 
			 try
			 { 
			 Connection con = connect(); 
			 if (con == null) 
			 { 
			 return " There is an Error while connecting to the database for updating."; 
			 } 
			 // create a prepared statement
			 String query = "UPDATE users SET fname=?,lname=?,age=?,pnumber=?,address=?,email=?,password=?,category=? WHERE idusers=?"; 
			 
			 PreparedStatement preparedStmt = con.prepareStatement(query); 
			 // binding values
			 preparedStmt.setString(1, fname); 
			 preparedStmt.setString(2, lname); 
			 preparedStmt.setString(3, age); 
			 preparedStmt.setString(4, pnumber); 
			 preparedStmt.setString(5, address); 
			 preparedStmt.setString(6, email); 
			 preparedStmt.setString(7, password); 
			 preparedStmt.setString(8, category); 
			 preparedStmt.setInt(9, Integer.parseInt(idusers)); 
			// execute the statement
			 preparedStmt.execute(); 
			 con.close(); 
			 String newItems = readUser(); 
			 output = "{\"status\":\"success\", \"data\": \"" + 
			 newItems + "\"}"; 
			 } 
			 catch (Exception e) 
			 { 
			 output = "{\"status\":\"error\", \"data\": \"Error while updating the item.\"}"; 
			 System.err.println(e.getMessage()); 
			 } 
			 return output; 
			 }
			 
	public String deleteUser(String idusers) 
	 { 
	 String output = ""; 
	 try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 { 
	 return "There is an Error while connecting to the database for deleting."; 
	 } 
	 // create a prepared statement
	 String query = "delete from users where idusers=?"; 
	 PreparedStatement preparedStmt = con.prepareStatement(query); 
	 // binding values
	 preparedStmt.setInt(1, Integer.parseInt(idusers)); 
	 // execute the statement
	 preparedStmt.execute(); 
	 con.close(); 
	 String newItems = readUser(); 
	 output = "{\"status\":\"success\", \"data\": \"" + 
	 newItems + "\"}"; 
	 } 
	 catch (Exception e) 
	 { 
	 output = "{\"status\":\"error\", \"data\": \"Error while deleting the item.\"}"; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 }
} 