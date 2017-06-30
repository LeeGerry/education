package edu.auburn.service;

import java.util.List;

import edu.auburn.domain.LessonFile;

public interface ILessonFileService {
	boolean addFile(LessonFile file);
	boolean delFileById(int id);
	List<LessonFile> getAllFileByLid(int lid);
	LessonFile getFileByFid(int fid);
}
