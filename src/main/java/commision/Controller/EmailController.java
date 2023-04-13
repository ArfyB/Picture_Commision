package commision.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import commision.Service.EmailService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/mail")
public class EmailController 
{
   @Autowired
   private EmailService svc;

   
   @GetMapping("/test")
   @ResponseBody
   public String sendTestMail()
   {
      //boolean isSent = svc.sendSimpleText();
      //boolean isSent = svc.sendAttachMail();
	  boolean isSent = svc.sendHTMLMessage();
      return isSent ? "메일 보내기 성공":"메일 보내기 실패";
   }
   
   @GetMapping("/auth/{code}")  // 보낸 메일에서 이용자가 인증 링크를 클릭했을 때
   @ResponseBody
   public String index(@PathVariable("code")String code)
   {
      log.info("인증코드 확인={}", code);
      return "인증코드확인=" + code;
   }
   
}