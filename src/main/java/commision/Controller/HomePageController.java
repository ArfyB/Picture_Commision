package commision.Controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import commision.Service.HomePageService;
import commision.UserDetailService.CustomUserDetails;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/commision")
public class HomePageController 
{
	@Autowired
	public HomePageService hps;
	
	@GetMapping("/home")
	public String HomePage(@AuthenticationPrincipal UserDetails user)
	{
		
		return "thymeleaf/Home/HomePage";
	}
}
