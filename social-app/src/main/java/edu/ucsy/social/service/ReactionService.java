package edu.ucsy.social.service;

import edu.ucsy.social.data.Service;

public interface ReactionService extends Service {

	boolean createReaction(long userId, int postId);

	long getReactionCount(int postId);

}
