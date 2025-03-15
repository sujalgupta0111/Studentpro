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

@WebServlet("/DeleteUser")
public class DeleteUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession(false);
		if(session==null) {
			response.sendRedirect("loginerror.html");
		}else {
			String id=request.getParameter("id");
			PrintWriter out=response.getWriter();
			//jdbc code
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection c=conn.getConnection();
				String Query ="delete from stu where id=?";
				PreparedStatement p=c.prepareStatement(Query);
				p.setString(1, id);
				int r=p.executeUpdate();
				c.close();
				if(r==0) {
					session.setAttribute("msg", "User Not Found!");
				}else {
					session.setAttribute("msg", "User Deleted Successfully!");
				}
				response.sendRedirect("ViewAllUsers");
			}catch (SQLIntegrityConstraintViolationException e) {
				session.setAttribute("msg", "User Already Exist!");
				response.sendRedirect("AdminHome");
			}catch (Exception e) {
				out.print(e);
			}		}

}
}