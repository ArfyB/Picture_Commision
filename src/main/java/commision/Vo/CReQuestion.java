package commision.Vo;

import java.sql.Clob;

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
public class CReQuestion 
{
	public int CQNum;
	public int CRQNum;
	public String Title;
	public String Contents;
	public Clob Contents_C;
	public java.sql.Date RecDate;
	public String Author;
	public String AuthorNick;
}
