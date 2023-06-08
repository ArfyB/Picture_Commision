$(function()
{
	
	var acnum = ($('#acnum').data('value'));
	var cnum = ($('#cnum').data('value'));
		
	$('#permit').on('click', function(event)
	{
		$.ajax({
	      url: '/cexplain/apply_order',
	      type: 'post',
	      data: {'acnum' : acnum, 'cnum' : cnum},
	      cache: false,
	      success: function(response) {
		alert(response.permit ? '승인' : '오류');
		location.href='/cexplain/orderdata?ACNum='+acnum;
	      },
	      error: function(e) {
		
	      }
	    });
	})
	
	$('#deny').on('click', function(event)
	{
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
	
	$('#finish').on('click', function(event)
	{
		$.ajax({
	      url: '/cexplain/finish_order',
	      type: 'post',
	      data: {'acnum' : acnum, 'cnum' : cnum},
	      cache: false,
	      success: function(response) {
		alert(response.finish ? '완료' : '오류');
		location.href='/cexplain/orderdata?ACNum='+acnum;
	      },
	      error: function(e) {
		
	      }
	    });
	})
	
})