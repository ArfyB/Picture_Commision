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
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import commision.Service.CExplainService;
import commision.Service.PageService;
import commision.Vo.CExplain;
import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/cexplain")
public class CExplainController 
{
	@Autowired
	public CExplainService cs;
	
	@Autowired
	PageService ps;
	
	@GetMapping("/add")
	public String CExplainAdd()
	{
		return "thymeleaf/CExplain/CExplainAdd";
	}
	
	@GetMapping("/cexplain")
	public String GetCExplain(@RequestParam("CNum")int CNum, Model m)
	{
		m.addAttribute("CExplain", cs.GetCExplain(CNum));
		return "thymeleaf/CExplain/CExplain";
	}
	
	@GetMapping("/list")
	public String list(@RequestParam(value="page", required = false, defaultValue="1")int page, Model m)
	{
		PageHelper.startPage(page,25);
		PageInfo <Map<String,Object>> pageinfo = new PageInfo<>(cs.AllCExplain());
		
		m.addAttribute("pageinfo", pageinfo);
		m.addAttribute("pages", ps.pages(pageinfo));  // 페이지이동
		
		return "thymeleaf/CExplain/CExplainList";
	}
	
	
	@PostMapping("/upload")
	@ResponseBody
	public Map<String, Object> upload(@RequestParam("Thumbnail")MultipartFile[] mfiles, CExplain cex, HttpServletRequest request)
	{
		cex.setPainter((String)request.getSession().getAttribute("nick"));
		cex.setPainterTag((String)request.getSession().getAttribute("tag"));
		Map<String,Object> map = new HashMap<>();
		map.put("CExplain", cex);
		map.put("mfiles", mfiles);
		map.put("request", request);
		
		
		
		Map<String,Object> added = new HashMap<>();
		added.put("added", cs.AddCExplain(map));
		
		return added;
	}
	
	@PostMapping("/test")
	public Map<String,Object> CExplainTest(CExplain cex)
	{
		Map<String,Object> map = new HashMap<>();
		System.out.println(cex.getContents());
		System.out.println(cex.getTitle());
		return null;
	}
}
