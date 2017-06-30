package edu.auburn.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.auburn.domain.EduUser;
import edu.auburn.service.IUserService;
import edu.auburn.service.impl.UserService;

@WebServlet("/ta")
public class TaServlet extends HttpServlet {
	IUserService userService = new UserService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		if (session == null) {
			resp.sendRedirect(req.getContextPath() + "/user");
		} else {
			String user = (String) session.getAttribute("user");

			if (user == null || "".equals(user)) {
				resp.sendRedirect(req.getContextPath() + "/user");
			} else {
				EduUser u = userService.getUserByName(user);
				if (u.getType() == 3) {
					req.getRequestDispatcher("/jsp/ta_manage.jsp").forward(req, resp);
				} else {
					resp.sendRedirect(req.getContextPath() + "");
				}
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
