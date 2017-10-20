package edu.auburn.service.impl;

import java.util.List;

import edu.auburn.dao.ICommentDao;
import edu.auburn.dao.impl.CommentDao;
import edu.auburn.domain.Comment;
import edu.auburn.service.ICommentService;

public class CommentService implements ICommentService{
	private ICommentDao commentDao = new CommentDao();

	@Override
	public boolean addComment(Comment comment) {
		// TODO Auto-generated method stub
		return commentDao.addComment(comment);
	}

	@Override
	public boolean delCommentById(int id) {
		// TODO Auto-generated method stub
		return commentDao.delCommentById(id);
	}

	@Override
	public List<Comment> getAll() {
		// TODO Auto-generated method stub
		return commentDao.getAll();
	}

	@Override
	public Comment getCommentById(int cid) {
		// TODO Auto-generated method stub
		return commentDao.getCommentById(cid);
	}

}
