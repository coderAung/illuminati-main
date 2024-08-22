package edu.ucsy.social.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import javax.sql.DataSource;

import edu.ucsy.social.data.Model;
import edu.ucsy.social.data.ModelFactory;
import edu.ucsy.social.data.OneToOne;
import edu.ucsy.social.data.db.ConnectorFactory;
import edu.ucsy.social.model.dto.form.CommentForm;
import edu.ucsy.social.model.dto.view.CommentView;
import edu.ucsy.social.model.entity.Comment;
import edu.ucsy.social.model.entity.ProfileImage;
import edu.ucsy.social.service.CommentService;

public class CommentServiceImpl implements CommentService{
	
	private Model<Comment> commentModel;
	
	public CommentServiceImpl(DataSource dataSource) {
		this.commentModel = ModelFactory.getModel(Comment.class, ConnectorFactory.getConnector(dataSource));
		}

	@Override
	public CommentView createComment(CommentForm commentForm) {
		
		var comment = new Comment(commentForm.getPostId() , commentForm.getUserId(), commentForm.getCommentContent(),);
		return null;
	}

	@Override
	public CommentView updateComment(CommentForm commentForm) {
		var comment = commentModel.findOne(commentForm.getUserId());
		if(comment == null) {
			return null;
		}
		
		var updatedComment = comment.updatedClone(LocalDateTime.now());
		
		updatedComment = new Comment
		return null;
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
		var comments = commentModel.find
		return null;
	}

}
