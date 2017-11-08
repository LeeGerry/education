package edu.auburn.service.impl;

import edu.auburn.dao.IResultScoreDistribute;
import edu.auburn.dao.impl.ResultScoreDistributeDao;
import edu.auburn.domain.ResultScoreDistribute;
import edu.auburn.service.IResultScoreDistributeService;

public class ResultScoreDistributeService implements IResultScoreDistributeService {
	IResultScoreDistribute dao = new ResultScoreDistributeDao();
	@Override
	public ResultScoreDistribute getDistanceDistribute(int eid) {
		// TODO Auto-generated method stub
		return dao.getDistribute(eid);
	}

}
