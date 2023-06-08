$(function()
{
	$('#permit').on('click', function(event)
	{
		var acnum = ($('#acnum').data('value'));
		var cnum = ($('#cnum').data('value'));
		
		$.ajax({
	      url: '/cexplain/apply_order',
	      type: 'post',
	      data: {'acnum' : acnum},
	      cache: false,
	      success: function(response) {
		alert(response.permit ? '승인' : '오류');
		
		$.ajax({
	      url: '/cexplain/apply_order2',
	      type: 'post',
	      data: {'cnum' : cnum},
	      cache: false,
	      success: function(response) {
		alert(response.permit ? '승인' : '오류');
		location.href='/cexplain/orderdata?ACNum='+acnum;
	      },
	      error: function(e) {
		
	      }
	    });
	    
	      },
	      error: function(e) {
		
	      }
	    });
	})
	
	$('#deny').on('click', function(event)
	{
		var acnum = ($('#pd').data('value'));
		
		$.ajax({
	      url: '/cexplain/deny_order',
	      type: 'post',
	      data: {'acnum' : acnum},
	      cache: false,
	      success: function(response) {
		alert(response.deny ? '거부' : '오류');
	      },
	      error: function(e) {
		
	      }
	    });
	})
	
})