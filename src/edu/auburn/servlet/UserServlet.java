package edu.auburn.servlet;

import java.io.IOException;
import java.sql.Date;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
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
import edu.auburn.utils.EmailUtils;
import edu.auburn.utils.StringConfig;

@WebServlet("/user")
public class UserServlet extends HttpServlet {
	IUserService userService = new UserService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String method = req.getParameter("method");
		if (StringUtils.isNullOrEmpty(method)) {
			resp.sendRedirect(req.getContextPath());
		} else if (method.equals("login")) {
			login(req, resp);
		} else if (method.equals("register")){
			register(req, resp);
		} else if (method.equals("gotoreset")){
			req.getRequestDispatcher("/jsp/reset.jsp").forward(req, resp);
		} else if (method.equals("send")){
			sendForCode(req, resp);
		} else if (method.equals("reset")){
			updatePWD(req, resp);
		}
	}

	private void updatePWD(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String pwd = req.getParameter("pwd");
		String confirm = req.getParameter("confirm");
		String code = req.getParameter("code");
		String mail = req.getParameter("mail");
		String message = "";
		int codeInt = -1;
		if(StringUtils.isNullOrEmpty(pwd) || StringUtils.isNullOrEmpty(confirm) || StringUtils.isNullOrEmpty(code))
			message = "param could not be empty";
		try{
			codeInt = Integer.parseInt(code);
		}catch(Exception e){
			message = "code must be six digital numbers";
			req.setAttribute("message", message);
			req.getRequestDispatcher("/jsp/error.jsp").forward(req, resp);
		}
		if(!pwd.equals(confirm)){
			message = "password and confirm password must be the same";
			req.setAttribute("message", message);
		}
		if(message.equals("")){
			boolean boo = userService.checkVerifyCodeByEmail(mail, codeInt);
			if(boo){	
				boolean success = userService.updatePassword(mail, confirm);
				if(success)
					message = "You have updated your password successfully! go to the " + "<a href='"+req.getContextPath()+"'><u>Index page</u></a>" +" to login.";
				else	
					message = "update failed. Please try again.";
				
				req.setAttribute("message", message);
				req.getRequestDispatcher("/jsp/error.jsp").forward(req, resp);
			}else{
				message = "verify code is incorrect";
				req.setAttribute("message", message);
				req.getRequestDispatcher("/jsp/error.jsp").forward(req, resp);
			}
		}else{
			req.setAttribute("message", message);
			req.getRequestDispatcher("/jsp/error.jsp").forward(req, resp);
		}
	}
	
	private void sendForCode(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String email = req.getParameter("email");
		req.setAttribute("email", email);
		int code = (int)((Math.random()*9+1)*100000);
		try {
			EduUser user = userService.getUserByEmail(email);
			if(null == user){
				String message = "user does not exist. Please check if your email is right.";
				req.setAttribute("message", message);
				req.getRequestDispatcher("/jsp/error.jsp").forward(req, resp);
			}else{
				EmailUtils.generateAndSendEmail(email, code+"");
				req.setAttribute("email", email);
				userService.addVerifyCodeByEmail(email, code);
				req.getRequestDispatcher("/jsp/code.jsp").forward(req, resp);
			}
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			String message = "code send failed.";
			req.setAttribute("message", message);
			req.getRequestDispatcher("/jsp/error.jsp").forward(req, resp);
		}
		
	}
	private void login(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String email = req.getParameter("email");
		String pwd = req.getParameter("pwd");
		boolean result = userService.checkUserByEmailAndPwd(email, pwd);
		if (result) { // login success
			HttpSession session = req.getSession();
			EduUser user = userService.getUserByEmail(email);
			session.setAttribute("user", user.getName());
			int type = user.getType();
			if (type == 1) {// administrator
				resp.sendRedirect(req.getContextPath() + "/admin");
			} else if (type == 2) { // teacher
				resp.sendRedirect(req.getContextPath() + "/teacher");
			} else if (type == 3) { // TA
				resp.sendRedirect(req.getContextPath() + "/ta");
			} else if (type == 4) { // student
				resp.sendRedirect(req.getContextPath() + "/student");
			} else {// non
				resp.sendRedirect(req.getContextPath() + "/non");
			}
		} else {// failed
			resp.sendRedirect(req.getContextPath() + "/user");
		}
	}

	private void register(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String email = req.getParameter("email");
		String pwd = req.getParameter("pwd");
		String confirm = req.getParameter("confirm");
		if (userService.getUserByEmail(email) != null) {
			req.setAttribute("message", StringConfig.EMAILHASREG);
			req.getRequestDispatcher("/jsp/non_student.jsp").forward(req, resp);
		} else {
			if (!StringUtils.isNullOrEmpty(email) && !StringUtils.isNullOrEmpty(pwd)
					&& !StringUtils.isNullOrEmpty(confirm) && pwd.equals(confirm)) {
				EduUser user = new EduUser();
				user.setEmail(email);
				user.setPassword(pwd);
				user.setDate(new Date(System.currentTimeMillis()));
				user.setType(5);
				String name = email.split("@")[0];
				user.setName(name);
				userService.addUser(user);
				HttpSession session = req.getSession();
				session.setAttribute("user", user.getName());
				req.setAttribute("message", StringConfig.NOTAPPROVED);
				req.getRequestDispatcher("/jsp/non_student.jsp").forward(req, resp);
			} else {
				resp.sendRedirect(req.getContextPath());
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}
}
