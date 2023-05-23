package commision.Mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import commision.Vo.CNotice;

@Mapper
public interface CNoticeMapper 
{
	public int AddCNotice(CNotice cn);
	public CNotice GetCNotice(int CNoticeNum);
	public List<Map<String, Object>> AllCNotice();
}
