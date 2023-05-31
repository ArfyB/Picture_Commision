$(function()
{
	$('#CNotice').on('click', function(event)
	{
		location.href='/cnotice/list'
	})
	
	$('#CExplain').on('click', function(event)
	{
		location.href='/cexplain/list'
	})
	
	$('#CQuestion').on('click', function(event)
	{
		location.href='/cquestion/list'
	})
})