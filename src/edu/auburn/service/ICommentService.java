package edu.auburn.service;

import java.util.List;

import edu.auburn.domain.Comment;

public interface ICommentService {
	boolean addComment(Comment comment);
	boolean delCommentById(int id);
	List<Comment> getAll();
	Comment getCommentById(int cid);
}
