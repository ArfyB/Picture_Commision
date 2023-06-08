package commision.Mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import commision.Vo.ApplyCExplain;
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
	public List<CExplain> PermitZeroCExplain_MyPage();
	public List<CExplain> PermitTwoCExplain_MyPage();
	public List<Map<String, Object>> MyPageCExplain(String PainterTag);
	public ApplyCExplain DataForOrder(int CNum);
	public int DoOrder(ApplyCExplain apc);
	public List<ApplyCExplain> MyOrder_MyPage(String AuthorTag);
	public List<ApplyCExplain> TakeOrder_MyPage(String PainterTag);
	public ApplyCExplain OrderData(int ACNum);
	public int PermitOrder(int ACNum);
	public int PermitOrder2(int CNum);
	public int DenyOrder(int ACNum);
}
