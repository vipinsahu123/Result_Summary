import javax.servlet.http.*;  
import javax.servlet.*;  
import java.io.*;  
import java.sql.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Register extends HttpServlet
{
	public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException  
	{
		
		response.setContentType("text/html");//setting the content type  
		PrintWriter out=response.getWriter();
			String name=request.getParameter("name");
			String email=request.getParameter("email");
			String pass=request.getParameter("pass");
			String username="";
			String regex = "^(.+)@.+\\..+$"; // regular expression for matching email addresses
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(email);
			RequestDispatcher dispatcher = null;
			Connection con = null;
			dispatcher = request.getRequestDispatcher("index.jsp");

			
			if (matcher.matches()) 
			{
				username = matcher.group(1);
			}
			
			
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
				String connectionURL = "jdbc:mysql://localhost:3306/result"; 
				con=DriverManager.getConnection(connectionURL, "root", "");
				Statement st=con.createStatement();
				int p=st.executeUpdate("insert into user(name,user_name,email,pass) values('"+name+"','"+username+"','"+email+"','"+pass+"')");
				if(p > 0)
				{
					request.setAttribute("status","success");
					int q=st.executeUpdate("create table "+username+"(category varchar(50), semester varchar(50), admitted_boys int, admitted_girls int, passed_boys int, passed_girls int, distinction_boys int, distinction_girls int(11), first_boys int, first_girls int, second_boys int, second_girls int);");
					
				}
				else
				{
					request.setAttribute("status","exception");
				}
				
				dispatcher.forward(request, response);
			}
			catch(Exception e)
			{
				e.printStackTrace();
				request.setAttribute("status","exception");
				dispatcher.forward(request, response);
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
	}
}