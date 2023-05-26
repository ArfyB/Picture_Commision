package commision.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;

import commision.Mapper.CNoticeMapper;
import commision.Vo.CNotice;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class CNoticeService 
{
	@Autowired
	CNoticeMapper cnm;
	
	public List<Map<String, Object>> AllCNotice() 
	{
		//List<Recipe>
		return cnm.AllCNotice();
	}
	
	public CNotice GetCNotice(int CNoticeNum)
	{
		return cnm.GetCNotice(CNoticeNum);
	}
	
	public CNotice GetCNoticeHits(int CNoticeNum)
	{
		cnm.CNoticeHits(CNoticeNum);
		return cnm.GetCNotice(CNoticeNum);
	}
	
	public boolean CNoticeAdd(CNotice cn, HttpServletRequest request)
	{
		if(request.getSession().getAttribute("nick") != null)
		{
			cn.setAuthor((String)request.getSession().getAttribute("tag"));
			cn.setAuthorNick((String)request.getSession().getAttribute("nick"));
		}
		
		java.util.Date utilDate = new java.util.Date(); // 현재시간을 java.util.Date 객체로 가져옴
	    cn.setRecDate(new java.sql.Date(utilDate.getTime())); 
	    
		return cnm.AddCNotice(cn) > 0;
	}
}
