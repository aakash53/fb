package com.credera.springsocial;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Index {
	
	
	@RequestMapping({"index","/"})
	   public ModelAndView indexpage() {
	      return new ModelAndView("index");
	   }
}
