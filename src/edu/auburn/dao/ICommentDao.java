package edu.auburn.dao;

import java.util.List;

import edu.auburn.domain.Comment;

public interface ICommentDao {
	boolean addComment(Comment comment);
	boolean delCommentById(int id);
	List<Comment> getAll();
	Comment getCommentById(int cid);
}
