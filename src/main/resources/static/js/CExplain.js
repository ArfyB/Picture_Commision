$(function()
{
	$('#permit').on('click', function(event)
	{
		var cnum = ($('#pd').data('value'));
		
		$.ajax({
	      url: '/cexplain/permit',
	      type: 'post',
	      data: {'cnum' : cnum},
	      cache: false,
	      success: function(response) {
		alert(response.permit ? '승인' : '오류');
	      },
	      error: function(e) {
		
	      }
	    });
	})
	
	$('#deny').on('click', function(event)
	{
		var cnum = ($('#pd').data('value'));
		
		$.ajax({
	      url: '/cexplain/deny',
	      type: 'post',
	      data: {'cnum' : cnum},
	      cache: false,
	      success: function(response) {
		alert(response.deny ? '거부' : '오류');
	      },
	      error: function(e) {
		
	      }
	    });
	})
})