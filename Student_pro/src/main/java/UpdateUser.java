

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import com.conn.conn;
@WebServlet("/UpdateUser")
public class UpdateUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession(false);
		if(session==null) {
			response.sendRedirect("loginerror.html");
		}else {
			String name=request.getParameter("name");
			String id=request.getParameter("id");
			String roll=request.getParameter("roll");
			int age=Integer.parseInt(request.getParameter("age"));
			String gender=request.getParameter("gender");
			String address=request.getParameter("adress");
			
			PrintWriter out=response.getWriter();
			//jdbc code
			try {
				
				Connection c=conn.getConnection();
				String Query ="update stu set name=?,roll=?,age=?,gender=?,adress=? where id=?";
				PreparedStatement p=c.prepareStatement(Query);
				p.setString(6, id);
				p.setString(1, name);
				p.setString(2, roll);
				p.setInt(3, age);
				p.setString(4, gender);
				p.setString(5, address);
				p.executeUpdate();
				c.close();
				
				session.setAttribute("msg", "User Update Successfully!");
				//response.sendRedirect("AllUsers");
				response.sendRedirect("UserDetails?id="+id);
			}catch (Exception e) {
				out.print(e);
			}	
			//end
	}

}}
