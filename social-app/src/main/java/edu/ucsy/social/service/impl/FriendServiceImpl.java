package edu.ucsy.social.service.impl;

import java.util.ArrayList;
import java.util.List;

import edu.ucsy.social.data.Model;
import edu.ucsy.social.data.ModelFactory;
import edu.ucsy.social.model.ProfileImageModel;
import edu.ucsy.social.model.dto.view.FriendView;
import edu.ucsy.social.model.entity.Friend;
import edu.ucsy.social.model.entity.ProfileImage;
import edu.ucsy.social.model.entity.User;
import edu.ucsy.social.service.FriendService;

public class FriendServiceImpl implements FriendService{

	private Model<Friend> friendModel;
	private   Model<User> userModel;
	private Model<ProfileImage> profileImageModel;
	public FriendServiceImpl() {
		this.friendModel 	     = ModelFactory.getModel(Friend.class);
		this.userModel			 = ModelFactory.getModel(User.class);
		this.profileImageModel 	 = ModelFactory.getModel(ProfileImage.class);
	}
	
	@Override
	public List<FriendView> getFriendViews(int userid, int limit) {

		 List<Friend> allFriends = friendModel.get(limit);
		    List<FriendView> friendViews = new ArrayList<>();

		    int count = 0;
		    for (Friend friend : allFriends) {
		        if (friend.userId() == userid) {
		            // Fetch user details for the current friend
		            String name =getUserName(friend.friendId());
		            String profileImage = getUserProfileImage(friend.friendId());

		            // Create a FriendView and add it to the list
		            FriendView friendView = new FriendView((int) friend.id(), name, profileImage);
		            friendViews.add(friendView);

		            count++;
		            if (count >= limit) {
		                break;
		            }
		        }
		    }
		    return friendViews;
	}

	private String getUserName(long userId) {
        User user = userModel.findOne(userId);
        return (user != null) ? user.name() : "Unknown";
    }

    private String getUserProfileImage(long userId) {
        User user = userModel.findOne(userId);
        ProfileImage pi = profileImageModel.findOne(userId);
        return (user != null) ? pi.name() : "defaultProfileImage.png";
    }

	@Override
	public boolean confirmFriendRequest(int friendRequestId) {
		Friend friend= friendModel.findOne(friendRequestId);
		
		if(null != friend) {
			friendModel.save(friend);
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteFriend(int userId, int friendId) {
		Friend friend = friendModel.findOne(userId);
		return false;
	}

}
