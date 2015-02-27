package com.credera.springsocial;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.social.connect.Connection;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.Page;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@Inject
    @Qualifier("facebook")
    Connection<Facebook> facebook;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		return "home";
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String welcome(Locale locale, Model model) {
		Facebook fb = facebook.getApi();

		List<Page> moviePages = fb.likeOperations().getMovies();
		
		List<String> movies = new ArrayList<String>();
		for (Page p : moviePages) {
			movies.add(p.getName());
		}
		
		// Uncomment the following line to see how easy posting to your timeline is.
		//fb.feedOperations().post(userId, "I just tried out the My Movies example site!");
		
		int numFriends = fb.friendOperations().getFriends().size();
		
		model.addAttribute("facebook", true);
		model.addAttribute("numFriends", numFriends);
		model.addAttribute("name", facebook.getDisplayName());
		model.addAttribute("movies", movies);
		
		return "welcome_user";
	}
	
}
