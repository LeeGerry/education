package edu.auburn.service.impl;

import java.util.ArrayList;
import java.util.List;

import edu.auburn.dao.IExamDao;
import edu.auburn.dao.IExamResultDao;
import edu.auburn.dao.IExamWordDao;
import edu.auburn.dao.IUserDao;
import edu.auburn.dao.impl.ExamDao;
import edu.auburn.dao.impl.ExamResultDao;
import edu.auburn.dao.impl.ExamWordDao;
import edu.auburn.dao.impl.UserDao;
import edu.auburn.domain.DisplayStudentExamResult;
import edu.auburn.domain.EduUser;
import edu.auburn.domain.Exam;
import edu.auburn.domain.ExamResult;
import edu.auburn.domain.ExamScore;
import edu.auburn.domain.ExamTeacherResult;
import edu.auburn.domain.ExamWord;
import edu.auburn.service.IExamResultService;
import edu.auburn.utils.CalculateScore;

public class ExamResultService implements IExamResultService {
	private IExamResultDao resultDao = new ExamResultDao();
	private IExamWordDao wordDao = new ExamWordDao();
	private IUserDao userDao = new UserDao();
	private IExamDao examDao = new ExamDao();
	@Override
	public boolean addResult(ExamResult result) {
		// TODO Auto-generated method stub
		return resultDao.addResult(result);
	}

	@Override
	public ExamResult getResultByUidAndEid(int uid, int eid) {
		// TODO Auto-generated method stub
		return resultDao.getResultByUidAndEid(uid, eid);
	}

	@Override
	public ExamScore getExamScoreByUidAndEid(int uid, int eid) {
		ExamResult er = getResultByUidAndEid(uid, eid);
		EduUser user = userDao.getUserById(uid);
		List<ExamWord> words = wordDao.getAllWordsByEid(eid);
		Exam exam = examDao.getExamById(eid);
		String[] parseAnswer = er.getAnswer().split("/");
		ExamScore es = new ExamScore();
		es.setWords(words);
		es.setStudent(user);
		es.setExam(exam);
		List<String> answers = new ArrayList<>();
		List<Float> scores = new ArrayList<>();
		for(int i = 0; i < words.size(); i++){
			String teacherAnswer = words.get(i).getPron();
			String studentAnswer = parseAnswer[i];
			answers.add(studentAnswer);
			scores.add(CalculateScore.getScore(studentAnswer, teacherAnswer));
		}
		es.setAnswers(answers);
		es.setScore(scores);
		return es;
	}
	
	@Override
	public List<DisplayStudentExamResult> getDisplayResult(int uid, int eid) {
		ExamScore score = getExamScoreByUidAndEid(uid, eid);
		List<String> answers = score.getAnswers();
		List<ExamWord> words = score.getWords();
		List<Float> s = score.getScore();
		List<DisplayStudentExamResult> result = new ArrayList<>();
		for(int i = 0; i<answers.size(); i++){
			DisplayStudentExamResult r = new DisplayStudentExamResult();
			r.setsAnswer(answers.get(i));
			r.settAnswer(words.get(i).getPron());
			r.setScore(s.get(i));
			result.add(r);
		}
		return result;
	}

	@Override
	public List<ExamTeacherResult> teacherCheckResultByEid(int eid) {
		ArrayList<ExamTeacherResult> result = new ArrayList<>();
		List<ExamResult> examResultList = resultDao.teacherCheckResultByEid(eid);
		ExamTeacherResult tr = new ExamTeacherResult();
		for(int i = 0; i<examResultList.size(); i++){
			ExamResult examResult = examResultList.get(i);
			tr.setEid(eid);
			tr.setSid(examResult.getUid());
			int uid = examResult.getUid();
			List<DisplayStudentExamResult> displayResult = getDisplayResult(uid, eid);
			float score = 0;
			for(int j = 0; j<displayResult.size();j++){
				score += displayResult.get(i).getScore();
			}
			EduUser user = userDao.getUserById(uid);
			tr.setStuName(user.getName());
			tr.setScore(score);
			result.add(tr);
		}
		return result;
	}
}
