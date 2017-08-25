package edu.auburn.service.impl;

import java.io.File;
import java.util.List;

import edu.auburn.dao.IWordVideoDao;
import edu.auburn.dao.impl.WordVideoDao;
import edu.auburn.domain.ExamVideo;
import edu.auburn.domain.WordVideo;
import edu.auburn.service.IWordVideoService;

public class WordVideoService implements IWordVideoService {
	private IWordVideoDao dao = new WordVideoDao();
	@Override
	public boolean addVideo(WordVideo video) {
		// TODO Auto-generated method stub
		return dao.addVideo(video);
	}

	@Override
	public boolean delVideoById(int vid) {
		// TODO Auto-generated method stub
		boolean delFromServer = false, delFromDB = false;
		WordVideo file = getWordVideoByVid(vid);
		String path = file.getPath();
		File f = new File(path);
		if(f.isFile() && f.exists()){
			delFromServer = f.delete();
		}
		delFromDB = dao.delVideoById(vid);
		return delFromServer && delFromDB;
	}

	@Override
	public List<WordVideo> getAllVideosByWid(int wid) {
		// TODO Auto-generated method stub
		return dao.getAllVideosByWid(wid);
	}

	@Override
	public WordVideo getWordVideoByVid(int vid) {
		// TODO Auto-generated method stub
		return dao.getWordVideoByVid(vid);
	}

}
