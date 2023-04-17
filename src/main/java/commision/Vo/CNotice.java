package commision.Vo;

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
	public java.sql.Date RecDate;
	public String Files;
	public String Author;
	public int Hits;
}
