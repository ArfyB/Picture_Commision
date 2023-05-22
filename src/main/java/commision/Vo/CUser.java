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
public class CUser 
{
	public String UserNick;
	public String UserEmail;	// 이메일
	public String UserPwd;		// 비밀번호 not null
	public String UserPhone;	// 전화번호 본인인증 후에 저장
	public String UserCertify;	// 본인인증여부 default 0
	public String UserRole;		// 커미션주 or 고객 default 0
	public String UserTag;
}