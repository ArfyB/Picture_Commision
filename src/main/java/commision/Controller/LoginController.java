package commision.Controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import commision.Service.EmailService;
import commision.Service.LoginService;
import commision.UserDetailService.CustomUserDetails;
import commision.Vo.CUser;
import commision.security.SimpleSecurityConfig;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/sec")
public class LoginController 
{
	@Autowired
	private LoginService ls;
	
	@Autowired
	private EmailService es;
	
	@Autowired
	private SimpleSecurityConfig ssc;
	
	@GetMapping("/login")
	public String GetLogin()
	{
		return "thymeleaf/User/LoginForm";
	}
	
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
	        request.getSession().setAttribute("nick", nick);
	    }

	    for (GrantedAuthority authority : auth) {
	        logined = authority.getAuthority();
	    }

	    CookieValue = "true"+"|"+email+"|"+nick+"|"+logined;

	    Cookie loginCookie = new Cookie("login",CookieValue);
	    loginCookie.setPath("/");
	    response.addCookie(loginCookie);
	    System.out.println(CookieValue);
	    
	    
	    System.out.println((String)request.getSession().getAttribute("nick"));
	    return "redirect:/commision/home";
	}
	
	@GetMapping("/logout")
	public String asd(HttpServletRequest request, HttpServletResponse response)
	{
		request.getSession().invalidate();
		return "redirect:/";
	}
	
	@GetMapping("/join1")
	public String GetJoin1()
	{
		return "thymeleaf/User/JoinForm1";
	}
	
	@GetMapping("/join2")
	public String GetJoin2()
	{
		return "thymeleaf/User/JoinForm2";
	}
	
	
	@PostMapping("/email")
	@ResponseBody
	public Map<String, Object> PostJoin(CUser user, HttpServletRequest request)
	{
		Map<String, Object> map = new HashMap<>();
		map.put("user", user);
		HttpSession session = request.getSession();
		
		if(ls.EmailCheck(user))
		{
			map.put("check", true);
			return map;
		}
		map.put("check", false);
	    boolean certify = es.sendHTMLMessage(map, session);
	    
	    map.put("certify", certify);
		return map;
	}
	
	@GetMapping("/auth/{code}")  // 보낸 메일에서 이용자가 인증 링크를 클릭했을 때
	public String index(@PathVariable("code")String code, HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		String auth = (String) session.getAttribute("auth");
		//log.info("인증코드 확인={}", code);
		
		boolean same = auth.equals(code);
		String email = (String) request.getSession().getAttribute("email");
		if(same)
		{
			return "thymeleaf/User/JoinForm2";
		}
		else
		{
			return "thymeleaf/User/JoinFormError";
		}
	}
	
	@PostMapping("/add")
	@ResponseBody
	public Map<String, Object> UserAdd(CUser user)
	{
		Map<String, Object> map = new HashMap<>();
		user.setUserPwd(ssc.pwdencoding(user.getUserPwd()));
		map.put("added",ls.UserAdd(user));
		return map;
	}
	   
}
