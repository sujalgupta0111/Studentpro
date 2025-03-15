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

@WebServlet("/PasswordChange")
public class PasswordChange extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession sess = request.getSession(false);
		if (sess == null) {
			response.sendRedirect("loginerror.html");
		} else {

			String opas = request.getParameter("opass");
			String npas = request.getParameter("npass");
			String id = (String) sess.getAttribute("id");
			Connection ab = conn.getConnection();
			// Update password
			String Query = "UPDATE login SET password=? WHERE id=? and password=?";
			PreparedStatement p;
			try {
				p = ab.prepareStatement(Query);
				p.setString(1, npas);
				p.setString(2, id);
				p.setString(3, opas);
				int ch = p.executeUpdate();

				if (ch == 0) {
					sess.setAttribute("msg", "Password Updation Failed!");
				} else {
					sess.setAttribute("msg", "Password Updated Successfully!");
				}

				response.sendRedirect("AdminHome");

			} catch (SQLException e) {

				e.printStackTrace();
			}
		}

	}
}
