package edu.ucsy.social.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import edu.ucsy.social.data.Model;
import edu.ucsy.social.data.ModelFactory;
import edu.ucsy.social.data.OneToMany;
import edu.ucsy.social.model.dto.view.CommentView;
import edu.ucsy.social.model.dto.view.PostDetailView;
import edu.ucsy.social.model.dto.view.PostView;
import edu.ucsy.social.model.entity.Comment;
import edu.ucsy.social.model.entity.Post;
import edu.ucsy.social.model.entity.PostImage;
import edu.ucsy.social.service.PostService;

public class PostServiceImpl implements PostService {
  
  private Model<Post> postModel;
  private Model<PostImage> postImageModel;
  private Model<Comment> commentModel;
  
  public PostServiceImpl() {
    this.postModel = ModelFactory.getModel(Post.class);
    this.postImageModel = ModelFactory.getModel(PostImage.class);
        this.commentModel = ModelFactory.getModel(Comment.class);
  }

  @Override
  public List<PostView> getPostViews(int userid, int limit) {
    // Fetch posts by user ID and limit
        List<Post> posts = postModel.get(limit);
        List<PostView> postViews = new ArrayList<>();

        for (Post post : posts) {
            List<PostImage> images = postImageModel.getRelational(OneToMany.class).getMany(PostImage.class, post.id());
            List<String> imageNames = new ArrayList<>();
            for (PostImage image : images) {
                imageNames.add(image.name());
            }
            PostView postView = new PostView(post.id(), post.content(), post.updatedAt(), post.userName(), imageNames);
            postViews.add(postView);
        }

        return postViews;
  }

  @Override
  public List<PostView> getRandomPostViews(int i) {
    List<Post> posts = postModel.getAll();
        List<Post> shuffledPosts = new ArrayList<>(posts);
        java.util.Collections.shuffle(shuffledPosts, new Random());
        List<Post> limitedPosts = shuffledPosts.subList(0, Math.min(i, shuffledPosts.size()));
        List<PostView> postViews = new ArrayList<>();

        for (Post post : limitedPosts) {
            List<PostImage> images = postImageModel.getRelational(OneToMany.class).getMany(PostImage.class, post.id());
            List<String> imageNames = new ArrayList<>();
            for (PostImage image : images) {
                imageNames.add(image.name());
            }
            PostView postView = new PostView(post.id(), post.content(), post.updatedAt(), post.userName(), imageNames);
            postViews.add(postView);
        }
        return postViews;
  }

  @Override
  public PostDetailView getPostDetailView(int postId) {
     Post post = postModel.findOne(postId);
          if (post == null) {
              return null;
          }

          List<PostImage> images = postImageModel.getRelational(OneToMany.class).getMany(PostImage.class, postId);
          List<String> imageNames = new ArrayList<>();
          for (PostImage image : images) {
              imageNames.add(image.name());
          }

          PostView postView = new PostView(post.id(), post.content(), post.updatedAt(), post.userName(), imageNames);

          
          List<Comment> comments = commentModel.getAll(); 
          List<CommentView> commentViews = new ArrayList<>();
          for (Comment comment : comments) {
              if (comment.postId() == postId) { // Ensure the comment is for the correct post
                  CommentView commentView = new CommentView(comment.id(), comment.content(), comment.userName(), comment.createdAt());
                  commentViews.add(commentView);
              }
          }
       // Create the PostDetailView object
          PostDetailView postDetailView = new PostDetailView();
          postDetailView.setPostView(postView);
          postDetailView.setCommentViews(commentViews);

          return postDetailView;
  }

  @Override
  public void deletePost(int postId) {
     // Remove associated images and post
        postImageModel.getRelational(OneToMany.class).deleteMany(PostImage.class, postId);
        postModel.delete(postId);
  }

}