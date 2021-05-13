package com;

import java.sql.*; 
public class user 
{ 
	//A common method to connect to the DB

	private Connection connect() 
	 { 
	 Connection con = null; 
	 try
	 { 
	 Class.forName("com.mysql.jdbc.Driver"); 
	 
	 //Provide the correct details: DBServer/DBName,username, password 
	 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/users", "root", "projectdatabases"); 
	 } 
	    catch (Exception e) 
	    {e.printStackTrace();} 
	 
	    return con; 
	 } 
	public String insertUser (String firstName, String lastName, String email, String gender, String occupation, String phone, String username, String password) 
	{ 
	  String output = ""; 

	try
	{ 
		 
	   Connection con = connect(); 
	   
	   if (con == null) 
	   {return "Error while connecting to the database for inserting."; } 

	   // create a prepared statement
	   String query = " insert into items values (?, ?, ?, ?, ?, ?, ?, ?)"; 

	   PreparedStatement preparedStmt = con.prepareStatement(query); 

	   // binding values
	   preparedStmt.setInt(1, 0); 
	   preparedStmt.setString(2, firstName); 
	   preparedStmt.setString(3, lastName); 
	   preparedStmt.setString(4, email); 
	   preparedStmt.setString(5, gender);
	   preparedStmt.setString(6, occupation);
	   preparedStmt.setString(7, phone);
	   preparedStmt.setString(8, username);
	   preparedStmt.setString(9, password);

	   // execute the statement
	   preparedStmt.execute(); 
	   con.close(); 
	    String newUsers = readUsers();
		 output =  "{\"status\":\"success\", \"data\": \"" + 
				 newUsers + "\"}"; 
		 } 

		catch (Exception e) 
		 { 
			output = "{\"status\":\"error\", \"data\": \"Error while inserting an user.\"}";  
		 System.err.println(e.getMessage()); 
		 } 
		return output; 
		}

	public String readUsers() 
	{ 
	   String output = ""; 
	   
	   try
	  { 
	     Connection con = connect(); 
	 
	     if (con == null) 
	     {return "Error while connecting to the database for reading."; } 
	 
	     // Prepare the html table to be displayed
	     output = "<table border=\"1\"><tr><th>User Id</th>"
					+ "<th>First Name</th><th>Last Name</th> "+" <th>Email</th> "+" <th>Gender</th> "+" <th>Occupation</th> "+" <th>Phone</th> "+" <th>Username</th> "+" <th>Password</th> "+" <th>Update</th><th>Remove</th></tr>";
              
	 
	   String query = "select * from users"; 
	   Statement stmt = con.createStatement(); 
	   ResultSet rs = stmt.executeQuery(query); 
	 
	   // iterate through the rows in the result set
	  while (rs.next()) 
	 { 
	      String uId = Integer.toString(rs.getInt("uId")); 
	      String firstName = rs.getString("firstName"); 
	      String lastName = rs.getString("lastName"); 
	      String email = rs.getString("email");
	      String gender = rs.getString("gender");
	      String occupation = rs.getString("occupation");
	      String phone = rs.getString("phone");
	      String username = rs.getString("username");
	      String password = rs.getString("password");
	      
	 
	   // Add a row into the html table
			 output += "<tr><td><input id='hiduIdUpdate' name='hiduIdUpdate' type='hidden' value='" + uId + "'>"
					 + firstName + "</td>";
			 output += "<td>" + lastName + "</td>"; 
			 output += "<td>" + email + "</td>"; 
			 output += "<td>" + gender + "</td>";
			 output += "<td>" + occupation + "</td>";
			 output += "<td>" + phone + "</td>";
			 output += "<td>" + username + "</td>";
			 output += "<td>" + password + "</td>";
			
			 // buttons
			 output += "<td><input name='btnUpdate' " 
			 + " type='button' value='Update' class =' btnUpdate btn btn-secondary'data-uId='" + uId + "'></td>"
			 + "<td><form method='post' action='users.jsp'>"
			 + "<input name='btnRemove' " 
			 + " type='button' value='Remove' class='btnRemove btn btn-danger' data-uId='" + uId + "'>"
			 + "<input name='hiduIdDelete' type='hidden' " 
			 + " value='" + uId + "'>" + "</form></td></tr>"; 
			 } 
			 con.close(); 
			 // Complete the html table
			 output += "</table>"; 
			 } 
			catch (Exception e) 
			 { 
			 output = "Error while reading the users."; 
			 System.err.println(e.getMessage()); 
			 } 
			return output; 
		}

	public String updateUser(Integer uId, String firstName, String lastName, String email, String gender, String occupation, String phone, String username, String password)
	 { 
	   
		String output = ""; 
	 
		try
	   { 
	      Connection con = connect(); 
	 
	      if (con == null) 
	      {return "Error while connecting to the database for updating."; } 
	 
	     // create a prepared statement
	     String query = "UPDATE useres SET firstName=?,lastName=?,email=?,gender=?,occupation=?,phone=?,username=?,password=? WHERE uId=?"; 
	     PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
	 
	     // binding values
	    preparedStmt.setString(1, firstName); 
	    preparedStmt.setString(2, lastName); 
	    preparedStmt.setString(3, email); 
	    preparedStmt.setString(4, gender);
	    preparedStmt.setString(5, occupation);
	    preparedStmt.setString(6, phone);
	    preparedStmt.setString(7, username);
	    preparedStmt.setString(8, password);
	    preparedStmt.setInt(9, uId); 
	 
	    // execute the statement
	    preparedStmt.execute(); 
	    con.close();
		String newUsers = readUsers();
		 output =  "{\"status\":\"success\", \"data\": \"" + 
				 newUsers + "\"}"; 
		 } 

		catch (Exception e) 
		 { 
			output = "{\"status\":\"error\", \"data\": \"Error while Updating the user.\"}";  
		
		System.err.println(e.getMessage());
		}
		return output;
		}


	public String deleteUser(Integer uId) 
	 { 
	 String output = ""; 
	 
	 try
	 { 
	    Connection con = connect(); 
	    if (con == null) 
	    {return "Error while connecting to the database for deleting."; } 
	 
	    // create a prepared statement
	    String query = "delete from users where uId=?"; 
	    PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
	    // binding values
	    preparedStmt.setInt(1, uId); 
	 
	 // execute the statement
	 	 preparedStmt.execute(); 
	 	 con.close(); 
	 	 String newUsers = readUsers();
	 	 output =  "{\"status\":\"success\", \"data\": \"" + 
	 			 newUsers + "\"}"; 
	 	 } 

	 	catch (Exception e) 
	 	 { 
	 		output = "{\"status\":\"error\", \"data\": \"Error while deleting the user.\"}";  
	 	 System.err.println(e.getMessage()); 
	 	 } 
	 	return output; 
	 		}



} 