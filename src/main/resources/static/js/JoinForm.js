

$(function()
{
	$('#join').on('click', function(event)
	{
		event.preventDefault();
		
		var pwd = $('#UserPwd').val();
		var check = $('#PwdCheck').val();
		var email = $('#UserEmail').val();
		var PwdCheck1 = $('#PwdCheck1');
		var PwdCheck2 = $('#PwdCheck2');
		var EmailCheck = $('#EmailCheck');
		
		function validateUserPwd1(pwd)
		{
			return pwd.length >= 12;
		}
		
		function validateUserPwd2(pwd)
		{
			return pwd.indexOf(" ") === -1 && pwd === pwd.trim();
		}
		
		function PwdCheck(pwd, check)
		{
			return pwd === check;	
		}
		
		function validateEmail(email) 
		{
	      var emailPattern = /^[a-zA-Z0-9._%+\-]+@[a-zA-Z0-9.\-]+\.[a-zA-Z]{2,}$/;
    	  return emailPattern.test(email);
    	}
    	
    	/* 정규표현식을 사용하여 특수문자를 1개 이상 포함하는지 체크 */
		function validatePwdSpecialChar(pwd) {
			var specialCharPattern = /[!@#$%^&*(),.?":{}|<>]/;
			return specialCharPattern.test(pwd);
		}
		
		function textclearPwd2E()
		{
			PwdCheck2.text('');
			EmailCheck.text('');
		}
		
		function textclearPwd1E()
		{
			PwdCheck1.text('');
			EmailCheck.text('');
		}
		
		function textclearPwd12()
		{
			PwdCheck1.text('');
			PwdCheck2.text('');
		}
		
		
		
		if (!validateEmail(email))
		{
			console.log(email);
			EmailCheck.text('유효하지않은 이메일 형식입니다');
			textclearPwd12();
			return;
		}
		else
		{
			EmailCheck.text('');
		}
		
		if (!validateUserPwd1(pwd)) {
			PwdCheck1.text('비밀번호는 12글자 이상');
			textclearPwd2E();
			return;
		}
		
		else if (!validateUserPwd2(pwd)) {
			PwdCheck1.text('비밀번호는 공백포함 불가');
			textclearPwd2E();
			return;
		}
		
		else if (!validatePwdSpecialChar(pwd)) {
			PwdCheck1.text('비밀번호는 특수문자가 1개이상 포함');
			textclearPwd2E();
			return;
		}
		else
		{
			PwdCheck1.text('');
		}

		if (!PwdCheck(pwd, check)) {
			PwdCheck2.text('비밀번호가 서로 다릅니다');
			textclearPwd1E();
			return;
		}
		else
		{
			PwdCheck2.text('');
		}
		
		var form = document.getElementById('CUser');
		var UserData = new FormData(form);
		
		$.ajax
		({
			type : 'post',
			url : '/login/join',
			data : UserData,
			processData : false,
			contentType : false,
			cache : false,
			success : function(res)
			{
				console.log(res.join? '성공' : '실패');
			},
			error : function(e)
			{
				console.log(e);
			}
		})
	})
})