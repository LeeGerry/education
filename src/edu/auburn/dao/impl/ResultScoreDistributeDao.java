package edu.auburn.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.sun.javafx.collections.MappingChange.Map;

import edu.auburn.dao.IExamResultDao;
import edu.auburn.dao.IResultScoreDistribute;
import edu.auburn.domain.ExamResult;
import edu.auburn.domain.ResultScoreDistribute;

public class ResultScoreDistributeDao implements IResultScoreDistribute {
	private IExamResultDao eDao = new ExamResultDao();
	@Override
	public ResultScoreDistribute getDistribute(int eid) {
		List<ExamResult> result = eDao.teacherCheckResultByEid(eid);
		ResultScoreDistribute distribute = new ResultScoreDistribute();
		ArrayList<Integer> distanceDis = new ArrayList<>();
		distribute.setCount(6);
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for(int i = 0; i< 6 ;i++){
			map.put(i, 0);
		}
		for(int i=0; i<result.size(); i++){
			ExamResult er = result.get(i);
			float total = er.getTotal();
			if(total >= 0 && total < 2)
				map.put(0, map.get(0)+1);
			else if(total >=2 && total < 4)
				map.put(1, map.get(1)+1);
			else if(total >=4 && total < 6)
				map.put(2, map.get(2)+1);
			else if(total >=6 && total < 8)
				map.put(3, map.get(3)+1);
			else if(total >=8 && total < 10)
				map.put(4, map.get(4)+1);
			else 
				map.put(5, map.get(5)+1);
		}
		for(int i = 0;i<6; i++){
			distanceDis.add(map.get(i));
		}
		distribute.setDistributeList(distanceDis);
		return distribute;
	}

}
