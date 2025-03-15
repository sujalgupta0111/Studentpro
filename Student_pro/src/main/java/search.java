

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/search")
public class search extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session == null)
			response.sendRedirect("loginfailed.html");
  String name = (String) session.getAttribute("name");
  PrintWriter out = response.getWriter();
	out.print("<html>");
	out.print("<head>");
	out.print("<style>");
	out.print("body { font-family: Arial, sans-serif; background-color: #f4f4f4; margin: 0; padding: 20px; }");
	out.print("h1 { color: #333; }");
	out.print("hr { border: 1px solid #ddd; }");
	out.print("p { font-size: 16px; }");
	out.print("a { text-decoration: none; color: #007bff; margin-right: 15px; }");
	out.print("a:hover { text-decoration: underline; }");
	out.print(
			"form { background: #fff; padding: 20px; border-radius: 5px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); max-width: 400px; margin: 20px auto; }");
	out.print("label { display: block; margin-bottom: 8px; font-weight: bold; }");
	out.print(
			"input[type='email'], input[type='text'], input[type='tel'], input[type='number'], textarea { width: 100%; padding: 8px; margin-bottom: 15px; border: 1px solid #ccc; border-radius: 4px; }");
	out.print("input[type='radio'] { margin-right: 10px; }");
	out.print(
			"button { background-color: #007bff; color: #fff; border: none; padding: 10px 20px; border-radius: 4px; cursor: pointer; }");
	out.print("button:hover { background-color: #0056b3; }");
	out.print(".msg-success { color: green; font-weight: bold; }");
	out.print(".msg-error { color: red; font-weight: bold; }");
	out.print("</style>");
	out.print("</head>");
	out.print("<body>");
	out.print("<h1>Add Student App</h1>");

	out.print("<hr/>");
/**************************************************************************************************/
	out.print("<p>Welcome <b>" + name + "</b> ");
	out.print("&nbsp;&nbsp;&nbsp; <a href='AdminHome'>Home</a>");
	out.print("&nbsp;&nbsp;&nbsp; <a href='Passwordvala'>Change Password</a>");
	out.print("&nbsp;&nbsp;&nbsp; <a href='ViewAllUsers'>View All Users</a>");
	out.print("&nbsp;&nbsp;&nbsp; <a href='search'>Search</a>");
	out.print("&nbsp;&nbsp;&nbsp; <a href='Logout'>Logout</a></p>");
	out.print("<hr/>");
	/************************************************************************/
	
	out.println("<h3 class=\"mb-4\">Search User</h3>");
	out.println("<form action='SearchUser' method='post'>");
	out.println("    <div class='mb-3'>");
	out.println("        <label for='name' class='form-label'>Name:</label>");
	out.println("        <input type='text' class='form-control bg-dark text-light' id='nameformSearch' name='nameformSearch' required>");
	out.println("    </div>");
	out.println("    <button type='submit' class='btn btn-primary w-100'>Search</button>");
	out.println("</form>");

	/***************************************************************************************/
	out.print("	</div>\r\n"
			+ "		</div>\r\n"
			+ "	</div>\r\n"
			+ "\r\n"
			+ "	<!-- Bootstrap JS (optional, for interactive components) -->\r\n"
			+ "	<script\r\n"
			+ "		src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js\"></script>\r\n"
			+ "\r\n"
			+ "</body>\r\n"
			+ "</html>");
	}

}
