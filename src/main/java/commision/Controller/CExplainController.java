package commision.Controller;

import java.sql.Connection;
import java.sql.SQLException;
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
		PageHelper.startPage(page,20);
		PageInfo <Map<String,Object>> pageinfo = new PageInfo<>(cs.AllCExplain());
		
		m.addAttribute("cexplain", pageinfo);
		m.addAttribute("pages", ps.pages(pageinfo));  // 페이지이동
		
		return "thymeleaf/CExplain/CExplainList";
	}
	
	@GetMapping("/mycommisionlist")
	public String mycommisionlist(@RequestParam(value="page", required = false, defaultValue="1")int page, Model m, HttpServletRequest request)
	{
		PageHelper.startPage(page,20);
		PageInfo <Map<String,Object>> pageinfo = new PageInfo<>(cs.MyCExplain((String)request.getSession().getAttribute("tag")));
		
		m.addAttribute("cexplain", pageinfo);
		m.addAttribute("pages", ps.pages(pageinfo));  // 페이지이동
		
		return "thymeleaf/CExplain/MyCExplainList";
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
		m.addAttribute("pages", ps.pages(permitpageinfo));
		
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
	
	@GetMapping("/deny")
	public String CExplainDeny(@RequestParam(value="page", required = false, defaultValue="1")int page, Model m)
	{
		PageHelper.startPage(page,20);
		PageInfo <Map<String,Object>> denypageinfo = new PageInfo<>(cs.PermitTwoCExplain());
		
		m.addAttribute("deny", denypageinfo);
		m.addAttribute("denypages", ps.pages(denypageinfo));
		
		return "thymeleaf/CExplain/PermitCExplain";
	}
	
	@PostMapping("/deny")
	@ResponseBody
	public Map<String, Object> DenyData(@RequestParam("cnum")int cnum)
	{
		Map<String, Object> map = new HashMap<>();
		map.put("deny", cs.DenyCExplain(cnum));
		return map;
	}
	
	@GetMapping("/myorderlist")
	public String myorderlist(@RequestParam(value="page", required = false, defaultValue="1")int page, Model m, HttpServletRequest request)
	{
		PageHelper.startPage(page,20);
		PageInfo <Map<String,Object>> MyOrder = new PageInfo<>(cs.MyOrder((String)request.getSession().getAttribute("tag")));
		
		m.addAttribute("myorder", MyOrder);
		m.addAttribute("pages", ps.pages(MyOrder));
		
		return "thymeleaf/CExplain/MyOrderList";
	}
	
	@GetMapping("/takeorderlist")
	public String takeorderlist(@RequestParam(value="page", required = false, defaultValue="1")int page, Model m, HttpServletRequest request)
	{
		PageHelper.startPage(page,20);
		PageInfo <Map<String,Object>> TakeOrder = new PageInfo<>(cs.TakeOrder((String)request.getSession().getAttribute("tag")));
		
		m.addAttribute("takeorder", TakeOrder);
		m.addAttribute("pages", ps.pages(TakeOrder));
		return "thymeleaf/CExplain/TakeOrderList";
	}
	
	@GetMapping("/orderdata")
	public String myorderpage(@RequestParam("ACNum")int ACNum, Model m)
	{
		m.addAttribute("OrderData", cs.OrderData(ACNum));
		return "thymeleaf/CExplain/OrderData";
	}
	
	@PostMapping("/apply_order")
	@ResponseBody
	public Map<String, Object> ApplyData(@RequestParam("acnum")int acnum, @RequestParam("cnum")int cnum)
	{
		Map<String, Object> map = new HashMap<>();
		
		map.put("permit", cs.PermitOrder(acnum));
		cs.UpdateOrder(cnum);
		
		return map;
	}
	
	@PostMapping("/deny_order")
	@ResponseBody
	public Map<String, Object> NopeCExplain(@RequestParam("acnum")int acnum)
	{
		Map<String, Object> map = new HashMap<>();
		map.put("deny", cs.DenyOrder(acnum));
		return map;
	}
	
	@PostMapping("/finish_order")
	@ResponseBody
	public Map<String, Object> FinishData(@RequestParam("acnum")int acnum, @RequestParam("cnum")int cnum)
	{
		Map<String, Object> map = new HashMap<>();
		
		map.put("finish", cs.FinishOrder(acnum));
		cs.UpdateOrder(cnum);
		
		return map;
	}
}
