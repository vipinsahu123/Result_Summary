import javax.servlet.http.*;  
import javax.servlet.*;  
import java.io.*;  
import java.sql.*;
import java.util.*;
public class Login extends HttpServlet
{
	public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException  
	{
		
		response.setContentType("text/html");//setting the content type  
		String email=request.getParameter("email");
		String pass=request.getParameter("pass");
		Connection con = null;
		HttpSession session = request.getSession();
		RequestDispatcher dispatcher = null;
		
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
				String connectionURL = "jdbc:mysql://localhost:3306/result"; 
				con=DriverManager.getConnection(connectionURL, "root", "");
				Statement st=con.createStatement();
				ResultSet p=st.executeQuery("select * from user where email ='"+email+"' and pass ='"+pass+"';");
				if(p.next())
				{
					session.setAttribute("user_name",p.getString("user_name"));
					dispatcher = request.getRequestDispatcher("home.jsp");
				}
				else
				{
					request.setAttribute("status","failed");
					dispatcher = request.getRequestDispatcher("index.jsp");
				}
				dispatcher.forward(request, response);
			}
			catch(Exception e)
			{
				e.printStackTrace();
				request.setAttribute("status","failed");
				dispatcher = request.getRequestDispatcher("index.jsp");
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