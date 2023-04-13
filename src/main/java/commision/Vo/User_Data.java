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
public class User_Data 
{
	public String UserId;		// 아이디
	public String UserPwd;		// 비밀번호
	public String UserEmail;	// 이메일
	public String UserCertify;	// 본인인증여부
	public String UserPhone;	// 전화번호
}