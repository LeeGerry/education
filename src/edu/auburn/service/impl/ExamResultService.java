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
	public boolean addResult(int uid, int eid, String sAnswers) {
		ExamResult er = new ExamResult();
		er.setEid(eid);
		er.setUid(uid);
		List<String> studentAnswers = new ArrayList<>();
		String [] sa = sAnswers.split("/");
		for(int i = 0; i<sa.length; i++){
			studentAnswers.add(sa[i]);
		}
		er.setsAnswers(studentAnswers);
		List<ExamWord> wordList = wordDao.getAllWordsByEid(eid);
		List<String> teacherAnswers = new ArrayList<>();
		for(int i = 0; i<wordList.size();i++){
			teacherAnswers.add(wordList.get(i).getPron());
		}
		er.settAnswers(teacherAnswers);
		List<Float> scoreList = new ArrayList<>();
		float total = 0;
		for(int i = 0; i< wordList.size();i++){
			float score = CalculateScore.getScore(studentAnswers.get(i), teacherAnswers.get(i));
			scoreList.add(score);
			total += score;
		}
		er.setScoreList(scoreList);
		er.setTotal(total);
		return resultDao.addResult(er);
	}
	@Override
	public ExamResult getResultByUidAndEid(int uid, int eid) {
		// TODO Auto-generated method stub
		return resultDao.getResultByUidAndEid(uid, eid);
	}
	@Override
	public List<ExamResult> teacherCheckResultByEid(int eid) {
		// TODO Auto-generated method stub
		return resultDao.teacherCheckResultByEid(eid);
	}
	@Override
	public boolean addResult(ExamResult er) {
		// TODO Auto-generated method stub
		ExamResult e = resultDao.getResultByUidAndEid(er.getUid(), er.getEid());
		if(null != e && e.getEid() > 0 && e.getUid() > 0){
			resultDao.deleteResult(er.getUid(), er.getEid());
			return resultDao.addResult(er);
		}
		return resultDao.addResult(er);
	}

	
}
