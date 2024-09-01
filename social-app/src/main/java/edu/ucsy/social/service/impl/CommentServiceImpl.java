package edu.ucsy.social.service.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import edu.ucsy.social.data.Model;
import edu.ucsy.social.data.ModelFactory;
import edu.ucsy.social.model.dto.form.CommentForm;
import edu.ucsy.social.model.dto.view.CommentView;
import edu.ucsy.social.model.entity.Comment;
import edu.ucsy.social.service.CommentService;

public class CommentServiceImpl implements CommentService {

	private Model<Comment> commentModel;

	public CommentServiceImpl() {
		this.commentModel = ModelFactory.getModel(Comment.class);
	}

	@Override
	public CommentView createComment(CommentForm commentForm) {
		Comment comment = new Comment(0, // ID will be auto-generated
				commentForm.getCommentContent(),
				null, // created_at will be generated by the model
				null, // updated_at will be generated by the model
				commentForm.getUserId(), 
				null, // user_name can be fetched later
				commentForm.getPostId());

		Comment savedComment = commentModel.save(comment);
		return savedComment != null ? convertToView(savedComment) : null;
	}

	private CommentView convertToView(Comment comment) {
		return new CommentView(comment.id(), 
				comment.content(), 
				comment.userName(), 
				comment.updatedAt());
	}

	@Override
	public CommentView updateComment(CommentForm commentForm) {
		var comment = commentModel.findOne(commentForm.getPostId());
		if (comment == null) {
			return null;
		}

		comment = new Comment(commentForm.getCommentContent(), 
				commentForm.getUserId(), 
				comment.userName(),
				commentForm.getPostId());
		;

		var updatedComment = commentModel.update(comment);

		return updatedComment != null ? convertToView(updatedComment) : null;
	}

	@Override
	public boolean deleteComment(int commentId) {

		Comment comment = commentModel.findOne(commentId);
		if (comment == null) {
			return false;
		}
		return commentModel.delete(commentId);
	}

	@Override
	public List<CommentView> getCommentViews(int postId, int lastCommentId, int i) {
		
		List<Comment> comments = commentModel.getAll();
	    List<CommentView> commentViews = new ArrayList<>();

	    int count = 0;
	    for (Comment comment : comments) {
	        if (comment.postId() == postId && comment.id() > lastCommentId) {
	            commentViews.add(convertToView(comment));
	            count++;
	            if (count >= i) {
	                break;
	            }
	        }
	    }

	    return commentViews;
}

	@Override
	public void initConnection(Connection connection) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void destroyConnection() {
		// TODO Auto-generated method stub
		
	}
	}
