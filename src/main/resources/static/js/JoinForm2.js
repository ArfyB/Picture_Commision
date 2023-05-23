$(function()
{
	$('#join').on('click', function(event)
	{
		event.preventDefault();
		
		var pwd = $('#UserPwd').val();
		var check = $('#PwdCheck').val();
		var PwdCheck1 = $('#PwdCheck1');
		var PwdCheck2 = $('#PwdCheck2');
		var nick = $('#UserNick').val();
		var NickCheck = $('#NickCheck');
		
		
		function validateUserPwd1(pwd)
		{
			return pwd.length >= 12;
		}
		
		function validateUserPwd2(pwd)
		{
			return pwd.indexOf(" ") === -1 && pwd === pwd.trim();
		}
		
		function validatePwdSpecialChar(pwd) {
			var specialCharPattern = /[!@#$%^&*(),.?":{}|<>]/;
			return specialCharPattern.test(pwd);
		}
		
		function PwdCheck(pwd, check)
		{
			return pwd === check;	
		}
		
		function PwdClear1()
		{
			PwdCheck1.text('');
		}
		
		function PwdClear2()
		{
			PwdCheck2.text('');
		}
		
		function NCheck(nick)
		{
			return nick === nick.trim() && nick.length >= 2 && nick.length <= 16;
		}

    	if(!validateUserPwd1(pwd))
    	{
			PwdCheck1.text('비밀번호는 12글자 이상');
			PwdClear2();
			return;
		}
		
		else if(!validateUserPwd2(pwd))
    	{
			PwdCheck1.text('비밀번호는 공백이 포함될 수 없음');
			PwdClear2();
			return;
		}
		
		else if (!validatePwdSpecialChar(pwd)) {
			PwdCheck1.text('비밀번호는 특수문자가 1개이상 포함');
			PwdClear2();
			return;
		}
		
		else if(!PwdCheck(pwd, check))
		{
			PwdCheck2.text('비밀번호가 다릅니다');
			PwdClear1();
			return;
		}
		
		else if(!NCheck(nick))
		{
			NickCheck.text('닉네임은 좌우여백없이 2~16글자 입니다');
			return;
		}
		
		else
		{
			PwdClear1();
			PwdClear2();
		}
		
		var form = document.getElementById('CUser');
		var UserData = new FormData(form);
		
		$.ajax
		({
			type : 'post',
			url : '/sec/add',
			data : UserData,
			processData : false,
			contentType : false,
			cache : false,
			success : function(res)
			{
				alert(res.added? '회원가입 성공' : '실패');
				location.href="/sec/join3";
			},
			error : function(e)
			{
				console.log(e);
			}
		})
	})
})