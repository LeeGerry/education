package edu.auburn.service.impl;

import java.io.File;
import java.util.List;

import edu.auburn.dao.IExamWordDao;
import edu.auburn.dao.impl.ExamWordDao;
import edu.auburn.domain.ExamWord;
import edu.auburn.service.IExamWordService;

public class ExamWordService implements IExamWordService {
	private IExamWordDao dao = new ExamWordDao();
	@Override
	public boolean addWord(ExamWord word) {
		// TODO Auto-generated method stub
		return dao.addWord(word);
	}

	@Override
	public boolean delWordById(int fid) {
		// TODO Auto-generated method stub
		boolean delFromServer = false, delFromDB = false;
		ExamWord file = getExamWordByFid(fid);
		String path = file.getPath();
		File f = new File(path);
		if(f.isFile() && f.exists()){
			delFromServer = f.delete();
		}
		delFromDB = dao.delWordById(fid);
		return delFromServer && delFromDB;
	}

	@Override
	public List<ExamWord> getAllWordsByEid(int eid) {
		// TODO Auto-generated method stub
		return dao.getAllWordsByEid(eid);
	}

	@Override
	public ExamWord getExamWordByFid(int fid) {
		// TODO Auto-generated method stub
		return dao.getExamWordByVid(fid);
	}

}
