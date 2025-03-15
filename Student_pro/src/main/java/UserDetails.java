
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

@WebServlet("/UserDetails")
public class UserDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session == null) {
			response.sendRedirect("loginerror.html");
		} else {
			String name = (String) session.getAttribute("name");

			PrintWriter out = response.getWriter();
			out.print("<html>");
			out.print("<head>");
			out.print(
					"<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css' rel='stylesheet'>");
			out.print(
					"<script src='https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js'></script>");
			out.print("<style>.card:hover {transform: translateY(-5px); transition: transform 0.3s;}</style>");
			out.print("<meta name='viewport' content='width=device-width, initial-scale=1'>");
			out.print("</head>");
			out.print("<body class='bg-light'>");
			out.print("<div class='container'>");
			out.print("<h1 class='text-center mt-5 mb-4 text-primary'>User App</h1>");

			// Navigation Bar
			out.print("<nav class='navbar navbar-dark bg-dark mb-4 rounded'>");
			out.print("<div class='container-fluid justify-content-center'>");
			out.print("<span class='navbar-text text-white me-3'>Welcome <b>" + name + "</b></span>");
			out.print("<a class='btn btn-outline-light me-2' href='AdminHome'>Home</a>");
			out.print("<a class='btn btn-outline-light me-2' href='Password'>Change Password</a>");
			out.print("<a class='btn btn-outline-light me-2' href='AllUsers'>View All Users</a>");
			out.print("<a class='btn btn-outline-danger' href='Logout'>Logout</a>");
			out.print("</div></nav>");

			// Display Session Message
			String msg = (String) session.getAttribute("msg");
			if (msg != null) {
				if (msg.contains("Success")) {
					out.print("<div class='alert alert-success text-center'>" + msg + "</div>");
				} else {
					out.print("<div class='alert alert-danger text-center'>" + msg + "</div>");
				}
				session.setAttribute("msg", null);
			}

			// JDBC Section
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection c = conn.getConnection();
				String query = "SELECT * FROM stu WHERE id=?";
				PreparedStatement p = c.prepareStatement(query);
				String id = request.getParameter("id");
				p.setString(1, id);
				ResultSet rs = p.executeQuery();

				if (rs.next()) {
					out.print("<div class='card shadow-sm mb-4'>");
					out.print("<div class='card-body'>");
					out.print("<h5 class='card-title'>User Details</h5>");
					out.print("<div class='card-text'>");
					out.print("<p class='mb-1'><b>ID:</b> " + rs.getString("id") + "</p>");
					out.print("<p class='mb-1'><b>Name:</b> " + rs.getString("name") + "</p>");
					out.print("<p class='mb-1'><b>Email:</b> " + rs.getString("email") + "</p>");
					out.print("<p class='mb-1'><b>Roll Number:</b> " + rs.getString("roll") + "</p>");
					out.print("<p class='mb-1'><b>Age:</b> " + rs.getInt("age") + "</p>");
					out.print("<p class='mb-1'><b>Gender:</b> " + rs.getString("gender") + "</p>");
					out.print("<p class='mb-1'><b>Address:</b> " + rs.getString("adress") + "</p>");
					out.print("</div>");
					/**************** Edit User ********************************************/
					out.print("<form action='EditUser' method='post' class='mt-3'>");
					out.print("<input type='hidden' name='id' value='" + rs.getString("id") + "'>");
					out.print("<button type='submit' class='btn btn-primary'>Edit</button>");
					out.print("</form>");
					out.print("</div></div>");
				} else {
					session.setAttribute("msg", "Invalid ID!");
					response.sendRedirect("ViewAllUsers");
				}
				c.close();
			} catch (Exception e) {
				out.print("<div class='alert alert-danger mt-4'>Error: " + e.getMessage() + "</div>");
			}

			out.print("</div></body></html>");

		}
	}
}
