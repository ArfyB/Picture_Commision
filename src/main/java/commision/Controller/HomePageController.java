package commision.Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import commision.Service.CExplainService;
import commision.Service.CNoticeService;
import commision.Service.CQuestionService;
import commision.Service.HomePageService;
import commision.Service.PageService;
import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/commision")
public class HomePageController 
{
	@Autowired
	HomePageService hps;
	
	@Autowired
	CExplainService cs;
	
	@Autowired
	CNoticeService cns;
	
	@Autowired
	CQuestionService cqs;
	
	@Autowired
	PageService ps;
	
	@GetMapping("/home")
	public String HomePage(@RequestParam(value="page", required = false, defaultValue="1")int page, Model m, HttpServletRequest request)
	{
		PageHelper.startPage(page,25);
		PageInfo <Map<String,Object>> pageinfo = new PageInfo<>(cs.AllCExplain());
		
		m.addAttribute("CExplain", pageinfo);
		m.addAttribute("pages", ps.pages(pageinfo));
		
		
		m.addAttribute("CExplain", cs.HomePageCExplain());
		m.addAttribute("CNotice", cns.HomePageCNotice());
		m.addAttribute("CQuestion", cqs.HomePageCQuestion());
		
		return "thymeleaf/Home/HomePage";
	}
}
