package commision.Mapper;

import org.apache.ibatis.annotations.Mapper;

import commision.Vo.CUser;

@Mapper
public interface UserMapper
{
//	public int UserAdd(Customer ui);
//	public Customer dologin(Customer ui);
	public CUser dologin(String user);
	public int UserUpdate(CUser user);
	public CUser GetUser(String UserTag);
	public CUser GetUserEmail(String UserEmail);
	public String GetProfile(String tag);
	public String GetBackground(String tag);
}
