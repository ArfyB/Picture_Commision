$(function()
{
	$('#join-btn').on('click', function(event)
	{
		location.href='/sec/join1';
	})
	
	
	
	/*
	$('#login-btn').on('click', function(event)
	{
		var form = $('#login_data')[0];
		var UserData = new FormData(form);
		
		$.ajax
		({
			type : 'post',
			url : '/sec/login',
			data : UserData,
			processData : false,
			contentType : false,
			cache : false,
			success : function(res)
			{
				
			},
			error : function(e)
			{
				console.log(e);
			}
		})
	})
	*/
	
})