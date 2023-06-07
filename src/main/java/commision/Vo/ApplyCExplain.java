package commision.Vo;

import java.sql.Clob;
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
public class ApplyCExplain 
{
	public int ACNum;
	public int CNum;			// 커미션넘버 개인 프로필에서 오래된순으로 정렬하려고//
	public String Painter;		// 제작자//
	public String PainterTag;		// 제작자식별//
	public String Author;		// 제작자
	public String AuthorTag;
	public String Title;		// 커미션 제목//
	public String Contents;//
	public Clob Contents_C;
	public int Permit;
}
