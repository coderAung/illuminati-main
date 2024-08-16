package edu.ucsy.social.model.dto.view;

import java.io.Serializable;
import java.util.List;

public class PostDetailView implements Serializable {

	private static final long serialVersionUID = 1L;

	private PostView postView;
	private List<CommentView> commentViews;

	public PostView getPostView() {
		return postView;
	}

	public void setPostView(PostView postView) {
		this.postView = postView;
	}

	public List<CommentView> getCommentViews() {
		return commentViews;
	}

	public void setCommentViews(List<CommentView> commentViews) {
		this.commentViews = commentViews;
	}

}
