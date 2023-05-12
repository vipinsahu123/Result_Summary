<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bootstrap demo</title>
   
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
	<link href="css/custom.css" rel="stylesheet">

   </head>
  <body>
		<div class="p-5 text-center">
			<table class="table table-bordered border-dark text-center">
				<thead class="table-dark">
					<tr>
						<th scope="col" colspan="12"><h3>RESULT SUMMARY</h3></th>
						
					<tr>
					<tr>
						<th scope="col" rowspan="2">Category</th>
						<th scope="col" rowspan="2">Semester</th>
						<th scope="col" colspan="2">Admitted Students</th>
						<th scope="col" colspan="2">Student Passed Out In First Attempt</th>
						<th scope="col" colspan="2">Student Passed Out With Distinction</th>
						<th scope="col" colspan="2">Student Passed Out With 1st Division</th>
						<th scope="col" colspan="2">Student Passed Out With 2nd Division</th>
					</tr>
					<tr>
						<th>Boys</th>
						<th>Girls</th>
						<th>Boys</th>
						<th>Girls</th>
						<th>Boys</th>
						<th>Girls</th>
						<th>Boys</th>
						<th>Girls</th>
						<th>Boys</th>
						<th>Girls</th>
					</tr>
				</thead>
				<tbody>
  
	<%@ page import="java.sql.*,java.util.*" %> 
	<%
			Connection con=null;
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
				String connectionURL = "jdbc:mysql://localhost:3306/result"; 
				con=DriverManager.getConnection(connectionURL, "root", "");
				Statement sta=con.createStatement();
				Statement sta1=con.createStatement();
				Statement sta2=con.createStatement();
				Statement sta3=con.createStatement();
				ResultSet rs=sta.executeQuery("select * from " + session.getAttribute("user_name") +" where category = 'general';");
				ResultSet rs1=sta1.executeQuery("select * from " + session.getAttribute("user_name") +" where category = 'obc';");
				ResultSet rs2=sta2.executeQuery("select * from " + session.getAttribute("user_name") +" where category = 'sc';");
				ResultSet rs3=sta3.executeQuery("select * from " + session.getAttribute("user_name") +" where category = 'st';");

								
				while(rs.next())
				{
	%>	
				<tr class="table-secondary">
						<th>GENERAL</th>
						<td><%= rs.getString("semester") %></td>
						<td><%= rs.getString("admitted_boys") %></td>
						<td><%= rs.getString("admitted_girls") %></td>
						<td><%= rs.getString("passed_boys") %></td>
						<td><%= rs.getString("passed_girls") %></td>
						<td><%= rs.getString("distinction_boys") %></td>
						<td><%= rs.getString("distinction_girls") %></td>
						<td><%= rs.getString("first_boys") %></td>
						<td><%= rs.getString("first_girls") %></td>
						<td><%= rs.getString("second_boys") %></td>
						<td><%= rs.getString("second_girls") %></td>
				</tr>
	<%	
				}
				while(rs1.next())
				{
	%>	
				<tr class="table-info">
						<th>OBC</th>
						<td><%= rs1.getString("semester") %></td>
						<td><%= rs1.getString("admitted_boys") %></td>
						<td><%= rs1.getString("admitted_girls") %></td>
						<td><%= rs1.getString("passed_boys") %></td>
						<td><%= rs1.getString("passed_girls") %></td>
						<td><%= rs1.getString("distinction_boys") %></td>
						<td><%= rs1.getString("distinction_girls") %></td>
						<td><%= rs1.getString("first_boys") %></td>
						<td><%= rs1.getString("first_girls") %></td>
						<td><%= rs1.getString("second_boys") %></td>
						<td><%= rs1.getString("second_girls") %></td>
				</tr>
	<%	
				}
				while(rs2.next())
				{
	%>	
				<tr class="table-primary">
						<th>SC</th>
						<td><%= rs2.getString("semester") %></td>
						<td><%= rs2.getString("admitted_boys") %></td>
						<td><%= rs2.getString("admitted_girls") %></td>
						<td><%= rs2.getString("passed_boys") %></td>
						<td><%= rs2.getString("passed_girls") %></td>
						<td><%= rs2.getString("distinction_boys") %></td>
						<td><%= rs2.getString("distinction_girls") %></td>
						<td><%= rs2.getString("first_boys") %></td>
						<td><%= rs2.getString("first_girls") %></td>
						<td><%= rs2.getString("second_boys") %></td>
						<td><%= rs2.getString("second_girls") %></td>
				</tr>
	<%	
				}
				while(rs3.next())
				{
	%>	
				<tr class="table-success">
						<th>ST</th>
						<td><%= rs3.getString("semester") %></td>
						<td><%= rs3.getString("admitted_boys") %></td>
						<td><%= rs3.getString("admitted_girls") %></td>
						<td><%= rs3.getString("passed_boys") %></td>
						<td><%= rs3.getString("passed_girls") %></td>
						<td><%= rs3.getString("distinction_boys") %></td>
						<td><%= rs3.getString("distinction_girls") %></td>
						<td><%= rs3.getString("first_boys") %></td>
						<td><%= rs3.getString("first_girls") %></td>
						<td><%= rs3.getString("second_boys") %></td>
						<td><%= rs3.getString("second_girls") %></td>
				</tr>
	<%	
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			finally
			{
				try
				{
					con.close();
				}
				catch(SQLException e)
				{
					e.printStackTrace();
				}
				
			}
			
			
	%>
  
				</tbody>
				
		</table>
		
	</div>	
	<div class="container">
	<div class="col-md-12 text-center">
		<a href="delete.jsp" class="btn btn-primary btn-lg mx-2">Delete Record</a>
		<a href="home.jsp" class="btn btn-primary btn-lg mx-2">Add More</a>
		</div>
	</div>
  
  
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>
  </body>
</html>