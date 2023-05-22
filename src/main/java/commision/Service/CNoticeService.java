package commision.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import commision.Mapper.CNoticeMapper;
import commision.Vo.CNotice;
import commision.Vo.CQuestion;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class CNoticeService 
{
	@Autowired
	CNoticeMapper cnm;
	
	public boolean CNoticeAdd(CNotice cn, HttpServletRequest request)
	{
		if(request.getSession().getAttribute("nick") != null)
		{
			cn.setAuthor((String)request.getSession().getAttribute("email"));
			cn.setAuthorNick((String)request.getSession().getAttribute("nick"));
		}
		
		java.util.Date utilDate = new java.util.Date(); // 현재시간을 java.util.Date 객체로 가져옴
	    cn.setRecDate(new java.sql.Date(utilDate.getTime())); 
	    
		return cnm.AddCNotice(cn) > 0;
	}
}
