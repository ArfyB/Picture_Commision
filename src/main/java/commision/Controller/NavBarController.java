package commision.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/nav")
public class NavBarController 
{
	@GetMapping("/nav")
	public String nav()
	{
		return "thymeleaf/nav/Nav";
	}
}
