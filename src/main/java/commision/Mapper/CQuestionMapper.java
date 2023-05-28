package commision.Mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import commision.Vo.CQuestion;
import commision.Vo.CReQuestion;

@Mapper
public interface CQuestionMapper 
{
	public int AddCQuestion(CQuestion cq);
	public int AddCReQuestion(CReQuestion crq);
	public CQuestion GetCQuestion(int CQNum);
	public List<Map<String, Object>> AllCQuestion();
}
