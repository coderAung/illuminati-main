package edu.ucsy.social.service.impl;

import java.sql.Connection;
import java.sql.SQLException;

import edu.ucsy.social.data.Countable;
import edu.ucsy.social.data.Model;
import edu.ucsy.social.data.ModelFactory;
import edu.ucsy.social.data.criteria.Criteria;
import edu.ucsy.social.data.criteria.Criteria.Type;
import edu.ucsy.social.data.db.DatabaseConnector;
import edu.ucsy.social.model.entity.Reaction;
import edu.ucsy.social.service.ReactionService;

public class ReactionServiceImpl implements ReactionService {

	private DatabaseConnector connector;

	private Model<Reaction> reactionModel;
	
	public ReactionServiceImpl(DatabaseConnector connector) {
		this.connector = connector;
		this.reactionModel = ModelFactory.getModel(Reaction.class);
	}

	@Override
	public void initConnection(Connection connection) {
		this.reactionModel.setConnection(connection);
	}

	@Override
	public void destroyConnection() {
		this.reactionModel.setConnection(null);
	}

	@Override
	public boolean createReaction(long userId, int postId) {
		try(var connection = connector.getConnection()) {
			initConnection(connection);
			
			var reaction = new Reaction(userId, postId);
			reaction = reactionModel.save(reaction);
			if(0 < reaction.getId()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			destroyConnection();
		}
		return false;
	}

	@Override
	public long getReactionCount(int postId) {
		try(var connection = connector.getConnection()) {
			initConnection(connection);
			
			var count = Countable.getCountable(reactionModel).count(new Criteria().where("post_id", Type.EQ, postId));
			return count;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			destroyConnection();
		}
		return 0;
	}

}
