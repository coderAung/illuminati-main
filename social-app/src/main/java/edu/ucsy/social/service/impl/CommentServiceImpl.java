package edu.ucsy.social.service.impl;

import java.util.List;

import javax.sql.DataSource;

import edu.ucsy.social.data.Model;
import edu.ucsy.social.data.ModelFactory;
import edu.ucsy.social.data.db.ConnectorFactory;
import edu.ucsy.social.model.dto.form.CommentForm;
import edu.ucsy.social.model.dto.view.CommentView;
import edu.ucsy.social.model.entity.Comment;
import edu.ucsy.social.service.CommentService;

public class CommentServiceImpl implements CommentService{
	
	private Model<Comment> commentModel;
	
	public CommentServiceImpl(DataSource dataSource) {
		this.commentModel = ModelFactory.getModel(Comment.class, ConnectorFactory.getConnector(dataSource));
		}

	@Override
	public CommentView createComment(CommentForm commentForm) {
		
		return null;
	}

	@Override
	public CommentView updateComment(CommentForm commentForm) {
		
		return null;
	}

	@Override
	public boolean deleteComment(int commentId) {
		
		return false;
	}

	@Override
	public List<CommentView> getCommentViews(int postId, int lastCommentId, int i) {
		
		return null;
	}

}
