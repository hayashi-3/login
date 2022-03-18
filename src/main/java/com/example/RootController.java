package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.auth.User;
import com.example.auth.UserRegistrationService;

@Controller
public class RootController {

	@Autowired
	private UserRegistrationService userRegistrationService;

	@Autowired
	private PasswordEncoder PasswordEncoder;

	@RequestMapping("/")
	public String root() {
		return "index";
	}

	@PostMapping("/index")
	public String index() {
		return "index";
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/registration")
	public String signup() {
		return "signup";
	}

	@PostMapping("/registration")
	public String registration(@RequestParam("email") String email, @RequestParam("emailConfirm") String emailConfirm,
			@RequestParam("username") String username, @RequestParam("password") String password,
			@RequestParam("passwordConfirm") String passwordConfirm) {

		User user = new User();
		user.setEmail(email);
		user.setUsername(username);
		user.setPassword(PasswordEncoder.encode(password));

		userRegistrationService.registerUser(user);

		return "login";

	}

}
