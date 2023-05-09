package commision.Service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
	
	public boolean AddCExplain(Map map)
	   {
		  MultipartFile[] mfiles = (MultipartFile[]) map.get("mfiles");
	      HttpServletRequest request = (HttpServletRequest) map.get("request");
	      CExplain cex = (CExplain) map.get("CExplain");
	      
	      ServletContext context = request.getServletContext();

	      List<CExplainPic> list = new ArrayList<>();
	      String absolutePath="";

	      Resource resource = resourceLoader.getResource("classpath:/static");

	      try
	      {
	    	  absolutePath = resource.getFile().getAbsolutePath();
	    	  System.out.println(absolutePath);
	    	  if(mfiles.length != 0)
	    	  {
	    		  Date now = new Date();
	    		  SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
	    		  String nowTime = format.format(now);
	    		  String filename = nowTime + mfiles[0].getOriginalFilename().replaceAll("[\\s:]+", "_"); 
	    		  cex.setThumbnail(filename);
	    		  
	    		  for(int i=0;i<mfiles.length;i++) 
	    		  {
	    			  filename = nowTime+mfiles[i].getOriginalFilename().replaceAll("[\\s:]+", "_");
	    			  mfiles[i].transferTo(
	    					  new File(absolutePath+"/pics/"+filename));
	               
	    			  CExplainPic cp = new CExplainPic();
	    			  cp.setCPicName(mfiles[i].getOriginalFilename());
	               
	    			  list.add(cp);
	    		  }
	    		//int add = rm.RecipeAdd(rec);
	            //int b = rm.RecPicAdd(list);
	            
	         }
	            
	            return true;
	            
	         } catch (Exception e) {
	            e.printStackTrace();
	            return false;
	         }
	 
	  }
}
