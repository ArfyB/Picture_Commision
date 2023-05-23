package commision.Controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import commision.Service.CQuestionService;
import commision.Vo.CQuestion;
import commision.Vo.CReQuestion;
import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/cquestion")
public class CQuestionController 
{
	@Autowired
	CQuestionService cqs;
	
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
	
	@GetMapping("/cq")
	public String cq(Model m)
	{
		CQuestion cques = cqs.GetCQuestion();
		m.addAttribute("CQuestion", cques);
		
		return "thymeleaf/CQuestion/CQuestion";
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
