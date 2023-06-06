package commision.Mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import commision.Vo.CExplain;

@Mapper
public interface CExplainMapper 
{
	public int AddCExplain(CExplain cex);
	public int AddCExplainPic(List<String> list);
	public int AddCTags(CExplain cex);
	public CExplain GetCExplain(int CNum);
	public List<Map<String, Object>> AllCExplain();
	public List<CExplain> HomePageCExplain();
	public int PermitCExplain(int CNum);
	public int DenyCExplain(int CNum);
	public List<String> GetTags(int CNum);
	public List<Map<String, Object>> PermitZeroCExplain();
	public List<Map<String, Object>> PermitTwoCExplain();
}
