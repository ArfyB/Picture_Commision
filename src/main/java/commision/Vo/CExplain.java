package commision.Vo;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Component
@Data //get set
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CExplain 
{
	public int CNum;			// 커미션넘버 개인 프로필에서 오래된순으로 정렬하려고
	public String Painter;		// 제작자
	public String Title;		// 커미션 제목
	public String Introduce;	// 커미션 메인 설명 글
	public String Preview;
	public int Price;
	public int Slot;
	public List<String> tag;
	
	public String Contents;
}
