package edu.auburn.servlet;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.mysql.jdbc.StringUtils;

import edu.auburn.domain.EduUser;
import edu.auburn.domain.Lesson;
import edu.auburn.domain.LessonFile;
import edu.auburn.domain.LessonStudent;
import edu.auburn.service.ILessonFileService;
import edu.auburn.service.ILessonService;
import edu.auburn.service.ILessonStudentService;
import edu.auburn.service.IUserService;
import edu.auburn.service.impl.LessonFileService;
import edu.auburn.service.impl.LessonService;
import edu.auburn.service.impl.LessonStudentService;
import edu.auburn.service.impl.UserService;
import edu.auburn.utils.LessonFileType;
import edu.auburn.utils.StringConfig;

@WebServlet("/teacher")
public class TeacherServlet extends HttpServlet {
	private IUserService userService = new UserService();
	private ILessonService lessonService = new LessonService();
	private int uid;
	private ILessonFileService fileService = new LessonFileService();
	private ILessonStudentService studentService = new LessonStudentService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		if (session == null) {
			resp.sendRedirect(req.getContextPath() + "/user");
		} else {
			String user = (String) session.getAttribute("user");

			EduUser u = userService.getUserByName(user);
			if (null != u && u.getType() == 2) {
				uid = u.getUid();
				req.setAttribute("uid", uid);
				String method = req.getParameter("method");
				if (StringUtils.isNullOrEmpty(method)) {
					req.getRequestDispatcher("/jsp/teacher_manage.jsp").forward(req, resp);
				} else if (method.equals("lessonlist")) {
					showlessons(req, resp);
				} else if (method.equals("add")) {
					addLesson(req, resp);
					showlessons(req, resp);
				} else if (method.equals("delete")) {
					delete(req, resp);
					showlessons(req, resp);
				} else if (method.equals("details")) {
					details(req, resp);
				} else if (method.equals("managestudent")) {
					manageStudent(req, resp);
				} else if (method.equals("update")) {
					updateRoleTa(req, resp);
				} else if (method.equals("degrade")) {
					updateRoleStu(req, resp);
				} else if (method.equals("upload")) {
					upload(req, resp);
				} else if (method.equals("deletef")) {
					deleteFile(req, resp);
				}
			} else {
				resp.sendRedirect(req.getContextPath() + "");
			}
		}
	}

	private void deleteFile(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("lid");
		int lid = Integer.parseInt(id);
		id = req.getParameter("fid");
		int fid = Integer.parseInt(id);
		fileService.delFileById(fid);
		req.setAttribute("lid", lid);
		req.getRequestDispatcher("/teacher?method=details&lid=" + lid).forward(req, resp);
	}

	private void updateRoleTa(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String sid = req.getParameter("sid");
		String lid = req.getParameter("lid");
		studentService.updateToTaByLidAndSid(Integer.parseInt(lid), Integer.parseInt(sid));
		manageStudent(req, resp);
	}

	private void updateRoleStu(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String sid = req.getParameter("sid");
		String lid = req.getParameter("lid");
		studentService.updateToStuByLidAndSid(Integer.parseInt(lid), Integer.parseInt(sid));
		manageStudent(req, resp);
	}

	private void manageStudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String lid = req.getParameter("lid");
		int id = Integer.parseInt(lid);
		Lesson lesson = lessonService.getLessonByLid(id);
		req.setAttribute("lesson", lesson);
		List<LessonStudent> list = studentService.getLSByLid(id);
		req.setAttribute("list", list);
		req.getRequestDispatcher("/jsp/lesson_user.jsp").forward(req, resp);
	}

	private void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String lid = req.getParameter("lid");
		lessonService.delLessonById(Integer.parseInt(lid));
	}

	private void details(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String lid = req.getParameter("lid");
		int id = Integer.parseInt(lid);
		Lesson lesson = lessonService.getLessonByLid(id);
		req.setAttribute("lesson", lesson);
		List<LessonFile> list = fileService.getAllFileByLid(id);
		req.setAttribute("list", list);
		req.getRequestDispatcher("/jsp/lesson_detail.jsp").forward(req, resp);
	}

	private void upload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String lessonid = request.getParameter("lid");
		int lid = Integer.parseInt(lessonid);
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		long size = 300 * 1024 * 1024;
		upload.setFileSizeMax(size);
		upload.setSizeMax(5 * size);
		upload.setHeaderEncoding("UTF-8");
		LessonFile lessonFile = new LessonFile();
		if (upload.isMultipartContent(request)) {
			try {
				List<FileItem> list = upload.parseRequest(request);
				for (FileItem item : list) {
					if (item.isFormField()) {
						String name = item.getFieldName();
						String value = item.getString();
						if (name.equals("fdesc")) {
							lessonFile.setFdesc(value);
						}
					} else {
						String name = System.currentTimeMillis() + "_" + item.getName().replaceAll(" ", "_");// file
																												// name
						int begin = name.lastIndexOf(".");
						String ext = name.substring(begin + 1, name.length());// ext
																				// name
						String type = getType(ext);
						if (type.equals(LessonFileType.NON.toString())) {
							// file illegal
							String message = StringConfig.FILENOTSUPPORTED;
							request.setAttribute("message", message);
							// forward
						} else {
							String basePath = getServletContext().getRealPath("/upload");
							File dir = new File(basePath);
							if (!dir.exists() && !dir.isDirectory()) {
								dir.mkdirs();
							}
							File file = new File(basePath, name);
							item.write(file);
							item.delete();
							lessonFile.setName(name);
							lessonFile.setFtype(type);
							lessonFile.setLid(lid);
							lessonFile.setPath(basePath + "/" + name);
							fileService.addFile(lessonFile);
						}
					}
				}
			} catch (FileUploadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		request.getRequestDispatcher("/teacher?method=details&lid=" + lid).forward(request, response);
	}

	private String getType(String ext) {
		ext = ext.toUpperCase();
		if (ext.equals(LessonFileType.TXT.toString())) {
			return "TXT";
		} else if (ext.equals(LessonFileType.PDF.toString())) {
			return "PDF";
		} else if (ext.equals(LessonFileType.MP4.toString())) {
			return "MP4";
		} else if (ext.equals(LessonFileType.WAV.toString())) {
			return "WAV";
		} else if (ext.equals(LessonFileType.DOC.toString())) {
			return "DOC";
		} else if (ext.equals(LessonFileType.DOCX.toString())) {
			return "DOCX";
		} else {
			return LessonFileType.NON.toString();
		}
	}

	private void addLesson(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String lname = req.getParameter("lname");
		String ldesc = req.getParameter("ldesc");
		String type = req.getParameter("ltype");
		if (!StringUtils.isNullOrEmpty(lname) && !StringUtils.isNullOrEmpty(ldesc)) {
			Lesson l = new Lesson();
			l.setName(lname);
			l.setDate(new Date(System.currentTimeMillis()));
			l.setDesc(ldesc);
			l.setType(Integer.parseInt(type));
			l.setUid(uid);
			lessonService.addLesson(l);
		}
	}

	private void showlessons(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Lesson> list = lessonService.getLessonsByUid(uid);
		req.setAttribute("list", list);
		req.getRequestDispatcher("/jsp/manage_lesson.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
