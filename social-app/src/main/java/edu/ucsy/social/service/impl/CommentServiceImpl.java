package edu.ucsy.social.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.ucsy.social.data.Model;
import edu.ucsy.social.data.ModelFactory;
import edu.ucsy.social.data.OneToOne;
import edu.ucsy.social.data.db.DatabaseConnector;
import edu.ucsy.social.model.dto.form.CommentForm;
import edu.ucsy.social.model.dto.view.CommentView;
import edu.ucsy.social.model.entity.Comment;
import edu.ucsy.social.model.entity.ProfileImage;
import edu.ucsy.social.model.entity.User;
import edu.ucsy.social.service.CommentService;

public class CommentServiceImpl implements CommentService {

	private DatabaseConnector connector;

	private Model<Comment> commentModel;
	private Model<User> userModel;
	
	public CommentServiceImpl(DatabaseConnector connector) {
		this.connector = connector;
		this.commentModel = ModelFactory.getModel(Comment.class);
		this.userModel = ModelFactory.getModel(User.class);
	}

	@Override
	public CommentView createComment(CommentForm commentForm) {

		try (var connection = connector.getConnection()) {
			initConnection(connection);
			
			var comment = new Comment(commentForm.getCommentContent(), commentForm.getUserId(), commentForm.getUserName(), commentForm.getPostId());
			comment = commentModel.save(comment);
			var commentView = convertToView(comment);
			
			var profileImage = userModel.getRelational(OneToOne.class).getOne(ProfileImage.class, commentView.getUserId());
			if(null != profileImage) {
				commentView.setProfileImage(profileImage.name());
			}
			
			return commentView;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			destroyConnection();
		}

		return null;
	}

	private CommentView convertToView(Comment comment) {
		return new CommentView(comment);
	}

	@Override
	public CommentView updateComment(CommentForm commentForm) {
		var comment = commentModel.findOne(commentForm.getPostId());
		if (comment == null) {
			return null;
		}

		comment = new Comment(commentForm.getCommentContent(), commentForm.getUserId(), comment.userName(),
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
		commentModel.setConnection(connection);
		userModel.setConnection(connection);
	}

	@Override
	public void destroyConnection() {
		commentModel.setConnection(null);
		userModel.setConnection(null);
	}
}
