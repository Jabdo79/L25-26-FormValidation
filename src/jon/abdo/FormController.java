package jon.abdo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	public String submitLogin(@ModelAttribute("command") User user, Model model) {
		model.addAttribute("username", user.getUsername());
		model.addAttribute("password", user.getPassword());
		return "confirmation";
	}
}