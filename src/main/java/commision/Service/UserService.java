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

import commision.Mapper.UserMapper;
import commision.Vo.CUser;

@Service
public class UserService 
{
	@Autowired
	ResourceLoader resourceLoader;
	
	@Autowired
	public UserMapper um;
	
	public boolean UserUpdate(Map map)
	{
		MultipartFile[] mfiles = (MultipartFile[]) map.get("mfiles");
	      CUser cuser = (CUser) map.get("CUser");
	      String defaultimg = "ex_img.png";
	      
	      List<String> list = new ArrayList<>();
	      String absolutePath="";

	      Resource resource = resourceLoader.getResource("classpath:/static");
	      
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
	    		  
	    		  for(int i=0;i<mfiles.length;i++) 
	    		  {
	    			  filename = nowTime+mfiles[i].getOriginalFilename().replaceAll("[\\s:]+", "_");
	    			  if(!filename.equals(nowTime))
	    			  {
	    			  mfiles[i].transferTo(
	    					  new File(absolutePath+"/pics/"+filename));
	    			  }
	    			  
	    			  list.add(filename);
	    		  }
	    		  if(list.get(0).equals(nowTime))
	    		  {
	    			  cuser.setUserProfilePic(um.GetProfile((String)cuser.getUserTag()));
	    		  }
	    		  else
	    		  {
	    			  cuser.setUserProfilePic(list.get(0));
	    		  }
	    		  
	    		  if(list.get(1).equals(nowTime))
	    		  {
	    			  cuser.setUserBackgroundPic(um.GetBackground((String)cuser.getUserTag()));
	    		  }
	    		  else
	    		  {
	    			  cuser.setUserBackgroundPic(list.get(1));
	    		  }
	    		  
	         }
	            
	            return um.UserUpdate(cuser) > 0;
	            
	         } catch (Exception e) {
	            e.printStackTrace();
	            return false;
	         }
	 
	}
	
	public CUser GetUser(String CUserTag)
	{
		return um.GetUser(CUserTag);
	}
	
}
