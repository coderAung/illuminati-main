package edu.ucsy.social.controller;

import java.io.IOException;

import javax.sql.DataSource;

import edu.ucsy.social.service.SearchingService;
import edu.ucsy.social.service.ServiceFactory;
import edu.ucsy.social.utils.DefaultPicture;
import edu.ucsy.social.utils.StringTool;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(
	urlPatterns = {"/search", "/search/user", "/search/post"},
	loadOnStartup = 1
)
public class SearchController extends Controller {

	private static final long serialVersionUID = 1L;
	
	private static final String SEARCH = "/search";
	private static final String SEARCH_USER = "/search/user";
	private static final String SEARCH_POST = "/search/post";

	@Resource(name = "social")
	private DataSource dataSource;
	
	private SearchingService searchingService;
	
	@Override
	public void init() throws ServletException {
		searchingService = ServiceFactory.getService(SearchingService.class, dataSource);
	}
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		var path = req.getServletPath();
		switch (path) {
		case SEARCH:
			search(req, resp);
			break;
		case SEARCH_USER:
			searchUser(req, resp);
			break;
		case SEARCH_POST:
			search(req, resp);
		default:
			break;
		}
	}

	private void searchUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(!StringTool.isEmpty(req.getParameter("word"))) {
			var word = req.getParameter("word");
			var userViews = searchingService.searchUsers(word);
			
			for(var uv : userViews) {
				
			}
			
			req.setAttribute("userViews", userViews);
			view(req, resp, "search-result");
		}
	}


	private void search(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(!StringTool.isEmpty(req.getParameter("word"))) {
			var loginUser = getLoginUser(req);
			var word = req.getParameter("word");
			var postViews = searchingService.searchPosts(word, loginUser.getId());
			
			req.setAttribute("word", word);
			
			for(var pv : postViews) {
				var postImageList = pv.getPostImageList();
				if(null != postImageList && 0 < postImageList.size()) {
					postImageList = postImageList.stream().map(pi -> getImagePath(pi, ImageType.POST)).toList();
				}
				pv.setPostImageList(postImageList);
				
				var pi = pv.getProfileImage();
				if(null != pi) {
					pv.setProfileImage(getImagePath(pi, ImageType.PROFILE));
				} else {
					pv.setProfileImage(getImagePath(DefaultPicture.defaultProfilePicture, ImageType.PROFILE));
				}
			}
			req.setAttribute("activeTab", "post");
			req.setAttribute("postViews", postViews);
			view(req, resp, "search-result");
		}
	}
}
