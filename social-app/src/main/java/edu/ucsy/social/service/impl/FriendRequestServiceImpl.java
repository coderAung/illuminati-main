package edu.ucsy.social.service.impl;

import java.util.ArrayList;
import java.util.List;

import edu.ucsy.social.data.Model;
import edu.ucsy.social.data.ModelFactory;
import edu.ucsy.social.data.OneToOne;
import edu.ucsy.social.model.dto.view.FriendRequestView;
import edu.ucsy.social.model.entity.FriendRequest;
import edu.ucsy.social.model.entity.ProfileImage;
import edu.ucsy.social.model.entity.User;
import edu.ucsy.social.service.FriendRequestService;

public class FriendRequestServiceImpl implements FriendRequestService {
  
  private Model<FriendRequest> friendRequestModel;
  private Model<User> userModel;
  
  public FriendRequestServiceImpl() {
    this.friendRequestModel = ModelFactory.getModel(FriendRequest.class);
    this.userModel = ModelFactory.getModel(User.class);
  }


  @Override
  public List<FriendRequestView> getFriendRequestViews(int userId) {
     // Fetch friend requests sent to the user
    List<FriendRequest> friendRequests = (List<FriendRequest>) friendRequestModel.findOne(userId, "request_by");
        List<FriendRequestView> friendRequestViews = new ArrayList<>();

        for (FriendRequest request : friendRequests) {
            // Get the user who sent the friend request
            var user = userModel.findOne((int) request.requestBy());
            if (user != null) {
                // Get the profile image of the user who sent the friend request
                var profileImage = userModel.getRelational(OneToOne.class).getOne(ProfileImage.class, userId);
                var friendRequestView = new FriendRequestView(request.id(), user.name());

                if (profileImage != null) {
                    friendRequestView.setFriendProfileImage(profileImage.name());
                }
                friendRequestViews.add(friendRequestView);
            }
        }
        return friendRequestViews;
  }

  @Override
  public boolean createFriendRequest(int userId, int requestToUserId) {
     // Create a new friend request
        var friendRequest = new FriendRequest(requestToUserId, userId);
        var result = friendRequestModel.save(friendRequest);
        if(null != result) {
      return true;
    }
    return false;
  }

  @Override
  public boolean deleteFriendRequest(int friendRequestId) {
    // Delete the friend request by id
        return friendRequestModel.delete(friendRequestId);
  }
}