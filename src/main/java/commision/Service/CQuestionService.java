package commision.Service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import commision.Mapper.CQuestionMapper;
import commision.Vo.CQuestion;
import commision.Vo.CReQuestion;
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
		cq.setAuthor((String)request.getSession().getAttribute("tag"));
		cq.setAuthorNick((String)request.getSession().getAttribute("nick"));
		}
		
		java.util.Date utilDate = new java.util.Date(); // 현재시간을 java.util.Date 객체로 가져옴
	    cq.setRecDate(new java.sql.Date(utilDate.getTime())); 
	    
	    if(cq.getAuthor() == null)
	    {
	    	cq.setAuthor("");
	    }
		
		return cqm.AddCQuestion(cq) > 0;
	}
	
	public boolean CReQuestionAdd(CReQuestion crq, HttpServletRequest request)
	{
		if(request.getSession().getAttribute("nick") != null)
		{
		crq.setAuthor((String)request.getSession().getAttribute("tag"));
		crq.setAuthorNick((String)request.getSession().getAttribute("nick"));
		}
		
		java.util.Date utilDate = new java.util.Date(); // 현재시간을 java.util.Date 객체로 가져옴
	    crq.setRecDate(new java.sql.Date(utilDate.getTime())); 
	    
	    if(crq.getAuthor() == null)
	    {
	    	crq.setAuthor("");
	    }
		
	    System.out.println(crq.getAuthor());
	    System.out.println(crq.getAuthorNick());
	    System.out.println(crq.getContents());
	    System.out.println(crq.getCQNum());
	    System.out.println(crq.getTitle());
	    System.out.println(crq.getRecDate());
	    
		return cqm.AddCReQuestion(crq) > 0;
	}
	
	public CQuestion GetCQuestion(int CQNum)
	{
		return cqm.GetCQuestion(CQNum);
	}
	
	public CReQuestion GetCReQuestion(int CRQNum)
	{
		return cqm.GetCReQuestion(CRQNum);
	}
	
	public List<Map<String, Object>> AllCQuestion()
	{
		return cqm.AllCQuestion();
	}

	public List<Map<String, Object>> AllCReQuestion()
	{
		return cqm.AllCReQuestion();
	}
	
	public List<Map<String, Object>> AllData()
	{
		return cqm.AllData();
	}
}
