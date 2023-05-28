package commision.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import commision.Service.CExplainService;
import commision.Service.HomePageService;

@Controller
@RequestMapping("/commision")
public class HomePageController 
{
	@Autowired
	HomePageService hps;
	
	@Autowired
	CExplainService cs;
	
	@GetMapping("/home")
	public String HomePage(Model m)
	{
		m.addAttribute("CExplain", cs.HomePageCExplain());
		return "thymeleaf/Home/HomePage";
	}
}
