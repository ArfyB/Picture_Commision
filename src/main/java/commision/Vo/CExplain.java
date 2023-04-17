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
	public int CNum;
	public String Painter;
	public String Title;
	public String Introduce;
	public int Price;
	public int Slot;
	public String Preview;
	public List<String> tag;
}
