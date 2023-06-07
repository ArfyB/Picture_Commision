package commision.Controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import commision.Service.CExplainService;
import commision.Service.EmailService;
import commision.Service.LoginService;
import commision.Service.PageService;
import commision.Service.UserService;
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
	private UserService us;
	
	@Autowired
	private CExplainService cs;
	
	@Autowired
	private PageService ps;
	
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
	    String tag = "";
	    String nick = "";
	    String logined = "";
	    String CookieValue = "";
	    
	    Collection<? extends GrantedAuthority> auth = user.getAuthorities();

	    if(user instanceof CustomUserDetails) {
	        CustomUserDetails customUser = (CustomUserDetails) user;
	        nick = customUser.getUsernick();
	        tag = customUser.getUsertag();
	    }

	    for (GrantedAuthority authority : auth) {
	        logined = authority.getAuthority();
	    }

	    CookieValue = "true"+"|"+email+"|"+nick+"|"+logined+"|"+tag;

	    Cookie loginCookie = new Cookie("login",CookieValue);
	    loginCookie.setPath("/");
	    response.addCookie(loginCookie);
	    
	    request.getSession().setAttribute("nick", nick);
	    request.getSession().setAttribute("tag", tag);
	    request.getSession().setAttribute("email", email);
	    request.getSession().setAttribute("role", logined);
	    
	    return "redirect:/commision/home";
	}
	
	@GetMapping("/logout")
	public String asd(HttpServletRequest request, HttpServletResponse response)
	{
		request.getSession().invalidate();
		return "redirect:/";
	}
	
	@GetMapping("/join")
	public String GetJoin1()
	{
		return "thymeleaf/User/JoinForm1";
	}
	
	@GetMapping("/join2")
	public String GetJoin2()
	{
		return "thymeleaf/User/JoinForm2";
	}
	
	@GetMapping("/join3")
	public String GetJoin3(Model m, HttpServletRequest request)
	{
		CUser user = us.GetUserEmail( (String)request.getSession().getAttribute("email") );
		System.out.println(user.getUserBackgroundPic());
		System.out.println(user.getUserProfilePic());
		System.out.println(user.getUserEmail());
		System.out.println(user.getUserNick());
		m.addAttribute("CUser", user);
		
		return "thymeleaf/User/JoinForm3";
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
		}
		else
		{
			map.put("check", false);
			boolean certify = es.sendHTMLMessage(map, session);
		    
		    map.put("certify", certify);
		}
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
	
	@PostMapping("/update")
	@ResponseBody
	public Map<String, Object> UserUpdate(@RequestParam("files")MultipartFile[] mfiles, CUser cuser, HttpServletRequest request)
	{
		Map<String, Object> map = new HashMap<>();
		map.put("mfiles", mfiles);
		map.put("CUser", cuser);
		
		Map<String, Object> updated = new HashMap<>();
		
		boolean update = us.UserUpdate(map);
		
		updated.put("updated", update);
		if(update)
		{
			// tag만 새로 설정하는 이유는 
			request.getSession().setAttribute("tag", cuser.getUserTag());
			request.getSession().setAttribute("nick", cuser.getUserNick());
		}
		return updated;
	}
	
	@PostMapping("/set")
	@ResponseBody
	public Map<String, Object> UserSet(@RequestParam("files")MultipartFile[] mfiles, CUser cuser, HttpServletRequest request)
	{
		Map<String, Object> map = new HashMap<>();
		map.put("mfiles", mfiles);
		map.put("CUser", cuser);
		
		Map<String, Object> updated = new HashMap<>();
		
		boolean set = us.UserUpdate(map);
		
		updated.put("set", set);
		if(set)
		{
			// tag만 새로 설정하는 이유는 
			request.getSession().setAttribute("tag", cuser.getUserTag());
			request.getSession().setAttribute("nick", cuser.getUserNick());
		}
		return updated;
	}
	
	@GetMapping("/mypage")
	public String MyPage(@RequestParam(value="page", required = false, defaultValue="1")int page, HttpServletRequest request, Model m)
	{
		Map<String, Object> map = new HashMap<>();
		m.addAttribute("CUser", us.GetUser((String)request.getSession().getAttribute("tag")));
		
		PageHelper.startPage(page,20);
		PageInfo <Map<String,Object>> permitpageinfo = new PageInfo<>(cs.PermitZeroCExplain());
		
		m.addAttribute("permit", permitpageinfo);
		m.addAttribute("permitpages", ps.pages(permitpageinfo));
		
		PageInfo <Map<String,Object>> denypageinfo = new PageInfo<>(cs.PermitTwoCExplain());
		
		m.addAttribute("deny", denypageinfo);
		m.addAttribute("denypages", ps.pages(denypageinfo));
		
		PageInfo <Map<String,Object>> MyCExplainpageinfo = new PageInfo<>(cs.MyPageCExplain((String)request.getSession().getAttribute("tag")));
		
		m.addAttribute("MyCExplain", MyCExplainpageinfo);
		m.addAttribute("MyCExplainpages", ps.pages(MyCExplainpageinfo));
		
		PageInfo <Map<String,Object>> MyOrderpageinfo = new PageInfo<>(cs.MyOrder((String)request.getSession().getAttribute("tag")));
		
		m.addAttribute("MyOrder", MyOrderpageinfo);
		m.addAttribute("MyOrderpages", ps.pages(MyOrderpageinfo));
		
		PageInfo <Map<String,Object>> TakeOrderpageinfo = new PageInfo<>(cs.TakeOrder((String)request.getSession().getAttribute("tag")));
		
		m.addAttribute("TakeOrder", TakeOrderpageinfo);
		m.addAttribute("TakeOrderpages", ps.pages(TakeOrderpageinfo));
		
		return "thymeleaf/User/MyPage";
	}
	
	@GetMapping("/edit")
	public String UserEdit(HttpServletRequest request, Model m)
	{
		Map<String, Object> map = new HashMap<>();
		m.addAttribute("CUser", us.GetUser((String)request.getSession().getAttribute("tag")));
		return "thymeleaf/User/UserEdit";
	}
}
