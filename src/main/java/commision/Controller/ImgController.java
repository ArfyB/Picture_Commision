package commision.Controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import commision.Service.ImageService;
import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/img")
public class ImgController 
{
	@Autowired
	public ImageService is;
	
	@PostMapping("/upload")
	@ResponseBody
	public Map<String, Object> ImgUpload(@RequestParam("files")MultipartFile[] mfiles, HttpServletRequest request)
	{
		Map<String, Object> map = new HashMap<>();
	       
	    map.put("mfiles", mfiles);
	    map.put("request", request);

	    Map<String, Object> result = is.AddImg(map);
	    
		return result;
	}
	
	@PostMapping("/delete")
	@ResponseBody
	public Map<String, Object> ImgDelete(String imageUrl)
	{
		System.out.println(imageUrl);
		Map<String, Object> map = new HashMap<>();
	       
	    map.put("del", is.DeleteImg(imageUrl));
	    
		return map;
	}
}