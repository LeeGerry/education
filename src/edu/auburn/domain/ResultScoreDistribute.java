package edu.auburn.domain;

import java.util.ArrayList;

public class ResultScoreDistribute {
	private int count;
	private ArrayList<Integer> distributeList;
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public ArrayList<Integer> getDistributeList() {
		return distributeList;
	}
	public void setDistributeList(ArrayList<Integer> distributeList) {
		this.distributeList = distributeList;
	}
	@Override
	public String toString() {
		return "ResultScoreDistribute [count=" + count + ", distributeList=" + distributeList + "]";
	}
	
}
