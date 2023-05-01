package commision.Php;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/php")
public class PhpController {
	
	@PostMapping("/upload")
	@ResponseBody
	public Map<String, Object> upload(@RequestParam("file") MultipartFile file) {
	    Map<String, Object> response = new HashMap<>();
	    /*
	    try {
	        // 저장할 경로
	        String uploadDir = "C:/Users/user/git/tp/target/classes/static/";
	        
	        // 파일 이름과 확장자
	        String fileName = file.getOriginalFilename();
	        String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1);
	        
	        // 새로운 파일 이름 생성 (현재시간을 기준으로)
	        String newFileName = md5(String.valueOf(System.currentTimeMillis()) + fileName) + "." + fileExt;
	        
	        // 파일을 업로드할 경로
	        String uploadPath = uploadDir + newFileName;
	        
	        // 파일 업로드
	        file.transferTo(new File(uploadPath));
	        
	        // 저장된 파일 경로 반환
	        response.put("url", uploadPath);
	        response.put("error", 0);  // 에러 없음
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	        response.put("url", "");
	        response.put("error", e.getMessage());
	    }
	    */
	    return response;
	}


}
