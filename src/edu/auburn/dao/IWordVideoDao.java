package edu.auburn.dao;

import java.util.List;

import edu.auburn.domain.WordVideo;


public interface IWordVideoDao {
	/**
	 * add a video for the word
	 * @param video
	 * @return
	 */
	boolean addVideo(WordVideo video);
	/**
	 * delete a video by vid
	 * @param vid
	 * @return
	 */
	boolean delVideoById(int vid);
	/**
	 * get all videos by the special word id (wid)
	 * @param wid
	 * @return
	 */
	List<WordVideo> getAllVideosByWid(int wid);
	/**
	 * get the video by vid
	 * @param vid
	 * @return
	 */
	WordVideo getWordVideoByVid(int vid);
}
