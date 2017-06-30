package edu.auburn.dao;

import java.util.List;

import edu.auburn.domain.LessonFile;

public interface ILessonFileDao {
	boolean addFile(LessonFile file);
	boolean delFileById(int id);
	List<LessonFile> getAllFileByLid(int lid);
	LessonFile getLessonFileByFid(int fid);
}
