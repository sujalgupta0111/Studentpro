
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class aaaaaaa
 */
@WebServlet("/Passwordvala")
public class Passwordvala extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession sess = request.getSession(false);
		if(sess==null)
			response.sendRedirect("loginfailed.html");
		String name = (String)sess.getAttribute("name");
		

		PrintWriter out = response.getWriter();
		out.print("<html>");
		out.print("<head>");
		out.print("<style>");
		out.print("body { font-family: Arial, sans-serif; background-color: #f4f4f4; margin: 0; padding: 0; }");
		out.print("h1 { color: #333; text-align: center; margin-top: 20px; }");
		out.print("hr { border: 1px solid #ddd; }");
		out.print("p { text-align: center; font-size: 18px; }");
		out.print(
				"a { text-decoration: none; color: #fff; background-color: #007bff; padding: 10px 15px; border-radius: 5px; margin: 5px; display: inline-block; }");
		out.print("a:hover { background-color: #0056b3; }");
		out.print(
				"form { width: 300px; margin: 20px auto; padding: 20px; background: #fff; border-radius: 5px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); }");
		out.print("label { display: block; margin-bottom: 8px; font-weight: bold; }");
		out.print(
				"input[type='password'] { width: 100%; padding: 10px; margin-bottom: 15px; border: 1px solid #ccc; border-radius: 5px; }");
		out.print(
				"button { width: 100%; padding: 10px; background-color: #28a745; color: #fff; border: none; border-radius: 5px; cursor: pointer; }");
		out.print("button:hover { background-color: #218838; }");
		out.print(".success { color: green; text-align: center; }");
		out.print(".error { color: red; text-align: center; }");
		out.print("</style>");
		out.print("</head>");
		out.print("<body>");
		out.print("<h1>User App</h1>");
/****************************************************************/
		out.print("<hr/>");
		out.print("<p>Welcome Admin <b>" + name + "</b> ");
		out.print("&nbsp;&nbsp;&nbsp; <a href='AdminHome'>Home</a>");
		out.print("&nbsp;&nbsp;&nbsp; <a href='Passwordvala'>Change Password</a>");
		out.print("&nbsp;&nbsp;&nbsp; <a href='ViewAllUsers'>View All Users</a>");
		out.print("&nbsp;&nbsp;&nbsp; <a href='Logout'>Logout</a></p>");
		out.print("<hr/>");
		/**********************************************/

		String msg = (String) sess.getAttribute("msg");
		if (msg != null) {
			if (msg.contains("Success")) {
				out.print("<p class='success'>" + msg + "</p>");
			} else {
				out.print("<p class='error'>" + msg + "</p>");
			}
			sess.setAttribute("msg", null);
		}
/*                                                              */
		out.print("<h3>Change Password</h3>");
		out.print("<form action='PasswordChange' method='post'>");
		out.print("<label>OLD Password:</label>");
		out.print("<input type='password' name='opass' required /> <br/><br/>");
		out.print("<label>NEW Password:</label>");
		out.print("<input type='password' name='npass' required /> <br/><br/>");
		out.print("<button>Submit</button>");
		out.print("</form>");
		out.print("</body>");
		out.print("</html>");
	}

}
