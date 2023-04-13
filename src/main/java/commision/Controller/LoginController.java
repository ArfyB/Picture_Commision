package commision.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController 
{
	@GetMapping("/login")
	public String login()
	{
		return "thymeleaf/User/LoginForm";
	}
	
	@GetMapping("/join")
	public String join()
	{
		return "thymeleaf/User/JoinForm";
	}
}
