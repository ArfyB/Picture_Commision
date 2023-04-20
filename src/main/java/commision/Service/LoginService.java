package commision.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import commision.Mapper.LoginMapper;
import commision.Vo.CUser;

@Service
public class LoginService 
{
	@Autowired
	public LoginMapper lp;
	
	public boolean UserAdd(CUser user)
	{
		return 0 < lp.UserAdd(user);
	}
	
	public boolean EmailCheck(CUser user)
	{
		CUser c = lp.EmailCheck(user);
		if(c == null)
		{
			return false;
		}
		else
		{
			return true;
		}
	}

}
