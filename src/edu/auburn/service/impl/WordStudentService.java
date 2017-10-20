package edu.auburn.service.impl;

import java.util.List;

import edu.auburn.dao.IWordStudentDao;
import edu.auburn.dao.impl.WordStudentDao;
import edu.auburn.domain.WordStudent;
import edu.auburn.service.IWordStudentService;

public class WordStudentService implements IWordStudentService {
	private IWordStudentDao wsDao = new WordStudentDao();
	@Override
	public boolean addStudentAnswerForWord(WordStudent ws) {
		return wsDao.addStudentAnswerForWord(ws);
	}

	@Override
	public boolean updateAnswer(WordStudent ws) {
		return wsDao.updateAnswer(ws);
	}

	@Override
	public WordStudent getStudentAnswerModelBySidAndWid(int sid, int wid) {
		return wsDao.getStudentAnswerModelBySidAndWid(sid, wid);
	}

	@Override
	public List<WordStudent> getStudentAnswerListBySidAndEid(int sid, int eid) {
		return wsDao.getStudentAnswerListBySidAndEid(sid, eid);
	}

	@Override
	public List<WordStudent> getStudentAnswerListByEidAndWid(int eid, int wid) {
		// TODO Auto-generated method stub
		return wsDao.getStudentAnswerListByEidAndWid(eid, wid);
	}

}
