package commision.Controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import commision.Vo.CExplain;

@Controller
@RequestMapping("/cexplain")
public class CExplainController 
{
	@GetMapping("/add")
	public String CExplainAdd()
	{
		return "thymeleaf/CExplain/CExplainAdd";
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
