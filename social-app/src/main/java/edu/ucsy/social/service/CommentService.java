package edu.ucsy.social.service;

import java.util.List;

import edu.ucsy.social.data.Service;
import edu.ucsy.social.model.dto.form.CommentForm;
import edu.ucsy.social.model.dto.view.CommentView;

public interface CommentService extends Service {

	CommentView createComment(CommentForm commentForm);

	CommentView updateComment(CommentForm commentForm);

	boolean deleteComment(int commentId);

	List<CommentView> getCommentViews(int postId, int lastCommentId, int i);

}
