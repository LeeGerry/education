package edu.auburn.service.impl;

import java.io.File;
import java.util.List;


import edu.auburn.dao.ILessonFileDao;
import edu.auburn.dao.impl.LessonFileDao;
import edu.auburn.domain.LessonFile;
import edu.auburn.service.ILessonFileService;

public class LessonFileService implements ILessonFileService {
	private ILessonFileDao fileDao = new LessonFileDao();
	@Override
	public boolean addFile(LessonFile file) {
		// TODO Auto-generated method stub
		return fileDao.addFile(file);
	}

	@Override
	public boolean delFileById(int id) {
		boolean delFileFromServer = false, delFileFromDB = false;
		LessonFile file = getFileByFid(id);
		String path = file.getPath();
		File f = new File(path);
		if(f.isFile() && f.exists()){
			delFileFromServer = f.delete();
		}
		delFileFromDB = fileDao.delFileById(id);
		return delFileFromServer && delFileFromDB;
	}

	@Override
	public List<LessonFile> getAllFileByLid(int lid) {
		// TODO Auto-generated method stub
		return fileDao.getAllFileByLid(lid);
	}

	@Override
	public LessonFile getFileByFid(int fid) {
		// TODO Auto-generated method stub
		return fileDao.getLessonFileByFid(fid);
	}

}
