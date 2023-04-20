package commision.Mapper;

import org.apache.ibatis.annotations.Mapper;

import commision.Vo.CUser;

@Mapper
public interface LoginMapper 
{
	public int UserAdd(CUser user);
	public CUser EmailCheck(CUser user);
}