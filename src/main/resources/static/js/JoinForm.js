

$(function()
{
	$('#join').on('click', function(event)
	{
		event.preventDefault();
		
		var uid = $('#UserId').val();
		var pwd = $('#UserPwd').val();
		var check = $('#PwdCheck').val();
		var email = $('#UserEmail').val();
		var IdCheck = $('#IdCheck');
		var PwdCheck1 = $('#PwdCheck1');
		var PwdCheck2 = $('#PwdCheck2');
		
		function validateUserId1(uid)
		{
			return uid.length >=8;
		}
		
		function validateUserId2(uid)
		{
			/* indexOf() ()내의 내용이 없을시 -1 반환 */
			return uid.indexOf(" ") === -1 && uid === uid.trim();
		}
		
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
		
		function textclearPwd12()
		{
			PwdCheck1.text('');
			PwdCheck2.text('');
		}
		
		function textclearIdPwd2()
		{
			IdCheck.text('');
			PwdCheck2.text('');
		} 
		
		function textclearIdPwd1()
		{
			IdCheck.text('');
			PwdCheck1.text('');
		} 
		
		if (!validateUserId1(uid)) {
			IdCheck.text('아이디는 8글자 이상');
			textclearPwd12();
			return;
		}
		
		else if (!validateUserId2(uid)) {
			IdCheck.text('아이디는 공백포함 불가');
			textclearPwd12();
			return;
		}
		else
		{
			IdCheck.text('');
		}

		if (!validateUserPwd1(pwd)) {
			PwdCheck1.text('비밀번호는 12글자 이상');
			textclearIdPwd2();
			return;
		}
		
		else if (!validateUserPwd2(pwd)) {
			PwdCheck1.text('비밀번호는 공백포함 불가');
			textclearIdPwd2();
			return;
		}
		
		else if (!validatePwdSpecialChar(pwd)) {
			PwdCheck1.text('비밀번호는 특수문자가 1개이상 포함');
			textclearIdPwd2();
			return;
		}
		else
		{
			PwdCheck1.text('');
		}

		if (!PwdCheck(pwd, check)) {
			PwdCheck2.text('비밀번호가 서로 다릅니다');
			textclearIdPwd1();
			return;
		}
		else
		{
			PwdCheck2.text('');
		}
		
		console.log('사용가능');
	})
})