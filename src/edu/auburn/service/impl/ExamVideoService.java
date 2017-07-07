package edu.auburn.service.impl;

import java.io.File;
import java.util.List;

import edu.auburn.dao.IExamVideoDao;
import edu.auburn.dao.impl.ExamVideoDao;
import edu.auburn.domain.ExamVideo;
import edu.auburn.service.IExamVideoService;

public class ExamVideoService implements IExamVideoService {
	private IExamVideoDao dao = new ExamVideoDao();
	@Override
	public boolean addVideo(ExamVideo video) {
		// TODO Auto-generated method stub
		return dao.addVideo(video);
	}

	@Override
	public boolean delVideoById(int vid) {
		// TODO Auto-generated method stub
		boolean delFromServer = false, delFromDB = false;
		ExamVideo file = getExamVideoByVid(vid);
		String path = file.getPath();
		File f = new File(path);
		if(f.isFile() && f.exists()){
			delFromServer = f.delete();
		}
		delFromDB = dao.delVideoById(vid);
		return delFromServer && delFromDB;
	}

	@Override
	public List<ExamVideo> getAllVideosByEid(int eid) {
		// TODO Auto-generated method stub
		return dao.getAllVideosByEid(eid);
	}

	@Override
	public ExamVideo getExamVideoByVid(int vid) {
		// TODO Auto-generated method stub
		return dao.getExamVideoByVid(vid);
	}

}
