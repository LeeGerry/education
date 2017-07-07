package edu.auburn.dao;

import java.util.List;

import edu.auburn.domain.ExamVideo;

public interface IExamVideoDao {
	boolean addVideo(ExamVideo video);
	boolean delVideoById(int vid);
	List<ExamVideo> getAllVideosByEid(int eid);
	ExamVideo getExamVideoByVid(int vid);
}
