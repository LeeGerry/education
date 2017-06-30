package edu.auburn.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.StringUtils;

import edu.auburn.domain.EduUser;
import edu.auburn.service.IUserService;
import edu.auburn.service.impl.UserService;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
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
				if(null != u && u.getType() == 1){
					String method = req.getParameter("method");
					if(StringUtils.isNullOrEmpty(method)){
						req.getRequestDispatcher("/jsp/admin_manage.jsp").forward(req, resp);
					}else if(method.equals("usermanage")){
						showuser(req, resp);
					}else if(method.equals("updatet")){
						int uid = Integer.parseInt(req.getParameter("uid"));
						EduUser eu = userService.getUserById(uid);
						if(null != eu && eu.getType() != 1){
							userService.updateUserTypeById(uid, 2);
						}
						showuser(req, resp);
					}else if(method.equals("updates")){
						int uid = Integer.parseInt(req.getParameter("uid"));
						EduUser eu = userService.getUserById(uid);
						if(null != eu && eu.getType() != 1){
							userService.updateUserTypeById(uid, 4);
						}
						showuser(req, resp);
					}else if(method.equals("deletes")){
						int uid = Integer.parseInt(req.getParameter("uid"));
						EduUser eu = userService.getUserById(uid);
						if(null != eu && eu.getType() != 1){
							userService.delUserById(uid);
						}
						showuser(req, resp);
					}
					
				}else{
					resp.sendRedirect(req.getContextPath()+"");
				}
			}
		}
	}

	private void showuser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<EduUser> list = userService.getAll();
		req.setAttribute("list", list);
		req.getRequestDispatcher("/jsp/admin_user_manage.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
