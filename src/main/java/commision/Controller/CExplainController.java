package commision.Controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import commision.Service.CExplainService;
import commision.Vo.CExplain;
import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/cexplain")
public class CExplainController 
{
	@Autowired
	public CExplainService cs;
	
	@GetMapping("/add")
	public String CExplainAdd()
	{
		return "thymeleaf/CExplain/CExplainAdd";
	}
	
	@PostMapping("/upload")
	@ResponseBody
	public Map<String, Object> upload(@RequestParam("Thumbnail")MultipartFile[] mfiles, CExplain cex, HttpServletRequest request)
	{
		cex.setPainter((String)request.getSession().getAttribute("nick"));
		cex.setPainterEmail((String)request.getSession().getAttribute("email"));
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
