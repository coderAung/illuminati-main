package edu.ucsy.social.model.dto.view;

import java.util.List;

public class PostDetailView {

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
