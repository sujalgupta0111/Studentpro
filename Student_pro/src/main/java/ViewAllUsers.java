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

@WebServlet("/ViewAllUsers")
public class ViewAllUsers extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session == null)
			response.sendRedirect("loginfailed.html");

		String name = (String) session.getAttribute("name");

		PrintWriter out = response.getWriter();
		out.print("<html>");
		out.print("<head>");
		out.print("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css' rel='stylesheet'>");
		out.print("<script src='https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js'></script>");
		out.print("<style>.card:hover {transform: translateY(-5px); transition: transform 0.3s;}</style>");
		out.print("<meta name='viewport' content='width=device-width, initial-scale=1'>");
		out.print("</head>");
		out.print("<body class='bg-light'>");
		out.print("<div class='container'>");
		out.print("<h1 class='text-center mt-5 mb-4 text-primary'>Student App</h1>");

		// Navigation Bar
		out.print("<nav class='navbar navbar-dark bg-dark mb-4 rounded'>");
		out.print("<div class='container-fluid justify-content-center'>");
		out.print("<span class='navbar-text text-white me-3'>Welcome Admin <b>" + name + "</b></span>");
		out.print("<a class='btn btn-outline-light me-2' href='AdminHome'>Home</a>");
		out.print("<a class='btn btn-outline-light me-2' href='Passwordvala'>Change Password</a>");
		out.print("<a class='btn btn-outline-light me-2' href='ViewAllUsers'>View All Users</a>");
		out.print("<a class='btn btn-outline-danger' href='Logout'>Logout</a>");
		out.print("</div></nav>");

		// JDBC Section
		try {
		    Class.forName("com.mysql.cj.jdbc.Driver");
		    Connection c = conn.getConnection();
		    PreparedStatement p = c.prepareStatement("SELECT * FROM stu");
		    ResultSet rs = p.executeQuery();
		    
		    out.print("<div class='row row-cols-1 row-cols-md-3 g-4'>");
		    while (rs.next()) {
		        out.print("<div class='col'>");
		        out.print("<div class='card h-100 shadow-sm'>");
		        out.print("<div class='card-body'>");
		        out.print("<h5 class='card-title'>" + rs.getString("name") + "</h5>");
		        out.print("<div class='card-text'>");
		        out.print("<p class='mb-1'><b>ID:</b> " + rs.getString("id") + "</p>");
		        out.print("<p class='mb-1'><b>Email:</b> " + rs.getString("email") + "</p>");
		        out.print("<p class='mb-1'><b>Roll:</b> " + rs.getString("roll") + "</p>");
		        out.print("<p class='mb-1'><b>Age:</b> " + rs.getInt("age") + "</p>");
		        out.print("</div></div>");
		        out.print("<div class='card-footer bg-white d-flex justify-content-between'>");
		        /************************user details***********************/
		        out.print("<form action='UserDetails' method='post' class='m-0'>");
		        out.print("<input type='hidden' name='id' value='" + rs.getString("id") + "'>");
		        out.print("<button type='submit' class='btn btn-primary btn-sm'>View Details</button>");
		        out.print("</form>");
		        /************************Delete details***********************/
			      
		        out.print("<a href='DeleteUser?id=" + rs.getString("id") + "' class='btn btn-danger btn-sm'>Delete</a>");
		        out.print("</div></div></div>");
		    }
		    out.print("</div>");
		    c.close();
		} catch (Exception e) {
		    out.print("<div class='alert alert-danger mt-4'>Error: " + e.getMessage() + "</div>");
		}

		out.print("</div></body></html>");
		

	}

}
