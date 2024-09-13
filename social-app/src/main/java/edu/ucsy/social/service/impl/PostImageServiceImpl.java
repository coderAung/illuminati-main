package edu.ucsy.social.service.impl;

import java.sql.Connection;
import java.sql.SQLException;

import edu.ucsy.social.data.Model;
import edu.ucsy.social.data.ModelFactory;
import edu.ucsy.social.data.db.DatabaseConnector;
import edu.ucsy.social.model.entity.PostImage;
import edu.ucsy.social.service.PostImageService;

public class PostImageServiceImpl implements PostImageService {

	private DatabaseConnector connector;
	
	private Model<PostImage> postImageModel;
	
	public PostImageServiceImpl(DatabaseConnector connector) {
		this.connector = connector;
		this.postImageModel = ModelFactory.getModel(PostImage.class);
	}
	
	@Override
	public void initConnection(Connection connection) {
		postImageModel.setConnection(connection);
	}

	@Override
	public void destroyConnection() {
		postImageModel.setConnection(null);
	}

	@Override
	public String findPostImageById(int postImageId) {
		try(var connection = connector.getConnection()) {
			initConnection(connection);
			
			var postImage = postImageModel.findOne(postImageId);
			if(null != postImage) {
				return postImage.name();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			destroyConnection();
		}

		return null;
	}

	@Override
	public boolean deletePostImageById(int postImageId) {
		try(var connection = connector.getConnection()) {
			initConnection(connection);
			
			var result = postImageModel.delete(postImageId);
			
			return result;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			destroyConnection();
		}

		return false;
	}

}
