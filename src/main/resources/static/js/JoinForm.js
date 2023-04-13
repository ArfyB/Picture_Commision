$(function()
{
	$('#PwdCheck').on('click', function(event)
	{
		var pwd = $('#UserPwd').val();
		var check = $('#PwdCheck').val();
		
		/* 공백여부 확인 */
		if (pwd.indexOf(" ") === -1 && pwd === pwd.trim())
		{
			/* 최소 6글자 이상 */
			if(pwd.length >= 6)
			{
				/* 비밀번호와 비밀번호확인이 같은가 */
				if (pwd === check)
				{
					console.log('사용가능!');
				}
				else
				{
					console.log('비밀번호가 다릅니다');
				}
			}
			else
			{
				console.log('비밀번호는 6글자 이상 이어야 합니다');
			}
		}
		else 
		{
			console.log('비밀번호에는 공백이 포함될 수 없습니다');
		}
		
	})
})