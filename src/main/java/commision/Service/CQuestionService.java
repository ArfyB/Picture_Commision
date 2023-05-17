package commision.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import commision.Mapper.CQuestionMapper;
import commision.Vo.CQuestion;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class CQuestionService 
{
	@Autowired
	CQuestionMapper cqm;
	
	public boolean CQuestionAdd(CQuestion cq, HttpServletRequest request)
	{
		if(request.getSession().getAttribute("nick") != null)
		{
		cq.setAuthor((String)request.getSession().getAttribute("nick"));
		}
		
		java.util.Date utilDate = new java.util.Date(); // 현재시간을 java.util.Date 객체로 가져옴
	    cq.setRecDate(new java.sql.Date(utilDate.getTime())); 
	    
	    if(cq.getAuthor() == null)
	    {
	    	cq.setAuthor("Admin");
	    }
		
		return cqm.AddCQuestion(cq) > 0;
	}
}
