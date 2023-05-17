$(function()
{
	function uploadImage(files) {
		
		var formData = new FormData();
		
		for (var i = 0; i < files.length; i++) {
	    var file = files[i];
	    formData.append('files', file);	// summernote에 추가한 이미지의 파라미터 이름을 설정
	    }
	    
	    $.ajax({
	      url: '/img/upload',
	      enctype : 'multipart/form-data',
	      type: 'post',
	      data: formData,
	      processData: false,
	      contentType: false,
	      cache: false,
	      timeout : 600000,
	      success: function(response) {
		if(response.added)
		{
	        var list = response.list;
	        for(var i = 0; i < list.length; i++)
	        {
				var img = $('<img>').attr('src', '/pics/'+list[i]);
	        	$('#CQuestion_Contents').summernote('insertNode', img[0]);		
			}
	    }
	    else
	    {
			alert('error');
		}
	      },
	      error: function(e) {
			console.log(e);
	        console.log('Failed to upload image');
	      }
	    });
	};
	
	function deleteImage(target) {
		
		var imageUrl = $(target).attr("src");
		console.log(imageUrl);
		
	    $.ajax({
	      url: '/img/delete',
	      type: 'post',
	      data: { imageUrl : imageUrl },
	      cache: false,
	      timeout : 600000,
	      success: function(response) {
	      },
	      error: function(e) {
		alert('실패');
	      }
	    });
	};
	
	$('#CQuestion_Contents').summernote({
	    placeholder: '자신의 커미션에 대한 자세한 설명 및 이미지를 첨부',
	    tabsize: 2,
	    height: 500,
	    defaultImageWidth:50,
	    toolbar: [
	      ['style', ['style', 'fontsize']],
	      ['font', ['bold', 'underline', 'italic', 'clear']],
	      ['color', ['color']],
	      ['para', ['ul', 'ol', 'paragraph']],
	      ['table', ['table']],
	      ['insert', ['link', 'picture', 'hr']],
	      ['view', ['help']]
	    ],
	    callbacks: {
      	onImageUpload: function(files) {
        uploadImage(files);
		},
		
      	onMediaDelete: function(target) {
		deleteImage(target);
		}
    }
	  });
	  
	  $('#upload').on('click', function()
	  {
		var title = $('#Title').val();
		var Contents = $('#CQuestion_Contents').summernote('code');
		
		var formid = $('#CQuestionData')[0];
		
		var formData = new FormData(formid);
		
		formData.set('Contents', Contents);
		formData.delete('files');
		
		console.log(formData);
		
		$.ajax
		({
			type : 'POST',
			url : '/cquestion/upload',
			enctype : 'multipart/form-data',
			processData : false,
			contentType : false,
			data : formData,
			cache : false,
			success : function(res)
			{
				console.log(res.added?'성공':'실패');
			},
			error : function(e)
			{
				console.log(e);
			}
		})
	})
})