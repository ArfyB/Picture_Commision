package commision.Controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import commision.Service.CNoticeService;
import commision.Vo.CNotice;
import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/cnotice")
public class CNoticeController 
{
	@Autowired
	CNoticeService cns;
	
	@GetMapping("/add")
	public String CNoticeAddForm()
	{
		return "thymeleaf/CNotice/CNoticeAdd";
	}
	
	@PostMapping("/upload")
	public Map<String, Object> CNoticeUpload(CNotice cn, HttpServletRequest request)
	{
		Map<String, Object> map = new HashMap<>();
		map.put("added", cns.CNoticeAdd(cn, request));
		
		return map;
	}
}
