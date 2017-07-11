package edu.auburn.servlet;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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
import edu.auburn.domain.Exam;
import edu.auburn.domain.ExamVideo;
import edu.auburn.domain.ExamWord;
import edu.auburn.domain.Lesson;
import edu.auburn.domain.LessonFile;
import edu.auburn.domain.LessonStudent;
import edu.auburn.service.IExamService;
import edu.auburn.service.IExamVideoService;
import edu.auburn.service.IExamWordService;
import edu.auburn.service.ILessonFileService;
import edu.auburn.service.ILessonService;
import edu.auburn.service.ILessonStudentService;
import edu.auburn.service.IUserService;
import edu.auburn.service.impl.ExamService;
import edu.auburn.service.impl.ExamVideoService;
import edu.auburn.service.impl.ExamWordService;
import edu.auburn.service.impl.LessonFileService;
import edu.auburn.service.impl.LessonService;
import edu.auburn.service.impl.LessonStudentService;
import edu.auburn.service.impl.UserService;
import edu.auburn.utils.LessonFileType;
import edu.auburn.utils.StringConfig;

@WebServlet("/student")
public class StudentServlet extends HttpServlet {
	private IUserService userService = new UserService();
	private int uid;
	private ILessonService lessonService = new LessonService();
	private ILessonFileService lessonFileService = new LessonFileService();
	private IExamService examService = new ExamService();
	private IExamVideoService examVideoService = new ExamVideoService();
	private IExamWordService wordService = new ExamWordService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		HttpSession session = req.getSession();
		if (session == null) {
			resp.sendRedirect(req.getContextPath() + "/user");
		} else {
			String uname = (String) session.getAttribute("user");
			if (uname == null || "".equals(uname)) {
				resp.sendRedirect(req.getContextPath() + "/user");
			} else {
				EduUser eduUser = userService.getUserByName(uname);
				if (null == eduUser) {
					resp.sendRedirect(req.getContextPath() + "/user");
				} else {
					if (eduUser.getType() == 4) {
						uid = eduUser.getUid();
						req.setAttribute("uid", uid);
						String method = req.getParameter("method");
						if (StringUtils.isNullOrEmpty(method)) {
							req.getRequestDispatcher("/jsp/student_manage.jsp").forward(req, resp);
						} else if (method.equals("lessons")) {
							showLessons(req, resp);
						} else if (method.equals("ldetails")) {
							lessonDetails(req, resp);
						} else if (method.equals("reg")) {
							regLesson(req, resp);
						} else if (method.equals("unreg")) {
							unregLesson(req, resp);
						} else if (method.equals("learnlesson")) {
							learnLesson(req, resp);
						} else if (method.equals("uploadl")){
							taUploadLessonFile(req, resp);
						} else if (method.equals("deletef")){
							taDelLessonFile(req, resp);
						} else if (method.equals("tme")){
							taManageExam(req, resp);
						} else if (method.equals("examup")){
							taUploadExam(req, resp);
						} else if (method.equals("uploadv")){
							taUploadVideo(req, resp);
						} else if (method.equals("uploadw")){
							taAddWord(req, resp);
						} else if (method.equals("deletev")){
							taDelVideo(req, resp);
						} else if (method.equals("deletew")){
							taDelWord(req, resp);
						} else if (method.equals("studentexamlist")){
							studentExamList(req, resp);
						} else if (method.equals("takeexam")){
							resp.getWriter().write("comming...");
							//studentTakeExam(req, resp);
						}
					} else {
						resp.sendRedirect(req.getContextPath() + "");
					}
				}
			}
		}
	}
	
	/**
	 * 
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void studentTakeExam(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String examId = req.getParameter("eid");
		int eid = Integer.parseInt(examId);
		req.getRequestDispatcher("/jsp/takingexam.jsp").forward(req, resp);
	}
	
	
	
	/**
	 * student-exam-list
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void studentExamList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String lessonId = req.getParameter("lid");
		int lid = Integer.parseInt(lessonId);
		Lesson lesson = lessonService.getLessonByLid(lid);
		List<Exam> exams = examService.getExamsByLid(lid);
		req.setAttribute("exams", exams);
		req.setAttribute("lesson", lesson);
		req.getRequestDispatcher("/jsp/student_exam_list.jsp").forward(req, resp);
	}

	
	/**
	 * ta-delete-exam-word
	 * 
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void taDelWord(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("vid");
		int fid = Integer.parseInt(id);
		wordService.delWordById(fid);
		taUploadExam(req, resp);
	}
	
	
	
	/**
	 * ta-exam-add-word
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void taAddWord(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// req.setCharacterEncoding("utf-8");
		String lessonid = req.getParameter("lid");
		 int lid = Integer.parseInt(lessonid);
		String examId = req.getParameter("eid");
		int eid = Integer.parseInt(examId);
		 Lesson lesson = lessonService.getLessonByLid(lid);
		Exam exam = examService.getExamById(eid);
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		long size = 1024 * 1024 * 1024;
		upload.setFileSizeMax(size);
		upload.setSizeMax(2 * size);
		upload.setHeaderEncoding("UTF-8");
		ExamWord word = new ExamWord();
		if (upload.isMultipartContent(req)) {
			try {
				List<FileItem> list = upload.parseRequest(req);
				for (FileItem item : list) {
					if (item.isFormField()) {
						String name = item.getFieldName();
						String value = new String(item.get(), "utf-8");
						if (name.equals("ipa")) {
							word.setPron(value);
						}
					} else {
						String name = System.currentTimeMillis() + "_" + item.getName().replaceAll(" ", "_");// file
						int begin = name.lastIndexOf(".");
						String ext = name.substring(begin + 1, name.length());// ext
						String type = getType(ext);
						if (!type.equals(LessonFileType.WAV.toString())) {
							// file illegal
							String message = StringConfig.FILENOTSUPPORTED;
							req.setAttribute("message", message);
							// forward
						} else {
							String basePath = getServletContext().getRealPath("/upload/" + lesson.getName() + "/" + exam.getName() + "/");
							File dir = new File(basePath);
							if (!dir.exists() && !dir.isDirectory()) {
								dir.mkdirs();
							}
							File file = new File(basePath, name);
							item.write(file);
							item.delete();
							word.setName(name);
							word.setEid(eid);
							word.setPath(basePath + "/" + name);
							word.setType(type);
							wordService.addWord(word);
						}
					}
				}
			} catch (FileUploadException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		taUploadExam(req, resp);
	}
	
	
	/**
	 * ta-delete-exam-video
	 * 
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void taDelVideo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("vid");
		int vid = Integer.parseInt(id);
		examVideoService.delVideoById(vid);
		taUploadExam(req, resp);
	}
	
	
	
	
	/**
	 * ta-exam-upload-video
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void taUploadVideo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		String lessonid = req.getParameter("lid");
		int lid = Integer.parseInt(lessonid);
		String examId = req.getParameter("eid");
		int eid = Integer.parseInt(examId);
		Lesson lesson = lessonService.getLessonByLid(lid);
		Exam exam = examService.getExamById(eid);
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		long size = 1024 * 1024 * 1024;
		upload.setFileSizeMax(size);
		upload.setSizeMax(2 * size);
		upload.setHeaderEncoding("UTF-8");
		ExamVideo video = new ExamVideo();
		if (upload.isMultipartContent(req)) {
			try {
				List<FileItem> list = upload.parseRequest(req);
				for (FileItem item : list) {
					if (item.isFormField()) {
						String name = item.getFieldName();
						String value = item.getString();
						if (name.equals("fdesc")) {
							video.setDesc(value);
						}
					} else {
						String name = System.currentTimeMillis() + "_" + item.getName().replaceAll(" ", "_");// file
						int begin = name.lastIndexOf(".");
						String ext = name.substring(begin + 1, name.length());// ext
						String type = getType(ext);
						if (!type.equals(LessonFileType.MP4.toString())) {
							// file illegal
							String message = StringConfig.FILENOTSUPPORTED;
							req.setAttribute("message", message);
							req.getRequestDispatcher("/jsp/error.jsp").forward(req, resp);
							// forward
						} else {
							String basePath = getServletContext().getRealPath("/upload/" + lesson.getName()+"/"+ exam.getName() + "/");
							File dir = new File(basePath);
							if (!dir.exists() && !dir.isDirectory()) {
								dir.mkdirs();
							}
							File file = new File(basePath, name);
							item.write(file);
							item.delete();
							video.setName(name);
							video.setEid(eid);
							video.setPath(basePath + "/" + name);
							video.setType(type);
							examVideoService.addVideo(video);
						}
					}
				}
			} catch (FileUploadException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		taUploadExam(req, resp);
	}

	
	
	
	/**
	 * ta-exam-upload-file
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void taUploadExam(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String lessonId = req.getParameter("lid");
		String examId = req.getParameter("eid");
		int lid = Integer.parseInt(lessonId);
		int eid = Integer.parseInt(examId);
		Exam exam = examService.getExamById(eid);
		Lesson lesson = lessonService.getLessonByLid(lid);
		req.setAttribute("exam", exam);
		req.setAttribute("lesson", lesson);
		List<ExamVideo> videos = examVideoService.getAllVideosByEid(eid);
		List<ExamWord> words = wordService.getAllWordsByEid(eid);
		req.setAttribute("videos", videos);
		req.setAttribute("words", words);
		req.getRequestDispatcher("/jsp/ta_exam_manage.jsp").forward(req, resp);
	}
	
	/**
	 * calculate file type by file extend name
	 * 
	 * @param ext
	 * @return
	 */
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
	
	
	/**
	 * ta-Del-lesson-file
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void taDelLessonFile(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String fileId = req.getParameter("fid");
		int fid = Integer.parseInt(fileId);
		lessonFileService.delFileById(fid);
		learnLesson(req, resp);
	}

	
	/**
	 * ta-upload-lesson-file
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void taUploadLessonFile(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String lessonid = req.getParameter("lid");
		int lid = Integer.parseInt(lessonid);
		Lesson lesson = lessonService.getLessonByLid(lid);
		req.setAttribute("lesson", lesson);
		req.setAttribute("lid", lid);
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		long size = 300 * 1024 * 1024;
		upload.setFileSizeMax(size);
		upload.setSizeMax(5 * size);
		upload.setHeaderEncoding("UTF-8");
		LessonFile lessonFile = new LessonFile();
		if (upload.isMultipartContent(req)) {
			try {
				List<FileItem> list = upload.parseRequest(req);
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
							req.setAttribute("message", message);
							req.getRequestDispatcher("/jsp/error.jsp").forward(req, resp);
							// forward
						} else {
							String basePath = getServletContext().getRealPath("/upload/" + lesson.getName() + "/");
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
							lessonFileService.addFile(lessonFile);
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
		learnLesson(req, resp);
	}
	
	
	
	
	/**
	 * ta-manage-exam
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void taManageExam(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String lessonId = req.getParameter("lid");
		int lid = Integer.parseInt(lessonId);
		List<Exam> exams = examService.getExamsByLid(lid);
		Lesson lesson = lessonService.getLessonByLid(lid);
		req.setAttribute("lesson", lesson);
		req.setAttribute("exams", exams);
		req.getRequestDispatcher("/jsp/ta_exam_list.jsp").forward(req, resp);
	}
	/**
	 * student-learn-lesson
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void learnLesson(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("lid");
		int lid = Integer.parseInt(id);
		req.setAttribute("lid", lid);
		Lesson lesson = lessonService.getLessonByLid(lid);
		req.setAttribute("lesson", lesson);
		List<LessonFile> files = lessonFileService.getAllFileByLid(lid);
		req.setAttribute("files", files);
		LessonStudent lessonStudent = lsService.getLSByUidAndLid(uid, lid);
		if(1 == lessonStudent.getType()){// ta
			req.getRequestDispatcher("/jsp/ta_lesson_detail.jsp").forward(req, resp);
		}else{// student
			req.getRequestDispatcher("/jsp/student_study_lesson.jsp").forward(req, resp);
		}
	}

	/**
	 * student-lesson-details
	 * 
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void lessonDetails(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("lid");
		int lid = Integer.parseInt(id);
		Lesson lesson = lessonService.getLessonByLid(lid);
		req.setAttribute("lesson", lesson);
		boolean isReg = lsService.checkRegLesson(lid, uid);
		req.setAttribute("isreg", isReg);
		req.getRequestDispatcher("/jsp/student_lesson_details.jsp").forward(req, resp);
	}

	/**
	 * student-lesson-register
	 * 
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void regLesson(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("lid");
		int lid = Integer.parseInt(id);
		Lesson lesson = lessonService.getLessonByLid(lid);
		req.setAttribute("lesson", lesson);
		boolean boo = lsService.addLessonStudent(lid, uid);
		if (boo) {
			req.getRequestDispatcher("/student?method=ldetails&lid=" + lid).forward(req, resp);
		}
	}

	/**
	 * student-lesson-unregister
	 * 
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void unregLesson(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("lid");
		int lid = Integer.parseInt(id);
		Lesson lesson = lessonService.getLessonByLid(lid);
		req.setAttribute("lesson", lesson);
		boolean boo = lsService.delLessonStudent(lid, uid);
		if (boo) {
			req.getRequestDispatcher("/student?method=ldetails&lid=" + lid).forward(req, resp);
		}
	}

	private ILessonStudentService lsService = new LessonStudentService();

	/**
	 * student-show-lesson-list
	 * 
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void showLessons(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Lesson> allLessons = lessonService.getAllLessons();
		List<LessonStudent> registeredLessons = lsService.getLSBySid(uid);
		req.setAttribute("allLessons", allLessons);
		req.setAttribute("registeredLessons", registeredLessons);
		req.getRequestDispatcher("/jsp/student_lesson_list.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
