import com.conn.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.conn.conn;

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		String pass = request.getParameter("password");

		HttpSession session = request.getSession();/* Session bana */
		Connection ab = conn.getConnection();
		try {
			String Query = "select * from login where id=? and password=?";
			PreparedStatement p = ab.prepareStatement(Query);
			p.setString(1, id);
			p.setString(2, pass);
			ResultSet r = p.executeQuery();
			
			
			if (r.next()) {
				session.setAttribute("name", r.getString("name"));
				session.setAttribute("id", id);
				response.sendRedirect("AdminHome");
			} else {
				response.sendRedirect("loginfailed.html");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

	}

}
