package commision.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import commision.Mapper.LoginMapper;
import commision.Vo.CUser;

@Service
public class LoginService 
{
	@Autowired
	public LoginMapper lm;
	
	public boolean UserAdd(CUser user)
	{
		return 0 < lm.UserAdd(user);
	}
	
	public boolean EmailCheck(CUser user)
	{
		CUser u = lm.EmailCheck(user);
		if(u == null)
		{
			return false;
		}
		else
		{
			return true;
		}
	}

}
