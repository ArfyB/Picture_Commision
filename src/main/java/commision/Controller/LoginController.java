package commision.Controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import commision.Service.LoginService;
import commision.Vo.CUser;

@Controller
@RequestMapping("/login")
public class LoginController 
{
	@Autowired
	public LoginService ls;
	
	@GetMapping("/login")
	public String GetLogin()
	{
		return "thymeleaf/User/LoginForm";
	}
	
	@GetMapping("/join")
	public String GetJoin()
	{
		return "thymeleaf/User/JoinForm";
	}
	
	@PostMapping("/join")
	@ResponseBody
	public Map<String, Object> PostJoin(CUser user)
	{
		System.out.println(user.UserPwd);
		Map<String, Object> map = new HashMap<>();
		map.put("join", ls.UserAdd(user));
		return map;
	}
	
	@GetMapping("/test")
	public String test()
	{
		return "thymeleaf/User/test";
	}
}
