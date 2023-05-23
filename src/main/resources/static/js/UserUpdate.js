$(function()
{
	$('#save').on('click', function(event)
	{
		var tag = $('#UserTag').val();
		var TagWarning = $('#TagWarning');
		
		function validateUserTag1(tag)
		{
			return tag.length >= 4 && tag.length <= 20;
		}
		
		function validateUserTag2(tag)
		{
			return tag.indexOf(" ") === -1 && tag === tag.trim();
		}
		
		function validateUserTag3(tag) 
		{
	      var tagPattern = /^[a-zA-Z0-9_]+$/;
    	  return tagPattern.test(tag);
    	}
		
    	if(!validateUserTag1(tag))
    	{
			TagWarning.text('태그는 4글자이상 20글자 이하');
			return;
		}
		
		else if(!validateUserTag2(tag))
    	{
			TagWarning.text('태그는 공백이 포함될 수 없음');
			return;
		}
		
		else if(!validateUserTag3(tag))
		{
			TagWarning.text('태그는 영어 소문자, 대문자, 숫자, _만 사용가능'+tag);
			return;
		}
		
		var form = document.getElementById('CUser');
		var UserData = new FormData(form);
		
		$.ajax
		({
			type : 'post',
			url : '/sec/update',
			data : UserData,
			enctype : 'multipart/form-data',
			processData: false,
			contentType: false,
			cache : false,
			success : function(res)
			{
				alert(res.updated?'성공':'실패');
			},
			error : function(e)
			{
				console.log(e);
			}
		})
	})
})