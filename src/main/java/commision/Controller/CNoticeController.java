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

import commision.Mapper.CNoticeMapper;
import commision.Service.CNoticeService;
import commision.Service.PageService;
import commision.Vo.CNotice;
import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/cnotice")
public class CNoticeController 
{
	@Autowired
	CNoticeService cns;
	
	@Autowired
	CNoticeMapper cnm;
	
	@Autowired
	PageService ps;
	
	@GetMapping("/add")
	public String CNoticeAddForm()
	{
		return "thymeleaf/CNotice/CNoticeAdd";
	}
	
	@GetMapping("/cnotice")
	public String GetCNoticeHits(@RequestParam("CNoticeNum") int CNoticeNum, Model m)
	{
		m.addAttribute("CNotice", cns.GetCNoticeHits(CNoticeNum));
		return "thymeleaf/CNotice/CNotice";
	}
	
	@GetMapping("/list")
	public String list(@RequestParam(value = "page", required = false, defaultValue = "1")int page, Model m)
	{
		PageHelper.startPage(page,10);
		PageInfo <Map<String,Object>> pageinfo = new PageInfo<>(cnm.AllCNotice());
		
		m.addAttribute("pageinfo", pageinfo);
		m.addAttribute("pages", ps.pages(pageinfo));  // 페이지이동
		
		return "thymeleaf/CNotice/CNoticeList";
	}
	
	@PostMapping("/upload")
	@ResponseBody
	public Map<String, Object> CQuestionUpload(CNotice cn, HttpServletRequest request)
	{
		Map<String, Object> map = new HashMap<>();
		map.put("added", cns.CNoticeAdd(cn, request));
		
		return map;
	}
}
