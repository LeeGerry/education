package edu.auburn.service;

import java.util.List;

import edu.auburn.domain.ExamVideo;

public interface IExamVideoService {
	boolean addVideo(ExamVideo video);
	boolean delVideoById(int vid);
	List<ExamVideo> getAllVideosByEid(int eid);
	ExamVideo getExamVideoByVid(int vid);
}
