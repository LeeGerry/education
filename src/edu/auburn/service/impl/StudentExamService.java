package edu.auburn.service.impl;

import edu.auburn.dao.IStudentExamDao;
import edu.auburn.dao.impl.StudentExamDao;
import edu.auburn.service.IStudentExamService;

public class StudentExamService implements IStudentExamService {
	private IStudentExamDao dao = new StudentExamDao();
	@Override
	public boolean checkIfExamTakenByStudent(int eid, int sid) {
		return dao.checkIfExamTakenByStudent(eid, sid);
	}

	@Override
	public boolean takeExam(int eid, int sid) {
		boolean boo = dao.checkIfExamTakenByStudent(eid, sid);
		if(boo) return true;
		
		return dao.takeExam(eid, sid);
	}

}
