package commision.Controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import commision.Service.CQuestionService;
import commision.Service.PageService;
import commision.Vo.CQuestion;
import commision.Vo.CReQuestion;
import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/cquestion")
public class CQuestionController 
{
	@Autowired
	CQuestionService cqs;
	
	@Autowired
	PageService ps;
	
	@GetMapping("/add")
	public String CQuestionAddForm()
	{
		return "thymeleaf/CQuestion/CQuestionAdd";
	}
	
	@GetMapping("/readd")
	public String CReQuestionAddForm()
	{
		return "thymeleaf/CQuestion/CReQuestionAdd";
	}
	
	@GetMapping("/cquestion")
	public String cq(@RequestParam("CQNum")int CQNum, Model m)
	{
		CQuestion cques = cqs.GetCQuestion(CQNum);
		m.addAttribute("CQuestion", cques);
		
		return "thymeleaf/CQuestion/CQuestion";
	}
	
	@GetMapping("/list")
	public String list(@RequestParam(value="page", required = false, defaultValue="1")int page, Model m)
	{
		PageHelper.startPage(page,25);
		PageInfo <Map<String,Object>> pageinfo = new PageInfo<>(cqs.AllCQuestion());
		
		m.addAttribute("pageinfo", pageinfo);
		m.addAttribute("pages", ps.pages(pageinfo));  // 페이지이동
		
		return "thymeleaf/CQuestion/CQuestionList";
	}
	
	@PostMapping("/upload")
	@ResponseBody
	public Map<String, Object> CQuestionUpload(CQuestion cq, HttpServletRequest request)
	{
		Map<String, Object> map = new HashMap<>();
		map.put("added", cqs.CQuestionAdd(cq, request));
		
		return map;
	}
	
	@PostMapping("/reupload")
	@ResponseBody
	public Map<String, Object> CReQuestionUpload(CReQuestion crq, HttpServletRequest request)
	{
		Map<String, Object> map = new HashMap<>();
		map.put("added", cqs.CReQuestionAdd(crq, request));
		
		return map;
	}
	
}
