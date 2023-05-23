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
	
	public Map<String,Object> pages(PageInfo pageinfo)
	{
		Map<String,Object> map = new HashMap<>();
		
		 System.out.println();
		
		int begin = pageinfo.getPageNum()-2;
		
		if(begin<=0)
		{
			begin = 1;
		}
		
		int end = pageinfo.getPageNum()+2;
		if(end>=pageinfo.getNavigateLastPage())
		{
			end = pageinfo.getNavigateLastPage();
		}
		
		
		map.put("begin", begin);
		map.put("end", end);
		return map;
	}
}
