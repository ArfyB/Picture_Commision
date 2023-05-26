package commision.Service;

import java.io.File;
import java.sql.Clob;
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
import commision.Vo.CExplain;
import commision.Vo.CExplainPic;
import jakarta.servlet.ServletContext;
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
}
