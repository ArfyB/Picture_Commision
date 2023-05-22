package commision.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import commision.Vo.CExplain;
import commision.Vo.CExplainPic;

@Mapper
public interface CExplainMapper 
{
	public int AddCExplain(CExplain cex);
	public int AddCExplainPic(List<String> list);
	public int AddCTags(CExplain cex);
}
