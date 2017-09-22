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

import edu.auburn.domain.DisplayStudentExamResult;
import edu.auburn.domain.EduUser;
import edu.auburn.domain.Exam;
import edu.auburn.domain.ExamResult;
import edu.auburn.domain.ExamScore;
import edu.auburn.domain.ExamVideo;
import edu.auburn.domain.ExamWord;
import edu.auburn.domain.Lesson;
import edu.auburn.domain.LessonFile;
import edu.auburn.domain.LessonStudent;
import edu.auburn.domain.WordStudent;
import edu.auburn.domain.WordVideo;
import edu.auburn.service.IExamResultService;
import edu.auburn.service.IExamService;
import edu.auburn.service.IExamVideoService;
import edu.auburn.service.IExamWordService;
import edu.auburn.service.ILessonFileService;
import edu.auburn.service.ILessonService;
import edu.auburn.service.ILessonStudentService;
import edu.auburn.service.IStudentExamService;
import edu.auburn.service.IUserService;
import edu.auburn.service.IWordStudentService;
import edu.auburn.service.IWordVideoService;
import edu.auburn.service.impl.ExamResultService;
import edu.auburn.service.impl.ExamService;
import edu.auburn.service.impl.ExamVideoService;
import edu.auburn.service.impl.ExamWordService;
import edu.auburn.service.impl.LessonFileService;
import edu.auburn.service.impl.LessonService;
import edu.auburn.service.impl.LessonStudentService;
import edu.auburn.service.impl.StudentExamService;
import edu.auburn.service.impl.UserService;
import edu.auburn.service.impl.WordStudentService;
import edu.auburn.service.impl.WordVideoService;
import edu.auburn.utils.CalculateScore;
import edu.auburn.utils.DownloadUtils;
import edu.auburn.utils.LessonFileType;
import edu.auburn.utils.ScoreUtils;
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
	private IExamResultService resultService = new ExamResultService();
	private IWordVideoService wordVideoService = new WordVideoService();
	private IStudentExamService studentExamService = new StudentExamService();
	private IWordStudentService wordStudentService = new WordStudentService();
	private IExamResultService examResultService = new ExamResultService();

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
						} else if (method.equals("uploadl")) {
							taUploadLessonFile(req, resp);
						} else if (method.equals("deletef")) {
							taDelLessonFile(req, resp);
						} else if (method.equals("tme")) {
							taManageExam(req, resp);
						} else if (method.equals("examup")) {
							examDetails(req, resp);
						} else if (method.equals("uploadv")) {
							taUploadVideo(req, resp);
						} else if (method.equals("taaddword")) {
							taAddWord(req, resp);
						} else if (method.equals("deletev")) {
							taDelVideo(req, resp);
						} else if (method.equals("delw")) {
							taDelWord(req, resp);
						} else if (method.equals("studentexamlist")) {
							studentExamList(req, resp);
						} else if (method.equals("takeexam")) {
							studentTakeExam(req, resp);
						} else if (method.equals("result")) {
							examResult(req, resp);
						} else if (method.equals("download")) {
							downLoad(req, resp);
						} else if (method.equals("ta_download")) {
							downLoad(req, resp);
						} else if (method.equals("wordDetails")) {
							wordDetails(req, resp);
						} else if (method.equals("wordAddVideo")) {
							wordAddVideo(req, resp);
						} else if (method.equals("deleteWordVideo")) {
							deleteWordVideo(req, resp);
						} else if (method.equals("takeexamspe")) {
							studentTakeExamSpe(req, resp);
						} else if (method.equals("saveanswer")) {
							studentSaveAnswer(req, resp);
						} else if (method.equals("submitresult")) {
							studentSubmitResult(req, resp);
						}
					} else {
						resp.sendRedirect(req.getContextPath() + "");
					}
				}
			}
		}
	}

	/**
	 * ta-new-word-delete-video
	 * 
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void deleteWordVideo(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String id = req.getParameter("wid");
		int wid = Integer.parseInt(id);
		id = req.getParameter("eid");
		int eid = Integer.parseInt(id);
		id = req.getParameter("lid");
		int lid = Integer.parseInt(id);
		id = req.getParameter("vid");
		int vid = Integer.parseInt(id);
		wordVideoService.delVideoById(vid);
		wordDetails(req, resp);
	}

	/**
	 * ta-new-word-add-videos
	 * 
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void wordAddVideo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		String id = req.getParameter("lid");
		int lid = Integer.parseInt(id);
		id = req.getParameter("eid");
		int eid = Integer.parseInt(id);
		id = req.getParameter("wid");
		int wid = Integer.parseInt(id);
		Lesson lesson = lessonService.getLessonByLid(lid);
		Exam exam = examService.getExamById(eid);
		ExamWord word = wordService.getExamWordByFid(wid);

		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		long size = 1024 * 1024 * 1024;
		upload.setFileSizeMax(size);
		upload.setSizeMax(2 * size);
		upload.setHeaderEncoding("UTF-8");
		WordVideo video = new WordVideo();
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
							// forward
						} else {
							String basePath = getServletContext()
									.getRealPath(File.separator+"upload"+File.separator + lesson.getName().replaceAll(" ", "_") + File.separator
											+ exam.getName().replaceAll(" ", "_") + File.separator + word.getFid() + File.separator);
							File dir = new File(basePath);
							if (!dir.exists() && !dir.isDirectory()) {
								dir.mkdirs();
							}
							File file = new File(basePath, name);
							item.write(file);
							item.delete();
							video.setName(name);
							video.setEid(eid);
							video.setWid(wid);
							video.setPath(basePath + File.separator + name);
							wordVideoService.addVideo(video);
						}
					}
				}
			} catch (FileUploadException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		wordDetails(req, resp);

	}

	/**
	 * ta-new-exam-details
	 * 
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void examDetails(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// req.setCharacterEncoding("UTF-8");
		// resp.setCharacterEncoding("UTF-8");
		String id = req.getParameter("eid");
		int eid = Integer.parseInt(id);
		id = req.getParameter("lid");
		int lid = Integer.parseInt(id);
		Lesson l = lessonService.getLessonByLid(lid);
		Exam e = examService.getExamById(eid);
		// List<ExamVideo> videos = videoService.getAllVideosByEid(eid);
		List<ExamWord> words = wordService.getAllWordsByEid(eid);
		req.setAttribute("words", words);
		// req.setAttribute("videos", videos);
		req.setAttribute("lesson", l);
		req.setAttribute("exam", e);
		req.getRequestDispatcher("/jsp/new_ta_exam_details.jsp").forward(req, resp);
	}

	/**
	 * student-download-lesson-file
	 * 
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void downLoad(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("fid");
		int fid = Integer.parseInt(id);
		LessonFile file = lessonFileService.getFileByFid(fid);
		String name = file.getName();
		String path = file.getPath();
		DownloadUtils utils = new DownloadUtils();
		utils.down(path, name, req, resp);
	}

	/**
	 * student-exam-result
	 * 
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void examResult(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("eid");
		int eid = Integer.parseInt(id);
		Exam exam = examService.getExamById(eid);
		String result = req.getParameter("result");
		resultService.addResult(uid, eid, result);
		ExamResult examResult = resultService.getResultByUidAndEid(uid, eid);
		studentExamService.takeExam(eid, uid);
		List<DisplayStudentExamResult> ds = new ArrayList<>();
		List<String> sAnswer = examResult.getsAnswers();
		List<String> tAnswer = examResult.gettAnswers();
		List<Float> scores = examResult.getScoreList();
		float totalPercentage = 0;
		
		for (int i = 0; i < sAnswer.size(); i++) {
			DisplayStudentExamResult r = new DisplayStudentExamResult();
			float distance = scores.get(i);
			String stuAns = sAnswer.get(i);
			String tAns = tAnswer.get(i);
			r.setsAnswer(sAnswer.get(i));
			r.settAnswer(tAnswer.get(i));
			r.setScore(distance);
			String percentage = CalculateScore.getPercentage(stuAns, tAns, distance);
			r.setPercentage(percentage);
			
			totalPercentage += Float.parseFloat(percentage.substring(0, percentage.length() - 1));
			
			ds.add(r);
		}
		float aveScore = examResult.getTotal()/ds.size();
		aveScore = (float) Math.round(aveScore * 100) / 100;
		req.setAttribute("ave", aveScore);
		req.setAttribute("exam", exam);
		req.setAttribute("result", ds);
		req.setAttribute("total", examResult.getTotal() + "");
		
		totalPercentage = (float) Math.round(totalPercentage * 100000) / 100000;
		req.setAttribute("totalp", totalPercentage + "%");
		float temp = totalPercentage / ds.size();
		float aveP = (float) Math.round(temp * 1000) / 1000;
		req.setAttribute("averagep", aveP + "%");
		
		req.getRequestDispatcher("/jsp/student_exam_result.jsp").forward(req, resp);
	}

	/**
	 * student-take-exam-specific-question
	 * 
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void studentTakeExamSpe(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String id = req.getParameter("eid");
		int eid = Integer.parseInt(id);
		id = req.getParameter("current");
		int current = Integer.parseInt(id);
		Exam exam = examService.getExamById(eid);
		List<ExamWord> words = wordService.getAllWordsByEid(eid);
		ExamWord word = words.get(current);
		List<WordVideo> videos = wordVideoService.getAllVideosByWid(word.getFid());
		int total = words.size();
		String path = word.getPath();
		int index = path.indexOf(File.separator+"upload");
		String p = req.getContextPath() + path.substring(index);
		word.setPath(p);

		for (int i = 0; i < videos.size(); i++) {
			WordVideo v = videos.get(i);
			String vPath = v.getPath();
			int pos = vPath.indexOf(File.separator+"upload"+File.separator);
			String pa = req.getContextPath() + vPath.substring(pos);
			v.setPath(pa);
		}
		String answer = "";
		WordStudent wordStudent = wordStudentService.getStudentAnswerModelBySidAndWid(uid, word.getFid());
		if (wordStudent != null && !"".equals(wordStudent.getAnswer())) {
			answer = wordStudent.getAnswer();
		}
		req.setAttribute("answer", answer);
		req.setAttribute("total", total);
		req.setAttribute("current", current);
		req.setAttribute("videos", videos);
		req.setAttribute("word", word);
		req.setAttribute("exam", exam);
		req.setAttribute("words", words);
		req.getRequestDispatcher("/jsp/new_takingexam.jsp").forward(req, resp);
	}

	/**
	 * here********* student-submit-result
	 * 
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void studentSubmitResult(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String id = req.getParameter("eid");
		int eid = Integer.parseInt(id);
		List<WordStudent> list = wordStudentService.getStudentAnswerListBySidAndEid(uid, eid);

		studentExamService.takeExam(eid, uid);
		Exam exam = examService.getExamById(eid);
		float tScore = 0;
		
		float totalPercentage = 0;
		// result
		List<DisplayStudentExamResult> ds = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			WordStudent ws = list.get(i);
			String teacherAnswer = wordService.getExamWordByFid(ws.getWid()).getPron();
			DisplayStudentExamResult displayStudentExamResult = new DisplayStudentExamResult();
			displayStudentExamResult.setsAnswer(ws.getAnswer());
			displayStudentExamResult.settAnswer(teacherAnswer);
			displayStudentExamResult.setScore(ws.getScore());
			displayStudentExamResult.setSid(ws.getSid());
			displayStudentExamResult.setWid(ws.getWid());
			String percentage = CalculateScore.getPercentage(ws.getAnswer(), teacherAnswer, ws.getScore());
			displayStudentExamResult.setPercentage(percentage);
			totalPercentage += Float.parseFloat(percentage.substring(0, percentage.length() - 1));
			ds.add(displayStudentExamResult);
			tScore += ws.getScore();
			
		}

		
		
		
		totalPercentage = (float) Math.round(totalPercentage * 10000) / 10000;
		float temp = totalPercentage / list.size();
		float aveP = (float) Math.round(temp * 1000) / 1000;
		
		
		
		
		ExamResult er = new ExamResult();
		er.setEid(eid);
		er.setUid(uid);
		er.setTotal(tScore);
		examResultService.addResult(er);
		if (exam.getIfPractice() == 1) {// practice, then show the result with
										// answer.
			req.setAttribute("exam", exam);
			req.setAttribute("result", ds);
			float aveScore = er.getTotal()/list.size();
			aveScore = (float) Math.round(aveScore * 100) / 100;
			req.setAttribute("ave", aveScore);
			req.setAttribute("total", er.getTotal() + "");
			
			req.setAttribute("averagep", aveP + "%");
			req.setAttribute("totalp", totalPercentage + "%");
			
			req.getRequestDispatcher("/jsp/student_exam_result.jsp").forward(req, resp);
		} else {// exam
			long currentTime = System.currentTimeMillis();
			long dueDate = exam.getEdue().getTime();
			// if after exam due data, show
			if (currentTime > dueDate) {
				req.setAttribute("exam", exam);
				req.setAttribute("result", ds);
				float aveScore = er.getTotal()/list.size();
				aveScore = (float) Math.round(aveScore * 100) / 100;
				req.setAttribute("ave", aveScore);
				req.setAttribute("total", er.getTotal() + "");
				
				req.setAttribute("averagep", aveP + "%");
				req.setAttribute("totalp", totalPercentage + "%");
				
				req.getRequestDispatcher("/jsp/student_exam_result.jsp").forward(req, resp);
			} else {
				// else show nothing.
				req.setAttribute("message", StringConfig.SCORE_NOT_PUBLISHED);
				req.getRequestDispatcher("/jsp/non_student.jsp").forward(req, resp);
			}

		}
	}

	/**
	 * student-save-answer
	 * 
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void studentSaveAnswer(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String id = req.getParameter("eid");
		int eid = Integer.parseInt(id);
		id = req.getParameter("wid");
		int wid = Integer.parseInt(id);
		String answer = req.getParameter("studentanswer");
		WordStudent ws = new WordStudent();
		ws.setAnswer(answer);
		ws.setEid(eid);
		ws.setSid(uid);
		ws.setWid(wid);
		ExamWord word = wordService.getExamWordByFid(wid);
		float score = 0;
		try {
			score = CalculateScore.getScore(answer, word.getPron());
		} catch (Exception e) {
			req.setAttribute("message", StringConfig.SCORE_CALCULATE_ERROR);
			req.getRequestDispatcher("/jsp/non_student.jsp").forward(req, resp);
		}

		ws.setScore(score);
		WordStudent exist = wordStudentService.getStudentAnswerModelBySidAndWid(uid, wid);
		if (null != exist) {
			wordStudentService.updateAnswer(ws);
		} else {
			wordStudentService.addStudentAnswerForWord(ws);
		}
		studentTakeExamSpe(req, resp);
	}

	/**
	 * student-take-exam
	 * 
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void studentTakeExam(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String examId = req.getParameter("eid");
		int eid = Integer.parseInt(examId);
		Exam exam = examService.getExamById(eid);
		boolean taken = studentExamService.checkIfExamTakenByStudent(eid, uid);
		if (taken) { // has taken the exam, show result
			if ((exam.getIfPractice() == 1) // is practice
					|| // or is exam && past the due date
					(exam.getIfPractice() == 0 && System.currentTimeMillis() > exam.getEdue().getTime())) {
				// show result
				List<WordStudent> list = wordStudentService.getStudentAnswerListBySidAndEid(uid, eid);
				List<DisplayStudentExamResult> ds = new ArrayList<>();
				float tScore = 0;
				float totalPercentage = 0;
				for (int i = 0; i < list.size(); i++) {
					WordStudent ws = list.get(i);
					String teacherAnswer = wordService.getExamWordByFid(ws.getWid()).getPron();
					DisplayStudentExamResult displayStudentExamResult = new DisplayStudentExamResult();
					displayStudentExamResult.setsAnswer(ws.getAnswer());
					displayStudentExamResult.settAnswer(teacherAnswer);
					displayStudentExamResult.setScore(ws.getScore());
					displayStudentExamResult.setSid(ws.getSid());
					displayStudentExamResult.setWid(ws.getWid());
					String percentage = CalculateScore.getPercentage(ws.getAnswer(), teacherAnswer, ws.getScore());
					displayStudentExamResult.setPercentage(percentage);
					
					totalPercentage += Float.parseFloat(percentage.substring(0, percentage.length()-1));
					
					ds.add(displayStudentExamResult);
					tScore += ws.getScore();
				}
				
				
				
				float aveScore = tScore/list.size();
				aveScore = (float) Math.round(aveScore * 100) / 100;
				req.setAttribute("ave", aveScore);
				req.setAttribute("result", ds);
				req.setAttribute("total", tScore + "");
				req.setAttribute("exam", exam);
				totalPercentage = (float) Math.round(totalPercentage * 100000) / 100000;
				req.setAttribute("totalp", totalPercentage + "%");
				float temp = totalPercentage / list.size();
				float aveP = (float) Math.round(temp * 1000) / 1000;
				req.setAttribute("averagep", aveP + "%");
				
				
				req.getRequestDispatcher("/jsp/student_exam_result.jsp").forward(req, resp);
			}else{
				// else show nothing.
				req.setAttribute("message", StringConfig.SCORE_NOT_PUBLISHED);
				req.getRequestDispatcher("/jsp/non_student.jsp").forward(req, resp);
			}
		} else { // go to take the exam
			if(System.currentTimeMillis() > exam.getEdue().getTime()){
				req.setAttribute("message", StringConfig.PASS_DUE_DATE);
				req.getRequestDispatcher("/jsp/non_student.jsp").forward(req, resp);
			}
			
			List<ExamWord> words = wordService.getAllWordsByEid(eid);
			int totalWords = words.size();

			List<WordVideo> wordVideos = null;
			// List<ExamVideo> videos = examVideoService.getAllVideosByEid(eid);
			if (words == null || words.size() == 0) {
				req.setAttribute("message", "the exam has not been published");
				req.getRequestDispatcher("/jsp/non_student.jsp").forward(req, resp);
			} else {
				wordVideos = wordVideoService.getAllVideosByWid(words.get(0).getFid());
				ExamWord word = words.get(0);
				String path = word.getPath();
				int index = path.indexOf(File.separator+"upload");
				String p = req.getContextPath() + path.substring(index);
				word.setPath(p);
				/********* here ******/
				for (int i = 0; i < wordVideos.size(); i++) {
					WordVideo v = wordVideos.get(i);
					String vPath = v.getPath();
					int pos = vPath.indexOf(File.separator+"upload"+File.separator);
					String pa = req.getContextPath() + vPath.substring(pos);
					v.setPath(pa);
				}
				req.setAttribute("total", totalWords);
				req.setAttribute("current", 0);
				req.setAttribute("videos", wordVideos);
				req.setAttribute("word", word);
				req.setAttribute("exam", exam);
				req.setAttribute("words", words);
				req.getRequestDispatcher("/jsp/new_takingexam.jsp").forward(req, resp);
			}

		}

	}

	/**
	 * student-exam-list
	 * 
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void studentExamList(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
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
		examDetails(req, resp);
	}

	/**
	 * ta-new-word-details(for delete or upload word video)
	 * 
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void wordDetails(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("wid");
		int wid = Integer.parseInt(id);
		id = req.getParameter("eid");
		int eid = Integer.parseInt(id);
		id = req.getParameter("lid");
		int lid = Integer.parseInt(id);
		Exam exam = examService.getExamById(eid);
		Lesson lesson = lessonService.getLessonByLid(lid);
		ExamWord word = wordService.getExamWordByFid(wid);
		List<WordVideo> videos = wordVideoService.getAllVideosByWid(wid);
		req.setAttribute("exam", exam);
		req.setAttribute("lesson", lesson);
		req.setAttribute("word", word);
		req.setAttribute("videos", videos);
		req.getRequestDispatcher("/jsp/new_ta_exam_word_details.jsp").forward(req, resp);
	}

	/**
	 * ta-exam-add-word
	 * 
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
							String basePath = getServletContext()
									.getRealPath(File.separator+"upload"+File.separator + lesson.getName().replaceAll(" ", "_") + File.separator
											+ exam.getName().replaceAll(" ", "_") + File.separator);
							File dir = new File(basePath);
							if (!dir.exists() && !dir.isDirectory()) {
								dir.mkdirs();
							}
							File file = new File(basePath, name);
							item.write(file);
							item.delete();
							word.setName(name);
							word.setEid(eid);
							word.setPath(basePath + File.separator + name);
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

		examDetails(req, resp);
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
	 * 
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
							String basePath = getServletContext()
									.getRealPath(File.separator+"upload"+File.separator + lesson.getName() + File.separator + exam.getName() + File.separator);
							File dir = new File(basePath);
							if (!dir.exists() && !dir.isDirectory()) {
								dir.mkdirs();
							}
							File file = new File(basePath, name);
							item.write(file);
							item.delete();
							video.setName(name);
							video.setEid(eid);
							video.setPath(basePath + File.separator + name);
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
	 * 
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
	 * 
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void taDelLessonFile(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String fileId = req.getParameter("fid");
		int fid = Integer.parseInt(fileId);
		lessonFileService.delFileById(fid);
		learnLesson(req, resp);
	}

	/**
	 * ta-upload-lesson-file
	 * 
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void taUploadLessonFile(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
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
							String basePath = getServletContext().getRealPath(File.separator+"upload"+File.separator + lesson.getName() + File.separator);
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
							lessonFile.setPath(basePath + File.separator + name);
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
	 * 
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
	 * 
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
		List<LessonFile> vFile = new ArrayList<>();
		List<LessonFile> dFile = new ArrayList<>();
		for(int i = 0; i<files.size(); i++){
			LessonFile f = files.get(i);
			String oldPath = f.getPath();
			int index = oldPath.indexOf(File.separator+"upload"+File.separator);
			String path = req.getContextPath()+oldPath.substring(index);
			f.setPath(path);
			String ext = f.getFtype().toLowerCase();
			if(ext.equals("mp4") || ext.equals("wav")){
				vFile.add(f);
			}else{
				dFile.add(f);
			}
		}
		
		req.setAttribute("files", vFile);
		req.setAttribute("dFile", dFile);
		LessonStudent lessonStudent = lsService.getLSByUidAndLid(uid, lid);
		if (1 == lessonStudent.getType()) {// ta
			req.getRequestDispatcher("/jsp/ta_lesson_detail.jsp").forward(req, resp);
		} else {// student
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
		if (null == allLessons || allLessons.size() == 0){
			allLessons = new ArrayList<>();
		}
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
