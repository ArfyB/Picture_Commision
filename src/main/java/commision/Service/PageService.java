package commision.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;

@Service
public class PageService 
{
	public Map<String,Object> pages(PageInfo pageinfo)
	{
		Map<String,Object> map = new HashMap<>();
		
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
		List<Integer> pages = new ArrayList<>();
		
		for(int i = begin; i <= end; i++)
		{
			pages.add(i);
		}
		
		map.put("begin", begin);
		map.put("end", end);
		map.put("pages", pages);
		return map;
	}
}
