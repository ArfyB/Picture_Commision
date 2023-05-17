package commision.Mapper;

import org.apache.ibatis.annotations.Mapper;

import commision.Vo.CNotice;

@Mapper
public interface CNoticeMapper 
{
	public int AddCNotice(CNotice cn);
}
