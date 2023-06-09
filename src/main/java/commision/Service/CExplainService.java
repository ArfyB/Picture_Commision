package commision.Service;

import java.io.File;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.sql.rowset.serial.SerialClob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import commision.Mapper.CExplainMapper;
import commision.Vo.ApplyCExplain;
import commision.Vo.CExplain;
import commision.Vo.CExplainPic;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class CExplainService 
{
	@Autowired
	ResourceLoader resourceLoader;
	
	@Autowired
	CExplainMapper cm;
	
	public static java.sql.Clob convert(String data) throws SQLException {
        java.sql.Clob clob = new SerialClob(data.toCharArray());
        return clob;
    }
	
	public boolean AddCExplain(Map map)
	   {
		  MultipartFile[] mfiles = (MultipartFile[]) map.get("mfiles");
	      CExplain cex = (CExplain) map.get("CExplain");
	      CExplainPic cp = new CExplainPic();
	      
	      List<String> list = new ArrayList<>();
	      String absolutePath="";

	      Resource resource = resourceLoader.getResource("classpath:/static");
	      
	      int cexplain=0;
	      int cexplainpic=0;
	      int ctags=0;
	      try
	      {
	    	  absolutePath = resource.getFile().getAbsolutePath();

	    	  // mfiles = 사진들
	    	  if(mfiles.length != 0)
	    	  {
	    		  Date now = new Date();
	    		  SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
	    		  String nowTime = format.format(now);
	    		  String filename = nowTime + mfiles[0].getOriginalFilename().replaceAll("[\\s:]+", "_"); 
	    		  cex.setPreview(filename);
	    		  
	    		  for(int i=0;i<mfiles.length;i++) 
	    		  {
	    			  filename = nowTime+mfiles[i].getOriginalFilename().replaceAll("[\\s:]+", "_");
	    			  mfiles[i].transferTo(
	    					  new File(absolutePath+"/pics/"+filename));
	               
	    			  list.add(filename);
	    		  }
	    		cp.setCPicName(list);
	    		
	    		cexplain = cm.AddCExplain(cex);
	    		cexplainpic = cm.AddCExplainPic(list);
	    		ctags = cm.AddCTags(cex);
	    		
	    		List<String> tags = cex.getTags();
	    		System.out.println(tags);
	    		System.out.println(cp.getCPicName());
	    		
	         }
	            
	            return cexplain > 0 && cexplainpic > 0 && ctags > 0;
	            
	         } catch (Exception e) {
	            e.printStackTrace();
	            return false;
	         }
	  }
	
	public CExplain GetCExplain(int CNum)
	{
		return cm.GetCExplain(CNum);
	}
	
	public List<Map<String, Object>> AllCExplain()
	{
		return cm.AllCExplain();
	}
	
	public List<Map<String, Object>> MyCExplain(String PainterTag)
	{
		return cm.MyCExplain(PainterTag);
	}
	
	public List<CExplain> HomePageCExplain()
	{
		return cm.HomePageCExplain();
	}
	
	public boolean PermitCExplain(int cnum)
	{
		return 0 < cm.PermitCExplain(cnum);
	}
	
	public boolean DenyCExplain(int cnum)
	{
		return 0 < cm.DenyCExplain(cnum);
	}
	
	public List<Map<String, Object>> PermitZeroCExplain()
	{
		return cm.PermitZeroCExplain();
	}
	
	public List<CExplain> PermitZeroCExplain_MyPage()
	{
		return cm.PermitZeroCExplain_MyPage();
	}
	
	public List<Map<String, Object>> PermitTwoCExplain()
	{
		return cm.PermitTwoCExplain();
	}
	
	public List<CExplain> PermitTwoCExplain_MyPage()
	{
		return cm.PermitTwoCExplain_MyPage();
	}
	
	public List<String> GetTags(int CNum)
	{
		return cm.GetTags(CNum);
	}
	
	public List<Map<String, Object>> MyPageCExplain(String PainterTag)
	{
		return cm.MyPageCExplain(PainterTag);
	}
	
	public ApplyCExplain DataForOrder(int CNum)
	{
		return cm.DataForOrder(CNum);
	}
	
	public boolean DoOrder(ApplyCExplain apc, HttpServletRequest request)
	{
		ApplyCExplain DataForOrder = cm.DataForOrder(apc.getCNum());
		apc.setPainter(DataForOrder.getPainter());
		apc.setPainterTag(DataForOrder.getPainterTag());
		apc.setAuthor((String)request.getSession().getAttribute("nick"));
		apc.setAuthorTag((String)request.getSession().getAttribute("tag"));
		
		return 0 < cm.DoOrder(apc);
	}
	
	public List<ApplyCExplain> MyOrder_MyPage(String AuthorTag)
	{
		return cm.MyOrder_MyPage(AuthorTag);
	}
	
	public List<ApplyCExplain> TakeOrder_MyPage(String PainterTag)
	{
		return cm.TakeOrder_MyPage(PainterTag);
	}
	
	public List<Map<String, Object>> MyOrder(String AuthorTag)
	{
		return cm.MyOrder(AuthorTag);
	}
	
	public List<Map<String, Object>> TakeOrder(String PainterTag)
	{
		return cm.TakeOrder(PainterTag);
	}
	
	public ApplyCExplain OrderData(int ACNum)
	{
		return cm.OrderData(ACNum);
	}
	
	public boolean PermitOrder(int acnum)
	{
		return 0 < cm.PermitOrder(acnum);
	}
	
	public boolean UpdateOrder(int cnum)
	{
		return 0 < cm.UpdateOrder(cnum);
	}
	
	public boolean DenyOrder(int acnum)
	{
		return 0 < cm.DenyOrder(acnum);
	}
	
	public boolean FinishOrder(int acnum)
	{
		return 0 < cm.FinishOrder(acnum);
	}
}
