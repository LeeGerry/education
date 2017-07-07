package edu.auburn.service.impl;

import java.util.List;

import edu.auburn.dao.IExamDao;
import edu.auburn.dao.impl.ExamDao;
import edu.auburn.domain.Exam;
import edu.auburn.service.IExamService;

public class ExamService implements IExamService {
	IExamDao dao = new ExamDao();
	@Override
	public boolean addExam(Exam exam) {
		// TODO Auto-generated method stub
		return dao.addExam(exam);
	}

	@Override
	public boolean delExamById(int id) {
		// TODO Auto-generated method stub
		return dao.delExamById(id);
	}

	@Override
	public List<Exam> getExamsByLid(int lid) {
		// TODO Auto-generated method stub
		return dao.getExamsByLid(lid);
	}

	@Override
	public Exam getExamById(int eid) {
		// TODO Auto-generated method stub
		return dao.getExamById(eid);
	}

}
