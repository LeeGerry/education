package edu.auburn.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import edu.auburn.dao.IExamDao;
import edu.auburn.dao.ILessonStudentDao;
import edu.auburn.dao.IWordStudentDao;
import edu.auburn.dao.impl.ExamDao;
import edu.auburn.dao.impl.LessonStudentDao;
import edu.auburn.dao.impl.WordStudentDao;
import edu.auburn.domain.LessonStudent;
import edu.auburn.domain.WordStudent;
import edu.auburn.service.IDistributeService;

public class DistributeService implements IDistributeService {
	private ILessonStudentDao lessonStudentDao = new LessonStudentDao();
	private IExamDao examDao = new ExamDao();
	private IWordStudentDao wordStudentDao = new WordStudentDao();

	@Override
	public ArrayList<Integer> getDistanceGroup(int eid, int wid) {
		List<WordStudent> wordStudentList = wordStudentDao.getStudentAnswerListByEidAndWid(eid, wid);
		int one = 0, two = 0, three = 0, four = 0, five = 0, six = 0;
		for (int i = 0; i < wordStudentList.size(); i++) {
			WordStudent ws = wordStudentList.get(i);
			float distance = ws.getScore();// distance
			if (distance >= 0 && distance < 2) {
				one++;
			} else if (distance >= 2 && distance < 4) {
				two++;
			} else if (distance >= 4 && distance < 6) {
				three++;
			} else if (distance >= 6 && distance < 8) {
				four++;
			} else if (distance >= 8 && distance < 10) {
				five++;
			} else {
				six++;
			}
		}
		int studentSelectLessonCount = getAllStudentByLid(getLidByEid(eid)).size();
		six += (studentSelectLessonCount - wordStudentList.size());
		ArrayList<Integer> result = new ArrayList<>();
		result.add(one);
		result.add(two);
		result.add(three);
		result.add(four);
		result.add(five);
		result.add(six);
		return result;
	}

	@Override
	public HashMap<String, Integer> getAnswerGroup(int eid, int wid) {
		HashMap<String, Integer> result = new HashMap<>();
		List<WordStudent> wordStudentList = wordStudentDao.getStudentAnswerListByEidAndWid(eid, wid);
		for (int i = 0; i < wordStudentList.size(); i++) {
			WordStudent ws = wordStudentList.get(i);
			String answer = ws.getAnswer();
			if (null == answer || answer.trim().equals("")) {
				result.put("none", result.getOrDefault("none", 0) + 1);
			} else {
				result.put(answer, result.getOrDefault(answer, 0) + 1);
			}
		}
		int studentSelectLessonCount = getAllStudentByLid(getLidByEid(eid)).size();
		int notAnswerCount = studentSelectLessonCount - wordStudentList.size();
		result.put("none", result.getOrDefault("none", 0) + notAnswerCount);
		return result;
	}

	@Override
	public double getPosition(int eid, int wid) {

		return 0;
	}

	public int getLidByEid(int eid) {
		return examDao.getExamById(eid).getLid();
	}

	public ArrayList<Integer> getAllStudentByLid(int lid) {
		List<LessonStudent> lsList = lessonStudentDao.getLSByLid(lid);
		ArrayList<Integer> result = new ArrayList<>();
		for (int i = 0; i < lsList.size(); i++) {
			LessonStudent ls = lsList.get(i);
			result.add(ls.getSid());
		}
		return result;
	}

	@Override
	public List<Integer> getPositionAndCount(int sid, int eid, int wid) {
		int count = getAllStudentByLid(getLidByEid(eid)).size();
		List<Integer> result = new ArrayList<>();
		result.add(count);
		ArrayList<Float> scoreList = new ArrayList<>();
		float currentStudentScore = 9999.9f;
		List<WordStudent> wordStudentList = wordStudentDao.getStudentAnswerListByEidAndWid(eid, wid);
		for (int i = 0; i < wordStudentList.size(); i++) {
			WordStudent ws = wordStudentList.get(i);
			float distance = ws.getScore();
			if (sid == ws.getSid())
				currentStudentScore = ws.getScore();
			scoreList.add(distance);
		}
		Collections.sort(scoreList, Collections.reverseOrder());// from big to small
		Collections.reverse(scoreList);
		int i = 0;
		for (;i < scoreList.size(); i++) {
			if(currentStudentScore == scoreList.get(i)) 
				break;
		}
		int notAnswerCount = count - wordStudentList.size();
		if(currentStudentScore > 9999){
			result.add(count);
		}else{
			result.add(notAnswerCount + wordStudentList.size() - i);
		}
		
		return result;
	}

	@Override
	public List<WordStudent> getDistanceListByEidAndWid(int eid, int wid) {
		List<WordStudent> list = wordStudentDao.getStudentAnswerListByEidAndWid(eid, wid);
		return list;
	}

}
