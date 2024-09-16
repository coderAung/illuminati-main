package edu.ucsy.social.service.impl;

import java.sql.Connection;
import java.sql.SQLException;

import edu.ucsy.social.data.Model;
import edu.ucsy.social.data.ModelFactory;
import edu.ucsy.social.data.Searchable;
import edu.ucsy.social.data.criteria.Criteria;
import edu.ucsy.social.data.criteria.Criteria.Type;
import edu.ucsy.social.data.db.DatabaseConnector;
import edu.ucsy.social.model.entity.CoverImage;
import edu.ucsy.social.model.entity.ProfileImage;
import edu.ucsy.social.model.entity.type.ImageStatus;
import edu.ucsy.social.service.ImageService;
import edu.ucsy.social.utils.DefaultPicture;

public class ImageServiceImpl implements ImageService {

	private DatabaseConnector connector;

	private Model<ProfileImage> profileImageModel;
	private Model<CoverImage> coverImageModel;
	private Searchable<ProfileImage> profileImageSearchModel;
	private Searchable<CoverImage> coverImageSearchModel;

	public ImageServiceImpl(DatabaseConnector connector) {
		this.connector = connector;

		this.profileImageModel = ModelFactory.getModel(ProfileImage.class);
		this.coverImageModel = ModelFactory.getModel(CoverImage.class);
		
		this.profileImageSearchModel = ModelFactory.getSearchModel(ProfileImage.class);
		this.coverImageSearchModel = ModelFactory.getSearchModel(CoverImage.class);
	}

	@Override
	public void initConnection(Connection connection) {
		profileImageModel.setConnection(connection);
		coverImageModel.setConnection(connection);
		profileImageSearchModel.setConnection(connection);
		coverImageSearchModel.setConnection(connection);
	}

	@Override
	public void destroyConnection() {
		profileImageModel.setConnection(null);
		coverImageModel.setConnection(null);
		profileImageSearchModel.setConnection(null);
		coverImageSearchModel.setConnection(null);
	}

	@Override
	public long saveProfileImage(long userId, String imageName) {
		try (var connection = connector.getConnection()) {
			initConnection(connection);

			try {
				connection.setAutoCommit(false);
				var criteria = new Criteria()
									.where("status", Type.EQ, ImageStatus.ACTIVE.name())
									.where("user_id", Type.EQ, userId);
				var lastActiveProfileImage = profileImageSearchModel.searchOne(criteria);

				if (null != lastActiveProfileImage) {
					profileImageModel
							.update(new ProfileImage(lastActiveProfileImage.id(), lastActiveProfileImage.name(), userId,
									ImageStatus.INACTIVE, lastActiveProfileImage.uploadedAt()));
				}

				var profileImage = new ProfileImage(imageName, userId);
				profileImage = profileImageModel.save(profileImage);

				if (profileImage != null) {
					connection.commit();
					return profileImage.id();
				}
			} catch (Exception e) {
				connection.rollback();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			destroyConnection();
		}
		return 0;
	}

	@Override
	public long saveCoverImage(long userId, String imageName) {
		try (var connection = connector.getConnection()) {
			initConnection(connection);

			try {
				connection.setAutoCommit(false);
				var criteria = new Criteria()
									.where("status", Type.EQ, ImageStatus.ACTIVE.name())
									.where("user_id", Type.EQ, userId);
				var lastActiveProfileImage = coverImageSearchModel.searchOne(criteria);

				if (null != lastActiveProfileImage) {
					coverImageModel
							.update(new CoverImage(lastActiveProfileImage.id(), lastActiveProfileImage.name(), userId,
									ImageStatus.INACTIVE, lastActiveProfileImage.uploadedAt()));
				}

				var coverImage = new CoverImage(imageName, userId);
				coverImage = coverImageModel.save(coverImage);

				if (coverImage != null) {
					connection.commit();
					return coverImage.id();
				}
			} catch (Exception e) {
				connection.rollback();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			destroyConnection();
		}
		return 0;
	}

	@Override
	public String getProfileImage(int userId) {
		try(var connection = connector.getConnection()) {
			initConnection(connection);
			var criteria = new Criteria()
					.where("status", Type.EQ, ImageStatus.ACTIVE.name())
					.where("user_id", Type.EQ, userId);
			var profileImage = profileImageSearchModel.searchOne(criteria);
			if(null != profileImage) {
				return profileImage.name();
			} else {
				return DefaultPicture.defaultProfilePicture;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			destroyConnection();
		}
		return null;
	}

	@Override
	public String getImageService(int userId) {
		try(var connection = connector.getConnection()) {
			initConnection(connection);
			var criteria = new Criteria()
					.where("status", Type.EQ, ImageStatus.ACTIVE.name())
					.where("user_id", Type.EQ, userId);
			var coverImage = coverImageSearchModel.searchOne(criteria);
			if(null != coverImage) {
				return coverImage.name();
			} else {
				return DefaultPicture.defaultCoverImage;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			destroyConnection();
		}
		return null;
	}

}
