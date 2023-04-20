package commision.Controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import commision.Service.EmailService;
import commision.Service.LoginService;
import commision.Vo.CUser;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/login")
public class LoginController 
{
	@Autowired
	public LoginService ls;
	
	@Autowired
	public EmailService es;
	
	@GetMapping("/login")
	public String GetLogin()
	{
		return "thymeleaf/User/LoginForm";
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
			return "";
		}
	}
	
	@PostMapping("/add")
	@ResponseBody
	public Map<String, Object> UserAdd(CUser user)
	{
		Map<String, Object> map = new HashMap<>();
		map.put("added",ls.UserAdd(user));
		return map;
	}
	   
	
	@GetMapping("/test")
	public String test()
	{
		return "thymeleaf/User/test";
	}
}
