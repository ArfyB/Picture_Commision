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
import commision.Vo.ApplyCExplain;
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
		CExplain cex = cs.GetCExplain(CNum);
		cex.setTags(cs.GetTags(CNum));
		
		m.addAttribute("CExplain", cex);
		return "thymeleaf/CExplain/CExplain";
	}
	
	@GetMapping("/order")
	public String DoOrder(@RequestParam("CNum")int CNum, Model m)
	{
		m.addAttribute("CNum", CNum);
		return "thymeleaf/CExplain/ApplyCExplain";
	}
	
	@PostMapping("/order")
	@ResponseBody
	public Map<String, Object> UploadOrder(ApplyCExplain apc, HttpServletRequest request)
	{
		// title, contents, cnum를 가지고 apc생성
		Map<String, Object> map = new HashMap<>();
		map.put("added", cs.DoOrder(apc, request));
		
		return map;
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
	
	@GetMapping("/permit")
	public String CExplainPermit(@RequestParam(value="page", required = false, defaultValue="1")int page, Model m)
	{
		PageHelper.startPage(page,20);
		PageInfo <Map<String,Object>> permitpageinfo = new PageInfo<>(cs.PermitZeroCExplain());
		
		m.addAttribute("permit", permitpageinfo);
		m.addAttribute("permitpages", ps.pages(permitpageinfo));
		
		PageInfo <Map<String,Object>> denypageinfo = new PageInfo<>(cs.PermitTwoCExplain());
		
		m.addAttribute("deny", denypageinfo);
		m.addAttribute("denypages", ps.pages(denypageinfo));
		
		return "thymeleaf/CExplain/PermitCExplain";
	}
	
	@PostMapping("/permit")
	@ResponseBody
	public Map<String, Object> PermitData(@RequestParam("cnum")int cnum)
	{
		Map<String, Object> map = new HashMap<>();
		map.put("permit", cs.PermitCExplain(cnum));
		return map;
	}
	
	@PostMapping("/deny")
	@ResponseBody
	public Map<String, Object> DenyData(@RequestParam("cnum")int cnum)
	{
		Map<String, Object> map = new HashMap<>();
		map.put("deny", cs.DenyCExplain(cnum));
		return map;
	}
}
