	function readImg(input) {
	if (input.files && input.files[0]) {
		var reader = new FileReader();
		reader.onload = function(e) {
		$('#preview').attr('src', e.target.result);
		console.log(e.target.result);
		};
		reader.readAsDataURL(input.files[0]);
		} 
		else
		{
		$('#preview').attr('src', "");
		}
	};

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
	        	$('#Commision_Contents').summernote('insertNode', img[0]);		
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
	
	$('#Commision_Contents').summernote({
	    placeholder: 'Hello stand alone ui',
	    tabsize: 2,
	    height: 500,
	    defaultImageWidth:50,
	    toolbar: [
	      ['style', ['style']],
	      ['font', ['bold', 'underline', 'clear']],
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
	  
	  $('#Commision_Introduce').summernote({
	    placeholder: 'Hello stand alone ui',
	    tabsize: 2,
	    height: 200,
	    width:500,
	    toolbar: [
	      
	    ]
	  });
	  
	  
	  $('#a').on('click', function()
	  {
		var title = $('#Title').val();
		var Contents = $('#Commision_Contents').summernote('code');
		var Introduce = $('#Commision_Introduce').summernote('code');
		
		console.log(title);
		console.log(Contents);
		console.log(Introduce);
		
		/*
		var cexplaindata = $('#cexplaindata');
		var cexplainform = new FormData(cexplaindata);
		*/
		
		/*
		var cexplainform = new FormData($('#cexplaindata')[0]);
		
		$.ajax
		({
			type : 'post',
			url : '/cexplain/test',
			data : cexplainform,
			processData : false,
			contentType : false,
			cache : false,
			success : function(res)
			{
				if (!res.check)
				{
				}
				else
				{
				}
			},
			error : function(e)
			{
				console.log(e);
			}
		})
		
		*/
	})
	
})