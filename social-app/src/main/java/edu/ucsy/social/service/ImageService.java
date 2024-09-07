package edu.ucsy.social.service;

import edu.ucsy.social.data.Service;

public interface ImageService extends Service {

	long saveProfileImage(long userId, String imageName);

	long saveCoverImage(long userId, String imageName);

}
