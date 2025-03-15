
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/AdminHome")
public class AdminHome extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//yaha false lagane paar yhea check karta hai ki session exist karta hai ki nahi karega tho naya session create nahi karega 
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
/**************************************************************************************************/
		
		String msg = (String) session.getAttribute("msg");
		if (msg != null) {
			if (msg.contains("Success")) {
				out.print("<p class='msg-success'>" + msg + "</p>");
			} else {
				out.print("<p class='msg-error'>" + msg + "</p>");
			}
			session.setAttribute("msg", null);
		}
/*--------------------------------------------------------------------------------------------------------------------------------*/
		out.print("<div class='card'>");
		out.print("<h3 class='mb-4'>Add New Student</h3>");
		out.print("<form action='AddUser' method='post'>");/*add user*/

	/*1*/out.print("<label>Stu_id:</label>");
		out.print("<input type='text' class='form-control' name='stuid' required /> <br/><br/>");

	/*1*/out.print("<label>Name:</label>");
		out.print("<input type='text' class='form-control' name='name' required /> <br/><br/>");

		/*1*/out.print("<label>Password:</label>");
		out.print("<input type='password' class='form-control' name='password' required /> <br/><br/>");

		/*1*/out.print("<label>Roll No:</label>");
		out.print("<input type='number' class='form-control' name='roll' required /> <br/><br/>");

		/*1*/out.print("<label>Email:</label>");
		out.print("<input type='email' class='form-control' name='email' required /> <br/><br/>");

		/*1*/out.print("<label>Age:</label>");
		out.print("<input type='number' class='form-control' name='age' required /> <br/><br/>");

		/*1*/out.print("<label>Gender:</label>");
		out.print("<input type='radio' name='gender' value='Male' checked /> Male");
		out.print("<input type='radio' name='gender' value='Female' /> Female");
		out.print("<br/><br/>");

		/*1*/	out.print("<label>Address:</label>");
		out.print("<textarea class='form-control' name='address' rows='3' required></textarea> <br/><br/>");
		out.print("<button type='submit' class='btn btn-primary w-100'>Add User</button>");

		out.print("</form>");
		out.print("</div>");
		out.print("</body>");
		out.print("</html>");
		out.close();
	}

}
