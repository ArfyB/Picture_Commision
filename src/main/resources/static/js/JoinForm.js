

$(function()
{
	$('#join').on('click', function(event)
	{
		event.preventDefault();
		
		var email = $('#UserEmail').val();
		var EmailCheck = $('#EmailCheck');
		
		function validateEmail(email) 
		{
	      var emailPattern = /^[a-zA-Z0-9._%+\-]+@[a-zA-Z0-9.\-]+\.[a-zA-Z]{2,}$/;
    	  return emailPattern.test(email);
    	}
    	
		if (!validateEmail(email))
		{
			console.log(email);
			EmailCheck.text('유효하지않은 이메일 형식입니다');
			return;
		}
		
		var form = document.getElementById('CUser');
		var UserData = new FormData(form);
		
		$.ajax
		({
			type : 'post',
			url : '/sec/email',
			data : UserData,
			processData : false,
			contentType : false,
			cache : false,
			success : function(res)
			{
				if (!res.check)
				{
					EmailCheck.text('');
					alert(res.certify? '메일이 전송 되었습니다' : '메일 전송에 실패 하였습니다');	
				}
				else
				{
					EmailCheck.text('이미 존재하는 계정입니다');
				}
			},
			error : function(e)
			{
				console.log(e);
			}
		})
	})
})