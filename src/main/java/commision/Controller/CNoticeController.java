package commision.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cnotice")
public class CNoticeController {
	
	@GetMapping("/add")
	public String CNoticeAddForm()
	{
		return "thymeleaf/CNotice/CNoticeAdd";
	}
}
