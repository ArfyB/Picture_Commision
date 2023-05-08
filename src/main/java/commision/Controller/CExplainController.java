package commision.Controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import commision.Vo.CExplain;
import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/cexplain")
public class CExplainController 
{
	@GetMapping("/add")
	public String CExplainAdd()
	{
		return "thymeleaf/CExplain/CExplainAdd";
	}
	
	@PostMapping("/imgplus")
	public Map<String, Object> ImgPlus(@RequestParam("files")MultipartFile[] mfiles, HttpServletRequest request)
	{
		for(int i = 0; i<mfiles.length; i++)
		{
			
			System.out.println(mfiles[i].getOriginalFilename());
		}
		return null;
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
