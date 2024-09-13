package edu.ucsy.social.service;

import edu.ucsy.social.data.Service;

public interface PostImageService extends Service {

	String findPostImageById(int postImageId);

	boolean deletePostImageById(int postImageId);

}
