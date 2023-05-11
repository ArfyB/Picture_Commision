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
public class CNotice 
{
	public String Title;
	public String Contents;
	public Clob Contents_C;
	public java.sql.Date RecDate;
	public String Author;
	public int Hits;
}
