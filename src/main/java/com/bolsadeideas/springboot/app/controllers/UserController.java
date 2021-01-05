package com.bolsadeideas.springboot.app.controllers;

import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.bolsadeideas.springboot.app.models.entity.Usuario;
import com.bolsadeideas.springboot.app.models.service.IUserService;

@Controller
@Secured("ROLE_ADMIN")
@RequestMapping("/users")
@SessionAttributes("user")
public class UserController {

	private final Log logger = LogFactory.getLog(this.getClass());

	@Autowired
	private IUserService userService; 
	
	@Autowired
	private MessageSource messageSource;
	
	@RequestMapping(value = { "/list", "/" }, method = RequestMethod.GET)
	public String list(Model model, Authentication authentication, HttpServletRequest request,Locale locale  ) {
		
		logger.info("ingresado al listado de usuarios");
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		List<Usuario> users = (List<Usuario>) userService.findAll();
		
		model.addAttribute("titulo", messageSource.getMessage("text.user.list.title", null, locale));
		model.addAttribute("users", users);
		
		return "/users/list";

	}

}
