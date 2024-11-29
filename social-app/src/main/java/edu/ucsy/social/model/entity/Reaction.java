package edu.ucsy.social.model.entity;

import java.util.Objects;

import edu.ucsy.social.data.annotation.Entity;

@Entity
public class Reaction {

	private long userId;
	private long postId;
	
	public Reaction(long userId, long postId) {
		this.userId = userId;
		this.postId = postId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getPostId() {
		return postId;
	}

	public void setPostId(long postId) {
		this.postId = postId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(postId, userId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reaction other = (Reaction) obj;
		return postId == other.postId && userId == other.userId;
	}


}
