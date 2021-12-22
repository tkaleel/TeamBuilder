package com.codingdojo.pokemon.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.codingdojo.pokemon.models.LoginUser;
import com.codingdojo.pokemon.models.User;
import com.codingdojo.pokemon.services.HomeService;



@Controller
public class HomeController {
	@Autowired
	private HomeService homeServ;
	
	@GetMapping("/")
	public String index() {
		
		return "pokeball.jsp";
	}
	
	@GetMapping("/logreg")
	public String logreg(Model model, HttpSession session) {
		if(session.getAttribute("user_id") != null) {
			return "redirect:/dashboard";
		}
		model.addAttribute("newUser", new User());
		model.addAttribute("newLogin", new LoginUser());
		return "logreg.jsp";
	}
	
	@PostMapping("/register")
    public String register(@Valid @ModelAttribute("newUser") User newUser, 
            BindingResult result,  HttpSession session, Model model) {
        homeServ.register(newUser, result);
        if(result.hasErrors()) {
    		model.addAttribute("newLogin", new LoginUser());
            return "logreg.jsp";
        }
        session.setAttribute("user_id", newUser.getId());
        return "redirect:/dashboard";
    }
    
    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin, 
            BindingResult result, Model model, HttpSession session) {
        User user = homeServ.login(newLogin, result);
        if(result.hasErrors()) {
            model.addAttribute("newUser", new User());
            return "logreg.jsp";
        }
        session.setAttribute("user_id", user.getId());
        return "redirect:/dashboard";
    }
	
	
	@GetMapping("/dashboard")
	public String dashboard(HttpSession session) {
		if(session.getAttribute("user_id") == null) {
    		return "redirect:/";
    	}else {
		return "dashboard.jsp";
    	}
	}
	

}
