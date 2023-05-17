package commision.Controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
	
	@PostMapping("/upload")
	public Map<String, Object> CQuestionUpload(CQuestion cq, HttpServletRequest request)
	{
		Map<String, Object> map = new HashMap<>();
		map.put("added", cqs.CQuestionAdd(cq, request));
		
		return map;
	}
	
	@PostMapping("/reupload")
	public Map<String, Object> CReQuestionUpload(CReQuestion crq, HttpServletRequest request)
	{
		Map<String, Object> map = new HashMap<>();
		map.put("added", cqs.CQuestionAdd(crq, request));
		
		return map;
	}
}
