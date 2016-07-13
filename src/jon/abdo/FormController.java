package jon.abdo;

import java.security.NoSuchAlgorithmException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FormController {

	@RequestMapping("/welcome")
	public ModelAndView welcome() {
		return new ModelAndView("welcome", "message", "Welcome to the GC Portal");
	}

	@RequestMapping("/createLogin")
	public ModelAndView createLogin() {
		return new ModelAndView("login", "command", new User());
	}

	@RequestMapping("/submitLogin")
	public String submitLogin(@ModelAttribute("command") User user, Model model, 
			@CookieValue(value = "loggedIn", defaultValue = "false") String loggedIn, HttpServletResponse response) {

		if (DAO.checkLogin(user)) {
			Cookie cookie = new Cookie("loggedIn", "true");
			cookie.setMaxAge(60);
			response.addCookie(cookie);
			model.addAttribute("cookie", cookie);
			
			return "memberArea";
			
		} else {
			if (DAO.containsUser(user))
				model.addAttribute("passError", "Password is incorrect.");
			else
				model.addAttribute("userError", "User doesn't exist.");

			return "login";
		}
	}
	
	@RequestMapping(value = { "1","2","3" })
	public String checkSession(Model model, @CookieValue(value = "loggedIn", defaultValue = "false") String loggedIn, 
			@CookieValue(value = "counter", defaultValue = "0") Long counter, HttpServletResponse response) {
		//if(loggedIn.equals("false")
		//return "welcome";
		counter++;
		Cookie cookie = new Cookie("counter", counter.toString());
		response.addCookie(cookie);
		
		return "memberArea";
	}

	@RequestMapping("/createUser")
	public ModelAndView createUser() {
		return new ModelAndView("signUp", "command", new User());
	}

	@RequestMapping("/submitNewUser")
	public String submitNewUser(@ModelAttribute("command") User user, Model model) throws NoSuchAlgorithmException {
		if (DAO.containsUser(user)) {
			user.resetPassword();
			model.addAttribute("username", user.getUsername());
			model.addAttribute("error", " is already taken.");
			return "signUp";
		} else {
			DAO.addUser(user);
			model.addAttribute("username", user.getUsername());
			return "confirmation";
		}
	}

}