package commision.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import commision.Service.HomePageService;

@Controller
@RequestMapping("/commision")
public class HomePageController 
{
	@Autowired
	public HomePageService hps;
	
	@GetMapping("/home")
	public String HomePage()
	{
		return "thymeleaf/Home/HomePage";
	}
}
