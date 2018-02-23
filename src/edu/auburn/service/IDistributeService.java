package edu.auburn.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import edu.auburn.domain.WordStudent;

public interface IDistributeService {
	/**
	 * for bar chart
	 * @param eid
	 * @param wid
	 * @return
	 */
	ArrayList<Integer> getDistanceGroup(int eid, int wid);
	/**
	 * for pie chart
	 * @param eid
	 * @param wid
	 * @return
	 */
	HashMap<String, Integer> getAnswerGroup(int eid, int wid);
	/**
	 * for position
	 * @param eid
	 * @param wid
	 * @return
	 */
	double getPosition(int eid, int wid);
	
	List<Integer> getPositionAndCount(int sid, int eid, int wid);
	
	List<WordStudent> getDistanceListByEidAndWid(int eid, int wid);
}
