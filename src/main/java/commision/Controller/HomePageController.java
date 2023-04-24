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
	
	@GetMapping("/loginsuccess")
	public String logincookie(@AuthenticationPrincipal UserDetails user, HttpServletResponse response, HttpServletRequest request)
	{
	    String email = user.getUsername();
	    String pwd = user.getPassword();
	    String nick = "";
	    String logined = "";
	    String CookieValue = "";
	    
	    Collection<? extends GrantedAuthority> auth = user.getAuthorities();

	    if(user instanceof CustomUserDetails) {
	        CustomUserDetails customUser = (CustomUserDetails) user;
	        nick = customUser.getUsernick();
	    }

	    for (GrantedAuthority authority : auth) {
	        logined = authority.getAuthority();
	    }

	    CookieValue = "true"+"|"+email+"|"+nick+"|"+logined;

	    Cookie loginCookie = new Cookie("login",CookieValue);
	    loginCookie.setPath("/");
	    response.addCookie(loginCookie);
	    System.out.println(CookieValue);
	    
	    return "redirect:/commision/home";
	}
	
	@GetMapping("/home")
	public String HomePage(@AuthenticationPrincipal UserDetails user)
	{
		
		return "thymeleaf/Home/HomePage";
	}
}
