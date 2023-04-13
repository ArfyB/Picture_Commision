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
public class Commision 
{
	public String title;
	public List<String> tag;
	public int price;
	public String introduce;
	public String painter;
	public int slot;
	public String preview;
}
