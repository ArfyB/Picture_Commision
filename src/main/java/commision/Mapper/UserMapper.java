package commision.Mapper;

import org.apache.ibatis.annotations.Mapper;

import commision.Vo.CUser;

@Mapper
public interface UserMapper
{
//	public int UserAdd(Customer ui);
//	public Customer dologin(Customer ui);
	public CUser dologin(String user);
}
