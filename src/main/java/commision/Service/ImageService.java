package commision.Service;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class ImageService 
{
	@Autowired
	ResourceLoader resourceLoader;
	
	public Map<String,Object> AddImg(Map map)
	   {
		  MultipartFile[] mfiles = (MultipartFile[]) map.get("mfiles");
		  
	      List<String> list = new ArrayList<>();
	      
	      String absolutePath="";
	      Resource resource = resourceLoader.getResource("classpath:/static");
	      
	      Map<String,Object> result = new HashMap<>();
	      
	      try
	      {
	    	  absolutePath = resource.getFile().getAbsolutePath();
	    	  
	    	  if(mfiles.length != 0)
	    	  {
	    		  Date now = new Date();
	    		  SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
	    		  String nowTime = format.format(now);
	    		  
	    		  for(int i=0;i<mfiles.length;i++) 
	    		  {
	    			  String filename = nowTime+mfiles[i].getOriginalFilename().replaceAll("[\\s:]+", "_");
	    			  mfiles[i].transferTo(
	    			  new File(absolutePath+"/pics/"+filename));
	               
	    			  list.add(filename);
	    		  }
	         }
	    	  result.put("added", true);
	    	  result.put("list", list);
	    	  return result;
	            
	         } catch (Exception e) {
	            e.printStackTrace();
	            result.put("added", false);
	            return result;
	         }
	  }
	
	public boolean DeleteImg(String imgUrl)
	   {
	      String absolutePath="";
	      Resource resource = resourceLoader.getResource("classpath:/static");
	      try {
			absolutePath = resource.getFile().getAbsolutePath();
			
			File fileToDelete = new File(absolutePath+imgUrl);
			System.out.println(fileToDelete.getName());
		      if(fileToDelete.delete()){
		          System.out.println(fileToDelete.getName() + " is deleted!");
		          return true;
		      } else {
		          System.out.println("Delete operation is failed.");
		          return false;
		      }
		} 
	      catch (IOException e) 
	      {
			e.printStackTrace();
		}
	      return false;
	  }
}
