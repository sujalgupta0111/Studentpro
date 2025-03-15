
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.conn.conn;

/**
 * Servlet implementation class aaaaaaa
 */
@WebServlet("/AddUser")
public class AddUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session == null)
			response.sendRedirect("loginfailed.html");

		String id = request.getParameter("stuid");
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String roll = request.getParameter("roll");
		int age = Integer.parseInt(request.getParameter("age"));
		String gender = request.getParameter("gender");
		String adress = request.getParameter("address");

		PrintWriter out = response.getWriter();
		Connection ab = conn.getConnection();

		String Query = "insert into stu (id,password,email,name,roll,age,gender,adress) VALUES (?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement p = ab.prepareStatement(Query);
			p.setString(1, id);
			p.setString(2, password);
			p.setString(3, email);
			p.setString(4, name);
			p.setString(5, roll);
			p.setInt(6, age);
			p.setString(7, gender);
			p.setString(8, adress);
			p.executeUpdate();
			session.setAttribute("msg", "User Added Successfully!");
			response.sendRedirect("AdminHome");

		} catch (SQLException e) {
			session.setAttribute("msg", "User Already Exist!");
			response.sendRedirect("AdminHome");
			e.printStackTrace();
		}
	}

}
