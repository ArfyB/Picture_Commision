package commision.Mapper;

import org.apache.ibatis.annotations.Mapper;

import commision.Vo.CQuestion;
import commision.Vo.CReQuestion;

@Mapper
public interface CQuestionMapper 
{
	public int AddCQuestion(CQuestion cq);
	public int AddCReQuestion(CReQuestion crq);
}
