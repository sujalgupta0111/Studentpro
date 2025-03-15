
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

@WebServlet("/EditUser")
public class EditUser extends HttpServlet {
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
			out.print("<style>");
			out.print("body { background-color: #f8f9fa; }");
			out.print(
					".form-container { max-width: 600px; margin: 20px auto; padding: 20px; background: white; border-radius: 10px; box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1); }");
			out.print("</style>");
			out.print("<meta name='viewport' content='width=device-width, initial-scale=1'>");
			out.print("</head>");
			out.print("<body>");
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
				PreparedStatement p = c.prepareStatement("SELECT * FROM stu WHERE id=?");
				String id = request.getParameter("id");
				p.setString(1, id);
				ResultSet rs = p.executeQuery();

				if (rs.next()) {
					out.print("<div class='form-container'>");

					/*******************************
					 * Update Edit User
					 **************************************/
					out.print("<form action='UpdateUser' method='post'>");

					out.print("<div class='mb-3'>");
					out.print("<label class='form-label'>ID:</label>");
					/* 1 */ out.print("<input type='text' class='form-control' name='id' value='" + rs.getString("id")
							+ "' readonly>");
					out.print("</div>");

					out.print("<div class='mb-3'>");
					out.print("<label class='form-label'>Name:</label>");
					/* 1 */ out.print("<input type='text' class='form-control' name='name' value='"
							+ rs.getString("name") + "' required>");
					out.print("</div>");

					out.print("<div class='mb-3'>");
					out.print("<label class='form-label'>Email:</label>");
					/* 1 */out.print("<input type='email' class='form-control' name='email' value='"
							+ rs.getString("email") + "' readonly>");
					out.print("</div>");

					out.print("<div class='mb-3'>");
					out.print("<label class='form-label'>Roll Number:</label>");
					/* 1 */ out.print("<input type='number' class='form-control' name='roll' value='"
							+ rs.getString("roll") + "' required>");
					out.print("</div>");

					out.print("<div class='mb-3'>");
					out.print("<label class='form-label'>Age:</label>");
					/* 1 */ out.print("<input type='number' class='form-control' name='age' min='1' max='120' value='"
							+ rs.getInt("age") + "' required>");
					out.print("</div>");

					out.print("<div class='mb-3'>");
					out.print("<label class='form-label'>Gender:</label><br>");
					/**/ if (rs.getString("gender").equalsIgnoreCase("male")) {
						out.print("<div class='form-check form-check-inline'>");
						out.print("<input class='form-check-input' type='radio' name='gender' value='Male' checked>");
						out.print("<label class='form-check-label'>Male</label>");
						out.print("</div>");
						out.print("<div class='form-check form-check-inline'>");
						out.print("<input class='form-check-input' type='radio' name='gender' value='Female'>");
						out.print("<label class='form-check-label'>Female</label>");
						out.print("</div>");
						/**/} else {
						out.print("<div class='form-check form-check-inline'>");
						out.print("<input class='form-check-input' type='radio' name='gender' value='Male'>");
						out.print("<label class='form-check-label'>Male</label>");
						out.print("</div>");
						out.print("<div class='form-check form-check-inline'>");
						out.print("<input class='form-check-input' type='radio' name='gender' value='Female' checked>");
						out.print("<label class='form-check-label'>Female</label>");
						out.print("</div>");
					}
					out.print("</div>");

					out.print("<div class='mb-3'>");
					out.print("<label class='form-label'>Address:</label>");
					/* 1 */ out.print("<textarea class='form-control' rows='3' name='adress' required>"
							+ rs.getString("adress") + "</textarea>");
					out.print("</div>");

					out.print("<div class='text-center'>");
					out.print("<button type='submit' class='btn btn-primary'>Update</button>");
					out.print("</div>");
					out.print("</form>");
					out.print("</div>");
				} else {
					session.setAttribute("msg", "Invalid Email ID!");
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
