<%@ page import="java.sql.*,java.util.*" %> 
<%
	Connection con=null;
	try
			{
				Class.forName("com.mysql.jdbc.Driver");
				String connectionURL = "jdbc:mysql://localhost:3306/result"; 
				con=DriverManager.getConnection(connectionURL, "root", "");
				Statement st=con.createStatement();
				int i=st.executeUpdate("delete from " + session.getAttribute("user_name") +";");
				
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
			String redirectURL = "hometable.jsp";
			response.sendRedirect(redirectURL);



%>