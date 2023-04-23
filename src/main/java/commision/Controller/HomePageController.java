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
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/commision")
public class HomePageController 
{
	@Autowired
	public HomePageService hps;
	
	@GetMapping("/loginsuccess")
	public String logincookie(@AuthenticationPrincipal UserDetails user, HttpServletResponse response)
	{
		String email = user.getUsername();
		String pwd = user.getPassword();
		String nick = "";
		String logined = "";
		Collection<? extends GrantedAuthority> auth = user.getAuthorities();
		
	    if(user instanceof CustomUserDetails) {
	        CustomUserDetails customUser = (CustomUserDetails) user;
	        nick = customUser.getUsernick();
	    }
	    
		for (GrantedAuthority authority : auth) {
		    logined = authority.getAuthority();
		}
		
		Cookie loginCookie = new Cookie("login", "true"+"|"+email+"|"+pwd+"|"+nick+"|"+logined);
		
		return "redirect:/home";
	}
	
	@GetMapping("/home")
	public String HomePage(@AuthenticationPrincipal UserDetails user)
	{
		
		return "thymeleaf/Home/HomePage";
	}
}
