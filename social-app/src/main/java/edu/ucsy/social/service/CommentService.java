package edu.ucsy.social.service;

import java.util.List;

import edu.ucsy.social.data.annotation.Service;
import edu.ucsy.social.model.dto.form.CommentForm;
import edu.ucsy.social.model.dto.view.CommentView;

@Service
public interface CommentService {

	CommentView createComment(CommentForm commentForm);

	CommentView updateComment(CommentForm commentForm);

	boolean deleteComment(int commentId);

	List<CommentView> getCommentViews(int postId, int lastCommentId, int i);

}
